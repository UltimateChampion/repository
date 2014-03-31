package test.java;

import com.example.app.InputValidator;

import junit.framework.TestCase;

/**
 * Created by michaelfalk on 3/30/14.
 */
public class testInputValidator extends TestCase {
    /**
     * Test 1: Test that a valid input return true (valid Name and valid (numeric) Value)
     */
    public void test1() throws Exception {
        String name = "Valid Account Name";
        String value = "3";
        assertTrue(InputValidator.isValid(name, value));
    }

    /**
     * Test 2: Test that a invalid input return false (valid Name and invalid (non-numeric) Value)
     */
    public void test2() throws Exception {
        String name = "Valid Account Name";
        String value = "Peanuts";
        assertFalse(InputValidator.isValid(name, value));
    }

    /**
     * Test 3: Test that a invalid input return false (invalid (no) Name and valid (numeric) Value)
     */
    public void test3() throws Exception {
        String name = "";
        String value = "3";
        assertFalse(InputValidator.isValid(name, value));
    }

    /**
     * Test 4: Test that a invalid input return false (valid Name and invalid (no) Value)
     */
    public void test4() throws Exception {
        String name = "Valid Account Name";
        String value = "";
        assertFalse(InputValidator.isValid(name, value));
    }

    /**
     * Test 5: Test that a invalid input return false (invalid (no) Name and invalid (no) Value)
     */
    public void test5() throws Exception {
        String name = "";
        String value = "";
        assertFalse(InputValidator.isValid(name, value));
    }
}
