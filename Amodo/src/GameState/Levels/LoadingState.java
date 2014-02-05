package GameState.Levels;

import java.awt.Graphics2D;

import GameState.GameState;
import GameState.GameStateManager;
import TileMap.Background;

public class LoadingState extends GameState{
	private Background bg;
	public LoadingState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	
	public void init() {
		
		bg = new Background("/Backgrounds/LoadingScreen.png", 0.1);
		
		if(gsm.getCurrentLevel() < 11){
		gsm.setState(GameStateManager.LEVEL1STATE);
		
		} else if(gsm.getCurrentLevel() > 10 && gsm.getCurrentLevel() < 19){
			gsm.setState(GameStateManager.LEVEL2STATE);
		} else if(gsm.getCurrentLevel() > 18 && gsm.getCurrentLevel() < 27){
			gsm.setState(GameStateManager.LEVEL3STATE);
		} else if(gsm.getCurrentLevel() > 26 && gsm.getCurrentLevel() < 41){
			gsm.setState(GameStateManager.LEVEL4STATE);
		} else if(gsm.getCurrentLevel() > 40 && gsm.getCurrentLevel() < 47){
			gsm.setState(GameStateManager.LEVEL5STATE);
		} else if(gsm.getCurrentLevel() > 46 && gsm.getCurrentLevel() < 48){
			gsm.setState(GameStateManager.LEVEL6STATE);
		} else if(gsm.getCurrentLevel() > 47 && gsm.getCurrentLevel() < 52){
			gsm.setState(GameStateManager.LEVEL7STATE);
		}else{
			gsm.setState(GameStateManager.LEVEL8STATE);
		}
	}

	
	public void update() {
		bg.update();
		
	}

	
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		
	}

	
	public void handleInput(){
		
	}

	
	
}
