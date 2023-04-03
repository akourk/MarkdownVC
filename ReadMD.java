import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class ReadMD {
    public static int getStoryVersion(String filename) {
        int storyVersion = -1;
        if (HandleMD.checkExists(filename)) {

            String line;
            try (Stream<String> lines = Files.lines(Paths.get(filename))) {
                line = lines.skip(3).findFirst().get();
                storyVersion = Integer.parseInt(line.substring(14));
                lines.close();
            } catch(IOException e){
                System.out.println(e);
                System.out.println("An error occurred when getting story version from file: \"" + filename + "\".");
            }
        } else {
            System.out.println("Cannot find existing file named: " + filename);
        }
        return storyVersion;
    }
}