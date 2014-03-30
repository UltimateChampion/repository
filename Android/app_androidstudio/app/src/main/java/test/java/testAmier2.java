package test.java;

/**
 * Created by AmierNaji on 3/30/14.
 */

        import android.test.ActivityUnitTestCase;
        import com.example.app.AccountRecord;
        import com.example.app.LoginActivity;
        import com.example.app.RegisterActivity;

        import junit.framework.TestCase;

/**
 *
 * Tests the Login method
 */
public class testAmier2 extends TestCase {



    public void test() throws Exception {
        RegisterActivity ra = new RegisterActivity();
        LoginActivity la = new LoginActivity();



    }

    public void testSomeMethodInClass() throws Exception {
        final int expectedx = 1;
        final int actual = 1;
        assertEquals(expectedx, actual);
    }
}