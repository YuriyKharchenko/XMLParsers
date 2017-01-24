package com.app.parsers.jaxp.SAX;

import com.app.parsers.jaxp.domain.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by uras on 24.01.2017.
 */
public class SAXHandler extends DefaultHandler {

    List<Employee> employeeList = new ArrayList<>();
    Employee employee = null;
    String content = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws org.xml.sax.SAXException {
        if (qName.equalsIgnoreCase("employee")) {
            employee = new Employee();
            employee.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("employee")) {
            employeeList.add(employee);
        } else if (qName.equalsIgnoreCase("firstName")) {
            employee.setFirstName(content);
        } else if (qName.equalsIgnoreCase("lastName")) {
            employee.setLastName(content);
        } else if (qName.equalsIgnoreCase("location")) {
            employee.setLocation(content);
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        content = String.copyValueOf(chars, start, length).trim();
    }
}
