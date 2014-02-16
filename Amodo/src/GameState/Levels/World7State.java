package GameState.Levels;
//{{
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Button;
import Entity.Door;
import Entity.FadeIn;
import Entity.FadeOut;
import Entity.Images;
import Entity.MapObject;
import Entity.Wall;
import Entity.Characters.Ji;
import Entity.Characters.Ata;
import Entity.Characters.Zav;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Keys;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class World7State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Point jiStart, zavStart, ataStart;
	
	private Zav zav;
	private Ata ata;
	private int moved = 0; // if 0 not setting up if 1 setting up
	private int switchable = 0, switched = 0; //which player in the list you are controlling
	private Door door, greenDoor, purpleDoor, door1;
	private Button button;
	@SuppressWarnings("unused")
	private Wall wall1, wall2, wall3, wall4;
	private Images image;
	private FadeIn fadein;
	private FadeOut fadeout;
	
	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;
	
	
	public World7State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void setItUp(){
		zav = new Zav(tileMap, true);
		ata = new Ata(tileMap, true);
		players.add(zav);
		players.add(ata);
		pictures = new ArrayList<Images>();
		
		
		if(gsm.getCurrentLevel() == 48){
			zav.setPosition(80, 70);
			ata.setPosition(200, 496);
			image = new Images(tileMap, "/Backgrounds/Texts/Level48.png");
			image.setPosition(320, 288);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 49){
			zav.setPosition(200, 600);
			ata.setPosition(200, 496);
			image = new Images(tileMap, "/Backgrounds/Texts/Level49.png", 672, 800);
			image.setPosition(336, 400);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 50){
			zav.setPosition(200, 600);
			ata.setPosition(200, 496);
			image = new Images(tileMap, "/Backgrounds/Texts/Level50.png", 640, 736);
			image.setPosition(320, 368);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 51){
			zav.setPosition(314, 773);
			ata.setPosition(200, 496);
			image = new Images(tileMap, "/Backgrounds/Texts/Level51.png", 640, 896);
			image.setPosition(320, 448);
			pictures.add(image);
		}
	}	
	@Override
	public void init() {
		walls = new ArrayList<Wall>();
		buttons = new ArrayList<Button>();
		players = new ArrayList<MapObject>();
		
		tileMap = new TileMap(32);
		tileMap.loadMap("/Maps/Level" + gsm.getCurrentLevel() + ".map");
		System.out.println("/Maps/Level" + gsm.getCurrentLevel() + ".map");
		
		tileMap.loadTiles("/Tilesets/TheDeepTileset.png");
		tileMap.setPosition(-0, 0);
		
		fadein = new FadeIn(tileMap, 20);
		fadein.setPosition(320, 240);
		fadeout = new FadeOut(tileMap, 20);
		fadeout.setPosition(320, 240);
		
		players = new ArrayList<MapObject>();
	
//}}		
		setItUp();
		zavStart = new Point(zav.getx(), zav.gety());
		
		bg = new Background("/Backgrounds/DeepBackground.png", -1);
		
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		populateDoors();
	}
//{{{add Parts
	public void populateDoors(){
		
		doors = new ArrayList<Door>();
		
		
		if(gsm.getCurrentLevel() == 48){
			door1 = new Door(tileMap, "normal");
			door1.setPosition(562, 224);//752
			doors.add(door1);
		}	
		if(gsm.getCurrentLevel() == 49){
			door = new Door(tileMap, "normal");
			door.setPosition(598, 160);//752
			doors.add(door);
		}	
		if(gsm.getCurrentLevel() == 50){
			door1 = new Door(tileMap, "normal");
			door1.setPosition(566, 224);//752
			doors.add(door1);
		}	
		if(gsm.getCurrentLevel() == 51){
			door = new Door(tileMap, "normal");
			door.setPosition(576, 129);//752
			doors.add(door);
		}	
	}
	
	public void switchActiveUp(){
		if(switched == 0){
			if(switchable == players.size() - 1){
				switchable = 0;
			}else{
				switchable++;
				}
			switched = 1;
			}
	}
	public void switchActiveDown(){
		if(switchable == 0){
			switchable = players.size();
		}else{
			switchable--;
		}
	}
	public void setActivePlayer(int i){
		switchable = i;
	}
	public MapObject getActivePlayer(){
			MapObject Active = players.get(switchable);
		
			return Active;
	}
	
	public void jiStartPoints(int x, int y){
		jiStart = new Point(x, y);
	}
	public void zavStartPoints(int x, int y){
		setZavStart(new Point(x, y));
	}
	//}}}
	public void restart(){
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			buttons.remove(b);
		}
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			walls.remove(w);
		}
		zav.setPosition(jiStart.x, jiStart.y);
		populateDoors();
		}
	
	public void checkRegularDoor(){
		if(door.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void check49Door(){
		if(door.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 50;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void check50Door(){
		if(door1.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 51;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void check48Door(){
		if(door1.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 49;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	
	public void whatUpdate(){
		if(gsm.getCurrentLevel() == 48){
			check48Door();
			door1.checkAta(ata, moved);
		}
		if(gsm.getCurrentLevel() == 49){
			check49Door();
			door.checkAta(ata, moved);
		}
		if(gsm.getCurrentLevel() == 50){
			check50Door();
			door1.checkAta(ata, moved);
		}
		if(gsm.getCurrentLevel() == 51){
			checkRegularDoor();
			door.checkAta(ata, moved);
		}
	}
	@Override
	public void update() {
		if(!fadein.isDone()){
			fadein.update();
			}
			if(!fadeout.isDone()){
				fadeout.update();
				}
		handleInput();

		zav.update();	
		zav.checkCatch(ata);
		ata.update();
		
		whatUpdate();
		bg.update();
		//bg.setPosition(0, 0);	
		
		for(int i = 0; i < pictures.size(); i++){
			Images j = pictures.get(i);
			j.update();
		}
		
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - getActivePlayer().getx(),
				GamePanel.HEIGHT / 2 - getActivePlayer().gety());
		}
		//wall.checkCollision(ji);
//}}}
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		tileMap.draw(g);
		
		for(int i = 0; i < pictures.size(); i++){
			Images j = pictures.get(i);
			j.draw(g);
		}
		
//		pinkDoor.Draw(g);
//		greenDoor.Draw(g);
		
		for(int i = 0; i < doors.size(); i++){
			Door d = doors.get(i);
			d.Draw(g);
		}
		
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			b.draw(g);
		}
		
		for(int i = 0; i < players.size(); i++){
			MapObject m = players.get(i);
			m.draw(g);
		}
		
	for(int i = 0; i < walls.size(); i++){
		walls.get(i).draw(g);
		}
	
	if(!fadein.isDone()){
		fadein.draw(g);
		}
		fadeout.draw(g);
	
	}

	@Override
	public void handleInput() {
		for(int i = 0; i < players.size(); i++){
			
			MapObject m = players.get(switchable);
			
			m.setUp(Keys.keyState[Keys.UP]);
			m.setLeft(Keys.keyState[Keys.LEFT]);
			m.setDown(Keys.keyState[Keys.DOWN]);
			m.setRight(Keys.keyState[Keys.RIGHT]);
			m.setJumping(Keys.keyState[Keys.BUTTON1]);
			
			if(Keys.isPressed(Keys.BUTTON2)){
				m.setUp(false);
				m.setLeft(false);
				m.setDown(false);
				m.setRight(false);
				m.setJumping(false);
				switchActiveUp();
				
			}else{
				switched = 0;
			}
			
		}
		
		
		
		if(Keys.isPressed(Keys.BUTTON3)) restart();
	}

	public Point getZavStart() {
		return zavStart;
	}

	public void setZavStart(Point zavStart) {
		this.zavStart = zavStart;
	} 
	
	public Point getAtaStart() {
		return ataStart;
	}

	public void setAtaStart(Point ataStart) {
		this.ataStart = ataStart;
	} 
}


