package gui;

import game.Alliance;
import game.Computer;
import game.Player;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GuiBoard extends JFrame {

	final static Player player = new Player(5, 5, Alliance.PLAYER);
	private final Computer computer = new Computer(5, 5);
	private JPanel contentPane;
	private JTextField txtOpponentHealth;
	private JTextField txtPlayerHealth;
	private HandPanel panel_hand;
	private JLabel lblTurnCount;
	private JLabel lblMana;
	public static boolean hasDrawn = false;
	private int turn = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBoard frame = new GuiBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 50, 15, 15));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = create_guiBoardLayout();
		contentPane.setLayout(gbl_contentPane);
		
		lblTurnCount = create_turnCount();
		GridBagConstraints gbc_lblTurnCount = create_TurnCountConstraints();
		contentPane.add(lblTurnCount, gbc_lblTurnCount);
		
		JLabel lblOpponentHealth = create_opponentHealthLbl();
		GridBagConstraints gbc_lblOpponentHealth = create_opponentHealthLblConstraints();
		contentPane.add(lblOpponentHealth, gbc_lblOpponentHealth);
		
		BattleAreaPanel panel_battleArea = create_battleAreaPanel();
		GridBagConstraints gbc_panel_BattleArea = create_battleAreaPanelConstraints();
		contentPane.add(panel_battleArea, gbc_panel_BattleArea);

		JButton btnEndTurn = create_endTurnBtn();
		GridBagConstraints gbc_btnEndTurn = create_endTurnBtnConstraints();
		contentPane.add(btnEndTurn, gbc_btnEndTurn);

		panel_hand = create_handPanel();
		GridBagConstraints gbc_panel_Hand = create_handPanelConstraints();
		contentPane.add(panel_hand, gbc_panel_Hand);

		JButton btnDraw = create_drawBtn();
		GridBagConstraints gbc_btnDraw = create_drawBtnConstraints();
		contentPane.add(btnDraw, gbc_btnDraw);
		
		lblMana = create_manaCount();
		GridBagConstraints gbc_lblMana = create_manaCountConstraints();
		contentPane.add(lblMana, gbc_lblMana);
		
		JLabel lblPlayerHealth = create_playerHealthLbl();
		GridBagConstraints gbc_lblPlayerHealth = create_playerHeathLblConstraints();
		contentPane.add(lblPlayerHealth, gbc_lblPlayerHealth);

	}

	private JLabel create_turnCount() {
		JLabel lblTurnCount = new JLabel("Turn Count: " + turn);
		lblTurnCount.setHorizontalAlignment(SwingConstants.LEFT);
		return lblTurnCount;
	}

	private GridBagConstraints create_TurnCountConstraints() {
		GridBagConstraints gbc_lblTurnCount = new GridBagConstraints();
		gbc_lblTurnCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblTurnCount.gridx = 1;
		gbc_lblTurnCount.gridy = 0;
		return gbc_lblTurnCount;
	}

	private JLabel create_manaCount() {
		JLabel lblMana = new JLabel("Mana: " + Integer.toString(player.getMana()));
		return lblMana;
	}

	private GridBagConstraints create_manaCountConstraints() {
		GridBagConstraints gbc_lblMana = new GridBagConstraints();
		gbc_lblMana.insets = new Insets(0, 0, 0, 5);
		gbc_lblMana.gridx = 1;
		gbc_lblMana.gridy = 3;
		return gbc_lblMana;
	}

	/**
	 * Sets the layout of the board
	 * @return
	 */
	private GridBagLayout create_guiBoardLayout() {
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		return gbl_contentPane;
	}

	/**
	 * Creates the "Draw" button
	 * @return
	 */
	private JButton create_drawBtn() {
		JButton btnDraw = new JButton("Draw\r\n");
		btnDraw.addActionListener(new ActionListener() {
			/**
			 * pulls a card from the deck to the players hand. (see HandPanel class) 
			 * The user can only draw one card per turn
			 */
			public void actionPerformed(ActionEvent e) {
				if (hasDrawn == false) {
					panel_hand.addCardToHand();
					hasDrawn = true;
				}
			}
		});
		return btnDraw;
	}

	/**
	 * sets the layout constraints of the "Draw" button.
	 * @return
	 */
	private GridBagConstraints create_drawBtnConstraints() {
		GridBagConstraints gbc_btnDraw = new GridBagConstraints();
		gbc_btnDraw.insets = new Insets(0, 0, 5, 0);
		gbc_btnDraw.gridx = 2;
		gbc_btnDraw.gridy = 2;
		return gbc_btnDraw;
	}

	/**
	 * creates the "End Turn" button
	 * @return
	 */
	private JButton create_endTurnBtn() {
		final int PLAYER_TURN = 0;
		final int COMPUTER_TURN = 1;
		JButton btnEndTurn = new JButton("End Turn\r\n");
		btnEndTurn.addActionListener(new ActionListener() {
			/**
			 * Ends the turn and resets the ability to draw and place a card
			 */
			public void actionPerformed(ActionEvent e) {
				hasDrawn = false;
				HandPanel.hasPlayed = false;
				BattleAreaPanel.battleAreaButtons.forEach(x -> x.setSelected(false));
				turn++;
				player.setMana(player.getMana() + turn);
				
				lblTurnCount.setText("Turn Count: " + turn);
				lblMana.setText("Mana: " + Integer.toString(player.getMana()));

				//Alternate turns and increase mana by number of turns
				if (turn == PLAYER_TURN) {
					player.setNumberOfTurns(player.getNumberOfTurns() + 1);
					//player.setMana(player.getNumberOfTurns());
				} else if (turn == COMPUTER_TURN) {
					computer.setNumberOfTurns(computer.getNumberOfTurns() + 1);
					computer.setMana(computer.getNumberOfTurns());
				}
				//turn = (turn == PLAYER_TURN) ? COMPUTER_TURN : PLAYER_TURN;
			}
		});
		return btnEndTurn;
	}

	/**
	 * sets the layout constraints of the "End Turn" button.
	 * 
	 * @return
	 */
	private GridBagConstraints create_endTurnBtnConstraints() {
		GridBagConstraints gbc_btnEndTurn = new GridBagConstraints();
		gbc_btnEndTurn.insets = new Insets(0, 0, 5, 0);
		gbc_btnEndTurn.gridx = 2;
		gbc_btnEndTurn.gridy = 1;
		return gbc_btnEndTurn;
	}

	/**
	 * Creates the opponents health counter
	 * @return
	 */
	private JLabel create_opponentHealthLbl() {
		JLabel lblOpponentHealth = new JLabel("10\r\n");
		lblOpponentHealth.setFont(new Font("Tahoma", Font.BOLD, 21));
		return lblOpponentHealth;
	}

	/**
	 * Sets the layout location of the opponents health counter
	 * @return
	 */
	private GridBagConstraints create_opponentHealthLblConstraints() {
		GridBagConstraints gbc_lblOpponentHealth = new GridBagConstraints();
		gbc_lblOpponentHealth.insets = new Insets(0, 0, 5, 0);
		gbc_lblOpponentHealth.gridx = 2;
		gbc_lblOpponentHealth.gridy = 0;
		return gbc_lblOpponentHealth;
	}

	/**
	 * Creates the players health counter
	 * @return
	 */
	private JLabel create_playerHealthLbl() {
		JLabel lblPlayerHealth = new JLabel("10");
		lblPlayerHealth.setFont(new Font("Tahoma", Font.BOLD, 21));
		return lblPlayerHealth;
	}

	/**
	 * Sets the layout location of the players health counter
	 * @return
	 */
	private GridBagConstraints create_playerHeathLblConstraints() {
		GridBagConstraints gbc_lblPlayerHealth = new GridBagConstraints();
		gbc_lblPlayerHealth.gridx = 2;
		gbc_lblPlayerHealth.gridy = 3;
		return gbc_lblPlayerHealth;
	}

	/**
	 * Creates the panel that contains the players hand (See HandPanel class)
	 * @return
	 */
	private HandPanel create_handPanel() {
		HandPanel panel_Hand = new HandPanel();
		return panel_Hand;
	}

	/**
	 * Sets the layout of the handPanel
	 * @return
	 */
	private GridBagConstraints create_handPanelConstraints() {
		GridBagConstraints gbc_panel_Hand = new GridBagConstraints();
		gbc_panel_Hand.insets = new Insets(0, 0, 5, 5);
		gbc_panel_Hand.fill = GridBagConstraints.BOTH;
		gbc_panel_Hand.gridx = 1;
		gbc_panel_Hand.gridy = 2;
		return gbc_panel_Hand;
	}

	/**
	 * Creates the panel that contains the Battle Area (See BattleAreaPanel class)
	 * @return
	 */
	private BattleAreaPanel create_battleAreaPanel() {
		BattleAreaPanel panel_BattleArea = new BattleAreaPanel();
		return panel_BattleArea;
	}

	/**
	 * Sets the layout of the BattleAreaPanel
	 * @return
	 */
	private GridBagConstraints create_battleAreaPanelConstraints() {
		GridBagConstraints gbc_panel_BattleArea = new GridBagConstraints();
		gbc_panel_BattleArea.insets = new Insets(0, 0, 5, 5);
		gbc_panel_BattleArea.fill = GridBagConstraints.BOTH;
		gbc_panel_BattleArea.gridx = 1;
		gbc_panel_BattleArea.gridy = 1;
		return gbc_panel_BattleArea;
	}		
}
