import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH =900;
	static final int SCREEN_HEIGHT=750;
	//the size of things(EVERTHING)
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS =(SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
	static  int DELAY= 175;
	
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	
	int bodyParts ;//= 6;
	int applesEaten;
	
	// where the apple is located
	int appleX;
	int appleY;
	
	// in which direction the snake starts
	char direction;// = 'R';
	
	boolean running = false;
	
	Timer timer;
	
	Random random;
	
	//for pause&resume 
	static boolean gameOn = false;
	
	//for reset button
	boolean text = true;
	JButton easy;
	JButton medium;
	JButton hard;
	JButton insane;
	
	//JButton startButton;
	

	GamePanel(){
		
        random = new Random();
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		/*start button
		startButton = new JButton("Play");
		this.add(startButton);
		startButton.setSize(100,50);
		startButton.setLocation(0,200);
		startButton.addActionListener(this);
		*/
		//for resume button / modes buttons
		easy   = new JButton("Easy");
		medium = new JButton("Medium");
		hard   = new JButton("Hard");
		insane = new JButton("Insane");
		
		this.add(easy);
		this.add(medium);
		this.add(hard);
		this.add(insane);
		
		easy.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DELAY = 175;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hard.hide();
				insane.hide();
			}
			
		});
		
		medium.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DELAY = 150;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hard.hide();
				insane.hide();
			}
			
		});
		
		hard.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DELAY = 100;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hard.hide();
				insane.hide();
			}
			
		});
		
		insane.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DELAY = 75;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hard.hide();
				insane.hide();
			}
			
		});

		
		startGame();

	}
	public void startGame() {
		
		
		newApple();
		
		running = true;
		
		timer = new Timer(DELAY,this);
		timer.start();
		
		bodyParts = 6;
		direction = 'R';
		
		repaint();
		

	}
	//pause& resume by pressing Space
	public void pause() {
		
		GamePanel.gameOn = true;
		timer.stop();
	}
	public void resume() {
		
		GamePanel.gameOn = false;
		timer.start();
	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		draw(g);
	}
	public void draw(Graphics g) {
		
		if (running) {

		/*//the grid in the background
				for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE;i++) {

					g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
					g.drawLine( 0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE );

				}*/
				//the apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
	
				//the snake
			for(int i = 0; i<bodyParts;i++) {
				if(i == 0) {
					
					g.setColor(Color.green);
					g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
					
				}else {
					
					g.setColor(new Color(45,180,0));
				//the snake color
				//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
			}
		}
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: "+ applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/5,g.getFont().getSize());
		}else if(!text){
			
			gameOver(g);
		}
		
	}
	public void newApple() {

		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	
	}
	public void move() {
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;	
			
		}
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			if(DELAY == 175) {
				applesEaten++;
			}else if(DELAY == 150) {
				applesEaten++;
			}else if(DELAY == 100) {
				applesEaten++;
			}else if(DELAY == 75) {
				applesEaten++;
			}
			//applesEaten++;
			newApple(); 
		}

	}
	public void checkCollisions() {
		//checks if the head collides with body
				for(int i = bodyParts;i>0;i--) {
					if((x[0] == x[i]) && (y[0] == y[i])) {
						running = false;
					}
				}
				//check if head touches left border
				if(x[0] < 0) {
					running = false;
					
				}
				//check if head touches right border
				if(x[0] >SCREEN_WIDTH) {
					running = false;
							
				}
				//check if head touches top border
				if(y[0] < 0) {
					running = false;
					
				}
				//check if head touches bottom border
				if(y[0] > SCREEN_HEIGHT) {
					running = false;
					
				}
				if(!running && !text) {
					timer.stop();
				}
			}

	
	public void gameOver(Graphics g) {
		//Game Over text
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		//Score
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());
		
		//Restart/Play
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("Press Space to  play",(SCREEN_WIDTH - metrics3.stringWidth("Press Space to play"))/2, SCREEN_HEIGHT/2 + 120);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(running) {
			
			move();
			checkApple();
			checkCollisions();

		}
		repaint();

	}

	public class MyKeyAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			//if(state == STATE.GAME) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_P:
				if(GamePanel.gameOn) {
					resume();
				}else {
					pause();
				}
				break;
			
			}
			
			if(!running && !text) {
				if(e.getKeyChar() == KeyEvent.VK_SPACE) {
					startGame();
					for(int i = bodyParts; i > 0 ;i--) {
						x[i] = bodyParts *- 1;
						y[i] = 0;
					}
					x[0] = 0;
					y[0] = 0;
					repaint();
					applesEaten = 0;
				}
			}
		}
		
	}

}
