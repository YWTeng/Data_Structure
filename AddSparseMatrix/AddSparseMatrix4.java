import java.io.*;
import java.util.*;


class Triple{
    int x;
    int y;
    int val;
    void Triple(){};
}

public class AddSparseMatrix4{

    public static Triple makeTriple(int row, int col, int value){
        Triple triple = new Triple();
        triple.x = row;
        triple.y = col;
        triple.val = value;
        return triple;
    }

    public static ArrayList <Triple> MergeMatrix (ArrayList <Triple> TripleList1, ArrayList <Triple> TripleList2){
        int i = 0, j = 0;
        ArrayList <Triple> Sum = new ArrayList <Triple> ();

        while (i < TripleList1.size() && j < TripleList2.size()){ 

            if (TripleList1.get(i).x < TripleList2.get(j).x){
                Sum.add(TripleList1.get(i));
                i++;
            }

            else if (TripleList1.get(i).x == TripleList2.get(j).x){

                if (TripleList1.get(i).y < TripleList2.get(j).y){
                    Sum.add (TripleList1.get(i));
                    i++;
                }

                else if (TripleList1.get(i).y == TripleList2.get(j).y){
                    if ((TripleList1.get(i).val + TripleList2.get(j).val) != 0)
                        Sum.add(makeTriple (TripleList1.get(i).x, TripleList1.get(i).y, TripleList1.get(i).val + TripleList2.get(j).val));
                    i++;
                    j++;
                }

                else{
                    Sum.add(TripleList2.get(j));
                    j++;
                }
            }

            else{
                Sum.add(TripleList2.get(j));
                    j++;
            }
        }

        if (i < TripleList1.size()){
            for (int m = i; m < TripleList1.size(); m++)
                Sum.add(TripleList1.get(m));
        }

        if (j < TripleList2.size()){
            for (int n = j; n < TripleList2.size(); n++)
                Sum.add(TripleList2.get(n));
        }

        return Sum;
    }

	public static void main(String[] args) throws Exception {


        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();

        FileReader freader1 = new FileReader(args[0]);
        BufferedReader breader1 = new BufferedReader(freader1);
        FileReader freader2 = new FileReader(args[1]);
        BufferedReader breader2 = new BufferedReader(freader2);
        FileWriter fwriter = new FileWriter(args[2], false);
        BufferedWriter bwriter = new BufferedWriter(fwriter);


        String dim1 = breader1.readLine();
        String[] RowCol = dim1.split(", ");
        int row = Integer.valueOf(RowCol[0]);
        int col = Integer.valueOf(RowCol[1]);

        ArrayList <Triple> TripleList1 = new ArrayList <Triple> ();

        while (true){
        	String str = breader1.readLine();
        	if (str == null)
        		break;
        	else{
                String[] strList = str.split(" ");
                int r = Integer.parseInt(strList[0]);
                for (int i = 1; i < strList.length; i++){
                    String[] strLst = strList[i].split(":");
                    if (strLst.length > 0){
                        Triple newTriple = makeTriple(r, Integer.parseInt(strLst[0]), Integer.parseInt(strLst[1]));
                        TripleList1.add(newTriple);
                    }
                }
            }
        }
        
        String dim2 = breader2.readLine();

        ArrayList <Triple> TripleList2 = new ArrayList <Triple> ();

        while (true){
            String str = breader2.readLine();
            if (str == null)
                break;
            else{
                String[] strList = str.split(" ");
                int r = Integer.parseInt(strList[0]);
                for (int i = 1; i < strList.length; i++){
                    String[] strLst = strList[i].split(":");
                    if (strLst.length > 0){
                        Triple newTriple = makeTriple(r, Integer.parseInt(strLst[0]), Integer.parseInt(strLst[1]));
                        TripleList2.add(newTriple);
                    }
                }
            }
        }

        ArrayList <Triple> Sum = MergeMatrix(TripleList1, TripleList2);

        int k = 1, i = 0;
        bwriter.write(dim1);
        bwriter.write("\r\n");

        while(k <= row){

            if (i < Sum.size() && k != Sum.get(i).x){ //
                bwriter.write(Integer.toString(k) + " :" );
                bwriter.write("\r\n");
            }

            else if (i == Sum.size() && k != Sum.get(Sum.size()-1).x){
                bwriter.write(Integer.toString(k) + " :" );
                bwriter.write("\r\n");
            }

            else{
                StringBuilder temp = new StringBuilder(Integer.toString(k));
                temp.append(" ");
                temp.append(Integer.toString(Sum.get(i).y));
                temp.append(":");
                temp.append(Integer.toString(Sum.get(i).val));
                i++;
                while (i < Sum.size()){
                    if (k == Sum.get(i).x){
                        temp.append(" ");
                        temp.append(Integer.toString(Sum.get(i).y));
                        temp.append(":");
                        temp.append(Integer.toString(Sum.get(i).val));
                        i++;
                    }
                    else 
                        break;
                }
                bwriter.write(temp.toString());
                bwriter.write(" ");
                bwriter.write("\r\n");
                
            }

            k++;
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
    }
}
