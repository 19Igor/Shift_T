package org.example;

import org.example.services.CommandLineHandler;
import org.example.services.FileHandler;
import org.example.services.FileStatisticHandler;

public class Main {
    public static void main(String[] args) {
        CommandLineHandler cLHandler = CommandLineHandler.getCommandLineHandler(args);
        if (cLHandler == null) {
            return;
        }

        FileStatisticHandler statisticDataHandler = new FileStatisticHandler(cLHandler.getStatisticMode());
        FileHandler fileHandler = new FileHandler(statisticDataHandler);
        fileHandler.distributeDataAcrossFiles(cLHandler.getFileNames(),
                                            cLHandler.getResPath(),
                                            cLHandler.getPrefix(),
                                            cLHandler.identifyAddToExistingFileFlag());

        statisticDataHandler.printResults();
    }
}