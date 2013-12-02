package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Wall extends MapObject{
	
	private boolean remove = false;
	private ArrayList<BufferedImage[]> sprites;
	
	int opener = 0;
	
	private static final int UP2 = 0;
	private static final int UP3 = 1;
	private static final int SIDE2 = 2;
	private static final int SIDE3 = 3;
	private static final int SINGLE = 4;
	private static final int TRAMPOLINE = 5;
	
	private static String dir;
	private static int wh;
	@SuppressWarnings("unused")
	private static int rx;
	@SuppressWarnings("unused")
	private static int ry;
	@SuppressWarnings("unused")
	private static String cond;
	@SuppressWarnings("unused")
	private static int cnum;
	
	@SuppressWarnings("static-access")
	public Wall(int wh, String dir, String cond, TileMap tm){
		super(tm);

		this.dir = dir;
		this.wh = wh;
		this.cond = cond;
		
		moveSpeed = 0;
		maxSpeed = 0;
		fallSpeed = 0;
		maxFallSpeed = 0;
		
		if(dir.equalsIgnoreCase("vert") || dir.equalsIgnoreCase("vertical")){
			if(wh == 3 || wh == 96){
				height = 96;
				cheight = 96;
				width = 32;
				cwidth = 32;
				currentAction = UP3;
			} else if(wh == 2 || wh == 64){
				height = 64;
				cheight = 64;
				width = 32;
				cwidth = 32;
				currentAction = UP2;
			}else if(wh == 1 || wh == 32){
					height = 32;
					cheight = 32;
					width = 32;
					cwidth = 32;
					currentAction = SINGLE;
				}
			//wh == 
		}else if(dir.equalsIgnoreCase("hor") || dir.equalsIgnoreCase("horizontal")){
			
				if(wh == 3 || wh == 96){
				height = 32;
				cheight = 32;
				width = 96;
				cwidth = 96;
				currentAction = SIDE3;
				}else if(wh == 2 || wh == 64){
					height = 32;
					cheight = 32;
					width = 64;
					cwidth = 64;
					currentAction = SIDE2;
				}else if(wh == 1 || wh == 32){
						height = 32;
						cheight = 32;
						width = 32;
						cwidth = 32;
						currentAction = SINGLE;
					}
			}else if(dir.equalsIgnoreCase("trampoline")){
				height = 32;
				cheight = 32;
				width = 32;
				cwidth = 32;
				currentAction = TRAMPOLINE;
			}
			
			try{
				
				BufferedImage spritesheet = ImageIO.read(
						getClass().getResourceAsStream(
								"/Tilesets/Indivs/Industrial.png"));
				
				sprites = new ArrayList<BufferedImage[]>();
				for(int i = 0; i < 6; i++){
					BufferedImage[] bi = new BufferedImage[1];
					for(int j = 0; j < 1; j++){
						bi[j] = spritesheet.getSubimage(0, downHowMuch(), width, height);
					}
					sprites.add(bi);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			facingRight = true;
			animation = new Animation();
			animation.setFrames(sprites.get(currentAction));
			animation.setDelay(500);
			
		}
	public int downHowMuch(){
		int down = 0;
		if(dir.equalsIgnoreCase("vert") || dir.equalsIgnoreCase("vertical")){
			if(wh == 3 || wh == 96){
				down = 64;
			}else if(wh == 2 || wh == 64){
				down = 160;
			} else {
				down = 224;
			}
		}else if(dir.equalsIgnoreCase("hor") || dir.equalsIgnoreCase("horizontal")){
			if(wh == 3 || wh == 96){
				down = 0;
			}else if(wh == 2 || wh == 64){
				down = 32;
			} else {
				down = 224;
			}
		}else{
			down = 256;
		}
		return down;
	}	

	public void remove(boolean b){remove = b;}
	public boolean removed(){return remove;}
	public boolean shouldRemove(){return remove;}
	
	public void checkCollision(MapObject m){
		
		//System.out.println("should" + removed());
		
			if(dir.equals("vert") || dir.equals("vertical") || dir.equalsIgnoreCase("single")){
				if(intersects(m)){
			 if(!m.facingRight){
				m.setLeft(false);
				m.setVector(.8, m.getDY());
			}else if(m.facingRight){
				m.setRight(false);
				m.setVector(-.8, m.getDY());
					}
				}
			}	
			if(dir.equals("hor") || dir.equalsIgnoreCase("horizontal")){
				if(m.getDY() < 0){
					if(intersects(m)){
					
					m.setVector(m.getDX(), m.getDY() + .25);
				}
			}
		}
			
			if(dir.equalsIgnoreCase("trampoline") || dir.equalsIgnoreCase("tramp")){
				if(m.getx() - 10 < getx() && (m.getx() + m.getCWidth() + 10) > (getx() + getCWidth())){ 
					if(m.gety() + m.getCHeight() - 10 <= gety() &&
							m.gety() + m.getCHeight() >= gety() - 5){
						if(m.getDY() >= 0){
								
						
							m.setDy((int)(m.getDY() - 10));
						
				
					}else if(m.getDY() > 0){
						m.setVector(m.getDX(), m.getDY() + 10);
					
					}
					}
				}
			}
			
	}
	
	public void draw(Graphics2D g){
		if(!shouldRemove()){
		setMapPosition();
		super.draw(g);
		}
	}
	
}
