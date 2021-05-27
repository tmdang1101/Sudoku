import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Sudoku sudoku;
		try {
			sudoku = new Sudoku("BasicPuzzle.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
