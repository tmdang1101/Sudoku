import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

public class Sudoku {
	private String[][] board = new String[9][9];
	private LinkedList<JButton> buttons = new LinkedList<JButton>();
	private String newValue = " ";
	private JFrame frame = new JFrame("Sudoku");
	private JFrame keys;
	
	
	public Sudoku(String filename) 
			throws IOException
	{
		frame.setLayout(new BorderLayout());
		
		//BOARDGAME
		JPanel game = new JPanel(new GridLayout(9,9));
		Scanner scan = new Scanner(new FileInputStream(filename));
    	for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
            	String n = scan.next();
            	if(n.equals("_"))
            		n = " ";
            	board[r][c] = n;
            	JButton button = new JButton(n + "");
            	
            	class BoardListener implements ActionListener {
            		public void actionPerformed(ActionEvent e) {
            			button.setText(newValue);
            			updateBoard();
            			if(!validBoard()) {
            				errorMessage();
            				button.setText(" ");
            				updateBoard();
            			}
            			if(finishedBoard())
            				victoryMessage();
            		}
            	}
            	
            	button.addActionListener(new BoardListener());
            	game.add(button);
            	
            	buttons.add(button);
            }
    	}
		frame.add(game, BorderLayout.CENTER);
		
		//SET UP
		JButton quit = new JButton("Quit");
		frame.add(quit, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
		createKeyBoard();
		
		quit.addActionListener(new QuitListener());
	}
	
	private class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public void createKeyBoard() {
		//KEYS
		keys = new JFrame("Keys");
		keys.setLayout(new GridLayout(3,3));
		for(int i = 1; i <= 9; i++) {
			JButton button = new JButton(i + "");
				
			class KeyListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					newValue = button.getText();
				}
			}					
				
			button.addActionListener(new KeyListener());
			keys.add(button);
		}
		
		keys.pack();
		keys.setVisible(true);
	}
	
	public void errorMessage() {
		JFrame error = new JFrame("Wrong Answer!");
		JLabel message = new JLabel("Wrong Answer!");
		error.add(message);
		error.setVisible(true);
	}
	
	public void victoryMessage() {
		JFrame victory = new JFrame("Puzzle Done!");
		JLabel message = new JLabel("Puzzle Done!");
		victory.add(message);
		victory.setVisible(true);
	}
	
	
	//Check for valid Sudoku board
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
            	if(!board[r][c].equals(" ")) {
        			num.add(board[r][c]);
        			n++;
        		}
            }
    	}
    	
    	if(num.size()==n)
    		return true;
    	return false;
    }
    
    
    //Update board after each answer
    public void updateBoard() {
    	Iterator<JButton> it = buttons.iterator();
    	for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
            	board[r][c] = it.next().getText();
            }
    	}
    }
    
    //Check for a finished puzzle
    public boolean finishedBoard() {
    	int count = 0;
    	
    	Iterator<JButton> it = buttons.iterator();
    	while(it.hasNext()) {
    		if(it.next().getText().equals(" "))
				count++;
    	}
    	
    	if(count==0 && validBoard())
    		return true;
    	return false;
    }

}