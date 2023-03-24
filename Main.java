import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.awt.Desktop;

//TODO: Re-organize architecture?
// - make classes private
// separate into different files
// make prompts? no don't do that. will be direct input from front-end anyway.
// prompts will only help with testing, but not necessary.
// build out read instructions from file?

class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        String title = "editable markdown file";

        String filename = handleMD.generateFileNameFromTitle(title);

        handleMD.deleteMDFile(filename);


        if (handleMD.checkExists(filename)) {
            handleMD.printMDContent(filename);
            // handleMD.OpenMD(filename);
        } else {
            handleMD.generateMDFile(filename);
            WriteToMD.setMetaData(filename, title);
            WriteToMD.setTitle(filename, title);
        }
        

        // handleMD.DeleteMDFile(filename);
    }
}

class handleMD {
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

class WriteToMD {
    public static void setTitle(String filename, String title) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write("# " + title);
            myWriter.close();
            System.out.println("Successfully initialized \"" + filename + "\" with the title: \"" + title + "\".");
        } catch (IOException e) {
            System.out.println("An error occurred when initializing file: \"" + filename + "\".");
            e.printStackTrace();
        }
    }

    public static void setMetaData(String filename, String title) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("---\n");
            myWriter.write("Title: " + title + "\n");
            myWriter.write("StoryID: " + "\n");
            myWriter.write("StoryVersion: " + 0 + "\n");
            myWriter.write("Filename: " + filename + "\n");
            myWriter.write("Description: " + "\n");
            myWriter.write("---\n");
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully created metadata for \"" + filename + "\" with the title: \"" + title + "\".");
        } catch (IOException e) {
            System.out.println("An error occurred while creating metadata for file: \"" + filename + "\".");
            e.printStackTrace();
        }

        iterateStoryVersion(filename);
    }

    public static void iterateStoryVersion(String filename) {
        int storyVersion = ReadMD.getStoryVersion(filename);

        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(filename));
            String line;
            try {
                line = lnr.readLine();
                System.out.println(line);
                System.out.println(lnr.getLineNumber());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

class ReadMD {
    public static int getStoryVersion(String filename) {
        int storyVersion = -1;
        if (handleMD.checkExists(filename)) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                for (int i = 0; i < 3; i++) { // story version should always be on line 4
                    br.readLine();
                }
                String line = br.readLine();
                if (line != null && line.length() > 14 && line.substring(0, 14).equals("StoryVersion: ")) {
                    storyVersion = Integer.parseInt(line.substring(14));
                } else {
                    System.out.println("An error occurred when getting story version from file: \"" + filename + "\". The MetaData may be incorrect.");
                    System.out.println("line: \"" + line + "\"\nline.length(): " + line.length());
                }
            } catch (IOException e) {
                System.out.println("An error occurred when getting story version from file: \"" + filename + "\".");
            }
        } else {
            System.out.println("Cannot find existing file named: " + filename);
        }

        return storyVersion;
    }
}