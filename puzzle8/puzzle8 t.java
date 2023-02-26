import java.util.*;

class puzzleNode{
    int fn;
    int gn;
    int[] pzl;
    String move;
    puzzleNode prev;
}

public class puzzle8 {
    public static ArrayList<puzzleNode> seq = new ArrayList<>();
    public static ArrayList<int[]> puzzleFinal = new ArrayList<>();
    public static ArrayList<int[]> process = new ArrayList<>();

    public static void main(String[] args) {
        String line1 = "8 6 7";
        String line2 = "2 5 4";
        String line3 = "3 0 1";
        int [] puzzle = puzzle8.get_puzzle(line1, line2, line3);
        puzzleFinal.add(new int[]{0, 0});
        puzzleFinal.add(new int[]{0, 1});
        puzzleFinal.add(new int[]{0, 2});
        puzzleFinal.add(new int[]{1, 0});
        puzzleFinal.add(new int[]{1, 1});
        puzzleFinal.add(new int[]{1, 2});
        puzzleFinal.add(new int[]{2, 0});
        puzzleFinal.add(new int[]{2, 1});
        puzzleFinal.add(new int[]{2, 2});
        puzzleNode init = new puzzleNode();
        init.fn = 0;
        init.gn = puzzle8.needStep(puzzle);
        init.pzl = puzzle;
        init.move = "init";
        init.prev = null;
        seq.add(init);

        while (seq.get(0).gn != 0){
            puzzle8.move_puzzle(seq.get(0));
        }
//        System.out.println(seq.get(0).fn);

        puzzle8.reverse(seq.get(0));
        for (int i = process.size() - 1; i >= 0; i --) {
            puzzle8.printOut(process.get(i));
        }
    }

    public static void reverse(puzzleNode node) {
        if (node.prev == null) {
            process.add(node.pzl);
        }
        else {
            process.add(node.pzl);
            puzzle8.reverse(node.prev);
        }
    }

    public static void printOut(int[] puzzle){
        for (int i = 0; i < 9; i += 3) {
            System.out.println(puzzle[i] + " " + puzzle[i + 1] + " " + puzzle[i + 2]);
        }
        System.out.println();
    }

    public static int[] get_puzzle(String line1, String line2, String line3){
        int [] puzzle = new int[9];
        String[] place1 = line1.split(" ");
        String[] place2 = line2.split(" ");
        String[] place3 = line3.split(" ");
        for (int i = 0; i < 3; i ++){
            puzzle[i] = Integer.parseInt(place1[i]);
        }
        for (int i = 0; i < 3; i ++){
            puzzle[i + 3] = Integer.parseInt(place2[i]);
        }
        for (int i = 0; i < 3; i ++){
            puzzle[i + 6] = Integer.parseInt(place3[i]);
        }
        return puzzle;
    }

    public static int needStep(int[] puzzle){
        int gn = 0;
        for (int i = 0; i < 9; i ++){
            if (puzzle[i] == 0) continue;
            gn += Math.abs(puzzleFinal.get(i)[0] - puzzleFinal.get(puzzle[i] - 1)[0]);
            gn += Math.abs(puzzleFinal.get(i)[1] - puzzleFinal.get(puzzle[i] - 1)[1]);
        }
        return gn;
    }

    public static void move_puzzle(puzzleNode nd) {
        seq.remove(0);
        int zeroPlace = puzzle8.findZero(nd.pzl);
        if (zeroPlace % 3 != 0 && !nd.move.equals("right")){
            puzzle8.newP(zeroPlace, zeroPlace - 1, nd, "left");
        }
        if (zeroPlace % 3 != 2 && !nd.move.equals("left")){
            puzzle8.newP(zeroPlace, zeroPlace + 1, nd, "right");
        }
        if (zeroPlace >= 3 && !nd.move.equals("down")){
            puzzle8.newP(zeroPlace, zeroPlace - 3, nd, "up");
        }
        if (zeroPlace <= 5 && !nd.move.equals("up")){
            puzzle8.newP(zeroPlace, zeroPlace + 3, nd, "down");
        }
    }

    public static void newP(int zeroPlace, int changePlace, puzzleNode nd, String move){
        puzzleNode node = new puzzleNode();
        int[] puzzle = nd.pzl.clone();
        puzzle[zeroPlace] = puzzle[changePlace];
        puzzle[changePlace] = 0;
        node.fn = nd.fn + 1;
        node.gn = puzzle8.needStep(puzzle);
        node.pzl = puzzle;
        node.prev = nd;
        node.move = move;
        int hn = node.fn + node.gn;
        for (int i = 0; i <= seq.size(); i++) {
            if (i == seq.size()) {
                seq.add(node);
                break;
            }
            if (hn <= seq.get(i).fn + seq.get(i).gn) {
                seq.add(i, node);
                break;
            }
        }
    }

    public static int findZero(int[] puzzle){
        int zeroPlace = 9;
        for (int i = 0; i < 9; i ++){
            if (puzzle[i] == 0)
                zeroPlace = i;
        }
        return zeroPlace;
    }
}