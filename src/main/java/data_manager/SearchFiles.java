package data_manager;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFiles {
    private List<File> files = new ArrayList<File>();

    public void getFilesInDir(String path) throws IOException {
        File[] fileList = new File(path).listFiles(sqlFilter());

        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                getFilesInDir(file.toString());
            } else {
                files.add(file);
            }
        }
    }

    public FileFilter sqlFilter() {

        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".sql")
                        || pathname.isDirectory();
            }
        };
    }

    public List<File> getFiles() {
        return this.files;
    }
}
