package com.flipkart.framework.data;

import com.flipkart.framework.internal.config.ConfigName;
import com.flipkart.framework.internal.config.FrameworkConfig;
import com.google.common.io.Files;
import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;
import groovy.util.slurpersupport.Node;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by santhosh.b on 13/06/15.
 */
public class XMLDataProvider implements DataProvider {

    private TestSuite testSuite;
    private TestCase currentTestCase;
    private TestStep currentTestStep;
    private Permutation currentPermutation;

    public TestSuite parse(String filePath) throws Exception {

        String extension = Files.getFileExtension(filePath);

        if (StringUtils.isEmpty(extension)) {
            filePath = filePath + ".xml";
        }
        XmlSlurper xmlSlurper = new XmlSlurper();
        GPathResult gPathResult = xmlSlurper.parse(new File(filePath));

        handleTestSuite(gPathResult);
        return testSuite;

    }

    private void handleTestSuite(GPathResult testSuiteResultPath) throws Exception {
        testSuite = new TestSuite();
        testSuite.setName(testSuiteResultPath.getProperty("@name").toString());
        testSuite.setDesc(testSuiteResultPath.getProperty("@desc").toString());
        Iterator itr = testSuiteResultPath.childNodes();
        while (itr.hasNext()) {

            Node node = (Node) itr.next();

            if ("parameters".equals(node.name())) {
                handleParameters(node, ParamType.TESTSUITE);

            } else if ("testcase".equals(node.name())) {
               // Iterator testCasesNodeItr = node.childNodes();
                //while (testCasesNodeItr.hasNext()) {
                  //  Node testCasesNode = (Node) testCasesNodeItr.next();
                   // if ("testcase".equals(testCasesNode.name()))
                        handleTestCase(node, ParamType.TESTCASE);
                //}

            } else {

                //TODO: Fix this path.
            }

        }

        logger.info(testSuite.toString());
    }

    /*
     * Below method builds the object of Parameters
     * <parameters name="parameter name">
     *     <parameter name="name" value="value"/>
     *     <parameter name="name" value="value"/>
     * </parameters>
     *
     * TODO:Passing ParamType may be obsolete....See if you can better
     *
     */
    private void handleParameters(Node node, ParamType paramType) {

        Parameters parameters = new Parameters();
        parameters.setName(node.attributes().get("name").toString());
        Map<String, Object> parameter = new HashMap<String, Object>();

        Iterator<Node> itr = node.childNodes();
        while (itr.hasNext()) {
            Node childNode = itr.next();
            String key = childNode.attributes().get("name").toString();
            String value = childNode.attributes().get("value").toString();
            parameter.put(key, value);
        }
        parameters.setParameter(parameter);
        switch (paramType) {

            case TESTSUITE:
                testSuite.addParameters(parameters);
                break;
            case TESTCASE:
                currentTestCase.addParameters(parameters);
                break;
            case TESTSTEP:
                currentTestStep.addParameters(parameters);
                break;
            case PERMUTATION:
                currentPermutation.addParameters(parameters);
                break;
        }

    }

    private void handleTestCase(Node node, ParamType paramType) throws Exception {

        Iterator<Node> itr = node.childNodes();
        currentTestCase = new TestCase();
        currentTestCase.setName(node.attributes().get("name").toString());
        currentTestCase.setDesc(node.attributes().get("desc").toString());
        if (node.attributes().get("groups") != null && !(node.attributes().get("groups").toString().isEmpty())) {
            currentTestCase.setGroups(node.attributes().get("groups").toString());
        }
//        TODO: make group mandatory
//        else {
//            throw new Exception("TestCase has no groups");
//        }
        while (itr.hasNext()) {
            Node childNode = itr.next();
            if ("parameters".equals(childNode.name())) {

                handleParameters(childNode, ParamType.TESTCASE);
            } else if ("step".equals(childNode.name())) {
                //Iterator testStepNodeItr = node.childNodes();
                //while (testStepNodeItr.hasNext()) {
                  //  Node testStepNode = (Node) testStepNodeItr.next();
                   // if ("step".equals(testStepNode.name()))
                        handleTestStep(childNode, ParamType.TESTSTEP);
                //}

            } else if ("permutations".equals(childNode.name())) {

                //TODO: handle permutations
                //handlePermutations(childNode);
            }
        }

        testSuite.addTestCase(currentTestCase);


    }

//    private void handlePermutations(Node node) {
//        Iterator<Node> itr = node.childNodes();
//        while (itr.hasNext()) {
//            currentPermutation = new Permutation();
//            Node childNode = itr.next();
//            if ("permutation".equals(childNode.name())) {
//                handleParameters(childNode, ParamType.PERMUTATION);
//            }
//            currentTestCase.addPermutation(currentPermutation);
//        }
//    }

    private void handleTestStep(Node node, ParamType paramType) throws Exception {


        Iterator<Node> itr = node.childNodes();
        currentTestStep = new TestStep();
        currentTestStep.setName(node.attributes().get("name").toString());
        currentTestStep.setDesc(node.attributes().get("desc").toString());
        while (itr.hasNext()) {

            Node childNode = itr.next();
            if ("parameters".equals(childNode.name())) {

                handleParameters(childNode, ParamType.TESTSTEP);
            } else if ("RESTRequest".equals(childNode.name())) {

                handleRESTRequest(childNode, currentTestStep, false);
            } else if ("ConfigRESTRequest".equals(childNode.name())) {

                handleRESTRequest(childNode, currentTestStep, true);
            }
        }

        currentTestCase.addStep(currentTestStep);
    }

    private void handleRESTRequest(Node node, TestStep step, boolean isConfigRequest) throws Exception {

        Iterator<Node> itr = node.childNodes();
        RESTRequest restRequest = new RESTRequest();
        logger.info("handleREST itr:" + itr);
        logger.info("handleREST node:" + node.attributes());
        String restName = node.attributes().get("name").toString();
        if (isConfigRequest) {

            restRequest = ConfigRequestParser.getInstance().getRESTRequest(restName);

        }
        restRequest.setName(restName);
        if (node.attributes().containsKey("path")) {
            restRequest.setPath(node.attributes().get("path").toString());
        }
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

        step.setRequest(restRequest);
    }


    @org.testng.annotations.DataProvider(name = "xmlDataProvider")
    public static Object[][] provides(Method method) {

        String testSuiteFilePath = FrameworkConfig.getString(ConfigName.TEST_SUITE_FILE_PATH.getConfigName());
        String fileName = testSuiteFilePath + File.separator + method.getDeclaringClass().getSimpleName() + ".xml";

        try {

            List<TestCase> filterTestCase = TestSuiteCacheManager.getTestCase(method.getDeclaringClass(), XMLDataProvider.class, fileName, method);
            Object[] filterTestCaseArray = filterTestCase.toArray();
            Object returnObject[][] = new Object[filterTestCaseArray.length][1];
            for (int i = 0; i < returnObject.length; i++) {
                for (int j = 0; j < returnObject[i].length; j++) {
                    returnObject[i][j] = filterTestCaseArray[i];
                }
            }

            logger.info("Returning TestCaseList:" + filterTestCase);
            logger.info("Returning 2D Array:" + returnObject);
            return returnObject;

        } catch (ExecutionException e) {
            e.printStackTrace();
            //Do nothing
        }
        return null;
    }

}
