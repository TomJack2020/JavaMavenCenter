package duck;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTools {

    // helper method to read a file and return its contents as a string
    public static String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }


}
