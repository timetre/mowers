package com.blablacar.mowers;

import com.blablacar.mowers.common.ErrorDictionary;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class RunnerTest {

    @DataProvider
    public Object[][] checkInputArgsDataProvider() {
        return new Object[][]{
                //Input args ; expected error
                {null, ErrorDictionary.ERR_WRONG_INPUT_PARAMS},
                {new String[0], ErrorDictionary.ERR_WRONG_INPUT_PARAMS},
                {new String[]{"no-file"}, ErrorDictionary.ERR_CANNOT_READ_FILE},
                {new String[]{getClass().getClassLoader().getResource("test-input").getPath()}, null},
        };
    }

    //Checking input tests are fine
    @Test(dataProvider = "checkInputArgsDataProvider")
    public void testCheckInputArgs(String[] args, ErrorDictionary expectedError) {
        ErrorDictionary error = Runner.checkInputArgs(args);

        Assert.assertEquals(error, expectedError);
    }

    // for debugging purpose. Not meant to be executed in regular test process
    @Test(enabled = false)
    public void go() {

        String[] args = {getClass().getClassLoader().getResource("test-input").getPath()};

        Runner.main(args);

    }
}
