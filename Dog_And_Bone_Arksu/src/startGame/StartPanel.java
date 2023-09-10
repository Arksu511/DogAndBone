package startGame;

import java.awt.*;
import javax.swing.*;

import hardLevel.HardLevel;
import hardLevel.MainHard;
import normalLevel.MainPanel;
import normalLevel.NormalLevel;

import java.awt.event.*;

public class StartPanel extends JPanel implements ActionListener {
	JLabel label;
	
	Timer timer;
	
	Image background;
	
	Image dog;
	int dogX = 25;
	int dogY = 150;
	
	int jump = 15;
	
	Image bone;
	int boneX = 450;
	int boneY = 140;
	
	JButton start;
	JButton quit;
	JFrame gameFrame;
	
	boolean up = false;
	
	private int delay = 300;
	
	SelectingPanel select;
	
	public StartPanel(String title){
		
		select = new SelectingPanel();
		
		gameFrame = new JFrame(title);
		label = new JLabel("Dog and Bone");
		start = new JButton("Start");
		quit = new JButton("Quit");
		timer = new Timer(delay,this);
		
		dog = new ImageIcon(StartPanel.class.getResource("/image/Dog_1.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		bone = new ImageIcon(StartPanel.class.getResource("/image/Bone.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		background = new ImageIcon(StartPanel.class.getResource("/image/Background.png")).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
	
		label.setFont(new Font("Serif", Font.BOLD,50));
		start.setFont(new Font("Serif", Font.BOLD,50));
		quit.setFont(new Font("Serif", Font.BOLD,50));
		
		label.setBounds(155,50,400,100);
		start.setBounds(215,200,175,100);
		quit.setBounds(215,350,175,100);
		
		start.setFocusable(false);
		quit.setFocusable(false);
		
		this.setLayout(null);
		this.add(label);
		this.add(start);
		this.add(quit);
		
		
		start.addActionListener(this);
		quit.addActionListener(this);

		gameFrame.setLayout(new GridLayout(1,1));
		gameFrame.add(this);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(new Dimension(600,600));
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		select.yes.addActionListener(this);
		select.no.addActionListener(this);
		timer.start();
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
		g.drawImage(dog, dogX, dogY, this);
		g.drawImage(bone, boneX, boneY, this);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer) {
			if(up) {
				dogY = dogY - jump;
				boneY = boneY - jump;
				up = false;
				repaint();
			}
			else {
				dogY = dogY + jump;
				boneY = boneY + jump;
				up = true;
				repaint();
			}
		}
		
		else if(e.getSource() == start) {
			timer.stop();
			gameFrame.remove(this);
			// continue to selecting panel
			gameFrame.add(select);
			// update Component for JFrame
			SwingUtilities.updateComponentTreeUI(gameFrame);
		}
		else if(e.getSource() == quit) {
			timer.stop();
			gameFrame.dispose();
		}
		
		else if(e.getSource() == select.yes) {
			if((String) SelectingPanel.menu.getSelectedItem() == "Normal (Default)") {
				MainPanel.gameReset();
				new NormalLevel("Dog and Bone");
				gameFrame.dispose();

			}
			else if((String) SelectingPanel.menu.getSelectedItem() == "Hard") {
				MainHard.gameReset();
				new HardLevel("Dog and Bone");
				gameFrame.dispose();

			}	
		}
		
		else if(e.getSource() == select.no) {
			gameFrame.remove(select);
			gameFrame.add(this);
			timer.start();
			SwingUtilities.updateComponentTreeUI(gameFrame);
		}
		
	}
	
	public static void main(String[] args) {
		new StartPanel("Dog and Bone");

	}
}
