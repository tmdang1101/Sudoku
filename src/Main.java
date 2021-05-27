import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Sudoku sudoku;
		try {
			sudoku = new Sudoku("EasyPuzzle.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//System.out.println(sudoku.validBoard());
		System.out.println(sudoku.validBox(1,1));
		System.out.println(sudoku.validBox(1,2));
		System.out.println(sudoku.validBox(1,3));
		System.out.println(sudoku.validBox(2,1));
		System.out.println(sudoku.validBox(2,2));
		System.out.println(sudoku.validBox(2,3));
		System.out.println(sudoku.validBox(3,1));
		System.out.println(sudoku.validBox(3,2));
		System.out.println(sudoku.validBox(3,3));
	}
}
