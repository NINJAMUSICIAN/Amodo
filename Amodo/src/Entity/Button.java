package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import TileMap.TileMap;

public class Button extends MapObject{

	private int done = 0;
	private int wallX, wallY;
	private String what;
	private Wall wall;
	private TileMap tileMap;
	
	private ArrayList<BufferedImage[]> sprites;
	
	private HashMap<String, AudioPlayer> sfx;
	
	private static final int UPNOT = 0;
	private static final int UPIS = 1;
	private static final int SIDENOT = 2;
	private static final int SIDEIS = 3;
	private static final int DOWNNOT = 4;
	private static final int DOWNIS = 5;
	
	public Button(TileMap tm, String dir, String what, int wallX, int wallY) {
		super(tm);
		
		this.tileMap = tm;
		
		this.what = what;
		this.wallX = wallX;
		this.wallY = wallY;
		
		width = 32;
		height = 32;
		if(dir.equalsIgnoreCase("up") || dir.equalsIgnoreCase("down")){
			cwidth = 32;
			cheight = 25;	
		}else if(dir.equalsIgnoreCase("right") || dir.equalsIgnoreCase("left")){
			cwidth = 25;
			cheight = 32;
		}
		
		moveSpeed = 0;
		maxSpeed = 0;
		stopSpeed = 0;
		fallSpeed = 0.0;
		maxFallSpeed = 0;
		jumpStart = 0;
		stopJumpSpeed = 0;
		
		if(dir.equalsIgnoreCase("left")){
			facingRight = false;
		}else{
			facingRight = true;
		}
		
		
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Tilesets/Button.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 6; i++){
				BufferedImage[] bi = new BufferedImage[1];
				for(int j = 0; j < 1; j++){
					bi[j] = spritesheet.getSubimage(0, i * height, width, height);
				}
				sprites.add(bi);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		if(dir.equalsIgnoreCase("up")){
			currentAction = UPNOT;
			animation.setFrames(sprites.get(UPNOT));
		}else if(dir.equalsIgnoreCase("down")){
			currentAction = DOWNNOT;
			animation.setFrames(sprites.get(DOWNNOT));
		}else{
			currentAction = SIDENOT;
			animation.setFrames(sprites.get(SIDENOT));
		}
		animation.setDelay(400);
		
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("down", new AudioPlayer("/Audio/FX/ButtonPress.mp3"));
		
	}
	
	public Button(TileMap tm, String dir, String what, Wall wall) {
		super(tm);
		
		this.tileMap = tm;
		this.wall = wall;
		this.what = what;
		
		width = 32;
		height = 32;
		if(dir.equalsIgnoreCase("up") || dir.equalsIgnoreCase("down")){
			cwidth = 32;
			cheight = 25;	
		}else if(dir.equalsIgnoreCase("right") || dir.equalsIgnoreCase("left")){
			cwidth = 25;
			cheight = 32;
		}
		
		moveSpeed = 0;
		maxSpeed = 0;
		stopSpeed = 0;
		fallSpeed = 0.0;
		maxFallSpeed = 0;
		jumpStart = 0;
		stopJumpSpeed = 0;
		
		if(dir.equalsIgnoreCase("left")){
			facingRight = false;
		}else{
			facingRight = true;
		}
		
		
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Tilesets/Button.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 6; i++){
				BufferedImage[] bi = new BufferedImage[1];
				for(int j = 0; j < 1; j++){
					bi[j] = spritesheet.getSubimage(0, i * height, width, height);
				}
				sprites.add(bi);
			}
			
			sfx = new HashMap<String, AudioPlayer>();
			sfx.put("down", new AudioPlayer("/Audio/FX/ButtonPress.mp3"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		if(dir.equalsIgnoreCase("up")){
			currentAction = UPNOT;
			animation.setFrames(sprites.get(UPNOT));
		}else if(dir.equalsIgnoreCase("down")){
			currentAction = DOWNNOT;
			animation.setFrames(sprites.get(DOWNNOT));
		}else{
			currentAction = SIDENOT;
			animation.setFrames(sprites.get(SIDENOT));
		}
		animation.setDelay(400);
		
	}
	
	public void check(){
		if(isDown()){
			
			whatToDo();
		}
	}
	public void whatToDo(){
		if(done == 1){
			
			sfx.get("down").play();
			
			if(what.equalsIgnoreCase("wall 2")){
				makeWall(wallX, wallY, 2);
				
			}else if(what.equalsIgnoreCase("wall 3")){
				makeWall(wallX, wallY, 3);
				
			}else if(what.equalsIgnoreCase("wall one")){
				makeWall(wallX, wallY, 1);
			
			}else if(what.equalsIgnoreCase("break")){
					breakWall(wall);
					
			}else if(what.equalsIgnoreCase("nothing")){
				return;
			
			}else if(what.equalsIgnoreCase("trampoline")){
				makeTrampoline(wallX, wallY);
				
			}else{
				System.out.println("what are you doing?");
			}
		}
	}
	public void makeWall(int x, int y, int height){
			wall = new Wall(height, "vert", "Anything", tileMap);
			wall.setPosition(x, y);
			done = 2;
		}	
	
	public void makeTrampoline(int x, int y){
		wall = new Wall(1, "trampoline", "trampoline", tileMap);
		wall.setPosition(x, y);
		System.out.println("made");
		done = 2;
		
	}
	
	public void breakWall(Wall wall){
		wall.remove(true);
		System.out.println(wall.shouldRemove());
		done = 2;
	}
	
	public void checkPress(MapObject m){
		
		if(currentAction == SIDENOT){
			if(facingRight){
				if(
						m.getx() > x &&
						m.getx() < x + cwidth &&
						m.gety() > y - height / 2 &&
						m.gety() < y + height / 2
						){
					currentAction = SIDEIS;
					done = 1;
					
				}//if
			}else{//facingright
				if(
						   m.getx() < x &&
						   m.getx() > x - width + 18 &&
						   m.gety() > y - height / 2 &&
						   m.gety() < y + height / 2){
					currentAction = SIDEIS;
					done = 1;
				}//if
			}//not facing right
		}
		
		if(currentAction == UPNOT){
			if(
					m.getx() < x + width / 2 &&
					m.getx() > x  - width / 2&&
					m.gety() < y + height &&
					m.gety() + m.getHeight() > y ){
				currentAction = UPIS;
				done = 1;
			}
		}
		
		if(currentAction == DOWNNOT){
			if(
					m.getx() < x + width / 2 &&
					m.getx() > x  - width / 2&&
					m.gety() < y + height &&
					m.gety() > y){
				currentAction = DOWNIS;
				done = 1;
			}
		}
//		System.out.println(m.getx());
//		System.out.println(m.gety());
//		System.out.println(currentAction);
	}
	public boolean isDown(){
		if(currentAction == DOWNIS || currentAction == UPIS || currentAction == SIDEIS){
			return true;
		}else{
			return false;
		}
	}
	
	
	public void updateWall(MapObject m){
		if(done == 2){
			if(what.equalsIgnoreCase("wall 2") || what.equalsIgnoreCase("wall 3") ||
					what.equalsIgnoreCase("wall one") || what.equalsIgnoreCase("trampoline")){
		wall.checkCollision(m);
			}
		}
	}
	public void update(){
		
		if(currentAction == SIDEIS){
			animation.setFrames(sprites.get(SIDEIS));
			animation.setDelay(400);
		}
		
		if(currentAction == UPIS){
			animation.setFrames(sprites.get(UPIS));
			animation.setDelay(400);
		}
		
		if(currentAction == DOWNIS){
			animation.setFrames(sprites.get(DOWNIS));
			animation.setDelay(400);
		}
		
		check();
		animation.update();
	}
	
	
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
		
		if(done == 2){
			if(what.equalsIgnoreCase("wall 2") || what.equalsIgnoreCase("wall 3") ||
					what.equalsIgnoreCase("wall one") || what.equalsIgnoreCase("trampoline")){
			wall.draw(g);
			}
		}	
	}
	
	
}
