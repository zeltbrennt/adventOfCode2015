package aocutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static List<String> multipleLines(String source) {
        BufferedReader file = null;
        List<String> puzzle = new ArrayList<>();
        String line = null;
        try {
            file = new BufferedReader(new FileReader(source));
            while ((line = file.readLine()) != null) {
                puzzle.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found. Did you download the input files?");
        }
        return puzzle;
    }

    public static String singleLine(String source) {
        BufferedReader file = null;
        String puzzle = null;
        try {
            file = new BufferedReader(new FileReader(source));
            puzzle = file.readLine();
        } catch (IOException e) {
            System.out.println("File not found. Did you download the input files?");
        }
        return puzzle;
    }

}
