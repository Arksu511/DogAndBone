package hardLevel;
import java.awt.*;
import javax.swing.*;

import normalLevel.*;
import startGame.*;

import java.awt.event.*;

public class HardLevel extends JFrame implements ActionListener,KeyListener {
	ScorePanel scorePanel;
	MainPanel mainPanel;
	PausedMenuPanel menuPanel;
	JPanel gamePanel;
	
	boolean openMenu = false;
	
	public HardLevel(String title){
		super(title);
		
		scorePanel = new ScoreHard();
		mainPanel = new MainHard();
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
			((MainHard) mainPanel).gameReset();
			new StartPanel("Dog and Bone");
			this.dispose();

		}
	
		else if(e.getSource() == mainPanel.gameOverPanel.yes) {
			((MainHard) mainPanel).gameReset();
			new HardLevel("Dog and Bone");
			this.dispose();
			
		}
		
		else if(e.getSource() == menuPanel.restartButton) {
			((MainHard) mainPanel).gameReset();
			new HardLevel("Dog and Bone");
			this.dispose();
			
		}
		else if(e.getSource() == mainPanel.gameOverPanel.no) {
			((MainHard) mainPanel).gameReset();
			new StartPanel("Dog and Bone");
			this.dispose();

		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Press esc button
		if(e.getKeyCode() == 27 && openMenu == false) {
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
		
		else if(e.getKeyCode() == 27 && openMenu == true ) {
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

