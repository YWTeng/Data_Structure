
import java.util.*;
import java.io.*;

public class AddSparseMatrix1 {

    public static String[] data = new String[2];

    public static void main(String[] args) throws Exception {

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();

        FileReader freader1 = new FileReader(args[0]);
        BufferedReader breader1 = new BufferedReader(freader1);
        String str = breader1.readLine();
        String[] range = str.split(", ");
        int[][] matrix = new int[Integer.parseInt(range[0]) + 1][Integer.parseInt(range[1]) + 1];
        str = breader1.readLine();
        while (str != null) {
        	AddSparseMatrix1.addElement(matrix, str.split(" "));
            str = breader1.readLine();
        }
        freader1.close();
        breader1.close();
    	
        FileReader freader2 = new FileReader(args[1]);
        BufferedReader breader2 = new BufferedReader(freader2);
        breader2.readLine();
        str = breader2.readLine();
        while (str != null) {
        	AddSparseMatrix1.addElement(matrix, str.split(" "));
            str = breader2.readLine();
        }
        freader2.close();
        breader2.close();
        
        FileWriter fwriter = new FileWriter(args[2], false);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        bwriter.write(range[0] + ", " + range[1] + "\r\n");
        int zeroCount;
        for (int i = 1; i <= Integer.parseInt(range[0]); i++) {
        	bwriter.write(Integer.toString(i));
        	bwriter.write(" ");
        	zeroCount = 0;
        	for (int j = 1; j <= Integer.parseInt(range[1]); j++) {
        		if (matrix[i][j] != 0) {
        			bwriter.write(j + ":" + matrix[i][j] + " ");
        		}
        		else
        			zeroCount ++;
        	}
        	if (zeroCount == Integer.parseInt(range[1]))
        		bwriter.write(":");
        	bwriter.write("\r\n");
        }
        bwriter.flush();
        bwriter.close();

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory increased: " + (int)((usedMemoryAfter - usedMemoryBefore)/1024/1024) + "MB");
        long endTime=System.currentTimeMillis();
        System.out.println("Time increased: " + (int)(endTime - startTime) + "ms");

    }

    public static void addElement(int[][] matrix, String[] lineValue){
        
        if (Objects.equals(lineValue[1], ":"));
        else
            for (int i = 1; i < lineValue.length; i ++){
                data = lineValue[i].split(":");
                matrix[Integer.parseInt(lineValue[0])][Integer.parseInt(data[0])] += Integer.parseInt(data[1]);
            }
        
    }

}
