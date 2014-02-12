package GameState;

import Entity.Button;
import Entity.Door;
import Entity.FadeIn;
import Entity.FadeOut;
import Entity.Images;
import Entity.MapObject;
import Entity.Wall;
import Entity.Characters.Ji;
import Entity.Characters.Rae;
import TileMap.Background;
import TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuState extends GameState {
	
	private TileMap tileMap;
	private Background bg;

	private Point jiStart, raeStart;
	private int currentChoice = 0;
	private int ready = 0;
	
	private boolean go = false;
	
	private Ji ji;
	private Rae rae;
	private int moved, musicStarted = 0, switched = 0; // if 0 not setting up if 1 setting up
	private int switchable = 0; //which player in the list you are controlling
	private Door door1, door2, door4, door5, door6, door7, door8, door9, door10, pinkDoor, greenDoor;
	private Button button;
	private Wall wall;
	private Images image1, image2;
	
	private int cursorY = 215;
	private Images cursor;
	
	private int done = 0;
	private FadeIn fadein;
	private FadeOut fadeout;
	
	private long waitTime;

	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		pictures = new ArrayList<Images>();
	
		bg =  new Background("/Menu/Background.png", 0.1);
		bg.setVector(-.5, 0);
		
	tileMap = new TileMap(32);
	tileMap.loadMap("/Maps/Intro"  + ".map");
	tileMap.loadTiles("/Tilesets/grasstileset.png");
	tileMap.setPosition(-0, 0);
	
	cursor = new Images(tileMap, "/Menu/Cursor.png", 11, 12);
	cursor.setPosition(250, cursorY);
	pictures.add(cursor);
	
	fadein = new FadeIn(tileMap, 60);
	fadein.setPosition(320, 240);
	fadeout = new FadeOut(tileMap, 60);
	fadeout.setPosition(320, 240);
	
	players = new ArrayList<MapObject>();

	if(gsm.getCurrentLevel() >= 3){
		ji = new Ji(tileMap, true);
		players.add(ji);
		rae = new Rae(tileMap, true);
		players.add(rae);
		
	}
	
//	bg = new Background("/Backgrounds/cloudbg.png", 0.1);
//	bg.setVector(-0.1, 0);
	tileMap.setPosition(0, 0);
	tileMap.setTween(1);
	
	image1 = new Images(tileMap, "/Menu/RealMenu.png");
	image1.setPosition(320, 240);
	pictures.add(image1);
	}
	//startMusic();}
	
	public void update() {
		handleInput();
		bg.update();
		if(!fadein.isDone()){
			fadein.update();
			}
		
		if(currentChoice == 0){
			cursorY = 215;
		}else{
			cursorY = 275;
		}
		
		cursor.setPosition(250, cursorY);
		if(!fadeout.isDone()){
			fadeout.update();
		}
		
		if(go){
			if(currentChoice == 0) {
				fadeout.go();
				if(fadeout.isDone()){
				gsm.setState(GameStateManager.LOADINGSTATE);
				}
			}
			if(currentChoice == 1) {
				fadeout.go();
				if(fadeout.isDone()){
				System.exit(0);
				}
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		if(!fadeout.isDone()){
		bg.draw(g);
		image1.draw(g);
		cursor.draw(g);
		}
		if(!fadein.isDone()){
			fadein.draw(g);
			}
		fadeout.draw(g);
	}
	
	private void select() {
		if(currentChoice == 0) {
			fadeout.go();
			if(fadeout.isDone()){
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
		if(currentChoice == 1) {
			fadeout.go();
			if(fadeout.isDone()){
			System.exit(0);
			}
		}
	}
	
	public void keyPressed(int k) {
		
	}
	public void keyReleased(int k) {}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)){
			go = true;
		}
		if(Keys.isPressed(Keys.DOWN)){
			
			if(ready == 0){
			if(currentChoice == 0){
			currentChoice = 1;
			}else{
				currentChoice = 0;
			}
			ready = 1;
			}
		}
		if(Keys.isPressed(Keys.UP)){
			
			if(ready == 0){
			if(currentChoice == 1){
			currentChoice = 0;
		}else{
			currentChoice = 1;
				}
			ready = 1;
			}
		}
		if(!Keys.isPressed(Keys.UP) && !Keys.isPressed(Keys.DOWN)){
			ready = 0;
		}
	}
}










