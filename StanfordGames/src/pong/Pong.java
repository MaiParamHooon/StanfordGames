package pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong implements ActionListener, KeyListener{
	
	public static Pong pong;
	public int width = 700, height = 700;
	public Renderer renderer;
	public Paddle player1;
	public Paddle player2;
	public boolean bot = false, w, s, up, down, selectingDiff;
	public Dimension dim;
	public Ball ball;
	public int gameStatus = 0; //0=Menu, 1=Paused, 2=Playing, 3=Over
	public Random random;
	public int botMoves, botCoolDown = 0, botDiff, scoreLimit=7, playerWon;
	
	
	public Pong(){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Timer timer = new Timer(20, this);
		random = new Random();
		JFrame jframe = new JFrame("Pong");
		renderer = new Renderer();
		jframe.setSize(width, height);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(renderer);
		jframe.setResizable(false);
		jframe.addKeyListener(this);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		timer.start();
		jframe.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pong = new Pong();
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(gameStatus == 0) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Pong",  width/2 - 40, 30);
			
			if(!selectingDiff) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Press Spacebar to Play",  width/2 - 160, height/2 - 45);
			g.drawString("Press Shift to Play with Bot",  width/2 - 190, height/2 - 5);
			g.drawString("Score Limit: " + scoreLimit,  width/2 - 100, height/2 + 35);
			}
		}
		
		if(selectingDiff) {
			String s = botDiff == 0 ? "Easy" : (botDiff == 1 ? "Medium" : "Hard");
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Bot Difficulty : " + s,  width/2 - 150, height/2 - 45);
			g.drawString("Press Spacebar to Play",  width/2 - 180, height/2 - 10);
		}
		if(gameStatus == 1) {
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Paused",  width/2 - 55, height / 2 - 25);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString(String.valueOf(player1.score),  width/2 - 45, 30);
			g.drawString(String.valueOf(player2.score),  width/2 + 25, 30);
			
		}
		
		if(gameStatus == 2) {
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(5f));
			g.drawLine(width/2, 0, width/2, height);
			g.drawOval(width/2 - 200, height/2 - 200, 400, 400);
			
			g.setFont(new Font("Arial", 1, 30));
			g.drawString(String.valueOf(player1.score),  width/2 - 45, 30);
			g.drawString(String.valueOf(player2.score),  width/2 + 25, 30);
			
			
			player1.render(g);
			player2.render(g);
			ball.render(g);
		}
		
		if(gameStatus == 3) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Pong",  width/2 - 40, 30);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Player " + playerWon + " Won",  width/2 - 90, 80);
			
			g.setColor(Color.PINK);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Press Spacebar to Play Again",  width/2 - 190, height/2 - 45);
			g.drawString("Press Escape for Menu",  width/2 - 160, height/2 - 5);
			
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(gameStatus == 2) update();
		renderer.repaint();
	
	}

	private void update() {
		// TODO Auto-generated method stub
		if(player1.score>=scoreLimit) {
			playerWon = 1;
			gameStatus = 3;
		}
		if(player2.score>=scoreLimit) {
			playerWon = 2;
			gameStatus = 3;
		}
		if(w) {
			player1.move(true);
		}
		if(s) {
			player1.move(false);
		}
		
		if(!bot) {
			if(up) {
				player2.move(true);
			}
			if(down) {
				player2.move(false);
			}
		
		}else {
			if(botCoolDown>0) {
				botCoolDown--;
				if(botCoolDown == 0) {
					botMoves = 0;
				}
			}
			if(botMoves<10) {
				
				if(player2.y + player2.height/2< ball.y) {
					player2.move(false);
					botMoves++;
				}
				if(player2.y + player2.height / 2 > ball.y) {
					player2.move(true);
					botMoves++;
				}
				if(botDiff == 0) {
					botCoolDown = 20;
				}
				if(botDiff == 1) {
					botCoolDown = 10;
				}
				if(botDiff == 2) {
					botCoolDown = 1;
				}		
			}

		}
		
		ball.update(player1, player2);
	}
	
	public void start() {
		gameStatus = 2;
		player1 = new Paddle(this, 1);
		player2 = new Paddle(this, 2);
		ball = new Ball(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int id = e.getKeyCode();
		if(id == KeyEvent.VK_W) {
			w = true;
		}
		if(id == KeyEvent.VK_S) {
			s = true;
		}
		if(id == KeyEvent.VK_UP) {
			up = true;
		}
		if(id == KeyEvent.VK_DOWN) {
			down = true;
		}
		if(id == KeyEvent.VK_RIGHT) {
			if(selectingDiff) {
				if(botDiff<2)
					botDiff++;
				else botDiff = 0;
			}else if(gameStatus == 0 && scoreLimit<20) {
				scoreLimit++;
			}
		}
		if(id == KeyEvent.VK_LEFT) {
			if(selectingDiff) {
				if(botDiff>0)
					botDiff--;
				else botDiff = 2;
			} else if(gameStatus == 0 && scoreLimit>1) {
				scoreLimit--;
			}
		}
		
		if(id == KeyEvent.VK_SHIFT && gameStatus == 0) {
			bot = true;
			selectingDiff = true;
		}
		if(id == KeyEvent.VK_ESCAPE && (gameStatus == 2 || gameStatus == 3)) {
			gameStatus = 0;
		}
		if(id == KeyEvent.VK_SPACE) {
			
			if(gameStatus == 0 || gameStatus == 3) {
				if(!selectingDiff)
					bot = false;
				else
					selectingDiff = false;
				start();
			}else if(gameStatus == 1) {
				gameStatus = 2;
			}else if(gameStatus == 2) {
				gameStatus = 1;
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int id = e.getKeyCode();
		if(id == KeyEvent.VK_W) {
			w = false;
		}
		if(id == KeyEvent.VK_S) {
			s = false;
		}
		if(id == KeyEvent.VK_UP) {
			up = false;
		}
		if(id == KeyEvent.VK_DOWN) {
			down = false;
		}
				
	}
}
