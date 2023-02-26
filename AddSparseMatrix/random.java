
import java.io.*;

public class random {

    public static void main(String[] args) throws Exception {

        FileWriter fwriter = new FileWriter(args[0], false);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        int c = 10000;
        int r = 5000;
        bwriter.write(c + ", " + r);
        bwriter.newLine();
        int n;
        int zeroCount;
        for (int i = 1; i <= c; i++) {
        	bwriter.write(Integer.toString(i));
        	bwriter.write(" ");
            zeroCount = 0;
        	for (int j = 1; j <= r; j++) {
        		if (Math.random() <= 0.01){
                    n = (int) ((Math.random()-0.5)*100);
                    if (n != 0){
                        bwriter.write(j + ":" + n + " ");
                        continue;
                    }
        		}
                zeroCount++;
        	}
            if (zeroCount == r)
        		bwriter.write(":");
        	bwriter.newLine();
        }
        bwriter.flush();
        bwriter.close();

    }

}