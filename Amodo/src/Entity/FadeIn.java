package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class FadeIn extends MapObject{

	private boolean done = false;
	private ArrayList<BufferedImage[]> sprites;
	
	public FadeIn(TileMap tm, int delay){
		
		super(tm);

		
		width = 640;
		height = 480;
		
		cwidth = 0;
		cheight = 0;
		
		facingRight = true;
		
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/Backgrounds/fade.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 1; i++){
				BufferedImage[] bi =
						new BufferedImage[10];
				for(int j = 0; j < 10; j++){
					bi[j] = spritesheet.getSubimage(
							j * width,
							i * height,
							width,
							height
						);
				}
				sprites.add(bi);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		animation = new Animation();
		animation.setFrames(sprites.get(0));
		animation.setDelay(delay);
	

	}
	
public FadeIn(TileMap tm, String s, int w, int h){
		
		super(tm);

		
		width = w;
		height = h;
		
		cwidth = 0;
		cheight = 0;
		
		facingRight = true;
		
		try{
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(s));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 1; i++){
				BufferedImage[] bi =
						new BufferedImage[1];
				for(int j = 0; j < 1; j++){
					bi[j] = spritesheet.getSubimage(
							j * width,
							i * height,
							width,
							height
						);
				}
				sprites.add(bi);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		animation = new Animation();
		animation.setFrames(sprites.get(0));
		animation.setDelay(60);
	

	}

	public void update(){
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		if(animation.getFrame() == 9){
			done = true;
		}else{
			animation.update();
		}
		
		
	}
	
	public boolean isDone(){
		return done;
	}

	public void setWidth(int w){
		width = w;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void draw(Graphics2D g){
		if(!isDone()){
	setMapPosition();
	super.draw(g);	
		}
	}
}