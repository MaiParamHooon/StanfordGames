package ticTacToe;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TTT extends JFrame{

	private Container pane;
	private String currentPlayer;
	private JButton [][] board;
	private boolean hasWinner;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem newGame;
	private int i=0;
	
	public TTT() {
		super();
		pane = getContentPane();
		pane.setLayout(new GridLayout(3,3));
		currentPlayer = "x";
		board = new JButton[3][3];
		hasWinner = false;
		intializeBoard();
		intializeMenuBar();
		setTitle("Tic Tac Toe");
		setSize(500, 500);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

	private void intializeMenuBar() {
		// TODO Auto-generated method stub
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetBoard();
			}
		});
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		menu.add(newGame);
		menu.add(quit);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

	private void intializeBoard() {
		// TODO Auto-generated method stub
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				JButton btn = new JButton();
				btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
				board[i][j] = btn;
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					if(((JButton)e.getSource()).getText().contentEquals("") && hasWinner == false) {
						btn.setText(currentPlayer);
						hasWinner();
						togglePlayer();
						}
					
					}
					
				});
				pane.add(btn);
			}
		}
	}
	
	private void resetBoard() {
		currentPlayer = "x";
		hasWinner = false;
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				board[i][j].setText("");
			}
		}
	}
	
	private void togglePlayer() {
		if(currentPlayer.equals("x")) {
			currentPlayer = "o";
		} else {
			currentPlayer = "x";
		}
	}
	
	private void hasWinner() {
		i++;
		if(board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer) && board[0][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
			hasWinner = true;
		} else if(i == 9) {
			JOptionPane.showMessageDialog(null, "draw");
			resetBoard();
		}
	}
	
	
}
