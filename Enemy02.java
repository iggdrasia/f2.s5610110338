package f2.s5610110338;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy02 extends Enemy{
	
	public static final int X_TO_DIE = 400;

	public Enemy02(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 10, 5);
		
	}
	
	@Override
	public void proceed(){
		x += step;
		if(x > X_TO_DIE){
			alive = false;
		}
	}

}

