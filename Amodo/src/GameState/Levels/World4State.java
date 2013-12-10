package GameState.Levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Button;
import Entity.Door;
import Entity.Images;
import Entity.MapObject;
import Entity.Wall;
import Entity.Characters.Ji;
import Entity.Characters.Rae;
import Entity.Characters.Zav;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Keys;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class World4State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Point jiStart, zavStart;
	
	private Ji ji;
	private Rae rae;
	private Zav zav;
	private int moved = 0; // if 0 not setting up if 1 setting up
	private int switchable = 0, switched = 0; //which player in the list you are controlling
	private Door door, greenDoor, pinkDoor, blueDoor, door1, door2, door3;
	private Button button;
	@SuppressWarnings("unused")
	private Wall wall1, wall2, wall3, wall4;
	private Images image;
	
	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;
	
	
	public World4State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void setItUp(){
		if(gsm.getCurrentLevel() >= 27 && gsm.getCurrentLevel() <= 30){
		ji = new Ji(tileMap, true);
		players.add(ji);
		rae = new Rae(tileMap, true);
		players.add(rae);
		}else{
			rae = new Rae(tileMap, true);
			zav = new Zav(tileMap, true);
			players.add(rae);
			players.add(zav);
		}
		pictures = new ArrayList<Images>();
		
		if(gsm.getCurrentLevel() == 27){
			rae.setPosition(560, 458);
			ji.setPosition(520, 458);
			setActivePlayer(0);
		}
		
		if(gsm.getCurrentLevel() == 28){
			rae.setPosition(560, 100);
			ji.setPosition(80, 256);
			image = new Images(tileMap, "/Backgrounds/Texts/Level28.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 29){
			rae.setPosition(80, 100);
			ji.setPosition(560, 256);
			image = new Images(tileMap, "/Backgrounds/Texts/Level29.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 30){
			rae.setPosition(656, 290);
			ji.setPosition(656, 130);
			image = new Images(tileMap, "/Backgrounds/Texts/Level30.png", 896, 800);
			image.setPosition(447, 400);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 31){
			zav.setPosition(225, 500);
			rae.setPosition(415, 500);
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
		
		tileMap.loadTiles("/Tilesets/ZavSet.png");
		tileMap.setPosition(-0, 0);
		
		players = new ArrayList<MapObject>();
	
//		
		setItUp();
		if(gsm.getCurrentLevel() < 31){
		jiStart = new Point(ji.getx(), ji.gety());
		}
		setZavStart(new Point(rae.getx(), rae.gety()));
		
		bg = new Background("/Backgrounds/cloudbg.png", 0.1);
		bg.setVector(-0.1, 0);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		populateDoors();
		wandB();
	}
	public void populateDoors(){
		
		doors = new ArrayList<Door>();
		
		if(gsm.getCurrentLevel() == 27){
			greenDoor = new Door(tileMap, "green");
			greenDoor.setPosition(410, 513);
			doors.add(greenDoor);
			pinkDoor = new Door(tileMap, "pink");
			pinkDoor.setPosition(265, 513);
			doors.add(pinkDoor);
		}
		
		if(gsm.getCurrentLevel() == 28){
			door3 = new Door(tileMap, "normal");
			door3.setPosition(570, 258);
			doors.add(door3);
		}
		
		if(gsm.getCurrentLevel() == 29){
			door = new Door(tileMap, "normal");
			door.setPosition(64, 258);
			doors.add(door);
		}
		
		if(gsm.getCurrentLevel() == 30){
			door1 = new Door(tileMap, "normal");
			door1.setPosition(64, 518);//518
			doors.add(door1);
			door2 = new Door(tileMap, "normal");
			door2.setPosition(120, 518);
			doors.add(door2);
			door3 = new Door(tileMap, "normal");
			door3.setPosition(186, 518);
			doors.add(door3);
		}
		
		if(gsm.getCurrentLevel() == 31){
			blueDoor = new Door(tileMap, "blue");
			blueDoor.setPosition(64, 98);
			doors.add(blueDoor);
			pinkDoor = new Door(tileMap, "pink");
			pinkDoor.setPosition(574, 98);
			doors.add(pinkDoor);
			
		}
		
	}
	public void wandB(){
		if(gsm.getCurrentLevel() == 27){
			wall1 = new Wall(3, "vert", "nothing", tileMap);
			wall1.setPosition(464, 497);
			walls.add(wall1);
			wall2 = new Wall(3, "vert", "nothing", tileMap);
			wall2.setPosition(208, 497);
			walls.add(wall2);
			
			button = new Button(tileMap, "up", "break", wall1);
			button.setPosition(368, 120);
			buttons.add(button);
			button = new Button(tileMap, "up", "wall 3", 496, 112);
			button.setPosition(368, 120);
			buttons.add(button);
			
			button = new Button(tileMap, "down", "break", wall2);
			button.setPosition(336, 464);
			buttons.add(button);
			//public Button(TileMap tm, String dir, String what, int wallX, int wallY)
		}
		
		if(gsm.getCurrentLevel() == 30){
			wall1 = new Wall(3, "vertical", "nothing", tileMap);
			wall1.setPosition(576, 272);
			walls.add(wall1);
			
			button = new Button(tileMap, "up", "break", wall1);
			button.setPosition(784, 640);
			buttons.add(button);
			button = new Button(tileMap, "up", "break", wall1);
			button.setPosition(816, 640);
			buttons.add(button);
			button = new Button(tileMap, "up", "break", wall1);
			button.setPosition(848, 640);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 31){
			button = new Button(tileMap, "up", "trampoline", 464, 272);
			button.setPosition(320, 152);
			buttons.add(button);
			button = new Button(tileMap, "up", "trampoline", 208, 272);
			button.setPosition(320, 152);
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
	public void zavStartPoints(int x, int y){
		setZavStart(new Point(x, y));
	}
	public void restart(){
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			buttons.remove(b);
		}
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			walls.remove(w);
		}
		//ji.setPosition(jiStart.x, jiStart.y);
		rae.setPosition(zavStart.x, zavStart.y);
		//zav.setpos
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
		if(pinkDoor.isSatisfied() && greenDoor.isSatisfied()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	
	public void checkColoredDoors2(){
		if(pinkDoor.isSatisfied() && blueDoor.isSatisfied()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	
	public void checkRegularDoor28(){
		if(door3.isSatisfied()){
			gsm.currentLevel = 29;
			doors.remove(door3);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	public void check30Doors(){
		if(door1.isSatisfied() || door2.isSatisfied()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	public void check31Doors(){
		if(door1.isSatisfied() || door2.isSatisfied() || door3.isSatisfied()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	
	
	public void whatUpdate(){
		if(gsm.getCurrentLevel() == 27){
			greenDoor.checkJi(ji, moved);
			pinkDoor.checkRae(rae, moved);
			checkColoredDoors();
		}
		
		if(gsm.getCurrentLevel() == 28){
			door3.checkPlayers(ji, moved);
			checkRegularDoor28();
		}
		
		if(gsm.getCurrentLevel() == 29){
			door.checkPlayers(ji, moved);
			checkRegularDoor();
		}
		
		if(gsm.getCurrentLevel() == 30){
			door1.checkPlayers(rae, moved);
			door2.checkPlayers(rae, moved);
			door3.checkPlayers(rae, moved);
			check31Doors();
		}
		if(gsm.getCurrentLevel() == 31){
			blueDoor.checkPlayers(zav, moved);
			pinkDoor.checkPlayers(rae, moved);
			checkColoredDoors2();
		}
	}
	@Override
	public void update() {
		
		handleInput();
		if(gsm.getCurrentLevel() < 31){
		ji.update();	
		ji.checkCatch(rae);
		}else{
			zav.update();	
			zav.checkCatch(rae);
		}
		rae.update();
		
		
		whatUpdate();
		bg.update();
		
		if(gsm.getCurrentLevel() < 31){
			if(ji.fellDead() || rae.fellDead()){
			//restart();
			}
		}else{
			if(rae.fellDead()){
				restart();
			}
		}
		
		
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			if(gsm.getCurrentLevel() < 31){
			b.checkPress(ji);
			b.updateWall(ji);
			}else{
			b.checkPress(zav);
			b.updateWall(zav);
			}
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
		
		if(gsm.getCurrentLevel() == 30){
			tileMap.setPosition(
					GamePanel.WIDTH / 2 - getActivePlayer().getx(),
					0);
		}else{
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - getActivePlayer().getx(),
				GamePanel.HEIGHT / 2 - getActivePlayer().gety());
		}
		//wall.checkCollision(ji);

	}

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
		}
		
		if(Keys.isPressed(Keys.BUTTON2)){
			switchActiveUp();
		}else{
			switched = 0;
		}
		
		if(Keys.isPressed(Keys.BUTTON3)) restart();
	}

	public Point getZavStart() {
		return zavStart;
	}

	public void setZavStart(Point zavStart) {
		this.zavStart = zavStart;
	} 	
}


