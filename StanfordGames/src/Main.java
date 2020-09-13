import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Main{
	
	public static void main(String[] args) {
		JFrame gui = new JFrame();
//		JButton east = new JButton("east");
//		ButtonEvt eastBtn = new ButtonEvt("East button clicked");
//		east.addActionListener(eastBtn);
//		
//		
//		JButton west = new JButton("west");
//		west.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("West Button clicked");
//			}
//			
//		});
//		JButton center = new JButton("center");
//		JButton south = new JButton("south");
//		JButton north = new JButton("north");
//		
//		gui.add(east, BorderLayout.EAST);
//		gui.add(west, BorderLayout.WEST);
//		gui.add(center, BorderLayout.CENTER);
//		gui.add(south, BorderLayout.SOUTH);
//		gui.add(north, BorderLayout.NORTH);
		
		Container pane = gui.getContentPane();
//		pane.setBackground(new Color(0,0,0));
//		pane.setLayout(new GridLayout(3,3,10,0));
		pane.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		pane.add(new JButton("1"));
		pane.add(new JButton("2"));
		pane.add(new JButton("3"));
		
		
		
		gui.setTitle("My first gui application");
		gui.setResizable(true);
		gui.setSize(500, 500);
		gui.setDefaultCloseOperation(gui.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
		
	}

}
