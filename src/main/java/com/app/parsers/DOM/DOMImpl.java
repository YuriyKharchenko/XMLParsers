package com.app.parsers.DOM;

import com.app.domain.Employee;
import com.app.parsers.Processor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uras on 23.01.2017.
 */
public class DOMImpl implements Processor {
    @Override
    public List<Employee> getEmployees(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        List<Employee> employees = new ArrayList<>();

        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Employee employee = new Employee();

                String id = node.getAttributes().getNamedItem("id").getNodeValue();
                employee.setId(Integer.valueOf(id));

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node chNode = childNodes.item(j);
                    if (chNode instanceof Element) {

                        String content = chNode.getLastChild().getTextContent().trim();

                        if (chNode.getNodeName().equalsIgnoreCase("firstName")) {
                            employee.setFirstName(content);
                        } else if (chNode.getNodeName().equalsIgnoreCase("lastName")) {
                            employee.setLastName(content);
                        } else if (chNode.getNodeName().equalsIgnoreCase("location")) {
                            employee.setLocation(content);
                        }
                    }
                }
                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployees() throws Exception {
        return null;
    }


}


