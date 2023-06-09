import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileIO<T> {


    private static String fileMessage(String fileName, String message) {
        return "%s %s at %s".formatted(fileName, message, Paths.get("").toAbsolutePath().normalize());
    }
    public static ArrayList<String> readDataFromFile(String filname) {
        //Create a new ArrayList
        ArrayList<String> dataList = new ArrayList<>();
        // Creates a BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filname)))) {
            String lineStr;
            //While there are lines in the file
            while ((lineStr = reader.readLine()) != null)
                //Add lines to the ArrayList
                dataList.add(lineStr);
        } catch (FileNotFoundException e) {
            System.out.println(fileMessage(String.valueOf(filname), "file not found"));
        } catch (IOException e) {
            System.out.println(fileMessage(String.valueOf(filname), "file can't be read. Error " + e.getMessage()));
        }
        return dataList;
    }

    public static void writeDataToFile(String fileName, ArrayList<String> userData) {
        try {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
                for (String s: userData) {
                    bufferedWriter.write(s + System.lineSeparator());
                }
            }
        }  catch (IOException e) {
            System.out.println(fileMessage(fileName, "cannot be written to"));
        }
    }

}
