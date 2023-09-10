package normalLevel;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.Random;

public class ScorePanel extends JPanel implements ActionListener{
	//For counting time
	Timer timerScore;

	
	private int delay = 100;
	int minutes = 0;
	int seconds = 0;
	int milliseconds = 0;
	String time = minutes + ":" + String.format("%02d",seconds);
	
	public JProgressBar bar = new JProgressBar(0,500);
	
	public Color colorArea = new Color(255, 139, 19);
	public Color colorBorder = new Color(255,96,0);
	public Color colorString = Color.black;
	
	
	public ScorePanel(){
		
		//Progress bar;
		bar = new JProgressBar(0,100);
		bar.setValue(100);
		bar.setBounds(440,27,150,30);
		bar.setStringPainted(true);
		bar.setString("Life = " + MainPanel.life);
		bar.setFont(new Font("Serif", Font.BOLD, 25));
		bar.setForeground(Color.RED);
		bar.setBackground(Color.BLACK);
		
		timerScore = new Timer(delay,this);
		
		this.add(bar);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(600,100));
	}
	
	protected void paintComponent(Graphics g) {
		if(MainPanel.gameOver) {
			gameStop();
			this.setBackground(Color.BLACK);
			this.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			
		}
		else {
			super.paintComponent(g);
			g.setColor(colorString);
			this.setBackground(colorArea);
			this.setBorder(BorderFactory.createLineBorder(colorBorder, 5, true));
		}
		//ShwoTime
		drawTime(g);
		
		//ShowScore
		drawScore(g);
		
		//ShowLife
		drawLife();
		
		if(MainPanel.isInGamePlay) {
			gameStart();
		}
		
	}
	
	public void drawScore(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD,50));
		g.drawString("Score: " + MainPanel.score , 10, g.getFont().getSize()+10);
	}
	
	public void drawLife() {
		bar.setValue(MainPanel.life*20);
		bar.setString(MainPanel.life + " Lives" );
	}
	
	public void drawTime(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD,50));
		g.drawString(time, 260, g.getFont().getSize()+10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==timerScore) {
			milliseconds += delay;
			
			if(milliseconds % 1000 == 0) {
				seconds = Math.addExact(seconds, 1);
				milliseconds = 0;
				
				if(seconds%60==0 && seconds ==60) {
					minutes = Math.addExact(minutes, 1);
					seconds = 0;
				}
				time = minutes + ":" + String.format("%02d",seconds);
				repaint();
			}
		}
	}
	
	public void gameStart() {
		timerScore.start();
	}
	
	public void gameStop() {
		timerScore.stop();
	}
	
}
