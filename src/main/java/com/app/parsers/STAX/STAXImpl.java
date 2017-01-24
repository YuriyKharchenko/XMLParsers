package com.app.parsers.STAX;

import com.app.domain.Employee;
import com.app.parsers.Processor;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uras on 24.01.2017.
 */
public class STAXImpl implements Processor {
    @Override
    public List<Employee> getEmployees() throws Exception {
        return null;
    }

    @Override
    public List<Employee> getEmployees(File file) throws Exception {
        List<Employee> employeeList = null;
        Employee currentEmployee = null;
        String tagContent = null;

        InputStream inputStream = new FileInputStream(file);

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader =
                xmlInputFactory.createXMLStreamReader(inputStream);
        while (xmlStreamReader.hasNext()) {
            int event = xmlStreamReader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                if ("employee".equals(xmlStreamReader.getLocalName())) {
                    currentEmployee = new Employee();
                    currentEmployee.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                }
                if ("employees".equals(xmlStreamReader.getLocalName())) {
                    employeeList = new ArrayList<>();
                }
            } else if (event == XMLStreamConstants.CHARACTERS) {
                tagContent = xmlStreamReader.getText().trim();
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if (xmlStreamReader.getLocalName().equals("employee")) {
                    employeeList.add(currentEmployee);
                } else if (xmlStreamReader.getLocalName().equals("firstName")) {
                    currentEmployee.setFirstName(tagContent);
                } else if (xmlStreamReader.getLocalName().equals("lastName")) {
                    currentEmployee.setLastName(tagContent);
                } else if (xmlStreamReader.getLocalName().equals("location")) {
                    currentEmployee.setLocation(tagContent);
                }
            } else if (event == XMLStreamConstants.START_DOCUMENT) {
                employeeList = new ArrayList<>();
            }

        }
        return employeeList;
    }
}