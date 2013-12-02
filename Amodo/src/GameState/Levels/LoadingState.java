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
		} else if(gsm.getCurrentLevel() > 18){
			gsm.setState(GameStateManager.LEVEL3STATE);
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
