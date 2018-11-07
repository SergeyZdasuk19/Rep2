import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Main {
    private static String ext;
    private static ArrayList<File> listOfFiles = new ArrayList<>();


    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        Main object = new Main();
        CreateXML createXML = new CreateXML();
        File dir = new File(args[0]);
        ext = "." + args[1];
        object.fetchChild(dir);
        createXML.create(listOfFiles);
    }

    public void fetchChild(File file) {
        if (file.isDirectory()) {
            findFiles(file, ext, listOfFiles);
            File[] children = file.listFiles();
            for (File child : children) {
                this.fetchChild(child);
            }
        }
    }

    public void findFiles(File dir, String ext, ArrayList<File> listOfFiles) {
        File file = dir;
        if (!file.exists()) {
            System.out.println(dir + " folder doesn't exist");
        }
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if (listFiles.length == 0) {
            //System.out.println(dir + " doesn't contains files " + ext);

        } else {
            for (File f : listFiles) {
                System.out.println(f);
                listOfFiles.add(f);
            }
        }
    }

    public static class MyFileNameFilter implements FilenameFilter {

        private String ext;

        public MyFileNameFilter(String ext) {
            this.ext = ext.toLowerCase();
        }

        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
    }


}
