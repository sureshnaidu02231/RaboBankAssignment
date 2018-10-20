package com.rabo.files;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Listener {
    
    /**
     * This main method takes the path of directory where csv and xml files are there as input
     * It list the files in every loop and process each file sequentially.
     * @param args
     * @throws Exception
     */
    
    public static void main(String[] args) throws Exception{
        File[] listOfFiles = null; 
        String directoryName = null;
        // if the argument directoryname is not passed then program should exit 
        if(args.length==0){
            System.out.println("Please Enter directory name to process files");
            exitProgram();
        }
        directoryName = args[0];
        FileSystem fileSystem =FileSystems.getDefault();
        while(true){
            listOfFiles = new File(directoryName).listFiles();
            //if the directoryName is not a directory it should come out of the program.
            if(listOfFiles==null){
                exitProgram();
            }
            for(File customerStatementFile : listOfFiles){
               String destinationFileString = System.getProperty("user.dir") + File.separator + "processFiles" + File.separator + customerStatementFile.getName();
               Files.move(fileSystem.getPath(customerStatementFile.getAbsolutePath().toString()),fileSystem.getPath(destinationFileString), StandardCopyOption.ATOMIC_MOVE);
               Type type = null;
               if(customerStatementFile.getName().endsWith(".xml")){
                   type = Type.XML; 
               }else if(customerStatementFile.getName().endsWith(".csv")){
                   type = Type.CSV; 
               }
               FileProcessHanlder fileHandler = FileProcessHandlerFactory.getHandler(type);
               List<CustomerRecord> customersRecords = fileHandler.read(destinationFileString);
               fileHandler.process(customersRecords,customerStatementFile.getName());
            }
        }
    }

    private static void exitProgram(){
        System.exit(0);
    }

}
