package com.santhosh.framework.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by santhosh.b on 13/06/15.
 */
public interface DataProvider {

    enum ParamType {TESTSUITE, TESTCASE, TESTSTEP, PERMUTATION};
    Logger logger = LoggerFactory.getLogger(XMLDataProvider.class);

    TestSuite parse(String filePath) throws Exception;
}
