
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rough implements KeyListener{
	static JFrame gui;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gui = new JFrame();
		
		
		gui.setTitle("My first gui application");
		gui.setResizable(false);
		gui.setSize(500, 500);
		gui.setDefaultCloseOperation(gui.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
		
	}
	
	public Rough() {
		gui.addKeyListener(this);
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i  = e.getKeyCode();
		if(i == KeyEvent.VK_A) {
			System.out.println("a");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int i  = e.getKeyCode();
		if(i == KeyEvent.VK_A) {
			System.out.println("a");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int i  = 65;
		if(i == KeyEvent.VK_A) {
			System.out.println("a");
		}
	}

	

}
