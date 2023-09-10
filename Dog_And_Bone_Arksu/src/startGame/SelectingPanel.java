package startGame;

import java.awt.*;
import javax.swing.*;

import normalLevel.MainPanel;

import java.awt.event.*;

public class SelectingPanel extends JPanel implements ActionListener {
	
	Image background;
	static JComboBox menu;
	String[] levels;
	JLabel selectLevel;
	JLabel select;
	JLabel output;
	JButton yes;
	JButton no;
	JLabel sure;
	
	public SelectingPanel(){
		yes = new JButton("Yes");
		yes.setFont(new Font("Serif", Font.BOLD,50));
		yes.setBounds(155, 350, 120, 100);
		yes.setFocusable(false);
		
		no = new JButton("No");
		no.setFont(new Font("Serif", Font.BOLD,50));
		no.setBounds(305, 350, 120, 100);
		no.setFocusable(false);
		
		selectLevel = new JLabel("Select Level");
		selectLevel.setFont(new Font("Serif", Font.BOLD,50));
		selectLevel.setBounds(165,30,400,50);
		
		output = new JLabel("");
		output.setFont(new Font("Serif", Font.BOLD,50));
		output.setBounds(105, 190, 400, 50);
		
		sure = new JLabel("Are you sure?");
		sure.setFont(new Font("Serif", Font.BOLD,35));
		sure.setBounds(190,275,250,40);
		
		
		select = new JLabel("Select your level: ");
		select.setFont(new Font("Serif", Font.BOLD,25));
		select.setBounds(120,125,200,30);
		
		levels = new String[]{"Normal (Default)", "Hard"};
		menu = new JComboBox(levels);
		menu.setSelectedItem(null);
		menu.setBounds(310, 125, 150, 30);
		menu.addActionListener(this);
		
		background = new ImageIcon(SelectingPanel.class.getResource("/image/Background.png")).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		
		
		this.setLayout(null);
		this.add(menu);
		this.add(selectLevel);
		this.add(select);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menu) {
			if((String) menu.getSelectedItem() == "Normal (Default)") {
				output.setForeground(Color.gray);
				output.setBounds(105, 190, 400, 50);
				output.setText((String) menu.getSelectedItem());
			}
			else if((String) menu.getSelectedItem() == "Hard") {
				output.setForeground(Color.red);
				output.setBounds(235, 190, 200, 50);
				output.setText((String) menu.getSelectedItem());
			}
			
			this.add(output);
			this.add(sure);
			this.add(yes);
			this.add(no);
			repaint();
		}
	}
}
