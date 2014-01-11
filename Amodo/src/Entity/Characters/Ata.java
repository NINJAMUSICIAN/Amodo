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

public class Ata extends MapObject{

	private int health;
	private int maxHealth;
	private TileMap tiley;
	
	private boolean dead;
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			1, 16, 1, 1
	};
	
	private HashMap<String, AudioPlayer> sfx;
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	
	
	
	public Ata(TileMap tm, boolean righting){
		super(tm);
		this.tiley = tm;
		
		width = 22;
		height = 53;
		cwidth = 22;
		cheight = 53;
		
		moveSpeed = 0.8;
		maxSpeed = 2.0;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -9;//-5.8
		stopJumpSpeed = 0.3;
		
		facingRight = righting;
		
		health = setMaxHealth(1);
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/Sprites/RaizelSheet.png"));
			
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
 					j * 30,
 					i * height, 
 					30,
 					height);
 				}else if(i == 2){
 					bi[j] = spritesheet.getSubimage(
 		 					j * 31,
 		 					i * height, 
 		 					31,
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
		sfx.put("jump", new AudioPlayer("/Audio/FX/Jumping/AtaJump.mp3"));
		
	}
	
	public int getHealth() {return health;}
	public boolean isDead(){return dead;}
	public void kill(){dead = true;}
	public void revive(){dead = false;}
	
	public void setDx(double x){
		dx = x;
	}
	
	public void setDy(double y){
		dy = y;
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
	
	public void reset(){
		health = setMaxHealth(1);
	}
	
	public boolean fellDead(){
		return dead;
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
	
	//System.out.println(currentAction);
	
	if(dy > 0){
		if(currentAction != FALLING){
			currentAction = FALLING;
			animation.setFrames(sprites.get(FALLING));
			animation.setDelay(100);
			cwidth = 30;
			width = 30;
		}
	}else if(dy < 0){
		if(currentAction != JUMPING){
			currentAction = JUMPING;
			animation.setFrames(sprites.get(JUMPING));
			animation.setDelay(100);
			
			cwidth = 31;
			width = 31;
		}
	}else if(right || left){
		if(currentAction != WALKING){
			currentAction = WALKING;
			animation.setFrames(sprites.get(WALKING));
			animation.setDelay(80);
			cwidth = 30;
			width = 30;
			}
		}else{
			if(currentAction != IDLE){
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				cwidth = 22;
				width = 22;
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
