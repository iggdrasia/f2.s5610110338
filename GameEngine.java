package f2.s5610110338;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.Timer;


public class GameEngine implements KeyListener{
	
	GamePanel gp;	
	private ArrayList<Enemy01> enemies01 = new ArrayList<Enemy01>();	
	private ArrayList<Enemy02> enemies02 = new ArrayList<Enemy02>();	
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();	
	private Timer timer;
	private double difficulty = 0.1;
	private SpaceShip v;
	private int bullet_delay = 0;
	Random randomBullet = new Random();

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

	private void generateBullet(){
		Bullet b = new Bullet( (v.x + 6), v.y);
		gp.sprites.add(b);
		bullet.add(b);
	}

	private void generateEnemy01(){
		Enemy01 e01 = new Enemy01((int)(Math.random()*390), 0);
		gp.sprites.add(e01);
		enemies01.add(e01);
	}

	private void generateEnemy02(){
		Enemy02 e02 = new Enemy02(0, randomBullet.nextInt(550 - 50) + 50);
		gp.sprites.add(e02);
		enemies02.add(e02);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy01();
			generateEnemy02();
		}
		
		if(bullet_delay % 5 == 0)
			generateBullet();

		Iterator<Bullet> b_iter = bullet.iterator();
		while(b_iter.hasNext()){
			Bullet bu = b_iter.next();
			bu.proceed();
			
			if(!bu.isAlive()){
				b_iter.remove();
				gp.sprites.remove(bu);
			}
		}
		
		Iterator<Enemy01> e_iter01 = enemies01.iterator();
		while(e_iter01.hasNext()){
			Enemy01 e1 = e_iter01.next();
			e1.proceed();
			
			if(!e1.isAlive()){
				e_iter01.remove();
				gp.sprites.remove(e1);
			}
		}

		Iterator<Enemy02> e_iter02 = enemies02.iterator();
		while(e_iter02.hasNext()){
			Enemy02 e2 = e_iter02.next();
			e2.proceed();
			
			if(!e2.isAlive()){
				e_iter02.remove();
				gp.sprites.remove(e2);
			}
		}
		
		gp.updateGameUI();
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er01, er02, bul;

		for(Enemy01 e : enemies01){
			for(Bullet b : bullet){
				bul = b.getRectangle();
				er01 = e.getRectangle();
				if(er01.intersects(vr)){
					die();
					return;
				}
				if(er01.intersects(bul)){
					e.alive = false;
					return;
				}
			}
		}

		for(Enemy02 e : enemies02){
			for(Bullet b : bullet){
				bul = b.getRectangle();
				er02 = e.getRectangle();
				if(er02.intersects(vr)){
					die();
					return;
				}
				if(er02.intersects(bul)){
					e.alive = false;
					return;
				}
			}
		}

		bullet_delay++;
	}
	
	public void die(){
		timer.stop();
	}

	public void Gen_Bullet(){
		timer.stop();
	}
	
	public void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;
		case KeyEvent.VK_UP:
			v.move(0,-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move(0,1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
