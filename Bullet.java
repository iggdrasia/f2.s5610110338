package f2.s5610110338;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	
	public static final int Y_TO_DIE = 0;
	public static final int X_TO_DIE = 400;
	protected int step = 16;
	protected boolean alive = true;

	public Bullet(int x, int y) {
		super(x, y, 9, 16);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);	
	}

	public void proceed(){
		y -= step;
		if(y <= Y_TO_DIE){
			alive = false;
		}
	}

	public boolean isAlive(){
		return alive;
	}

}

