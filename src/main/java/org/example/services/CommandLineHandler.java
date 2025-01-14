package org.example.services;

import lombok.Getter;
import org.example.Constants;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandLineHandler {
    private final String[] args;
    private final String   prefix;
    private final String   resPath;
    private final boolean  addToExistingFilesFlag;
    private final String   statisticMode;

    public static CommandLineHandler getCommandLineHandler(String[] args){
        if (args.length == 0) {
            System.out.println("Пустая командная строка.");
            return null;
        }
        return new CommandLineHandler(args);
    }
    private CommandLineHandler(String[] args) {
        this.args = args;
        prefix = identifyPrefix();
        resPath = identifyResPath();
        addToExistingFilesFlag = identifyAddToExistingFileFlag();
        statisticMode = identifyStatisticMode();
    }

    private String identifyPrefix() {
        for (int i = 0; i < args.length && !args[i].equals(Constants.FILE_FORMAT); i++){
            if (args[i].equals(Constants.PREFIX_TO_RESULT_FILE))
                return args[i + 1];
        }
        return "";
    }

    private String identifyResPath() {
        for (int i = 0; i < args.length && !args[i].contains(Constants.FILE_FORMAT); i++){
            if (args[i].equals(Constants.PATH_TO_RESULT_FILE))
                return args[i + 1];
        }
        return "";
    }

    public boolean identifyAddToExistingFileFlag() {
        for (String line: args){
            if (line.equals(Constants.IS_ADD_TO_EXISTING_FILES))
                return true;
        }
        return false;
    }

    private String identifyStatisticMode() {
        for(String line: args){
            if (line.equals(Constants.SHORT_STATISTIC_MODE))
                return Constants.SHORT_STATISTIC_MODE;
            else
                return Constants.FULL_STATISTIC_MODE;
        }
        return Constants.DEFAULT_STATISTIC_MODE;
    }

    public List<String> getFileNames() {
        List<String> names = new ArrayList<>();
        for (String s: args){
            if (s.contains(Constants.FILE_FORMAT))
                names.add(Constants.PATH_TO_SOURCE_FILE + s);
        }
        return names;
    }
}
