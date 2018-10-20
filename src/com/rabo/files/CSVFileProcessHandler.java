package com.rabo.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileProcessHandler extends FileProcessHanlder {
/**
 * This method reads the csv file and prepares the customerRecords collection
 */
    @Override
    public List<CustomerRecord> read(String destinationFileString) {
        BufferedReader br = null;
        String customerRecordLine  = null;
        List<CustomerRecord> customerRecords = new ArrayList<CustomerRecord>();
        int recordsCount = 0;
        try {
            br = new BufferedReader(new FileReader(destinationFileString));
            while ((customerRecordLine = br.readLine()) != null) {
                recordsCount++;
                if(recordsCount==1){
                    continue;
                }
                String[] customerRecordData = customerRecordLine.split(",");
                customerRecords.add(new CustomerRecord.CustomerRecordBuilder().referenceNumber(customerRecordData[0])
                                                          .accountNumber(customerRecordData[1])
                                                          .description(customerRecordData[2])
                                                          .startBalance(customerRecordData[3])
                                                          .mutation(customerRecordData[4])
                                                          .endBalance(customerRecordData[5])
                                                          .build());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return customerRecords;  
    }
}
