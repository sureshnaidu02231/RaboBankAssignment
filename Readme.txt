I have created a standlone Project which list the files in a directory for every iteration.
Processing of files are happening sequentially.
In the project Root Directory I have created to two directories for ProcessedFiles and outputfiles.
outputFiles --> In this directory if a file procesing has issues with business rules then an error html file is generated.
processFiles  --> In this directory processed files are there.

when we run the program we have to pass an argument where the file are there.I mean the directory path where input files are there.
Sample argument : C:\rad\onePAM\WorkSpace\RaboBankAssignment\CustomerStatementFiles

I did not use any frameworks to implement this but i know spring batch,spring integarton as well.