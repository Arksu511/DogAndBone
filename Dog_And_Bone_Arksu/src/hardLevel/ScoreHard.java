package hardLevel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import normalLevel.MainPanel;
import normalLevel.ScorePanel;

public class ScoreHard extends ScorePanel {
	
	
	public ScoreHard(){
		super.colorArea = new Color(79, 69, 87);
		super.colorBorder = new Color(57, 54, 70);
		super.colorString = Color.white;

	}
	
	public void drawLife() {
		bar.setValue(MainPanel.life*10);
		bar.setString(MainPanel.life + " Lives" );
	}
}
