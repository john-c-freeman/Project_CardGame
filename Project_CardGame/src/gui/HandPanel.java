package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class HandPanel extends JPanel {
	private List<JButton> listHand = new ArrayList<>();
	private List<ImageIcon> listHandIcon = new ArrayList<ImageIcon>();

	//private ImageIcon cardSelectedIcon = new ImageIcon(); // 

	private String[] files = { "/gui/Resources/test1.png", "/gui/Resources/test2.png", "/gui/Resources/test3.png",
			"/gui/Resources/test1.png", "/gui/Resources/test2.png", "/gui/Resources/test3.png" };

	private int index = 0;
	private int totalSlotsAvailable = 7; // only needed if we want to restrict the number of cards a player can hold
	public int currentSlotInHand = 0; // the package knows how many cards are in the players hands
	public static List<Boolean> listBattleAreaSlotAvailable = new ArrayList<>(Arrays.asList(true, true, true, true));; // Check if there is a card already on the board for that index
	public static boolean hasPlayed = false; // Once the player places a card, they can no longer place a card until the next turn
	/**
	 * 
	 * Creates the panel. (Constructor)
	 */
	public HandPanel() {
		setBorder(new EmptyBorder(100, 0, 0, 0));
		GridBagLayout gridBagLayout = create_handLayout();
		setLayout(gridBagLayout);
	}
	
	/**
	 * Sets the layout of the Hand JPanel
	 * @return
	 */
	private GridBagLayout create_handLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		return gridBagLayout;
	}

	/**
	 * Creates a new card and adds the card to the Hand List
	 * @return
	 */
	public JButton create_Card() {
		JButton btnNewButton = new JButton();
		btnNewButton.setPreferredSize(new Dimension(100, 100)); // set's the button to be a reasonable size
		listHand.add(btnNewButton); // Adds a new JButton (Card) to the Hand List
		add(listHand.get(listHand.size() - 1)); // Add that same new Jbutton (Card) to the JPanel
		
		listHand.get(listHand.size() - 1).addActionListener(new ActionListener() { // Once the card is chosen
			public void actionPerformed(ActionEvent e) {
				handToBattleArea(0); // Play the card to the Battle Area index 0 
				handToBattleArea(1);
				handToBattleArea(2);
				handToBattleArea(3);
			}

			/**
			 * 
			 * @param areaIndex
			 */
			private void handToBattleArea(int areaIndex) {
				if (hasPlayed == false) {
					if (BattleAreaPanel.battleAreaButtons.get(areaIndex).isSelected() && listBattleAreaSlotAvailable.get(areaIndex) == true) {
						BattleAreaPanel.battleAreaButtons.get(areaIndex).setIcon(listHandIcon.get(listHand.size() - 1));   //ERROR it always takes the last card pulled
						listHand.get(listHand.size() - 1).setVisible(false);; // Hides the card so it no longer appears in players hand
						
						hasPlayed = true;
						BattleAreaPanel.battleAreaButtons.get(areaIndex).setSelected(false);
						listBattleAreaSlotAvailable.set(areaIndex, false);
					}
				}
			}
		});
		return btnNewButton;
	}

	/**
	 * The Gui has a button "Deck". When "Deck is pressed a card is added with (addcard()) to the players hand. The card is created with an Icon and the class counts the card
	 * soas to know how many cards are in the players hand. 
	 */
	public void addCardToHand() {
		listHandIcon.add(new ImageIcon(HandPanel.class.getResource(files[index]))); // adds new image to list Icon
		create_Card(); // Creates a new card
		listHand.get(currentSlotInHand).setIcon(listHandIcon.get(currentSlotInHand)); // sets the icon to the new card

		currentSlotInHand = ++currentSlotInHand % totalSlotsAvailable; // allows the program to keep trying regardless of how small our minimum slots allowed value is. 
		index = ++index % files.length; // allows the program to keep trying regardless if the deck is out of cards. 
	}
}
