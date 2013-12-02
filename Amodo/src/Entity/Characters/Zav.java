package Entity.Characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;

public class Zav extends MapObject{

	private int health;
	private int maxHealth;
	private TileMap tiley;
	
	private double bounceSpeed = -6.4; //how high other person bounces
	
	private boolean dead;
	
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			4, 4, 1, 1
	};
	
	private HashMap<String, AudioPlayer> sfx;
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	
	 
	
	
	
	public Zav(TileMap tm, boolean righting){
		super(tm);
		this.tiley = tm;
		
		width = 33;
		height = 55;
		cwidth = 33;
		cheight = 55;
		
		moveSpeed = 0.7;
		maxSpeed = 2.0;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -5.8;
		stopJumpSpeed = 0.3;
		
		facingRight = righting;
		
		health = setMaxHealth(1);
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/Sprites/ZavSheet.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 4; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++){
 				if(i == 0){
 					bi[j] = spritesheet.getSubimage(
 							j* width,
 							i * height,
 							width,
 							height);
 				} else if(i == 1 || i == 3){
 					bi[j] = spritesheet.getSubimage(
 					j * 33,
 					i * height, 
 					33,
 					height);
 				}else if(i == 2){
 					bi[j] = spritesheet.getSubimage(
 		 					j * 33,
 		 					i * height, 
 		 					33,
 		 					height);
 				}
 			}
				sprites.add(bi);
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("jump", new AudioPlayer("/Audio/FX/Jumping/ZavJump.mp3"));
		sfx.put("bounce", new AudioPlayer("/Audio/FX/Jumping/DoubleBounce.mp3"));
		///sfx.put("scratch		
	}
	
	public int getHealth() {return health;}
	public boolean isDead(){return dead;}
	public void kill(){dead = true;}
	public void revive(){dead = false;}
	
	public void setDx(double x){
		dx = x;
	}
	
	public void setCurrentAction(int action){
		currentAction = action;
	}
	public int getCurrentAction(){
		return currentAction;
	}

	public void checkFallDead(){
		if(y > tiley.getHeight() - 70){
			dead = true;
		} else {
			dead = false;
		}
	}
	
	public boolean fellDead(){
		return dead;
	}
	
	public boolean checkCatch(Rae rae){
		
		boolean caught = false;
		
		if(rae.getx() - 10 < getx() && (rae.getx() + rae.getCWidth() + 10) > (getx() + getCWidth())){ 
			if(rae.gety() + rae.getCHeight() <= gety() &&
					rae.gety() + rae.getCHeight() >= gety() - 5){
				if(rae.getCurrentAction() == FALLING){
						
					sfx.get("bounce").play();
					rae.setDy(bounceSpeed);
				}
						
				caught = true;
			}
			
		}
		//System.out.println(caught);
		return caught;
		
	}
	public boolean checkCatch(Ji ji){
		
		boolean caught = false;
		
		if(ji.getx() - 10 < getx() && (ji.getx() + ji.getCWidth() + 10) > (getx() + getCWidth())){ 
			if(ji.gety() + ji.getCHeight() <= gety() &&
					ji.gety() + ji.getCHeight() >= gety() - 5){
				if(ji.getCurrentAction() == FALLING){
						
					sfx.get("bounce").play();
					ji.setDy(bounceSpeed);
				}
						
				caught = true;
			}
			
		}
		//System.out.println(caught);
		return caught;
		
	}
	
	
	public void reset(){
		health = setMaxHealth(1);
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		if(jumping && !falling) {
			sfx.get("jump").play();
			dy = jumpStart;
			falling = true;	
			
		}
		
if(falling) {
			
			dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
	}

	public void update(){
	getNextPosition();
	checkTileMapCollision();
	checkFallDead();
	setPosition(xtemp, ytemp);
	
	if(dy > 0){
		if(currentAction != FALLING){
			currentAction = FALLING;
			animation.setFrames(sprites.get(FALLING));
			animation.setDelay(100);
			cwidth = 33;
			width = 33;
		}
	}else if(dy < 0){
		if(currentAction != JUMPING){
			currentAction = JUMPING;
			animation.setFrames(sprites.get(JUMPING));
			animation.setDelay(100);
			
			cwidth = 33;
			width = 33;
		}
	}else if(right || left){
		if(currentAction != WALKING){
			currentAction = WALKING;
			animation.setFrames(sprites.get(WALKING));
			animation.setDelay(80);
			cwidth = 33;
			width = 33;
			}
		}else{
			if(currentAction != IDLE){
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				cwidth = 33;
				width = 33;
			}
		}
	animation.update();
	
	if(right) facingRight = true;
	if(left) facingRight = false;
	}

	public void draw(Graphics2D g){
		setMapPosition();
		
		super.draw(g);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		return maxHealth;
	}
	
}
