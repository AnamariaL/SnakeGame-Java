import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		/*
		 * public Rectangle playButton = new Rectangle(SCREEN_WIDTH / 2 + 120, 150,100,50);
		 * public Rectangle quitButton = new Rectangle(SCREEN_WIDTH / 2 + 120, 250,100,50);
		 * */
		//playbutton
		if(mx >= GamePanel.SCREEN_WIDTH /2 +120 && mx <= GamePanel.SCREEN_WIDTH /2 +220) {
			if(my >= 150 && my <= 200) {
				//press play button
				GamePanel.state= GamePanel.state.GAME;
				
			}
		}
		//quitutton
				if(mx >= GamePanel.SCREEN_WIDTH /2 +120 && mx <= GamePanel.SCREEN_WIDTH /2 +220) {
					if(my >= 350 && my <= 400) {
						//press play button
						System.exit(1);
						
					}
				}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
