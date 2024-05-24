import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProcessEmployeeData {

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            String[] data = readInput(inputFile);
            Map<String, Integer> result = processData(data);
            writeOutput(result, outputFile);
            System.out.println("Output has been written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] readInput(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString().split("\n");
    }

    public static Map<String, Integer> processData(String[] data) {
        Map<String, Integer> departmentSalaryMap = new HashMap<>();
        Map<String, Integer> departmentMaxEmployeeIdMap = new HashMap<>();
        
        for (String line : data) {
            String[] tokens = line.split(", ");
            int employeeId = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            String department = tokens[2];
            int salary = Integer.parseInt(tokens[3]);

            int maxEmployeeId = departmentMaxEmployeeIdMap.getOrDefault(department, Integer.MIN_VALUE);
            if (employeeId > maxEmployeeId) {
                departmentMaxEmployeeIdMap.put(department, employeeId);
                departmentSalaryMap.put(department, salary);
            }
        }
        
        return departmentSalaryMap;
    }

    public static void writeOutput(Map<String, Integer> result, String outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        writer.close();
    }
}
