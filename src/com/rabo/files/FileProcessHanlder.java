package com.rabo.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FileProcessHanlder {
    /**
     * The Records are iterated and applied the business rules to print the failed records.
     * @param customerRecords totalRecords in the file
     * @param fileName the file that is processing currently
     * @throws IOException
     */
    public void process(List<CustomerRecord> customerRecords,String fileName) throws IOException{
        Set<String> referneceSet = new HashSet<String>();
        List<CustomerRecord> failedCustomerRecords = new ArrayList<CustomerRecord>();
        customerRecords.stream().forEach(customerRecord ->{
            if((!referneceSet.add(customerRecord.getReferenceNumber())) ||
                    (!customerRecord.getEndBalance().equals(customerRecord.getStartBalance().add(customerRecord.getMutation())))){
                failedCustomerRecords.add(customerRecord);
            }
        });
        if(failedCustomerRecords.size()>0){
            writeOutputToFile(failedCustomerRecords,fileName);
        }
    }
    public abstract List<CustomerRecord> read(String destinationFileString);
    /**
     * The failed Records are written to the output directory
     * @param failedCustomerRecords
     * @param fileName
     * @throws IOException
     */
    private void writeOutputToFile(List<CustomerRecord> failedCustomerRecords,String fileName) throws IOException{
        String destinationOutputFileString = System.getProperty("user.dir") + File.separator + "outputFiles" + File.separator + fileName + ".html";
        BufferedWriter writer = null;
        try{
                new File(destinationOutputFileString).createNewFile();
                writer = new BufferedWriter(new FileWriter(destinationOutputFileString)); 
                writer.write("<html><head></head><body>");
                writer.newLine();
                for(int failedRecordCount= 0 ; failedRecordCount < failedCustomerRecords.size(); failedRecordCount++){
                    CustomerRecord failedCustomerRecord = failedCustomerRecords.get(failedRecordCount);
                    writer.write(  "reference --> " + failedCustomerRecord.getReferenceNumber() + " description --> "+ failedCustomerRecord.getDescription());
                    writer.newLine();
                }
                    writer.write("</body></head></html>");
                    writer.flush();
        }catch(IOException ex){
            // we can log for the error. Right now i am just eating the exception.
            ex.printStackTrace();
        }
        finally {
            if(writer!=null){
                writer.close();
            }
        }
    }
}
