package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private static final Logger log = LogManager.getLogger(FileHandler.class);
    private final FileStatisticHandler statisticHandler;

    public FileHandler(FileStatisticHandler statisticHandler) {
        this.statisticHandler = statisticHandler;
    }

    public void distributeDataAcrossFiles(List<String> fileNames, String filePathFlagPrefix, String filePrefix, boolean flagA){
        String path = filePathFlagPrefix + filePrefix;

        final String INTEGER_PATH_FILE = path + Constants.INTEGERS_FILE_NAME;
        final String FLOAT_PATH_FILE   = path + Constants.FLOATS_FILE_NAME;
        final String STRING_PATH_FILE  = path + Constants.STRING_FILE_NAME;

        try (BufferedWriter outNumerics = new BufferedWriter(new FileWriter(INTEGER_PATH_FILE, flagA));
             BufferedWriter outFloats   = new BufferedWriter(new FileWriter(FLOAT_PATH_FILE, flagA));
             BufferedWriter outStrings  = new BufferedWriter(new FileWriter(STRING_PATH_FILE, flagA))){

            for(String fileName: fileNames) {
                try(Scanner sourceFile = new Scanner(new File(fileName))) {

                    while(sourceFile.hasNext()) {
                        String line = sourceFile.nextLine();

                        if (isNumeric(line)) {
                            outNumerics.write(line + "\n");
                            statisticHandler.addLong(Long.parseLong(line));
                        }
                        else if (isFloat(line)) {
                            outFloats.write(line + "\n");
                            statisticHandler.addFloat(Float.parseFloat(line));
                        }
                        else if (isString(line)) {
                            if (!line.isEmpty())
                                outStrings.write(line + "\n");
                            statisticHandler.addString(line);
                        }
                        else{
                            System.out.println("Неверный тип данных в файле.");
                        }
                    }
                }
                catch (FileNotFoundException e) {
                    log.error("При открытии source-файла произошла ошибка", e);
                }
            }
        }catch (FileNotFoundException e) {
            log.error("При открытии итогово файла произошла ошибка", e);
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            log.error("Ошибка при открытии файлов для вывода результатов.", e);
            System.out.println(e.getMessage());
        }
        finally {
            checkFileEmptiness(INTEGER_PATH_FILE);
            checkFileEmptiness(FLOAT_PATH_FILE);
            checkFileEmptiness(STRING_PATH_FILE);
        }
    }

    private void checkFileEmptiness(String file) {
        Path filePath = Paths.get(file);
        try{
            if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
                if (Files.size(filePath) == 0) {
                    System.out.println("The file: " + file + " is empty.");
                    log.info("The file: {} is empty.", file);
                    Files.delete(filePath);
                }
                else {
                    log.info("The file: {} is not empty.", file);
                }
            }
            else {
                log.info("The provided path doesn't point to a file.");
                System.out.println("The provided path doesn't point to a file.");
            }
        }
        catch (IOException e){
            log.error("An error occurred while checking whether a file is empty.", e);
            System.err.println("I/O error: " + e.getMessage());
        }
        catch (SecurityException e){
            log.error("Access to a file was denied.", e);
            System.err.println("Access to the file is denied: " + e.getMessage());
        }
    }

    private boolean isFloat(String word) {
        return word.matches("-?\\d+\\.\\d+([eE][+-]?\\d+)?");
    }

    private boolean isString(String word) {
        return word.matches("^\\D*$");
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d{1,19}");
    }
}
