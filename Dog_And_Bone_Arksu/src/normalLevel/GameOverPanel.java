package normalLevel;
import java.awt.*;
import javax.swing.*;

public class GameOverPanel extends JPanel {
	
	JLabel gameOver;
	JLabel again;
	public JButton yes;
	public JButton no;
	
	
	public GameOverPanel(){
		gameOver = new JLabel("GAME OVER");
		again = new JLabel("Play again?");
		yes = new JButton("Yes");
		no = new JButton("No");
		
		yes.setFocusable(false);
		no.setFocusable(false);
		
		gameOver.setFont(new Font("Serif", Font.BOLD,75));
		gameOver.setForeground(Color.RED);
		again.setFont(new Font("Serif", Font.BOLD,50));
		again.setForeground(Color.WHITE);
		yes.setFont(new Font("Serif", Font.BOLD,50));
		no.setFont(new Font("Serif", Font.BOLD,50));
		
		gameOver.setBounds(75, 25, 500, 100);
		again.setBounds(175,175,300,100);
		yes.setBounds(125,325,125,100);
		no.setBounds(350,325,125,100);
		
		this.add(gameOver);
		this.add(again);
		this.add(yes);
		this.add(no);
		
		this.setLayout(null);
		this.setBackground(Color.black);
	}

}
