package test.java;

import com.example.app.InputValidator;

import junit.framework.TestCase;

/**
 * @author Cody Berry 
 * created on 3/31/2014
 */

public class testInputValidator2 extends TestCase {

    /**
     * Test 1: Test for a valid input to return true. Must have both
     * a valid name and valid numeric numVal.
     * @throws Exception exc
     */

    public void test1() throws Exception {

        String name = "Where's the muscly";
        String numVal = "1";
        assertTrue(InputValidator.isValid(name, numVal));
    }

    /**
     * Test 2: Test for an invalid input to return false. Case with a 
     * valid name but an invalid numVal (i.e. numVal isn't numeric).
     * @throws Exception exc
     */

    public void test2() throws Exception {

        String name = "Cody Berry";
        String numVal = "Giggity-Giggity-Goo";
        assertFalse(InputValidator.isValid(name, numVal));
    }

    /**
     * Test 3: Test for an invalid input to return false. Case with an 
     * invalid/no name but avalid numval (i.e. numval is numeric).
     * @throws Exception exc
     */

    public void test3() throws Exception {

        String name = "";
        String numVal = "1";
        assertFalse(InputValidator.isValid(name, numVal));
    }

    /**
     * Test 4: Test for an invalid input to return false. Case with a 
     * valid name but an invalid numVal (i.e. numVal has no value).
     * @throws Exception exc
     */

    public void test4() throws Exception {

        String name = "Cody Berry";
        String numVal = "";
        assertFalse(InputValidator.isValid(name, numVal));
    }

    /**
     * Test 5: Test for an invalid input to return false. Case with an 
     * invalild/no name and an invalid numVal (i.e. numVal has no value).
     * @throws Exception exc
     */

    public void test5() throws Exception {

        String name = "";
        String numVal = "";
        assertFalse(InputValidator.isValid(name, numVal));
    }
}