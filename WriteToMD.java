import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteToMD {
    public static void setInitializeFlow(String filename, String title) {
        setMetaData(filename, title);
        setTitle(filename, title);
        setContents(filename);
    }

    // set initial metadata methods
    public static void setMetaData(String filename, String title) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("---\n");
            myWriter.write("Title: " + title + "\n");
            myWriter.write("StoryID: " + helper.generateUUID() + "\n");
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
    }

    public static void setTitle(String filename, String title) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write("# " + title + "\n");
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully set title of \"" + filename + "\" as: \"" + title + "\".");
        } catch (IOException e) {
            System.out.println("An error occurred when setting the title of file: \"" + filename + "\".");
            e.printStackTrace();
        }
    }

    public static void setContents(String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            myWriter.write("# Contents\n");
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully created Contents section of \"" + filename + "\".");
        } catch (IOException e) {
            System.out.println("An error occurred when trying to create Contents section of file: \"" + filename + "\".");
            e.printStackTrace();
        }
    }

    // edit metadata methods
    public static void iterateStoryVersion(String filename) {
        int storyVersion = ReadMD.getStoryVersion(filename);
        storyVersion++;
        String newLine = "StoryVersion: " + storyVersion;

        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");

            // TODO: add checks in here
            for (int i = 0; i < 3; i++) {
                raf.readLine();
            }
            raf.writeBytes(newLine);
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem reading file.");
        }
    }
}