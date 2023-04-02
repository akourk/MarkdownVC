import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class WriteToMD {
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