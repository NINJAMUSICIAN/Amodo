package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entity.Characters.Ji;
import Entity.Characters.Rae;
import Entity.Characters.Zav;
import TileMap.TileMap;

public class Door extends MapObject{
	
	String type;
	private boolean satisfied = false;
	
	private ArrayList<BufferedImage[]> sprites;
	
	public Door(TileMap tm, String type){
		super(tm);
		this.type = type;
		
		width = 44;
		height = 62;
		cwidth = 44;
		cheight = 62;
		
		facingRight = true;
		
		
		String s = null;
		if(type.equalsIgnoreCase("Normal")){
			s = "/Sprites/Doors/Door.png";
		}else if(type.equalsIgnoreCase("Green")){
			s = "/Sprites/Doors/GreenDoor.png";
		} else if(type.equalsIgnoreCase("Pink")){
			s = "/Sprites/Doors/PinkDoor.png";
		} else if(type.equalsIgnoreCase("blue")){
			s = "/Sprites/Doors/BlueDoor.png";
		}else{
			System.out.print("thats not what you call a door");
		}

		try {
			
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
		animation.setDelay(400);
	
	}

	public void setSatisfied(boolean b){satisfied = b;}
	public boolean isSatisfied(){return satisfied;}
	
	public void checkRae(Rae rae, int p){
		if(intersects(rae)){
			setSatisfied(true);
			p = 1;
		}else{
			setSatisfied(false);
		}
		
	}
	public void checkJi(Ji ji, int p){
		if(intersects(ji)){
			setSatisfied(true);
			
			p = 1;
		}else{
			setSatisfied(false);
		}
		
	}
	public void checkZav(Zav zav, int p){
		if(intersects(zav)){
			setSatisfied(true);
			
			p = 1;
		}else{
			setSatisfied(false);
		}
	}
	
	public void checkPlayers(MapObject player, int p){
			if(intersects(player)){
				setSatisfied(true);
				
				p = 1;
			}else{
				setSatisfied(false);
			}
			
			
			
		}	
	public void checkPlayers(MapObject player, MapObject player2, int p){
		if(intersects(player) || intersects(player2)){
			setSatisfied(true);
			p++;
		}else{
			setSatisfied(false);
		}
	}
	public void checkPlayers(MapObject player, MapObject player2, MapObject player3, int p){
		if(intersects(player) || intersects(player2) || intersects(player3)){
			setSatisfied(true);
			p = 1;
		}else{
			setSatisfied(false);
		}
	}
	public void checkPlayers(MapObject player, MapObject player2, MapObject player3, MapObject player4, int p){
		if(intersects(player) || intersects(player2) || intersects(player3) || intersects(player4)){
			setSatisfied(true);
			p = 1;
		}else{
			setSatisfied(false);
		}
	}
	
	public void update(){
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		animation.update();
	}
	
	public void Draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}
	
}
