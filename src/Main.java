import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Sudoku sudoku;
		try {
    		sudoku = new Sudoku("BasicPuzzle.txt");
        } catch (IOException e) {
        	// no way to recover from this, so just repackage as runtime exception
        	throw new RuntimeException(e);
        }
	}
}
