
import java.util.*;
import java.io.*;
import java.lang.*;

public class HuffmanDecompression {

    static ArrayList<Integer> data = new ArrayList<>();
    static ArrayList<String> c = new ArrayList<>();

    public static String getDecompressedCode(String str) {

        int length = data.size();
        int[] depth = new int[length];
        for (int i = 0; i < length; i++){
            depth[i] = c.get(i).length();
        }

        int[] seq = new int[length];
        int dep;
        int place = 0;
        for (int i = 0; i < length; i++){
            dep = 0;
            for (int j = 0; j < length; j++){
                if (depth[j] > dep) {
                    dep = depth[j];
                    place = j;
                }
            }
            seq[i] = place;
            depth[place] = 0;
        }

        place = 0;
        int len = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (place < str.length()){
            for (int i = 0; i < length; i++){
                len = c.get(seq[i]).length();
                if (place + len > str.length()) continue;
                else if (place + len == str.length()){
                    if (c.get(seq[i]).equals(str.substring(place))) {
                        result.add(data.get(seq[i]));
                        break;
                    }
                }
                else {
                    if (c.get(seq[i]).equals(str.substring(place, place + len))) {
                        result.add(data.get(seq[i]));
                        break;
                    }
                }
            }
            place += len;
        }

        char[] code = new char[result.size()];
        int ch;
        for (int i = 0; i < result.size(); i++){
            ch = result.get(i);
            code[i] = (char)(ch);
        }
        return String.valueOf(code);
    }

    public static void wordDecode(String line){
        
        int place = line.indexOf(":");
        data.add(Integer.parseInt(line.substring(0, place)));
        c.add(line.substring(place + 1));
    }

    public static void main(String[] args) throws Exception {

        FileReader freaderc = new FileReader(args[0]);
        BufferedReader breaderc = new BufferedReader(freaderc);
        String compressed = breaderc.readLine();
        freaderc.close();
        breaderc.close();
        FileReader freaderd = new FileReader(args[1]);
        BufferedReader breaderd = new BufferedReader(freaderd);
        String strLine = breaderd.readLine();
        while (strLine != null) {
            wordDecode(strLine);
            strLine = breaderd.readLine();
        }
        freaderd.close();
        breaderd.close();

        String decompressedCode = HuffmanDecompression.getDecompressedCode(compressed);
        FileWriter fwriterd = new FileWriter(args[2], false);
        BufferedWriter bwriterd = new BufferedWriter(fwriterd);
        if (decompressedCode != null)
            bwriterd.write(decompressedCode);
        bwriterd.flush();
        bwriterd.close();
    }
}
