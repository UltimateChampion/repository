package test.java;

import com.example.app.AccountValidator;

import junit.framework.TestCase;

/**
 * Created by harrisongalloway on 3/30/14.
 */
public class testAccountValidator extends TestCase {
    /**
     * Test 1: Test that a valid username and valid password return true
     */
    public void test1() throws Exception {
        String username = "Valid Username";
        String password = "Password123";
        assertTrue(AccountValidator.isValid(username, password));
    }

    /**
     * Test 2: Test that a valid username and invalid password return false
     */
    public void test2() throws Exception {
        String username = "Valid Username";
        String password = "Password";
        assertFalse(AccountValidator.isValid(username, password));
    }

    /**
     * Test 3: Test that a valid username and invalid password return false
     */
    public void test3() throws Exception {
        String username = "Valid Username";
        String password = "Pass";
        assertFalse(AccountValidator.isValid(username, password));
    }

    /**
     * Test 4: Test that a valid username and invalid password return false
     */
    public void test4() throws Exception {
        String username = "Valid Username";
        String password = "password123";
        assertFalse(AccountValidator.isValid(username, password));
    }

    /**
     * Test 5: Test that an invalid username and valid password return false
     */
    public void test5() throws Exception {
        String username = "12345678";
        String password = "Password123";
        assertFalse(AccountValidator.isValid(username, password));
    }

    /**
     * Test 6: Test that an invalid username and valid password return false
     */
    public void test6() throws Exception {
        String username = "User";
        String password = "Password123";
        assertFalse(AccountValidator.isValid(username, password));
    }

    /**
     * Test 7: Test that an invalid username and valid password return false
     */
    public void test7() throws Exception {
        String username = "";
        String password = "Password123";
        assertFalse(AccountValidator.isValid(username, password));
    }

    /**
     * Test 8: Test that an invalid username and valid password return false
     */
    public void test8() throws Exception {
        String username = "User";
        String password = "Password123";
        assertFalse(AccountValidator.isValid(username, password));
    }

}
