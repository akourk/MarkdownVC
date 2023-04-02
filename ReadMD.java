import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;


public class ReadMD {
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
