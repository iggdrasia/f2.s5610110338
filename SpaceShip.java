package f2.s5610110338;
import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.PINK);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction_X, int direction_Y){
		x += (step * direction_X);
		y += (step * direction_Y);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
		if(y < 0)
			y = 0;
		if(y > 600 - width)
			y = 600 - width;
	}

}
