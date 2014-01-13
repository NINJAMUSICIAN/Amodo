package GameState.Levels;
//{{
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Button;
import Entity.Door;
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

public class World5State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Point jiStart, zavStart, ataStart;
	
	private Ji ji;
	private Ata ata;
	private int moved = 0; // if 0 not setting up if 1 setting up
	private int switchable = 0, switched = 0; //which player in the list you are controlling
	private Door door, greenDoor, purpleDoor, door1;
	private Button button;
	@SuppressWarnings("unused")
	private Wall wall1, wall2, wall3, wall4;
	private Images image;
	
	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;
	
	
	public World5State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void setItUp(){
		ji = new Ji(tileMap, true);
		players.add(ji);
		ata = new Ata(tileMap, true);
		players.add(ata);
		pictures = new ArrayList<Images>();
		
		if(gsm.getCurrentLevel() == 41){
			ji.setPosition(100, 100);
			ata.setPosition(535, 100);
		}		
		if(gsm.getCurrentLevel() == 42){
			ji.setPosition(100, 415);
			ata.setPosition(160, 415);
		}
		if(gsm.getCurrentLevel() == 43){
			ji.setPosition(100, 511);
			ata.setPosition(100, 511);
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
		
		players = new ArrayList<MapObject>();
	
//}}		
		setItUp();
		jiStart = new Point(ji.getx(), ji.gety());
		setAtaStart(new Point(ata.getx(), ata.gety()));
		
		bg = new Background("/Backgrounds/DeepBackground.png", 0.1);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		populateDoors();
		wandB();
	}
//{{{add Parts
	public void populateDoors(){
		
		doors = new ArrayList<Door>();
		
		if(gsm.getCurrentLevel() == 41){
			purpleDoor = new Door(tileMap, "purple");
			greenDoor = new Door(tileMap, "green");
			purpleDoor.setPosition(374, 258);
			greenDoor.setPosition(240, 450);//208 452
			doors.add(purpleDoor);
			doors.add(greenDoor);
		}
		
		if(gsm.getCurrentLevel() == 42){
			door = new Door(tileMap, "normal");
			door.setPosition(580, 258);
			doors.add(door);		
		}
		if(gsm.getCurrentLevel() == 43){
			door = new Door(tileMap, "normal");
			door.setPosition(580, 354);
			doors.add(door);
		}
		
	}
	public void wandB(){
	}
//}}}	
//{{{ getters and setters
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
		ji.setPosition(jiStart.x, jiStart.y);
		ata.setPosition(ataStart.x, ataStart.y);
		populateDoors();
		wandB();
	}
	
	public void checkRegularDoor(){
		if(door.isSatisfied()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	public void checkColoredDoors(){
		if(purpleDoor.isSatisfied() && greenDoor.isSatisfied()){
			players.clear();
		 	gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
//{{{update stuff	
	public void whatUpdate(){
		if(gsm.getCurrentLevel() == 41){
			checkColoredDoors();
			purpleDoor.checkAta(ata, moved);
			greenDoor.checkJi(ji, moved);
		}
		if(gsm.getCurrentLevel() == 42){
		       checkRegularDoor();
	       		door.checkAta(ata, moved);		
		}
	}
	@Override
	public void update() {
		
		handleInput();

		ji.update();	
		ji.checkCatch(ata);
		ata.update();
		
		
		whatUpdate();
		bg.update();
		
		
			if(ji.fellDead() || ata.fellDead()){
			restart();
			}
		
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			b.checkPress(ji);
			b.updateWall(ji);
			
			
			b.checkPress(ata);
			b.updateWall(ata);
			b.update();
		}
		
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			if(!w.removed()){
				
			w.checkCollision(ji);		
			w.checkCollision(ata);
				}
			
			if(w.shouldRemove()){
				walls.remove(w);
				i--;
			}
		}
		
		
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


