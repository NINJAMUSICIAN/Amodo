package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Audio.AudioPlayer;
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
import TileMap.Background;
import TileMap.TileMap;

public class GrayBalloonState extends GameState {

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
	private Images image1, image2;
	
	private int done = 0;
	private FadeIn fadein;
	private FadeOut fadeout;
	
	private long waitTime;
	
	
	
	private ArrayList<Door> doors;
	private ArrayList<MapObject> players;
	private ArrayList<Wall> walls;
	private ArrayList<Button> buttons;
	private ArrayList<Images> pictures;
	
	private AudioPlayer bgMusic;
	
	public GrayBalloonState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}

	public void init() {
		
		pictures = new ArrayList<Images>();
		
		tileMap = new TileMap(32);
		tileMap.loadMap("/Maps/Intro"  + ".map");
		tileMap.loadTiles("/Tilesets/grasstileset.png");
		tileMap.setPosition(-0, 0);
		
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
		
//		bg = new Background("/Backgrounds/cloudbg.png", 0.1);
//		bg.setVector(-0.1, 0);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		image1 = new Images(tileMap, "/GrayBalloon.png");
		image1.setPosition(320, 240);
		pictures.add(image1);
		
		startMusic();
		
		//JukeBox.load("Audio/CuriousCritters2.mp3", "level1");
		//JukeBox.loop("level1", 600, JukeBox.getFrames("level1") - 2200);
	}
	
	public void startMusic(){
		
			bgMusic = new AudioPlayer("/GrayBalloonSound.mp3");
			bgMusic.play();
			setMusicStarted(getMusicStarted() + 1);
	}
	
	public void stopMusic(){
		bgMusic.stop();
	}
	
	public void update() {
		
		if(!fadein.isDone()){
			if(done == 0){
				waitTime = System.nanoTime();
				done++;
			}
		}
			if(fadein.isDone() && done >= 1){
				long elapsed =
						(System.nanoTime() - waitTime) / 1000000;
					if(elapsed > 6992) {
						fadeout.go();
											}
			}
		fadein.update();
		
		if(!fadeout.isDone()){
			fadeout.update();
			}else if(fadeout.isDone()){
				stopMusic();
				gsm.setState(GameStateManager.LOADINGSTATE);
			}
	
		
		for(int i = 0; i < pictures.size(); i++){
			Images j = pictures.get(i);
			j.update();
		}
	
	}
	

	public void draw(Graphics2D g) {
		
		//bg.draw(g);
		
		tileMap.draw(g);
	
//		for(int i = 0; i < pictures.size(); i++){
//			Images j = pictures.get(i);
//			j.draw(g);
//		}
		
//		pinkDoor.Draw(g);
//		greenDoor.Draw(g);
	
		if(!fadeout.isDone()){
			image1.draw(g);
		}
		if(!fadein.isDone()){
			fadein.draw(g);
			}
	fadeout.draw(g);
	
	}

	public void handleInput() {
	
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
