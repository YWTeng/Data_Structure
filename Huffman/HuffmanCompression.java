
import java.util.*;
import java.io.*;
import java.lang.*;

class HuffmanNode {

    int data;
    int c;

    HuffmanNode left;
    HuffmanNode right;
}

public class HuffmanCompression {

    static ArrayList<HuffmanNode> nodes = new ArrayList<>();
    static String[] huffmanCodes = new String[128];

    public static String getCompressedCode(String inputText, String[] huffmanCodes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputText.length(); i++)
            result.append(huffmanCodes[inputText.charAt(i)]);
        return String.valueOf(result);
    }

    public static String[] getHuffmanCode(String inputText) {
        int[] wordCounts = new int[128];
        for (int i = 0; i < inputText.length(); i++)
            wordCounts[inputText.charAt(i)]++;
        int biggest;
        int place = 0;
        while (true) {
            biggest = 0;
            for (int i = 0; i < wordCounts.length; i++) {
                if (wordCounts[i] > biggest) {
                    biggest = wordCounts[i];
                    place = i;
                }
            }
            if (biggest == 0) break;
            nodes.add(HuffmanCompression.setNodes(biggest, place));
            wordCounts[place] = 0;
        }
        int length = nodes.size();
        for (int i = length - 1; i > 0; i--) {
            HuffmanCompression.setTree(i);
        }
        HuffmanCompression.traverseTree(nodes.get(0), "");

        return huffmanCodes;
    }

    public static HuffmanNode setNodes(int biggest, int place) {
        HuffmanNode nd = new HuffmanNode();
        nd.data = biggest;
        nd.c = place;
        nd.left = null;
        nd.right = null;
        return nd;
    }

    public static void setTree(int i) {
        HuffmanNode nd = new HuffmanNode();
        nd.data = nodes.get(i).data + nodes.get(i - 1).data;
        nd.c = 128;
        nd.left = nodes.get(i);
        nd.right = nodes.get(i - 1);
        int place = 0;
        if (i == 1);
        else{
            for (int j = i - 1; nd.data > nodes.get(j).data; j--) {
                place = j;
                if (j == 0) break;
            }
        }
        nodes.add(place, nd);
    }

    public static void traverseTree(HuffmanNode node, String code) {
        if (node.left == null || node.right == null) {
            huffmanCodes[node.c] = code;
        }
        else {
            HuffmanCompression.traverseTree(node.left, code + "0");
            HuffmanCompression.traverseTree(node.right, code + "1");
        }
    }

    public static void main(String[] args) throws Exception {
        // obtain input text from a text file encoded with ASCII code
        String inputText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(args[0])), "US-ASCII");
        // get Huffman codes for each character and write them to a dictionary file
        String[] huffmanCodes = HuffmanCompression.getHuffmanCode(inputText);
        FileWriter fwriter1 = new FileWriter(args[1], false);
        BufferedWriter bwriter1 = new BufferedWriter(fwriter1);
        for (int i = 0; i < huffmanCodes.length; i++) 
            if (huffmanCodes[i] != null) {
                bwriter1.write(Integer.toString(i) + ":" + huffmanCodes[i]);
                bwriter1.newLine();
            }
        bwriter1.flush();
        bwriter1.close();
        // get compressed code for input text based on huffman codes of each character
        String compressedCode = HuffmanCompression.getCompressedCode(inputText, huffmanCodes);
        FileWriter fwriter2 = new FileWriter(args[2], false);
        BufferedWriter bwriter2 = new BufferedWriter(fwriter2);
        if (compressedCode != null) 
            bwriter2.write(compressedCode);
        bwriter2.flush();
        bwriter2.close();
    }
}
