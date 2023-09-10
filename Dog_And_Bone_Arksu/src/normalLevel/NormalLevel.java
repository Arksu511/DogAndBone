package normalLevel;
import java.awt.*;
import javax.swing.*;

import startGame.StartPanel;

import java.awt.event.*;

public class NormalLevel extends JFrame implements ActionListener,KeyListener{
	ScorePanel scorePanel;
	MainPanel mainPanel;
	PausedMenuPanel menuPanel;
	JPanel gamePanel;
	
	boolean openMenu = false;
	
	public NormalLevel(String title){
		super(title);
		
		scorePanel = new ScorePanel();
		mainPanel = new MainPanel();
		menuPanel = new PausedMenuPanel();
		gamePanel = new JPanel();
		
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(scorePanel,BorderLayout.NORTH);
		gamePanel.add(mainPanel);
		
		menuPanel.resumeButton.addActionListener(this);
		menuPanel.quitButton.addActionListener(this);
		menuPanel.restartButton.addActionListener(this);
		mainPanel.gameOverPanel.yes.addActionListener(this);
		mainPanel.gameOverPanel.no.addActionListener(this);
		
		this.add(gamePanel);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuPanel.resumeButton) {
			mainPanel.gameStart();
			mainPanel.addMouseListener(mainPanel);
			gamePanel.remove(menuPanel);
			gamePanel.add(scorePanel,BorderLayout.NORTH);
			gamePanel.add(mainPanel);
			gamePanel.repaint();
			gamePanel.revalidate();
			openMenu = false;
		}
		
		
		else if(e.getSource() == menuPanel.quitButton) {

			MainPanel.gameReset();
			new StartPanel("Dog and Bone");
			this.dispose();
		}
	
		else if(e.getSource() == mainPanel.gameOverPanel.yes) {
			
			MainPanel.gameReset();
			new NormalLevel("Dog and Bone");
			this.dispose();

			
		}
		
		else if(e.getSource() == menuPanel.restartButton) {
					
			MainPanel.gameReset();
			new NormalLevel("Dog and Bone");
			this.dispose();

		}
		else if(e.getSource() == mainPanel.gameOverPanel.no) {
			MainPanel.gameReset();
			new StartPanel("Dog and Bone");
			this.dispose();

		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//Press esc button
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !openMenu) {

			mainPanel.gameStop();
			scorePanel.gameStop();
			
			mainPanel.removeMouseListener(mainPanel);
			mainPanel.removeMouseMotionListener(mainPanel);
			gamePanel.remove(mainPanel);
			gamePanel.remove(scorePanel); 
			gamePanel.add(menuPanel);
			gamePanel.repaint();
			gamePanel.revalidate();

		}
		
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE && openMenu ) {
			mainPanel.gameStart();

			mainPanel.addMouseListener(mainPanel);
			mainPanel.removeMouseMotionListener(mainPanel);
			gamePanel.remove(menuPanel);
			gamePanel.add(scorePanel,BorderLayout.NORTH);
			gamePanel.add(mainPanel);
			gamePanel.repaint();
			gamePanel.revalidate();
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			openMenu = !openMenu;
		}
		
				
	}

}
