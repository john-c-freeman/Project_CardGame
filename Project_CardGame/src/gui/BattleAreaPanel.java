package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class BattleAreaPanel extends JPanel {

	public static List<JToggleButton> battleAreaButtons = new ArrayList<>();
	private static List<JLabel> ComputerSetToPlayList = new ArrayList<>();
	private static List<JLabel> ComputerInPlayList = new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public BattleAreaPanel() {
		int tableWidth = 4;
		
		setLayout(new GridLayout(3, 4, 50, 50));
		for (int i = 0; i < tableWidth; i++) {
			ComputerSetToPlayList.add(new JLabel("",SwingConstants.CENTER));
			ComputerInPlayList.add(new JLabel());
		}
		ComputerSetToPlayList.forEach(x -> x.setBorder(new LineBorder(new Color(0, 0, 0))));
		ComputerInPlayList.forEach(x -> x.setBorder(new LineBorder(new Color(0, 0, 0))));
		ComputerSetToPlayList.forEach(x -> x.setPreferredSize(new Dimension(100, 100)));
		ComputerInPlayList.forEach(x -> x.setPreferredSize(new Dimension(100, 100)));

		ComputerSetToPlayList.forEach(x -> add(x));
		ComputerInPlayList.forEach(x -> add(x));
		
		ComputerSetToPlayList.get(0).setText("test");
		
		
		for (int i = 0; i < tableWidth; i++) { // Initializes the 4 BattleArea slots to the list
			battleAreaButtons.add(new JToggleButton());
			add(battleAreaButtons.get(i));
		}
	}

}
