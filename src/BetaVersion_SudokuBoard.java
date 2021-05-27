import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BetaVersion_SudokuBoard extends JFrame
{
	private BetaVersion_Sudoku sudoku;
	
	private static final long serialVersionUID = 1L;
	
	protected final int MARGIN_SIZE = 10;
    protected final int DOUBLE_MARGIN_SIZE = MARGIN_SIZE*2;
    protected int squareSize = 50;
    private int numRows = 9;
    private int numCols = 9;
    
    private int width = DOUBLE_MARGIN_SIZE + squareSize * numCols;    		
    private int height = DOUBLE_MARGIN_SIZE + squareSize * numRows;    		
    
    private JPanel canvas;
    private JMenuBar menuBar;
    
	private void drawGrid(Graphics2D g) {
		g.setColor(Color.BLACK);
		Font font = new Font("Verdana", Font.BOLD, 25);
		g.setFont(font);

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
            	int x = c * squareSize + MARGIN_SIZE;
            	int y = r * squareSize + MARGIN_SIZE;
            	
            	g.drawString(sudoku.get(r, c), 
                		x + squareSize/3, 
                		y + 2*squareSize/3);
           
                g.drawRect(x, y, squareSize, squareSize); 
            }
        }
        
        for (int r = 0; r < numRows; r+=3) {
            for (int c = 0; c < numCols; c+=3) {
            	int x = c * squareSize + MARGIN_SIZE;
            	int y = r * squareSize + MARGIN_SIZE;
            	
            	int thickness = 3;
            	Stroke oldStroke = g.getStroke();
            	g.setStroke(new BasicStroke(thickness));
            	g.drawRect(x, y, squareSize*3, squareSize*3);
            	g.setStroke(oldStroke);
            }
        }
    }
    
    private void createMenuBar() {
    	menuBar = new JMenuBar();
        JMenu menu = new JMenu("Actions");
        menuBar.add(menu);
        
        addToMenu(menu, "New Game", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        this.setJMenuBar(menuBar);
    }
    
    private void addToMenu(JMenu menu, String title, ActionListener listener) {
    	JMenuItem menuItem = new JMenuItem(title);
    	menu.add(menuItem);
    	menuItem.addActionListener(listener);
    }
    
    private void createKeyboardHandlers() {
    	this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO: key machine where you keep sort of walking down the Trie
				// if you hit a word, it highlights it
				// if you hit enter after typing a word, it will add to your score
				// can also do the breakdown of words of different lengths
				// could also track things you type that aren't words
				System.out.println(e.getKeyChar());
			}
		});
    }
    
    private void createMouseHandlers() {
    	// TODO create mouse handlers
    }
    
    public BetaVersion_SudokuBoard() {
    	
    	try {
    		sudoku = new BetaVersion_Sudoku("EasyPuzzle.txt");
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    	
        setTitle("Sudoku");

        JFrame frame = this;
        
        canvas = new JPanel() {
        	 /* (non-Javadoc)
             * @see javax.swing.JComponent#getMinimumSize()
             */
            public Dimension getMinimumSize() {
                return new Dimension(width, height);
            }
            
            /* (non-Javadoc)
             * @see javax.swing.JComponent#getMaximumSize()
             */
            public Dimension getMaximumSize() {
                return new Dimension(width, height);
            }
            
            /* (non-Javadoc)
             * @see javax.swing.JComponent#getPreferredSize()
             */
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
            
            /* (non-Javadoc)
             * @see java.awt.Component#isFocusable()
             */
            public boolean isFocusable() {
                return true;
            }

			@Override
        	public void paint(Graphics graphics) {
        		Graphics2D g = (Graphics2D)graphics;

        		drawGrid(g);

        		//frame.setPreferredSize(new Dimension(numRows*squareSize + MARGIN_SIZE, numCols*squareSize + MARGIN_SIZE));
        		setPreferredSize(new Dimension((numCols+2)*squareSize + 2*MARGIN_SIZE, (numRows+2)*squareSize + 2*MARGIN_SIZE));
        		frame.pack();
        	}
        };
        
        this.getContentPane().add(canvas, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
        this.setLocation(100,100);
        this.setFocusable(true);
        
        createMenuBar();
        createKeyboardHandlers();
        createMouseHandlers();
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        
        repaint();
        this.setVisible(true);
    }
   
}