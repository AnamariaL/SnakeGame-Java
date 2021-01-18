import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameMenu {
	
	final int x[] = new int[GamePanel.GAME_UNITS];
	final int y[] = new int[GamePanel.GAME_UNITS];
	
	public Rectangle playButton = new Rectangle(GamePanel.SCREEN_WIDTH / 3 + 120, 150,100,50);
	public Rectangle quitButton = new Rectangle(GamePanel.SCREEN_WIDTH / 3 + 120, 250,100,50);
		
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		g.drawString("Snake Game", GamePanel.SCREEN_WIDTH /3 ,100 );
		
		g.setColor(Color.cyan);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		g.drawString("Play", playButton.x + 5 ,playButton.y + 31 );
		g2d.draw(playButton);
	
		g.drawString("Quit", quitButton.x + 5 ,quitButton.y + 31 );
		g2d.draw(quitButton);
		
	}

}
