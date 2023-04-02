import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;

public class WriteToMD {

    // set initial metadata methods
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

        iterateStoryVersion(filename);
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

