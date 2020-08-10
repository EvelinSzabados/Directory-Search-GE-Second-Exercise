import data_manager.DataAnalyzer;
import data_manager.SearchFiles;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.getFilesInDir("/home/evelin/Codecool/Test");

        DataAnalyzer dataAnalyzer = new DataAnalyzer(searchFiles.getFiles());
        dataAnalyzer.readFile();
    }
}
