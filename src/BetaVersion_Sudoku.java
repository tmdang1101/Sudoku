import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class BetaVersion_Sudoku
{
    private String[][] board = new String[9][9];
    
    public String get(int row, int col) {
        if (row<0 || col<0 || row>=board.length || col>=board.length) {
            return null;
        }
        return board[row][col];
    }
    
    public BetaVersion_Sudoku(String filename) 
    throws IOException
    {
    	Scanner scan = new Scanner(new FileInputStream(filename));
    	for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
            	String n = scan.next();
            	if(n.equals("_"))
            		n = " ";
            	board[r][c] = n;
            }
    	}
    }
    
}