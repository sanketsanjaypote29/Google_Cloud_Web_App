import java.util.*;
import java.io.*;

public class Main {
    public static Map<String,Integer> processData(ArrayList<String> array) {
        Map<String, Integer> departmentSalaryMap = new HashMap<>();
        Map<String, Integer> departmentMaxEmployeeIdMap = new HashMap<>();

        for (String line : array) {
            String[] tokens = line.split(", ");
            int employeeId = Integer.parseInt(tokens[0]);
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

    public static void main (String[] args) {
        ArrayList<String> inputData = new ArrayList<String>();
        String line;
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
            while(in.hasNextLine())
                inputData.add(in.nextLine());
            Map<String,Integer> retVal = processData(inputData);
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            for(Map.Entry<String,Integer> e: retVal.entrySet())
                output.println(e.getKey() + ": " + e.getValue());
            output.close();
        } catch (IOException e) {
            System.out.println("IO error in input.txt or output.txt");
        }
    }
}
