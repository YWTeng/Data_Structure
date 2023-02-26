
import java.util.*;
import java.io.*;

public class AddSparseMatrix2 {

    public static ArrayList<String> line = new ArrayList<>();
    public static String[] data1 = new String[2];
    public static String[] data2 = new String[2];
    public static int i;
    public static int j;
    public static int n;

    public static void main(String[] args) throws Exception {

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();

        FileReader freader1 = new FileReader(args[0]);
        BufferedReader breader1 = new BufferedReader(freader1);
        String str1 = breader1.readLine();

        FileReader freader2 = new FileReader(args[1]);
        BufferedReader breader2 = new BufferedReader(freader2);
        breader2.readLine();

        FileWriter fwriter = new FileWriter(args[2], false);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        bwriter.write(str1);
        bwriter.write("\r\n");

        str1 = breader1.readLine();
        String str2 = breader2.readLine();
        while (str1 != null && str2 != null) {
            bwriter.write(AddSparseMatrix2.write(str1, str2));
            bwriter.write("\r\n");
            str1 = breader1.readLine();
            str2 = breader2.readLine();
        }

        freader1.close();
        breader1.close();

        freader2.close();
        breader2.close();

        bwriter.flush();
        bwriter.close();

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory increased: " + (int)((usedMemoryAfter - usedMemoryBefore)/1024/1024) + "MB");
        long endTime = System.currentTimeMillis();
        System.out.println("Time increased: " + (int)(endTime - startTime) + "ms");
//        System.out.println((int)(runtime.getRuntime().maxMemory()/1024/1024));

    }

    public static String write(String str1, String str2){

        String[] part1 = str1.split(" ");
        String[] part2 = str2.split(" ");
        if (Objects.equals(part1[1], ":"))
            return str2;
        else if (Objects.equals(part2[1], ":"))
            return str1;
        else{
            line.clear();
            line.add(part1[0]);
            line.add(" ");
            i = 1;
            j = 1;
            data1 = part1[i].split(":");
            data2 = part2[j].split(":");
            while (i < part1.length || j < part2.length){
                if (i == part1.length){
                    line.add(part2[j]);
                    line.add(" ");
                    j++;
                    continue;
                }
                if (j == part2.length){
                    line.add(part1[i]);
                    line.add(" ");
                    i++;
                    continue;
                }
                if (data1[0].equals(data2[0])){
                    n = Integer.parseInt(data1[1]) + Integer.parseInt(data2[1]);
                    if (n != 0)
                        line.add(data1[0] + ":" + n + " ");
                    i++;
                    j++;
                    if (i == part1.length || j == part2.length) continue;
                    data1 = part1[i].split(":");
                    data2 = part2[j].split(":");

                }
                else if (Integer.parseInt(data1[0]) < Integer.parseInt(data2[0])){
                    line.add(part1[i]);
                    line.add(" ");
                    i++;
                    if (i != part1.length)
                        data1 = part1[i].split(":");
                }
                else{
                    line.add(part2[j]);
                    line.add(" ");
                    j++;
                    if (j != part2.length)
                        data2 = part2[j].split(":");
                }
            }
            StringBuilder sentence = new StringBuilder(line.size());
            for (String s : line){
                sentence.append(s);
            }
            return sentence.toString();
        }
    }
}
