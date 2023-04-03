import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HandleMD {
    public static String generateFileNameFromTitle(String title) {
        if (title.length() > 100) {
            System.out.println("filename too long");
        }

        String lowercaseTitle = title.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lowercaseTitle.length(); i++) {
            if (lowercaseTitle.charAt(i) > 96 && lowercaseTitle.charAt(i) < 123) {
                sb.append(lowercaseTitle.charAt(i));
            }
        }
        sb.append(".md");
        System.out.println("Generated filename: " + sb.toString() + " from title: " + title);
        return sb.toString();
    }

    public static void generateMDFile(String filename) {
        System.out.println("Starting generation of file: " + filename);

        if (!checkExists(filename)) {
            System.out.println("Creating file: " + filename);
            try {
                File f = new File(filename);
                if (f.createNewFile()) {
                    System.out.println("File created: " + f.getName());
                } else {
                    System.out.println("File \"" + filename + "\" already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred when generating \"" + filename + "\".");
                e.printStackTrace();
            }
        } else {
            System.out.println("Cancelling creation of " + filename + " because it already exists.");
        }
    }

    public static void deleteMDFile(String filename) {
        System.out.println("Starting process of deleting file: " + filename);
        System.out.println("If you're sure you'd like to delete this file, press 'Y', otherwise, if you'd like to cancel, press 'N'.");
        // TODO: read user input or whatever

        if (checkExists(filename)) {
            File f = new File(filename);
            if (f.delete()) {
                System.out.println("File deleted: " + f.getName());
            } else {
                System.out.println("An error occurred when deleting \"" + filename + "\".");
            }
        } else {
            System.out.println("Cancelling deletion of " + filename + " because it doesn't exist.");
        }
    }

    public static boolean checkExists(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            System.out.println(filename + " exists.");
            return true;
        } else {
            System.out.println(filename + " does not exist.");
            return false;
        }
    }

    public static void printMDContent(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when initializing file: \"" + filename + "\".");

        }
    }

    public static void openMD(String filename) {
        System.out.println("Starting process of opening file: " + filename);
        try {
            File f = new File(filename);
            if(!Desktop.isDesktopSupported()) {  
                System.out.println("Desktop not supported");  
                return;  
            }
            Desktop desktop = Desktop.getDesktop();
            if(f.exists()) {
                desktop.open(f);
            }
        } catch (Exception e) {
            System.out.println("An error occurred when opening file: \"" + filename + "\".");
            e.printStackTrace();
        }
    }

}
