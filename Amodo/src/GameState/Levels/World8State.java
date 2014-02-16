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
import Entity.Characters.Rae;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Keys;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class World8State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Point jiStart, raeStart;
	
	private Ji ji;
	private Rae rae;
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
	
	
	public World8State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void setItUp(){
		if(gsm.getCurrentLevel() == 52 || gsm.getCurrentLevel() == 54){
		ji = new Ji(tileMap, true);
		players.add(ji);
		}
		if(gsm.getCurrentLevel() == 53 || gsm.getCurrentLevel() == 54){
		rae = new Rae(tileMap, true);
		players.add(rae);

		}
		
				pictures = new ArrayList<Images>();
		
		
		if(gsm.getCurrentLevel() == 52){
			ji.setPosition(80, 415);
			image = new Images(tileMap, "/Backgrounds/Texts/Level52.png", 640, 480);
			image.setPosition(320, 240);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 53){
			rae.setPosition(80, 900);
			image = new Images(tileMap, "/Backgrounds/Texts/Level53.png", 640, 960);
			image.setPosition(320, 480);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 54){
			ji.setPosition(200, 415);
			rae.setPosition(300, 196);
			image = new Images(tileMap, "/Backgrounds/Texts/Level28.png", 1376, 576);
			image.setPosition(688, 288);
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
		
		tileMap.loadTiles("/Tilesets/grasstileset.png");
		tileMap.setPosition(-0, 0);
		
		fadein = new FadeIn(tileMap, 20);
		fadein.setPosition(320, 240);
		fadeout = new FadeOut(tileMap, 20);
		fadeout.setPosition(320, 240);
		
		players = new ArrayList<MapObject>();
	
//}}		
		setItUp();
		wandb();
		//jiStart = new Point(ji.getx(), ji.gety());
		
		bg = new Background("/Backgrounds/cloudbg.png", 0.1);
		bg.setVector(-0.1, 0);
		
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		populateDoors();
	}
//{{{add Parts
	public void populateDoors(){
		
		doors = new ArrayList<Door>();
		
		
		if(gsm.getCurrentLevel() == 52){
			door1 = new Door(tileMap, "normal");
			door1.setPosition(80, 98);//752
			doors.add(door1);
		}	
		if(gsm.getCurrentLevel() == 53){
			door = new Door(tileMap, "normal");
			door.setPosition(578, 194);//192
			doors.add(door);
		}	
		if(gsm.getCurrentLevel() == 54){
			door1 = new Door(tileMap, "normal");
			door1.setPosition(865, 194);//752
			doors.add(door1);
		}	
	}
	
	public void wandb(){
		if(gsm.getCurrentLevel() == 54){
			wall1 = new Wall(2, "vertical", "nothing", tileMap);
			wall1.setPosition(672, 416);
			walls.add(wall1);
			button = new Button(tileMap, "down", "break", wall1);
			button.setPosition(672, 272);
			buttons.add(button);
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
	public void raeStartPoints(int x, int y){
		setraeStart(new Point(x, y));
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
	public void check52Door(){
		if(door1.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 53;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void check53Door(){
		if(door.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 54;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void check54Door(){
		if(door1.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 49;
			//gsm.setState(GameStateManager.CREDITSTATE);
			}
		}
	}
	
	public void whatUpdate(){
		if(gsm.getCurrentLevel() == 52){
			check52Door();
			door1.checkJi(ji, moved);
		}
		if(gsm.getCurrentLevel() == 53){
			check53Door();
			door.checkRae(rae, moved);
		}
		if(gsm.getCurrentLevel() == 54){
			check54Door();
			door1.checkRae(rae, moved);
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

		if(gsm.getCurrentLevel() == 52 || gsm.getCurrentLevel() == 54){
		ji.update();	
		}
		if(gsm.getCurrentLevel() == 54){
		ji.checkCatch(rae);
		}
		if(gsm.getCurrentLevel() == 53 || gsm.getCurrentLevel() == 54){
		rae.update();
		}
		
		whatUpdate();
		bg.update();
		//bg.setPosition(0, 0);	
		
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			b.checkPress(ji);
			b.updateWall(ji);
			
			
			b.checkPress(rae);
			b.updateWall(rae);
			b.update();
		}
		
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			if(!w.removed()){
				
			w.checkCollision(ji);		
			w.checkCollision(rae);
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

	public Point getjiStart() {
		return jiStart;
	}

	public void setjiStart(Point jiStart) {
		this.jiStart = jiStart;
	} 
	
	public Point getraeStart() {
		return raeStart;
	}

	public void setraeStart(Point raeStart) {
		this.raeStart = raeStart;
	} 
}


