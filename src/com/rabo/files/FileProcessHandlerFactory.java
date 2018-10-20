package com.rabo.files;

public class FileProcessHandlerFactory {
    /**
     * This factory method returns an FileProcessHandler based on file Type.
     * @param fileType
     * @return
     */
    public static FileProcessHanlder getHandler(Type fileType){
        FileProcessHanlder fileHandler = null;
          if(fileType.equals(Type.XML)){
              fileHandler = new XMLFileProcessHandler();
           }else if(fileType.equals(Type.CSV)){
               fileHandler = new CSVFileProcessHandler(); 
           }
          return fileHandler;
    }
}
