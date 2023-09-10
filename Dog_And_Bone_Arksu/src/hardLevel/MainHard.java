package hardLevel;

import java.awt.*;
import javax.swing.*;

import normalLevel.MainPanel;
import normalLevel.ScorePanel;

import java.awt.event.*;
import java.util.Random;

public class MainHard extends MainPanel{
	Image dog2;
	int dogWidth2 = 50;
	int dogHeight2 = 50;
	int xDog2 = WIDTH-dogWidth2;
	int yDog2 = 45;
	int dogVelo2 = 20;
	int xVelo2 = 5;
	int yVelo2 = 5;
	
	boolean stateDog2 = false;
	
	Random randomDog2;
	Random randomColor;
	
	public MainHard() {
		super();
		dog2 = new ImageIcon(MainHard.class.getResource("/image/Dog_1.png")).getImage().getScaledInstance(dogWidth2, dogHeight2, Image.SCALE_SMOOTH);
		super.grass = new ImageIcon(MainHard.class.getResource("/image/Brick.png")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		randomDog2 = new Random();
		super.dogVelo+=10;
		randomColor = new Random();
		MainPanel.life = 10;
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.red);
		super.paintComponent(g);
		
	}
	
	public void drawDog(Graphics g) {
		super.drawDog(g);
		g.drawImage(dog2, xDog2, yDog2, this);
	}
	
	public void checkCollision() {
		super.checkCollision();
		if((xDog2+dogWidth2/2) > xBone && (xDog2+dogWidth2/2)< xBone+ boneWidth && (yDog2+dogHeight2/2) > yBone && (yDog2+dogHeight2/2) < yBone + boneHeight) {
			life--;
			newBone();
			this.removeMouseMotionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		if(isInGamePlay) {
			if(stateDog2) {
				//Dog moving
				xDog2 = Math.addExact(xDog2, xVelo2);
				yDog2 = Math.addExact(yDog2, yVelo2);
			
				if(xDog2<0) {
					xVelo2 = randomDog2.nextInt(dogVelo2)+5;
					xDog2 = 0;
				}
				else if(xDog2 > WIDTH-dog2.getWidth(this)) {
					xVelo2 = -(randomDog2.nextInt(dogVelo2)+5);
					xDog2 = WIDTH-dog2.getWidth(this);
				}
				
				if(yDog2<0) {
					yVelo2 = randomDog2.nextInt(dogVelo2)+5;
					yDog2 = 0;
				}
				else if(yDog2 > HEIGHT-dog2.getHeight(this)) {
					yVelo2 = -(randomDog2.nextInt(dogVelo2)+5);
					yDog2 = HEIGHT-dog2.getHeight(this);
				}
				
				if(xDog2< houseWidth && yDog2 < houseHeight) {
					xVelo2 = randomDog2.nextInt(dogVelo2)+5;
					yVelo2 = randomDog2.nextInt(dogVelo2)+5;
					
				}
				
				else if(xDog2+dogWidth2 > WIDTH-houseWidth && yDog2 < houseHeight ) {
					xVelo2 = -(randomDog2.nextInt(dogVelo2)+5);
					yVelo2 = randomDog2.nextInt(dogVelo2)+5;

				}

				checkCollision();
				
				checkLife();
				
				repaint();

			
			}
			else {
				xVelo2 = -2;
				yVelo2 = 0;
				xDog2 = xDog2 + xVelo2;
				dogDoorEntered = xDog2 > 600-90+doorX && xDog2 < 600-90+doorX+doorWidth;
				
				
				if(xDog2 < WIDTH-houseWidth) {
					stateDog2 = true;
				}
				
				repaint();
			}
		}

	}
	public void drawDogHouse(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		int[] xPoints = new int[] {600,600-85,600-115,600};
		int[] yPoints = new int[] {1,1,25,25};
		g2D.setColor(new Color(randomColor.nextInt(256),randomColor.nextInt(256),randomColor.nextInt(256)));
		g2D.fillPolygon(xPoints, yPoints, 4);
		g2D.setColor(Color.black);
		g2D.setStroke(new BasicStroke(3));
		g2D.drawPolygon(xPoints, yPoints, 4);
		g2D.setColor(new Color(86, 43, 8));
		g2D.fill3DRect(600-90, 25, 90, 70, true);
		g2D.setColor(Color.black);
		g2D.drawRect(600-90, 25, 90, 70);
		
		
		g2D.setColor(new Color(randomColor.nextInt(256),randomColor.nextInt(256),randomColor.nextInt(256)));
		g2D.fillRect(600-90+5, 30, 80, 60);
		
		//door
		g2D.setColor(new Color(randomColor.nextInt(256),randomColor.nextInt(256),randomColor.nextInt(256)));
		g2D.fill3DRect(600-90+doorX, doorY, doorWidth, doorHeight, true);
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.black);
		g2D.drawRect(600-90+doorX, doorY, doorWidth, doorHeight);
		g2D.setColor(new Color(randomColor.nextInt(256),randomColor.nextInt(256),randomColor.nextInt(256)));
		g2D.fillOval(600-90+53, 65, 5, 5);

	}
	public static void gameReset() {
		MainPanel.gameReset();
		life = 10;
		
	}

}
