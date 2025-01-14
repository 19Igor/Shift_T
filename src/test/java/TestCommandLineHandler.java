import org.example.Constants;
import org.example.services.CommandLineHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCommandLineHandler {
    @Test
    void testCommandLineHandler(){
        String[] args = "-o src/Results/ -f -p sample- in1.txt in2.txt".split(" ");
        CommandLineHandler handler = CommandLineHandler.getCommandLineHandler(args);

        assert handler != null;
        Assertions.assertEquals("src/Results/", handler.getResPath());
        Assertions.assertEquals("-f", handler.getStatisticMode());
        Assertions.assertEquals("sample-", handler.getPrefix());
    }

    @Test
    void testGetFileNames(){
        String[] a1 = {"one", "a.txt", "b.txt", "banana"};
        CommandLineHandler handler = CommandLineHandler.getCommandLineHandler(a1);
        assert handler != null;
        List<String> l = handler.getFileNames();
        Assertions.assertEquals(Constants.PATH_TO_SOURCE_FILE + "a.txt", l.get(0));
        Assertions.assertEquals(Constants.PATH_TO_SOURCE_FILE + "b.txt", l.get(1));
        Assertions.assertEquals(2, l.size());

        String[] emptyArray = {"one", "banana"};
        CommandLineHandler h1 = CommandLineHandler.getCommandLineHandler(emptyArray);
        assert h1 != null;
        List<String> l1 = h1.getFileNames();
        Assertions.assertEquals(0, l1.size());
    }

    @Test
    void testIdentifyAddToExistingFileFlag(){
        String[] a1 = {"-a", "a.txt", "banana"};
        CommandLineHandler handler1 = CommandLineHandler.getCommandLineHandler(a1);
        assert handler1 != null;
        Assertions.assertTrue(handler1.identifyAddToExistingFileFlag());

        String[] a2 = {"one", "two", "a"};
        CommandLineHandler handler2 = CommandLineHandler.getCommandLineHandler(a2);
        assert handler2 != null;
        Assertions.assertFalse(handler2.identifyAddToExistingFileFlag());
    }
}
