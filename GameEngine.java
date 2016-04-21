package f2.s5610110338;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.Timer;


public class GameEngine{
	GamePanel gp;
	
	private SpaceShip v;	
	
	private Timer timer;
	
	Random randomInrange = new Random();

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	private void process(){
		gp.updateGameUI();
	}
	
}
