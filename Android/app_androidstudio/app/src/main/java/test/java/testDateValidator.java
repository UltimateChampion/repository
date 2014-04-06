package test.java;


import android.test.ActivityUnitTestCase;
import com.example.app.AccountRecord;
import com.example.app.DateValidator;
import com.example.app.LoginActivity;
import com.example.app.RegisterActivity;
import junit.framework.TestCase;

/**
 * Created by AmierNaji on 3/31/14.
 */
public class testDateValidator extends TestCase {

    /**
     * Tests cases where valid Start Date is input correctly and incorrectly
     * @throws Exception
     */
    public void testStart() throws Exception {

        assertEquals(true, DateValidator.validateDate("05/01/12", "05/02/13"));// start is valid
        assertEquals(false, DateValidator.validateDate("asdf", "05/02/13"));// start is invalid
    }

    /**
     * Branch 1: Assuming Start Date is valid, checks if End date is input correctly or incorrectly
     * @throws Exception
     */
    public void testEnd() throws Exception {

        assertEquals(true, DateValidator.validateDate("05/01/12", "05/02/13"));// start is valid, end is valid
        assertEquals(false, DateValidator.validateDate("05/01/12", "jkl;"));// start is valid, end is invalid
    }

    /**
     * Branch 2: Assuming Start and End are both validly input, checks if start is indeed before end
     * @throws Exception
     */
    public void testStartBeforeEnd() throws Exception {

        assertEquals(true, DateValidator.validateDate("01/03/00", "04/06/12"));// start and end are valid, start comes before end
        assertEquals(false, DateValidator.validateDate("01/03/12", "04/06/00"));// start and end are valid, start comes AFTER end
    }

    /**
     * Tests other miscellaneous combinations of start, end validity and chronology
     * @throws Exception
     */
    public void testMisc() throws Exception {

        assertEquals(false, DateValidator.validateDate("01/03/00", "01/03/00"));// same date should not be valid
        assertEquals(false, DateValidator.validateDate("lkjhf", "vnjckl"));// both invalid
    }


    /**
     * Tests to make sure JUnit is running properly
     * @throws Exception
     */
    public void testJUnit() throws Exception {

        final int expected = 1;
        final int actual = 1;
        assertEquals(expected, actual);
    }


}


