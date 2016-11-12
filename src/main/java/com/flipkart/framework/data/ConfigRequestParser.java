package com.flipkart.framework.data;

import com.flipkart.framework.config.ConfigException;
import com.flipkart.framework.internal.config.ConfigName;
import com.flipkart.framework.internal.config.FrameworkConfig;
import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;
import groovy.util.slurpersupport.Node;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.Maps;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by santhosh.b on 04/07/15.
 */

public class ConfigRequestParser {

    private static final Logger logger = LoggerFactory.getLogger(ConfigRequestParser.class);
    private static Map<String, Request> configMap = Maps.newHashMap();
    private static ConfigRequestParser configRequestParser;

    public static ConfigRequestParser getInstance() {

        return configRequestParser;
    }

    public static ConfigRequestParser createInstance() throws ConfigException {

        if (configRequestParser == null)
            configRequestParser = new ConfigRequestParser();

        return configRequestParser;
    }


    public void init() throws ConfigException {

        String fileName = FrameworkConfig.getString(ConfigName.REQUEST_CONFIG_FILE_PATH.getConfigName());
        try {
            XmlSlurper xmlSlurper = new XmlSlurper();
            GPathResult gPathResult = xmlSlurper.parse(new File(fileName));
            handleConfigRequest(gPathResult);

        } catch (Exception ex) {

            throw new ConfigException("Failed to parse the config request file");
        }

    }

    private void handleConfigRequest(GPathResult configRequestResultPath) {
        Iterator itr = configRequestResultPath.childNodes();
        while (itr.hasNext()) {

            Node node = (Node) itr.next();

            if ("RESTRequest".equals(node.name())) {

                handleRESTRequest(node);
            } else {

                //throw new ConfigException();
                //TODO: Handle other config request packets.

            }

        }


    }

    private void handleRESTRequest(Node node) {

        Iterator<Node> itr = node.childNodes();
        RESTRequest restRequest = new RESTRequest();
        String requestName = node.attributes().get("name").toString();
        restRequest.setName(requestName);
        if (node.attributes().containsKey("path"))
            restRequest.setPath(node.attributes().get("path").toString());
        if (node.attributes().containsKey("endPoint")) {
            restRequest.setEndPoint(node.attributes().get("endPoint").toString());
        }
        if (node.attributes().containsKey("method")) {
            String sMethod = node.attributes().get("method").toString();
            HTTPRequest.HTTPMethod method = HTTPRequest.HTTPMethod.valueOf(sMethod);
            restRequest.setMethod(method);
        }

        while (itr.hasNext()) {

            Node childNode = itr.next();
            if ("Headers".equals(childNode.name())) {

                Iterator<Node> headerIterator = childNode.childNodes();
                Headers headers = new Headers();
                while (headerIterator.hasNext()) {

                    Node header = headerIterator.next();
                    if ("header".equals(header.name())) {
                        String name = header.attributes().get("name").toString();
                        String value = header.attributes().get("value").toString();
                        headers.add(name, value);
                    }


                }
                restRequest.setHeaders(headers);
            } else if ("QueryParams".equals(childNode.name())) {

                Iterator<Node> queryIterator = childNode.childNodes();
                QueryParams queryParams = new QueryParams();
                while (queryIterator.hasNext()) {

                    Node queryParam = queryIterator.next();
                    if ("queryParam".equals(queryParam.name())) {
                        String name = queryParam.attributes().get("name").toString();
                        String value = queryParam.attributes().get("value").toString();
                        queryParams.add(name, value);
                    }
                }

                restRequest.setQueryParams(queryParams);
            } else if ("body".equals(childNode.name())) {

                restRequest.setBody(childNode.text());
            }
        }

        configMap.put(requestName, restRequest);


    }

    public RESTRequest getRESTRequest(String name) {

        RESTRequest request = (RESTRequest)configMap.get(name);
        return new RESTRequest(request);


    }
}