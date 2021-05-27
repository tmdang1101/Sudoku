import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class BetaVersion_Sudoku
{
    private String[][] board = new String[9][9];
    
    /**
     * Get the letter located at the given row and column.
     * @return The letter or letters at the given row and column.
     */
    public String get(int row, int col) {
        if (row<0 || col<0 || row>=board.length || col>=board.length) {
        	// should we throw an exception instead?
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
    
    public boolean validBoard() {
    	for(int i = 0; i < 9; i++) {
    		if(!validRow(i))
    			return false;
    		if(!validColumn(i))
    			return false;
    	}
    	
    	for(int i = 1; i <= 3; i++) {
    		for(int j = 1; j <= 3; j++) {
    			if(!validBox(i,j))
    				return false;
    		}
    	}
    	
    	
    	return true;
    }
    
    public boolean validRow(int row) {
    	Set num = new HashSet<String>();
    	int n = 0;
    	for(String s : board[row]) {   		
    		if(!s.equals(" ")) {
    			num.add(s);
    			n++;
    		}
    	}
    	
    	if(num.size()==n)
    		return true;
    	return false;
    }
    
    public boolean validColumn(int column) {
    	Set num = new HashSet<String>();
    	int n = 0;
    	for(int i = 0; i < 9; i++) { 		
    		if(!board[i][column].equals(" ")) {
    			num.add(board[i][column]);
    			n++;
    		}
    	}
    	if(num.size()==n)
    		return true;
    	return false;
    }

    public boolean validBox(int x, int y) {
    	Set num = new HashSet<String>();
    	int n = 0;
    	for (int r = 0 + 3*(x-1); r < 0 + 3*x; r++) {
            for (int c = 0 + 3*(y-1); c < 0 + 3*y; c++) {
            	num.add(board[r][c]);
    			n++;
            }
    	}
    	
    	if(num.size()==n)
    		return true;
    	return false;
    }
}