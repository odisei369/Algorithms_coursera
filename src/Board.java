import java.util.ArrayList;

public class Board {
    private int[][] blocks;
    private int[] zeroPozition = new int[2];

    public Board(int[][] blocks){
        for (int a = 0; a < blocks.length; a++){
            for (int b = 0; b < blocks[a].length; b++){
                this.blocks[a][b] = blocks[a][b];
                if(blocks[a][b] == 0){
                    zeroPozition[0] = a;
                    zeroPozition[1] = b;
                }
            }
        }
    }           // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {return blocks.length;}                // board dimension n
    public int hamming(){
        int numberOut = 0;
        for(int a = 0; a < blocks.length * blocks.length - 1; a++){
            if(blocks[a/blocks.length][a % blocks.length] != a + 1)
                numberOut++;
        }
        return numberOut;
    }                   // number of blocks out of place
    public int manhattan(){
        int counter = 0;
        for(int a = 0; a < blocks.length * blocks.length; a++){
            int currentX = a / blocks.length;
            int currentY = a % blocks.length;
            int number = blocks[currentX][currentY];
            if(number == 0) continue;
            int numberX = (number - 1) / blocks.length;
            int numberY = (number - 1) % blocks.length;
            counter += Math.abs(currentX - numberX) + Math.abs(currentY - numberY);
        }
    }                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal() {
        for(int a = 0; a < blocks.length * blocks.length - 1; a++){
            if(blocks[a/blocks.length][a % blocks.length] != a + 1)
                return  false;
        }
        return true;
    }                // is this board the goal board?
    public Board twin(){
        Board newB = new Board(blocks);
        int tmp;
        if(zeroPozition[0] != 0){
            tmp = newB.blocks[0][0];
            newB.blocks[0][0] = newB.blocks[0][1];
            newB.blocks[0][1] = tmp;
        }else{
            tmp = newB.blocks[1][0];
            newB.blocks[1][0] = newB.blocks[1][1];
            newB.blocks[1][1] = tmp;
        }
        return newB;
    }                    // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y){
        if(y == this) return true;
        if(y == null) return false;
        if (this.getClass() != y.getClass())
            return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension()) return false;
        for(int a = 0; a < blocks.length * blocks.length - 1; a++){
            if(blocks[a/blocks.length][a % blocks.length] != that.blocks[a/blocks.length][a % blocks.length])
                return  false;
        }
        return true;
    }        // does this board equal y?
    public Iterable<Board> neighbors(){
        ArrayList<Board> neighbors = new ArrayList<>();
        int tmp;
        if(zeroPozition[0] != 0){
            Board brd = new Board(blocks);
            brd.blocks[zeroPozition[0]][zeroPozition[1]] = brd.blocks[zeroPozition[0] + 1][zeroPozition[1]];
            brd.blocks[zeroPozition[0] + 1][zeroPozition[1]] = 0;
            neighbors.add(brd);
        }
        //TODO
    }     // all neighboring boards
    public String toString(){
        String str = new String();
        str += dimension() + "\n";
        for (int a = 0; a < blocks.length; a++){
            for (int b = 0; b < blocks.length; b++){
                str += " " + blocks[a][b];
            }
            str += " \n";
        }
        return str;
    }               // string representation of this board (in the output format specified below)

    public static void main(String[] args) {} // unit tests (not graded)
}