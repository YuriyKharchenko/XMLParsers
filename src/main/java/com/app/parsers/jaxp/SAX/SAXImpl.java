package com.app.parsers.jaxp.SAX;

import com.app.parsers.jaxp.domain.Employee;
import com.app.parsers.jaxp.Processor;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;


/**
 * Created by uras on 24.01.2017.
 */
public class SAXImpl implements Processor {
    @Override
    public List<Employee> getEmployees(File file) throws Exception {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SAXHandler handler  = new SAXHandler();
        saxParser.parse(file, handler);

        List<Employee> result = handler.employeeList;

        return result;
    }

    @Override
    public List<Employee> getEmployees() throws Exception {
        return null;
    }
}
