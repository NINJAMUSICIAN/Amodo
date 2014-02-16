package GameState.Levels;

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
import Entity.Characters.Zav;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Keys;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class World3State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Point jiStart, zavStart;
	
	private Ji ji;
	private Zav zav;
	private int moved = 0; // if 0 not setting up if 1 setting up
	private int switchable = 0, switched = 0; //which player in the list you are controlling
	private Door door, greenDoor, blueDoor, bdoor22, gdoor22, door1, door2, door3;
	@SuppressWarnings("unused")
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
	
	
	public World3State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void setItUp(){
		
		ji = new Ji(tileMap, true);
		players.add(ji);
		zav = new Zav(tileMap, true);
		players.add(zav);
		pictures = new ArrayList<Images>();
		
		if(gsm.getCurrentLevel() == 20){
			zav.setPosition(100, 415);
			ji.setPosition(652, 415);
			setActivePlayer(1);
			image = new Images(tileMap, "/Backgrounds/Texts/Level20.png", 1280, 576);
			image.setPosition(640, 288);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 19){
			zav.setPosition(580, 830);
			ji.setPosition(400, 415);
			setActivePlayer(1);
			image = new Images(tileMap, "/Backgrounds/Texts/Level19.png", 640, 1056);
			image.setPosition(320, 528);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 21){
			zav.setPosition(1900, 415);//1900 415
			ji.setPosition(1980, 415);
			image = new Images(tileMap, "/Backgrounds/Texts/Level21.png", 2304, 576);
			image.setPosition(1152, 288);
			pictures.add(image);
			}
		
		if(gsm.getCurrentLevel() == 22){
			zav.setPosition(300, 415);// 300 415
			ji.setPosition(330, 415);
			setActivePlayer(1);
			image = new Images(tileMap, "/Backgrounds/Texts/Level22.png", 800, 672);
			image.setPosition(400, 336);
			pictures.add(image);
		}
		if(gsm.getCurrentLevel() == 23){
			zav.setPosition(180, 256);
			ji.setPosition(300, 510);
		}
		
		if(gsm.getCurrentLevel() == 24){
			zav.setPosition(80, 415);// 80 415
			ji.setPosition(80, 200);//80 200
			setActivePlayer(0);
			image = new Images(tileMap, "/Backgrounds/Texts/Level24.png", 2560, 640);
			image.setPosition(1280, 320);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 25){
			zav.setPosition(200, 440);
			ji.setPosition(400, 440);
		}
		
		if(gsm.getCurrentLevel() == 26){
			zav.setPosition(120, 415);
			ji.setPosition(80, 415);
			image = new Images(tileMap, "/Backgrounds/Texts/Level26.png", 1280, 736);
			image.setPosition(640, 368);
			
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
		
		tileMap.loadTiles("/Tilesets/ZavSet.png");
		tileMap.setPosition(-0, 0);
		
		fadein = new FadeIn(tileMap, 20);
		fadein.setPosition(320, 240);
		fadeout = new FadeOut(tileMap, 20);
		fadeout.setPosition(320, 240);
		
		
		players = new ArrayList<MapObject>();
	
//		
		setItUp();
		
		jiStart = new Point(ji.getx(), ji.gety());
		setZavStart(new Point(zav.getx(), zav.gety()));
		
		bg = new Background("/Backgrounds/cloudbg.png", 0.1);
		bg.setVector(-0.1, 0);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		populateDoors();
		wandB();
	}
	public void populateDoors(){
		
		doors = new ArrayList<Door>();
		
		if(gsm.getCurrentLevel() == 20){
			blueDoor = new Door(tileMap, "blue");
			blueDoor.setPosition(1136, 321);
			doors.add(blueDoor);
			greenDoor = new Door(tileMap, "green");
			greenDoor.setPosition(1200, 321);
			doors.add(greenDoor);
		}
		
		if(gsm.getCurrentLevel() == 19){
			 greenDoor = new Door(tileMap, "green");
			 greenDoor.setPosition(80, 257);
			 doors.add(greenDoor);
			 blueDoor = new Door(tileMap, "blue");
			 blueDoor.setPosition(140, 257);
			 doors.add(blueDoor);
		}
		
		if(gsm.getCurrentLevel() == 21){
			door = new Door(tileMap, "normal");
			door.setPosition(80, 194);
			doors.add(door);
		}
		
		if(gsm.getCurrentLevel() == 22){
			gdoor22 = new Door(tileMap, "green");
			gdoor22.setPosition(60, 161);
			doors.add(gdoor22);
			bdoor22 = new Door(tileMap, "blue");
			bdoor22.setPosition(120, 161);
			doors.add(bdoor22);
		}
		
		if(gsm.getCurrentLevel() == 23){
			bdoor22 = new Door(tileMap, "blue");
			bdoor22.setPosition(512, 194);
			doors.add(bdoor22);
			gdoor22 = new Door(tileMap, "green");
			gdoor22.setPosition(128, 258);
			doors.add(gdoor22);
		}
		
		if(gsm.getCurrentLevel() == 24){
			blueDoor = new Door(tileMap, "blue");
			blueDoor.setPosition(2496, 482);
			doors.add(blueDoor);
			greenDoor = new Door(tileMap, "green");
			greenDoor.setPosition(2496, 98);
			doors.add(greenDoor);
		}
		
		if(gsm.getCurrentLevel() == 25){
			door = new Door(tileMap, "normal");
			door.setPosition(260, 98);
			doors.add(door);
		}
		
		if(gsm.getCurrentLevel() == 26){
			door1 = new Door(tileMap, "normal");
			door1.setPosition(1072, 608);
			doors.add(door1);
			door2 = new Door(tileMap, "normal");
			door2.setPosition(1136, 608);
			doors.add(door2);
			door3 = new Door(tileMap, "normal");
			door3.setPosition(1200, 608);
			doors.add(door3);
		}
		
	}
	public void wandB(){
		
		if(gsm.getCurrentLevel() == 21){
			wall1 = new Wall(3, "vert", "nothing", tileMap);
			wall1.setPosition(1840, 272);
			walls.add(wall1);
			button = new Button(tileMap, "left", "break", wall1);
			button.setPosition(2224, 288);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 22){
			wall1 = new Wall(2, "horizontal", "nothing", tileMap);
			wall1.setPosition(288, 304);
			walls.add(wall1);
			button = new Button(tileMap, "left", "break", wall1);
			button.setPosition(784, 160);
			buttons.add(button);
			button = new Button(tileMap, "right", "trampoline", 272, 352);
			button.setPosition(176, 256);
			buttons.add(button);
			button = new Button(tileMap, "right", "trampoline", 272, 416);
			button.setPosition(176, 256);
			buttons.add(button);
			button = new Button(tileMap, "right", "trampoline", 272, 490);
			button.setPosition(176, 256);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 23){
			button = new Button(tileMap, "right", "trampoline", 272, 340);
			button.setPosition(48, 160);
			buttons.add(button);
			button = new Button(tileMap, "right", "trampoline", 272, 528);
			button.setPosition(48, 160);
			buttons.add(button);
			button = new Button(tileMap, "left", "trampoline", 352, 434);
			button.setPosition(592, 192);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 24){
			button = new Button(tileMap, "up", "trampoline", 464, 448);
			button.setPosition(288, 248);
			buttons.add(button);
			
			wall1 = new Wall(3, "vertical", "nothing", tileMap);
			wall1.setPosition(1040, 80);
			walls.add(wall1);
			button = new Button(tileMap, "up", "break", wall1);
			button.setPosition(800, 504);
			buttons.add(button);
			
			wall2 = new Wall(3, "vertical", "nothing", tileMap);
			wall2.setPosition(1200, 336);
			walls.add(wall2);
			button = new Button(tileMap, "up", "break", wall2);
			button.setPosition(1232, 248);
			buttons.add(button);
			
			button = new Button(tileMap, "up", "trampoline", 1616, 200);
			button.setPosition(1312, 504);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 25){
			wall1 = new Wall(2, "horizontal", "nothing", tileMap);
			wall1.setPosition(192, 304);
			walls.add(wall1);
			button = new Button(tileMap, "right", "break", wall1);
			button.setPosition(336, 88);
			buttons.add(button);
//			button = new Button(tileMap, "right", "trampoline", 176, 464);
//			button.setPosition(336, 88);
//			buttons.add(button);
			button = new Button(tileMap, "right", "trampoline", 180, 496);
			button.setPosition(336, 88);
			buttons.add(button);
			button = new Button(tileMap, "right", "trampoline", 180, 368);
			button.setPosition(336, 88);
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
		ji.setPosition(jiStart.x, jiStart.y);
		zav.setPosition(zavStart.x, zavStart.y);
		//zav.setpos
		populateDoors();
		wandB();
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
	public void checkColoredDoors(){
		fadeout.go();
		if(fadeout.isDone()){
		if(blueDoor.isSatisfied() && greenDoor.isSatisfied()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void checkDoors22(){
		if(bdoor22.isSatisfied() && gdoor22.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 23;
			doors.remove(bdoor22);
			doors.remove(gdoor22);
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void checkDoors23(){
		if(bdoor22.isSatisfied() && gdoor22.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 24;
			doors.remove(bdoor22);
			doors.remove(gdoor22);
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	
	public void checkFinalDoors(){
		if(door1.isSatisfied() || door2.isSatisfied() || door3.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 27;
			doors.remove(door1);
			doors.remove(door2);
			doors.remove(door3);
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	
	public void whatUpdate(){
		
		if(gsm.getCurrentLevel() == 20){
			greenDoor.checkJi(ji, moved);
			blueDoor.checkZav(zav, moved);
			checkColoredDoors();
		}
		
		if(gsm.getCurrentLevel() == 19){
			greenDoor.checkJi(ji, moved);
			blueDoor.checkZav(zav, moved);
			checkColoredDoors();
		}
		
		if(gsm.getCurrentLevel() == 21){
			door.checkPlayers(ji, zav, moved);
			checkRegularDoor();
		}
		if(gsm.getCurrentLevel() == 22){
			gdoor22.checkJi(ji, moved);
			bdoor22.checkZav(zav, moved);
			checkDoors22();
		}
		
		if(gsm.getCurrentLevel() == 23){
			gdoor22.checkJi(ji, moved);
			bdoor22.checkZav(zav, moved);
			checkDoors23();
		}
		
		if(gsm.getCurrentLevel() == 24){
			greenDoor.checkJi(ji, moved);
			blueDoor.checkZav(zav, moved);
			checkColoredDoors();
		}
		
		if(gsm.getCurrentLevel() == 25){
				door.checkPlayers(ji, zav, moved);
				checkRegularDoor();
		}
		
		if(gsm.getCurrentLevel() == 26){
			door1.checkPlayers(zav, moved);
			door2.checkPlayers(zav, moved);
			door3.checkPlayers(zav, moved);
			checkFinalDoors();
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
		
		ji.update();	
		zav.update();
		ji.checkCatch(zav);
		zav.checkCatch(ji);
		
		whatUpdate();
		bg.update();
		
		if(ji.fellDead() || zav.fellDead()){
			restart();
		}
		
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			b.checkPress(ji);
			b.updateWall(ji);
			b.checkPress(zav);
			b.updateWall(zav);
			b.update();
		}
		
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			if(!w.removed()){
			w.checkCollision(ji);
			w.checkCollision(zav);
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
		
		if(gsm.getCurrentLevel() != 26){
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - getActivePlayer().getx(),
				GamePanel.HEIGHT / 2 - getActivePlayer().gety());
		}else{
			tileMap.setPosition(
					GamePanel.WIDTH / 2 - getActivePlayer().getx(),
					0);
		}
		//wall.checkCollision(ji);

	}

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
	
	if(!fadein.isDone()){
		fadein.draw(g);
		}
		fadeout.draw(g);
	
	}

	
	public Point getZavStart() {
		return zavStart;
	}
	public void setZavStart(Point zavStart) {
		this.zavStart = zavStart;
	} 	
}


