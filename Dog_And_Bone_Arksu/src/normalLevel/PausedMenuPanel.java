package normalLevel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PausedMenuPanel extends JPanel{
	public JButton resumeButton;
	public JButton quitButton;
	public JButton restartButton;
	
	JLabel label;
	
	Image menubackground;
	
	public PausedMenuPanel(){
		resumeButton = new JButton("Resume");
		quitButton = new JButton("Quit");
		restartButton = new JButton("Restart");
		
		label = new JLabel("PAUSE");
		label.setFont(new Font("Serif", Font.BOLD,50));
		resumeButton.setFont(new Font("Serif", Font.BOLD,20));
		quitButton.setFont(new Font("Serif", Font.BOLD,20));
		restartButton.setFont(new Font("Serif", Font.BOLD,20));
		
		resumeButton.setFocusable(false);
		quitButton.setFocusable(false);
		restartButton.setFocusable(false);
		
		
		menubackground = new ImageIcon(PausedMenuPanel.class.getResource("/image/Menu.png")).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		
		label.setBounds(240,60,200,50);
		resumeButton.setBounds(245, 150, 150, 75);
		restartButton.setBounds(245,300,150,75);
		quitButton.setBounds(245, 450, 150, 75);
		
		
		this.add(label);
		this.add(resumeButton);
		this.add(quitButton);
		this.add(restartButton);
		this.setLayout(null);
		this.setBackground(new Color(141, 123, 104));
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(menubackground, 0, 0, this);
	}
}
