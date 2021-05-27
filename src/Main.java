import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Sudoku sudoku;
		try {
			sudoku = new Sudoku("EasyPuzzle.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
