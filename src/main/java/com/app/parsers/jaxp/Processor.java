package com.app.parsers.jaxp;

import com.app.parsers.jaxp.domain.Employee;

import java.io.File;
import java.util.List;

/**
 * Created by uras on 23.01.2017.
 */
public interface Processor {

    List<Employee> getEmployees(File file) throws Exception;
    List<Employee> getEmployees() throws Exception;
}
