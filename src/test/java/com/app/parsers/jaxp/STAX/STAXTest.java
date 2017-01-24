package com.app.parsers.jaxp.STAX;

import com.app.parsers.jaxp.domain.Employee;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uras on 24.01.2017.
 */
public class STAXTest {
    private static final File TESTFILE = new File("test.xml");

    public static List<Employee> expectedResult = new ArrayList<>();

    @BeforeClass
    public static void initEmployeeList() {
        Employee tom = new Employee(111, "Tom", "Bra", "Bangalore");
        Employee john = new Employee(112, "John", "Davis", "Chennai");
        Employee choi = new Employee(113, "Choi", "Voui", "Pune");
        expectedResult.add(tom);
        expectedResult.add(john);
        expectedResult.add(choi);
    }

    @Test
    public void getEmployeeTest() throws Exception {
        STAXImpl sax = new STAXImpl();
        List<Employee> actualResult = sax.getEmployees(TESTFILE);

        Assert.assertEquals(expectedResult, actualResult);
    }
}
