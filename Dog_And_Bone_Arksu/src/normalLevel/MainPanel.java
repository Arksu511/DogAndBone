package normalLevel;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.Random;

public class MainPanel extends JPanel implements ActionListener,MouseMotionListener,MouseListener {
	
	public static boolean isInGamePlay = false;
	public static boolean gameOver = false;
	
	boolean isPause = false;
	
	//Timer for starting
	Timer intro;
	static int duration = 1000;
	int count = 4;
	
	//For dog starting to move
	public boolean stateDog = false;
	public boolean dogDoorEntered = false;
	
	//Timer for moving dog
	Timer timerMain;
	
	//Random the velocity of the dog 
	Random randomDog;
	
	//Random the location of the bone
	Random randomBone;
	
	//Width and height of panel
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	
	//Background
	public Image grass;
	
	//Dog
	Image dog;
	int dogWidth = 50;
	int dogHeight = 50;
	int xDog = WIDTH-dogWidth;
	int yDog = 45;
	public int dogVelo = 10;
	int xVelo = 5;
	int yVelo = 5;
	
	//Bone
	Image bone;
	public int boneWidth = 50;
	public int boneHeight = 50;
	public int xBone;
	public int yBone;
	
	//House
	public int houseWidth = 115;
	public int houseHeight = 100;
	
	
	//Door
	public int doorWidth = 40;
	public int doorHeight = 45;
	public int doorX = 25;
	public int doorY = 45;
	boolean doorEntered;

	//Score & Life
	public static int score;
	public static int life = 5;
	
	
	private int delay = 1;	
	
	//GameOverPanel
	public GameOverPanel gameOverPanel;
	
	public Color colorBackground = new Color(130, 205, 71);
	
	public MainPanel(){
		
		randomDog = new Random();
		randomBone = new Random();
		
		//set Image of the dog. This may have to set the new location of dog according to the location in your Computer
		dog = new ImageIcon(MainPanel.class.getResource("/image/Dog_1.png")).getImage().getScaledInstance(dogWidth, dogHeight, Image.SCALE_SMOOTH);
		
		//set Image of the bone. This may have to set the new location of bone according to the location in your Computer
		bone = new ImageIcon(MainPanel.class.getResource("/image/Bone.png")).getImage().getScaledInstance(boneWidth, boneHeight, Image.SCALE_SMOOTH);
		
		//set Image of background
		grass = new ImageIcon(MainPanel.class.getResource("/image/Grass.jpg")).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		
		//Random the location of the bone
		newBone();

		this.setBackground(new Color(130, 205, 71));
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.addMouseListener(this);
		this.setLayout(new BorderLayout());
		
		
		gameOverPanel = new GameOverPanel();
		
		timerMain = new Timer(delay,this);

		intro = new Timer(duration, this);
		intro.start();
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(grass, 0, 0, this);
		this.setBackground(colorBackground);
		if(isInGamePlay) {

			//drawHouse(g);
			drawHouse(g);
			//drawDogHouse
			drawDogHouse(g);
			
			//CheckDoor
			if(doorEntered) {
				drawOpenDoor(g);
			}
			if(dogDoorEntered) {
				drawDogOpenDoor(g);
			}
			//Draw the dog
			drawDog(g);

			//Draw the bone
			drawBone(g);
		}
		else {
			//Check whether it is game over
			if(gameOver) {
				this.add(gameOverPanel);
				repaint();
			}
			else {
				//This is for intro part (3 2 1 go) 
				g.setFont(new Font("Serif", Font.BOLD,200));
				if(count == 1) {
					g.drawString("GO", 160, g.getFont().getSize()+80);
				}
				else if(count<=0) {
					isInGamePlay = true;
					gameStart();
					intro.stop();
				}
				else {
					g.drawString((count-1)+"", 260, g.getFont().getSize()+80);
				}
			}
		}
	}
	
	public void gameStart() {
		if(isInGamePlay) {
			timerMain.start();
		}
		else {
			intro.start();
		}
	}
	
	public void gameStop() {
		if(isInGamePlay) {
			timerMain.stop();
		}
		else {
			intro.stop();
		}
	}
	
	public void gameOver() {
		gameStop();
		isInGamePlay = false;
		gameOver = true;
		this.removeMouseListener(this);
		this.removeMouseMotionListener(this);
	}
	
	public static void gameReset() {
		gameOver = false;
		isInGamePlay = false;
		score = 0;
		life = 5;	
	}
	
	public void drawDog(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(dog, xDog, yDog, this);
	}
	
	public void drawOpenDoor(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.black);
		g2D.fill3DRect(doorX, doorY, doorWidth, doorHeight, true);
		g2D.setColor(new Color(125, 60, 10));
		g2D.fill3DRect(doorX-doorWidth, doorY, doorWidth, doorHeight, true);
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.black);
		g2D.drawRect(doorX-doorWidth, doorY, doorWidth, doorHeight);
		
	}
	public void drawDogOpenDoor(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.black);
		g2D.fill3DRect(600-90+doorX, doorY, doorWidth, doorHeight, true);
		g2D.setColor(new Color(125, 60, 10));
		g2D.fill3DRect(600-90+doorX-doorWidth, doorY, doorWidth, doorHeight, true);
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.black);
		g2D.drawRect(600-90+doorX-doorWidth, doorY, doorWidth, doorHeight);
	}
	
	
	public void drawHouse(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		int[] xPoints = new int[] {0,85,115,0};
		int[] yPoints = new int[] {1,1,25,25};
		g2D.setColor(new Color(255, 96, 0));
		g2D.fillPolygon(xPoints, yPoints, 4);
		g2D.setColor(Color.black);
		g2D.setStroke(new BasicStroke(3));
		g2D.drawPolygon(xPoints, yPoints, 4);
		g2D.setColor(new Color(86, 43, 8));
		g2D.fill3DRect(0, 25, 90, 70, true);
		g2D.setColor(Color.black);
		g2D.drawRect(0, 25, 90, 70);
		
		
		g2D.setColor(new Color(184, 98, 27));
		g2D.fillRect(5, 30, 80, 60);
		
		//door
		g2D.setColor(new Color(125, 60, 10));
		g2D.fill3DRect(doorX, doorY, doorWidth, doorHeight, true);
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.black);
		g2D.drawRect(doorX, doorY, doorWidth, doorHeight);
		g2D.setColor(new Color(255, 237, 0));
		g2D.fillOval(53, 65, 5, 5);

	}
	
	public void drawDogHouse(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		int[] xPoints = new int[] {600,600-85,600-115,600};
		int[] yPoints = new int[] {1,1,25,25};
		g2D.setColor(new Color(255, 96, 0));
		g2D.fillPolygon(xPoints, yPoints, 4);
		g2D.setColor(Color.black);
		g2D.setStroke(new BasicStroke(3));
		g2D.drawPolygon(xPoints, yPoints, 4);
		g2D.setColor(new Color(86, 43, 8));
		g2D.fill3DRect(600-90, 25, 90, 70, true);
		g2D.setColor(Color.black);
		g2D.drawRect(600-90, 25, 90, 70);
		
		
		g2D.setColor(new Color(184, 98, 27));
		g2D.fillRect(600-90+5, 30, 80, 60);
		
		//door
		g2D.setColor(new Color(125, 60, 10));
		g2D.fill3DRect(600-90+doorX, doorY, doorWidth, doorHeight, true);
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.black);
		g2D.drawRect(600-90+doorX, doorY, doorWidth, doorHeight);
		g2D.setColor(new Color(255, 237, 0));
		g2D.fillOval(600-90+53, 65, 5, 5);

	}
	
	
	public void drawBone(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(bone, xBone, yBone, this);
	}
	
	
	public void newBone() {
		xBone = randomBone.nextInt(WIDTH - boneWidth);
		yBone = randomBone.nextInt(HEIGHT - boneHeight);
		
		// No bone spawns in the house
		if(xBone < houseWidth && yBone < houseHeight) {
			newBone();
		}
		//No bone spawns at the dog
		else if((xDog+(dogWidth/2)) > xBone && (xDog+(dogWidth/2)) < (xBone+ boneWidth) && (yDog+(dogHeight/2)) > yBone && (yDog+(dogHeight/2)) < (yBone + boneHeight)) {
			newBone();
		}
		// No bone spawns in the doghouse
		else if(xBone + boneWidth > WIDTH-houseWidth && yBone < houseHeight) {
			newBone();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isInGamePlay) {
			if(stateDog) {
				//Dog moving
				xDog = Math.addExact(xDog, xVelo);
				yDog = Math.addExact(yDog, yVelo);
			
				if(xDog<0) {
					xVelo = randomDog.nextInt(dogVelo)+5;
					xDog = 0;
				}
				else if(xDog > WIDTH-dog.getWidth(this)) {
					xVelo = -(randomDog.nextInt(dogVelo)+5);
					xDog = WIDTH-dog.getWidth(this);
				}
				
				if(yDog<0) {
					yVelo = randomDog.nextInt(dogVelo)+5;
					yDog = 0;
				}
				else if(yDog > HEIGHT-dog.getHeight(this)) {
					yVelo = -(randomDog.nextInt(dogVelo)+5);
					yDog = HEIGHT-dog.getHeight(this);
				}
				
				if(xDog< houseWidth && yDog < houseHeight) {
					xVelo = randomDog.nextInt(dogVelo)+5;
					yVelo = randomDog.nextInt(dogVelo)+5;
				}
				else if(xDog+dogWidth > WIDTH-houseWidth && yDog < houseHeight ) {
					xVelo = -(randomDog.nextInt(dogVelo)+5);
					yVelo = randomDog.nextInt(dogVelo)+5;

				}

				checkCollision();
				
				checkLife();
				
				repaint();

			
			}
			else {
				// For dog moving out of the house
				xVelo = -2;
				yVelo = 0;
				xDog = xDog + xVelo;
				yDog = yDog + yVelo;
				dogDoorEntered = xDog > 600-90+doorX && xDog < 600-90+doorX+doorWidth;

				// When dog is out of the house
				if(xDog < WIDTH-houseWidth) {
					stateDog = true;
				}
				repaint();
			}
			
		}
		else {
			//Counting (3 2 1 go)
			repaint();
			count--;
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {
		//Click bone to start dragging
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(e.getX() > xBone && e.getX() < bone.getWidth(this)+xBone && e.getY() > yBone && e.getY() < bone.getHeight(this)+yBone) {
				xBone = e.getX()- (boneWidth/2);
				yBone = e.getY()- (boneHeight/2);
				this.addMouseMotionListener(this);
				
				Graphics2D g2D = (Graphics2D) this.getGraphics();
				drawBone(g2D);
			}
		}
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(e.getX() > doorX && e.getX() < doorX+doorWidth && e.getY() > doorY && e.getY() < doorY+doorHeight 
					&& e.getX() > xBone && e.getX() < bone.getWidth(this)+xBone && e.getY() > yBone && e.getY() < bone.getHeight(this)+yBone) {
				score = Math.addExact(score, 1);
				doorEntered = false;
				newBone();
				this.removeMouseMotionListener(this);
			}
			else {
				this.removeMouseMotionListener(this);
			}
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		//Move Bone
		xBone = e.getX()- boneWidth/2;
		yBone = e.getY() - boneWidth/2;
		
		if(xBone<0) {
			xBone = 0;
		}
		else if(xBone>WIDTH-boneWidth) {
			xBone = WIDTH-boneWidth;
		}
		if(yBone<0) {
			yBone = 0;
		}
		else if(yBone>HEIGHT-boneHeight) {
			yBone = HEIGHT-boneHeight;
		}
		
		//Check whether it's at the door
		if(e.getX() > doorX && e.getX() < doorX+doorWidth && e.getY() > doorY && e.getY() < doorY+doorHeight) {
			doorEntered = true;
		}
		else {
			doorEntered = false;
		}
		
		repaint();
	}
	
	public void checkCollision() {
		if((xDog+dogWidth/2) > xBone && (xDog+dogWidth/2)< xBone+ boneWidth && (yDog+dogHeight/2) > yBone && (yDog+dogHeight/2) < yBone + boneHeight) {
			life = Math.decrementExact(life);
			newBone();
			this.removeMouseMotionListener(this);
		}
	}
	
	public void checkLife() {
		if(life==0) {
			gameOver();
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}

	
}
