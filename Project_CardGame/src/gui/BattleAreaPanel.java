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

public class BattleAreaPanel extends JPanel {

	public static List<JToggleButton> battleAreaButtons = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public BattleAreaPanel() {
		JPanel panel = new JPanel();  
		int tableWidth = 4;
		
		setLayout(new GridLayout(3, 4, 50, 50));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1);
		
		for (int i = 0; i < tableWidth; i++) { // Initializes the 4 BattleArea slots to the list
			battleAreaButtons.add(new JToggleButton());
			add(battleAreaButtons.get(i));
		}

		



	}

}
