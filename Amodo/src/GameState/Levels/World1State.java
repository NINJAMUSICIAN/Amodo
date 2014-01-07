package GameState.Levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Audio.AudioPlayer;
import Entity.Button;
import Entity.Door;
import Entity.Images;
import Entity.MapObject;
import Entity.Wall;
import Entity.Characters.Ji;
import Entity.Characters.Rae;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Keys;
import TileMap.Background;
import TileMap.TileMap;

public class World1State extends GameState {

	private TileMap tileMap;
	private Background bg;

	private Point jiStart, raeStart;
	
	private Ji ji;
	private Rae rae;
	private int moved, musicStarted = 0, switched = 0; // if 0 not setting up if 1 setting up
	private int switchable = 0; //which player in the list you are controlling
	private Door door1, door2, door4, door5, door6, door7, door8, door9, door10, pinkDoor, greenDoor;
	private Button button;
	private Wall wall;
	private Images image;
	
	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;
	
	private AudioPlayer bgMusic;
	
	public World1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void setItUp(){
		
		pictures = new ArrayList<Images>();
		
		if(gsm.getCurrentLevel() == 1){
			ji = new Ji(tileMap, true);
			ji.setPosition(80, 400);
			players.add(ji);
			image = new Images(tileMap, "/Backgrounds/Texts/LevelOne.png");
			image.setPosition(320, 240);
			pictures.add(image);
			image = new Images(tileMap, "/Maps/Other/LevelOne.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 2){
			rae = new Rae(tileMap, true);
			rae.setPosition(580, 400);
			players.add(rae);
			image = new Images(tileMap, "/Backgrounds/Texts/LevelTwo.png");
			image.setPosition(320, 240);
			pictures.add(image);
			
		}
		
		if(gsm.getCurrentLevel() == 3){
			ji.setPosition(80, 400);
			rae.setPosition(580, 400);
			image = new Images(tileMap, "/Backgrounds/Texts/LevelThree.png");
			image.setPosition(320, 240);
			pictures.add(image);
			image = new Images(tileMap, "/Maps/Other/LevelThree.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 4){
			ji.setPosition(80, 400);
			rae.setPosition(160, 400);
			image = new Images(tileMap, "/Backgrounds/Texts/LevelFour.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 5){
			ji.setPosition(80, 400);
			rae.setPosition(160, 400);
		}
		
		if(gsm.getCurrentLevel() == 6){
			ji.setPosition(50, 400);
			rae.setPosition(80, 400);
			image = new Images(tileMap, "/Backgrounds/Texts/LevelSix.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 7){
			ji.setPosition(300, 400);
			rae.setPosition(340, 400);
		}
		
		if(gsm.getCurrentLevel() == 8){
			ji.setPosition(240, 415);
			rae.setPosition(60, 160);
			setActivePlayer(1);
			image = new Images(tileMap, "/Backgrounds/Texts/LevelEight.png");
			image.setPosition(320, 240);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 9){
			ji.setPosition(260, 250);
			rae.setPosition(240, 415);
			image = new Images(tileMap, "/Maps/Other/LevelEleven.png");
			image.setPosition(320, 431);
			pictures.add(image);
		}
		
		if(gsm.getCurrentLevel() == 10){
				ji.setPosition(350, 415);//350 415
				rae.setPosition(350, 100);
				setActivePlayer(0);
				wall = new Wall(3, "vert", "anything", tileMap);
				wall.setPosition(144, 144);
				walls.add(wall);
				button = new Button(tileMap,"left", "break", wall);
				button.setPosition(592, 432);
				buttons.add(button);
				image = new Images(tileMap, "/Backgrounds/Texts/LevelTen.png");
				image.setPosition(320, 240);
				pictures.add(image);
		
		}
		

}
	public void init() {
		
		walls = new ArrayList<Wall>();
		buttons = new ArrayList<Button>();
		
		tileMap = new TileMap(32);
		tileMap.loadMap("/Maps/Level" + gsm.getCurrentLevel() + ".map");
		System.out.println("/Maps/Level" + gsm.getCurrentLevel() + ".map");
		
		tileMap.loadTiles("/Tilesets/grasstileset.png");
		tileMap.setPosition(-0, 0);
		
		
		
		players = new ArrayList<MapObject>();
	
		if(gsm.getCurrentLevel() >= 3){
			ji = new Ji(tileMap, true);
			players.add(ji);
			rae = new Rae(tileMap, true);
			players.add(rae);
			
		}
		populateDoors();
		setItUp();
		
		
		bg = new Background("/Backgrounds/cloudbg.png", 0.1);
		bg.setVector(-0.1, 0);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		
		
		startMusic();
		
		//JukeBox.load("Audio/CuriousCritters2.mp3", "level1");
		//JukeBox.loop("level1", 600, JukeBox.getFrames("level1") - 2200);
	}
	public void populateDoors(){
				
		doors = new ArrayList<Door>();

		for(int i = 0; i < doors.size(); i++){
			Door d = doors.get(i);
			doors.remove(d);
		}
		
		if(gsm.getCurrentLevel() == 1){
		door1 = new Door(tileMap, "normal");
		door1.setPosition(580, 355);
		doors.add(door1);
		}
		
		if(gsm.getCurrentLevel() == 2){
		door2 = new Door(tileMap, "normal");
		door2.setPosition(80, 325);
		doors.add(door2);
		}
		
		if(gsm.getCurrentLevel() == 3){
			pinkDoor = new Door(tileMap, "pink");
			pinkDoor.setPosition(380, 322);
			doors.add(pinkDoor);
			greenDoor = new Door(tileMap, "green");
			greenDoor.setPosition(260, 355);
			doors.add(greenDoor);
		}
		
		if(gsm.getCurrentLevel() == 4){
			door4 = new Door(tileMap, "normal");
			door4.setPosition(580, 291);
			doors.add(door4);
		}
		if(gsm.getCurrentLevel() == 5){
			door5 = new Door(tileMap, "normal");
			door5.setPosition(92, 161);
			doors.add(door5);
			System.out.println(door5.isSatisfied());
		}
		
		if(gsm.getCurrentLevel() == 6){
			door6 = new Door(tileMap, "normal");
			door6.setPosition(580, 418);
			doors.add(door6);
			
		}
		
		if(gsm.getCurrentLevel() == 7){
			door7 = new Door(tileMap, "normal");
			door7.setPosition(285, 97);
			doors.add(door7);
			
		}
		
		if(gsm.getCurrentLevel() == 8){
			door8 = new Door(tileMap, "normal");
			door8.setPosition(578, 417);
			doors.add(door8);
		}
		
		if(gsm.getCurrentLevel() == 9){
			door9 = new Door(tileMap, "normal");
			door9.setPosition(305, 97);
			doors.add(door9);
		}
		
		if(gsm.getCurrentLevel() == 10){
			door10 = new Door(tileMap, "normal");
			door10.setPosition(60, 162);
			doors.add(door10);
		}
		
		
		
		
	}

	public void startMusic(){
		if(gsm.getCurrentLevel() == 1){
			bgMusic = new AudioPlayer("/Audio/Music/CuriousCritters2.mp3");
			bgMusic.play();
			setMusicStarted(getMusicStarted() + 1);
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
		setJiStart(new Point(x, y));
	}
	public void raeStartPoints(int x, int y){
		setRaeStart(new Point(x, y));
	}
	
	public void removeAll(){
		for(int i = 0; i < buttons.size(); i++){
			Button b = buttons.get(i);
			buttons.remove(b);
		}
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			walls.remove(w);
		}
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
		
		populateDoors();
		setItUp();
	}
	
	
	public void checkRegularDoor1(){
			if(door1.isSatisfied()){
				gsm.currentLevel = 2;
				doors.remove(door1);
				gsm.setState(GameStateManager.LOADINGSTATE);
			}
		
	}
	public void checkRegularDoor2(){
		if(door2.isSatisfied()){
			gsm.currentLevel = 3;
			doors.remove(door2);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkRegularDoor4(){
		if(door4.isSatisfied()){
			gsm.currentLevel = 5;
			doors.remove(door4);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}

	public void checkRegularDoor5(){
		if(door5.isSatisfied()){
			gsm.currentLevel = 6;
			doors.remove(door5);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkRegularDoor6(){
		if(door6.isSatisfied()){
			gsm.currentLevel = 7;
			doors.remove(door6);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkRegularDoor7(){
		if(door7.isSatisfied()){
			gsm.currentLevel = 8;
			doors.remove(door7);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkRegularDoor8(){
		if(door8.isSatisfied()){
			gsm.currentLevel = 9;
			doors.remove(door8);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkRegularDoor9(){
		if(door9.isSatisfied()){
			gsm.currentLevel = 10;
			doors.remove(door9);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkRegularDoor10(){
		if(door10.isSatisfied()){
			gsm.currentLevel = 11;
			doors.remove(door10);
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
		
	}
	public void checkColoredDoors(){
		if(pinkDoor.isSatisfied() && greenDoor.isSatisfied()){
			players.clear();
			gsm.currentLevel = 4;
			gsm.setState(GameStateManager.LOADINGSTATE);
		}
	}
	public void whatUpdate(){
	
	if(gsm.getCurrentLevel() == 1){
		ji.update();
		door1.checkPlayers(ji, moved);
		checkRegularDoor1();
	}
	
	if(gsm.getCurrentLevel() == 2){
		rae.update();
		door2.checkPlayers(rae, moved);
		checkRegularDoor2();
		}
	
	if(gsm.getCurrentLevel() == 3){
		pinkDoor.checkRae(rae, moved);
		greenDoor.checkJi(ji, moved);
		checkColoredDoors();
		}
	
	if(gsm.getCurrentLevel() == 4){
		door4.checkRae(rae, moved);
		checkRegularDoor4();
		
	}
	
	if(gsm.getCurrentLevel() == 5){
		door5.checkPlayers(ji, rae, moved);
		checkRegularDoor5();
		
	}
	
	if(gsm.getCurrentLevel() == 6){
		door6.checkPlayers(ji, rae, moved);
		checkRegularDoor6();
		ji.checkCatch(rae);
	}
	
	if(gsm.getCurrentLevel() == 7){
		door7.checkPlayers(ji, rae, moved);
		checkRegularDoor7();
		ji.checkCatch(rae);
	}
	
	if(gsm.getCurrentLevel() == 8){
		door8.checkPlayers(ji, rae, moved);
		checkRegularDoor8();
		ji.checkCatch(rae);
	}
	
	if(gsm.getCurrentLevel() == 9){
		door9.checkPlayers(ji, rae, moved);
		checkRegularDoor9();
		ji.checkCatch(rae);
	}
	
	if(gsm.getCurrentLevel() == 10){
		button.checkPress(ji);
		button.updateWall(ji);
		button.update();
		door10.checkPlayers(ji, rae, moved);
		checkRegularDoor10();
	}
	
	
	if(gsm.getCurrentLevel() >= 3){
		ji.update();
		rae.update();
		ji.checkCatch(rae);
		if(ji.fellDead() || rae.fellDead()){
			restart();
			}
		}
	}
	public void update() {
		
		handleInput();
		
		if(gsm.getCurrentLevel() == 4){
			ji.checkCatch(rae);
		}
		
		for(int i = 0; i < pictures.size(); i++){
			Images j = pictures.get(i);
			j.update();
		}
		
		whatUpdate();
		bg.update();
		
		for(int i = 0; i < walls.size(); i++){
			Wall w = walls.get(i);
			if(!w.removed()){
			w.checkCollision(ji);
			w.checkCollision(rae);
			}
			if(w.shouldRemove()){
				walls.remove(wall);
				i--;
			}
		}
		
		
		//wall.checkCollision(ji);
	}
	

	public void draw(Graphics2D g) {
		
		bg.draw(g);
		
		tileMap.draw(g);
	
		for(int i = 0; i < pictures.size(); i++){
			Images j = pictures.get(i);
			j.draw(g);
		}
		
//		pinkDoor.Draw(g);
//		greenDoor.Draw(g);
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

	public int getMusicStarted() {
		return musicStarted;
	}

	public void setMusicStarted(int musicStarted) {
		this.musicStarted = musicStarted;
	}

	public Point getJiStart() {
		return jiStart;
	}

	public void setJiStart(Point jiStart) {
		this.jiStart = jiStart;
	}

	public Point getRaeStart() {
		return raeStart;
	}

	public void setRaeStart(Point raeStart) {
		this.raeStart = raeStart;
	} 	
 	
}
