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
import Entity.Characters.Rae;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Keys;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class World2State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Point jiStart, raeStart;
	
	private Ji ji;
	private Rae rae;
	private int moved; // if 0 not setting up if 1 setting up
	private int switchable = 0, switched = 0; //which player in the list you are controlling
	private Door door, door11, pinkDoor, greenDoor;
	private Button button;
	private Wall wall1, wall2, wall3, wall4;
	private Images image;
	private FadeIn fadein;
	private FadeOut fadeout;
	
	
	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;

	
	public World2State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	//INITIAL STUFF
	public void setItUp(){
		
		pictures = new ArrayList<Images>();
		
		ji = new Ji(tileMap, true);
		players.add(ji);
		rae = new Rae(tileMap, true);
		players.add(rae);
		
		if(gsm.getCurrentLevel() == 11){
			ji.setPosition(450, 80);
			rae.setPosition(240, 415);
			setActivePlayer(0);
			image = new Images(tileMap, "/Maps/Other/LevelEleven.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 12){
			ji.setPosition(300, 80);
			rae.setPosition(180, 415);
			
		}
		
		if(gsm.getCurrentLevel() == 13){
			ji.setPosition(100, 80);// 100 80
			rae.setPosition(100, 415);
		}
		if(gsm.getCurrentLevel() == 14){
		
			ji.setPosition(100, 415);
			rae.setPosition(130, 415);
			
			
		}
		if(gsm.getCurrentLevel() == 15){
			
			ji.setPosition(100, 415);
			rae.setPosition(130, 415);
			
		}
		if(gsm.getCurrentLevel() == 16){
			
			ji.setPosition(285, 415);
			rae.setPosition(315, 415);
			image = new Images(tileMap, "/Backgrounds/Texts/Level16.png");
			image.setPosition(320, 288);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 17){
			ji.setPosition(315, 415);
			rae.setPosition(100, 80);
		}
		
		if(gsm.getCurrentLevel() == 18){
			ji.setPosition(80, 240);
			rae.setPosition(120, 240);
			image = new Images(tileMap, "/Backgrounds/Texts/Level18.png");
			image.setPosition(320, 250);
			pictures.add(image);
		}

}
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
	
//		
		setItUp();
		
		jiStart = new Point(ji.getx(), ji.gety());
		raeStart = new Point(rae.getx(), rae.gety());
		
		bg = new Background("/Backgrounds/cloudbg.png", 0.1);
		bg.setVector(-0.1, 0);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		populateDoors();
		wandB();
		
		
	}
	public void populateDoors(){
		doors = new ArrayList<Door>();
		
		if(gsm.getCurrentLevel() == 11){
		door11 = new Door(tileMap, "normal");
		door11.setPosition(580, 417);
		doors.add(door11);
		}
		
		if(gsm.getCurrentLevel() == 12){
			door = new Door(tileMap, "normal");
			door.setPosition(60, 290);
			doors.add(door);
		}
		
		if(gsm.getCurrentLevel() == 13){
			greenDoor = new Door(tileMap, "green");
			greenDoor.setPosition(1260, 417);
			doors.add(greenDoor);
			pinkDoor = new Door(tileMap, "pink");
			pinkDoor.setPosition(1316, 417);
			doors.add(pinkDoor);
		}
		if(gsm.getCurrentLevel() == 14){
			door = new Door(tileMap, "normal");
			door.setPosition(1028, 417);
			doors.add(door);
		}
		
		if(gsm.getCurrentLevel() == 15){
			pinkDoor = new Door(tileMap, "pink");
			pinkDoor.setPosition(1028, 417);
			doors.add(pinkDoor);
			greenDoor = new Door(tileMap, "green");
			greenDoor.setPosition(972, 417);
			doors.add(greenDoor);
		}
		
		if(gsm.getCurrentLevel() == 16){
			door = new Door(tileMap, "normal");
			door.setPosition(580, 258);
			doors.add(door);
		}
		
		if(gsm.getCurrentLevel() == 17){
			greenDoor = new Door(tileMap, "green");
			pinkDoor = new Door(tileMap, "pink");
			greenDoor.setPosition(118, 257);
			pinkDoor.setPosition(62, 257);
			doors.add(pinkDoor);
			doors.add(greenDoor);
		}
		if(gsm.getCurrentLevel() == 18){
			door = new Door(tileMap, "normal");
			door.setPosition(580, 257);
			doors.add(door);
		}
	}
	public void wandB(){
		if(gsm.getCurrentLevel() == 11){
		wall1 = new Wall(3, "vert", "nothing", tileMap);
		wall1.setPosition(496, 400);
		walls.add(wall1);
		button = new Button(tileMap, "up", "break", wall1);
		button.setPosition(80, 280);
		buttons.add(button);
		button = new Button(tileMap, "up", "nothing", wall1);
		button.setPosition(560, 280);
		buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 12){
			wall1 = new Wall(3, "vert", "nothing", tileMap);
			wall1.setPosition(150, 80);
			walls.add(wall1);
			wall2 = new Wall(3, "vert", "nothing", tileMap);
			wall2.setPosition(496, 80);
			walls.add(wall2);
			wall3 = new Wall(3, "vert", "nothing", tileMap);
			wall3.setPosition(496, 400);
			walls.add(wall3);
			button = new Button(tileMap, "down", "break", wall3);
			button.setPosition(240, 44);
			buttons.add(button);
			button = new Button(tileMap, "left", "break", wall1);
			button.setPosition(592, 415);
			buttons.add(button);
			button = new Button(tileMap, "right", "break", wall2);
			button.setPosition(46, 96);
			buttons.add(button);	
		}
		
		if(gsm.getCurrentLevel() == 14){
			button = new Button(tileMap, "up", "trampoline",336, 464);
			button.setPosition(528, 440);
			buttons.add(button);
//			wall1 = new Wall(1, "trampoline", "trampoline", tileMap);
//			walls.add(wall1);
//			wall1.setPosition(200, 100);
		}
		if(gsm.getCurrentLevel() == 15){
			button = new Button(tileMap, "up", "trampoline", 336, 464);
			button.setPosition(528, 440);
			buttons.add(button);
			button = new Button(tileMap, "left", "trampoline", 720, 464);
			button.setPosition(1040, 336);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 16){
			wall1 = new Wall(3, "vert", "nothing", tileMap);
			wall1.setPosition(368, 81);
			walls.add(wall1);
			wall2 = new Wall(3, "vert", "nothing", tileMap);
			wall2.setPosition(368, 241);
			walls.add(wall2);
			wall3 = new Wall(3, "vert", "nothing", tileMap);
			wall3.setPosition(368, 401);
			walls.add(wall3);
			
			button = new Button(tileMap, "right", "break", wall3);
			button.setPosition(48, 416);
			buttons.add(button);
			button = new Button(tileMap, "left", "break", wall1);
			button.setPosition(592, 416);
			buttons.add(button);
			button = new Button(tileMap, "left", "wall 3", 208, 401);
			button.setPosition(592, 416);
			buttons.add(button);
			button = new Button(tileMap, "left", "break", wall2);
			button.setPosition(592, 96);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 17){
			wall1 = new Wall(3, "vert", "nothing", tileMap);
			wall1.setPosition(400, 240);
			walls.add(wall1);
			wall2 = new Wall(3, "vert", "nothing", tileMap);
			wall2.setPosition(400, 400);
			walls.add(wall2);
			
			button = new Button(tileMap, "left", "break", wall2);
			button.setPosition(592, 150);
			buttons.add(button);
			button = new Button(tileMap, "left", "break", wall1);
			button.setPosition(592, 415);
			buttons.add(button);
			button = new Button(tileMap, "down", "trampoline", 212, 433);
			button.setPosition(64, 204);
			buttons.add(button);
		}
		
		if(gsm.getCurrentLevel() == 18){
			wall1 = new Wall(3, "vert", "nothing", tileMap);
			wall1.setPosition(335, 240);
			walls.add(wall1);
			wall2 = new Wall(3, "vert", "nothing", tileMap);
			wall2.setPosition(367, 240);
			walls.add(wall2);
			wall3 = new Wall(3, "vert", "nothing", tileMap);
			wall3.setPosition(495, 240);
			walls.add(wall3);
			wall4 = new Wall(3, "vert", "nothing", tileMap);
			wall4.setPosition(525, 240);
			walls.add(wall4);
			
			button = new Button(tileMap, "down", "trampoline", 272, 401);
			button.setPosition(272, 48);
			buttons.add(button);
			button = new Button(tileMap, "down", "break", wall2);
			button.setPosition(272, 48);
			buttons.add(button);
			button = new Button(tileMap, "up", "break", wall1);
			button.setPosition(272, 440);
			buttons.add(button);
			
			button = new Button(tileMap, "down", "break", wall3);
			button.setPosition(434, 48);
			buttons.add(button);
			button = new Button(tileMap, "up", "break", wall4);
			button.setPosition(434, 440);
			buttons.add(button);
		}
		
	}
	
	//SWITCH STUFF
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
	
	//RESTART STUFF
	public void jiStartPoints(int x, int y){
		jiStart = new Point(x, y);
	}
	public void raeStartPoints(int x, int y){
		raeStart = new Point(x, y);
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
		rae.setPosition(raeStart.x, raeStart.y);
		populateDoors();
		wandB();
	}
	
	//UPDATE STUFF
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
	public void checkRegularDoor11(){
		if(door11.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel = 12;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void checkColoredDoors(){
		if(pinkDoor.isSatisfied() && greenDoor.isSatisfied()){
			fadeout.go();
			if(fadeout.isDone()){
			players.clear();
			gsm.currentLevel++;
			gsm.setState(GameStateManager.LOADINGSTATE);
			}
		}
	}
	public void whatUpdate(){
	
	if(gsm.getCurrentLevel() == 11){
		button.checkPress(ji);
		button.updateWall(ji);
		button.update();
		door11.checkPlayers(ji, rae, moved);
		checkRegularDoor11();
		}
	
	if(gsm.getCurrentLevel() == 12){
		door.checkPlayers(ji, rae, moved);
		checkRegularDoor();
	}
	
	if(gsm.getCurrentLevel() == 13){
		greenDoor.checkJi(ji, moved);
		pinkDoor.checkRae(rae, moved);
		checkColoredDoors();
	}
	
	if(gsm.getCurrentLevel() == 14){
		door.checkPlayers(ji, rae, moved);
		checkRegularDoor();
	}
	if(gsm.getCurrentLevel() == 15){
		greenDoor.checkJi(ji, moved);;
		pinkDoor.checkRae(rae, moved);
		checkColoredDoors();
	}
	
	if(gsm.getCurrentLevel() == 16){
		door.checkPlayers(ji, rae, moved);
		checkRegularDoor();
		}
	
	if(gsm.getCurrentLevel() == 17){
		greenDoor.checkJi(ji, moved);;
		pinkDoor.checkRae(rae, moved);
		checkColoredDoors();
	}
	
	if(gsm.getCurrentLevel() == 18){
		door.checkPlayers(ji, rae, moved);
		checkRegularDoor();
		}
	
	}
	public void update() {
		if(!fadein.isDone()){
			fadein.update();
			}
			if(!fadeout.isDone()){
				fadeout.update();
				}
		handleInput();
		
		ji.update();	
		rae.update();
		ji.checkCatch(rae);
		
		whatUpdate();
		bg.update();
		
		if(ji.fellDead() || rae.fellDead()){
			restart();
		}
		
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
		
		//wall.checkCollision(ji);
	}

	public void draw(Graphics2D g) {
		
		bg.draw(g);
		tileMap.draw(g);
//		pinkDoor.Draw(g);
//		greenDoor.Draw(g);
		
		for(int i = 0; i < pictures.size(); i++){
			Images j = pictures.get(i);
			j.draw(g);
		}
		
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			b.draw(g);
		}
		for(int i = 0; i < doors.size(); i++){
			Door d = doors.get(i);
			d.Draw(g);
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
}
