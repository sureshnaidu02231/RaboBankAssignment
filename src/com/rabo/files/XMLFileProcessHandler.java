package com.rabo.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLFileProcessHandler extends FileProcessHanlder{
/**
 * This method reads xml file and prepares custoemr records collection.
 */
    @Override
    public List<CustomerRecord> read(String destinationFileString) {
        List<CustomerRecord> customerRecords = null;
        try {
                File file = new File(destinationFileString);
                DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                customerRecords = fetchCustomerRecords(doc.getElementsByTagName("record"));
            }catch (Exception e) {
                    System.out.println(e.getMessage());
            }
        return customerRecords;
    }
    
    private List<CustomerRecord> fetchCustomerRecords(NodeList nodeList) {
        List<CustomerRecord> customerRecords = new ArrayList<CustomerRecord>();
        for (int recordCount = 0; recordCount < nodeList.getLength(); recordCount++) {
                 Node recordNode = nodeList.item(recordCount);
                // make sure it's element node.
                if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                       Element element = (Element) recordNode;
                       customerRecords.add(new CustomerRecord.CustomerRecordBuilder().referenceNumber(element.getAttribute("reference"))
                                                                 .accountNumber(element.getElementsByTagName("accountNumber").item(0).getTextContent())
                                                                 .description(element.getElementsByTagName("description").item(0).getTextContent())
                                                                 .startBalance(element.getElementsByTagName("startBalance").item(0).getTextContent())
                                                                 .mutation(element.getElementsByTagName("mutation").item(0).getTextContent())
                                                                 .endBalance(element.getElementsByTagName("endBalance").item(0).getTextContent())
                                                                 .build());
                }
        }
    return customerRecords;
   }
}
