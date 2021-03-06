package GameState;

import Audio.JukeBox;
import GameState.Levels.*;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	
	public int currentLevel = 1;
	
	public static final int NUMGAMESTATES = 20;
	public static final int LEVEL1STATE = 0;
	public static final int LEVEL2STATE = 1;
	public static final int LEVEL3STATE = 2;
	public static final int LEVEL4STATE = 3;
	public static final int LEVEL5STATE = 4;
	public static final int LEVEL6STATE = 5;
	public static final int LEVEL7STATE = 6;
	public static final int LEVEL8STATE = 7;
	public static final int LOGOSTATE = 8;
	public static final int MENUSTATE = 9;
	public static final int LOADINGSTATE = 19;
	
	
	public GameStateManager(){
		JukeBox.init();
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = LOGOSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state){
		if(state == LOADINGSTATE){
			gameStates[state] = new LoadingState(this);
		}
		if(state == LEVEL1STATE){
			gameStates[state] = new World1State(this);
		}if(state == LEVEL2STATE){
			gameStates[state] = new World2State(this);
		}if(state == LEVEL3STATE){
			gameStates[state] = new World3State(this);
		}if(state == LEVEL4STATE){
			gameStates[state] = new World4State(this);
		}if(state == LEVEL5STATE){
		        gameStates[state] = new World5State(this);	
		}if(state == LEVEL6STATE){
	        gameStates[state] = new World6State(this);	
		}if(state == LEVEL7STATE){
	        gameStates[state] = new World7State(this);	
		}if(state == LEVEL8STATE){
	        gameStates[state] = new World8State(this);	
		}if(state == LOGOSTATE){
	        gameStates[state] = new GrayBalloonState(this);	
		}if(state == MENUSTATE){
	        gameStates[state] = new MenuState(this);	
		}
	}
	
	private void unloadState(int state){
		gameStates[state] = null;
	}
	
	public int getCurrentLevel(){return currentLevel;}
	
	public void setState(int state){
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	public void update(){
		try{
		gameStates[currentState].update();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void draw(java.awt.Graphics2D g){
		try{
		gameStates[currentState].draw(g);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
