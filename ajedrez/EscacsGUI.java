package ajedrez;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class EscacsGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EscacsGUI frame = new EscacsGUI();
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
	private JButton buttonArray[][] = new JButton[8][8]; // El array de botons que no conté informacio pero aguantara
															// els events i interfaç
	private boolean pieceGot = false;
	private String lastPos = "";
	private JButton lastButt;

	private JPanel panel_left = new JPanel();
	private Taulell jocLogica = new Taulell();
	// private JLabel lblLastMoves = new JLabel("");
	private JLabel lblTorn = new JLabel("Blanques(Minus)");

	public EscacsGUI() {
		super("Escacs amb GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 2, 0, 2 };
		gbl_contentPane.rowHeights = new int[] { 251, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_panel_left = new GridBagConstraints();
		gbc_panel_left.insets = new Insets(0, 0, 0, 5);
		gbc_panel_left.fill = GridBagConstraints.BOTH;
		gbc_panel_left.gridx = 0;
		gbc_panel_left.gridy = 0;

		inicializeGUIBoard(); // Creara la board de numeros

		contentPane.add(panel_left, gbc_panel_left);
		panel_left.setLayout(new GridLayout(8, 8, 0, 0));

		JPanel panel_right = new JPanel();
		GridBagConstraints gbc_panel_right = new GridBagConstraints();
		gbc_panel_right.fill = GridBagConstraints.BOTH;
		gbc_panel_right.gridx = 1;
		gbc_panel_right.gridy = 0;
		contentPane.add(panel_right, gbc_panel_right);
		panel_right.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel_right.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblAsad = new JLabel("Torn Actual : ");
		lblAsad.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAsad);

		lblTorn.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTorn);

		JPanel panel_1 = new JPanel();
		panel_right.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		/*
		 * JLabel lblUltimsMoviments = new JLabel("Ultims moviments:");
		 * GridBagConstraints gbc_lblUltimsMoviments = new GridBagConstraints();
		 * gbc_lblUltimsMoviments.insets = new Insets(5, 5, 5, 0);
		 * gbc_lblUltimsMoviments.gridx = 0; gbc_lblUltimsMoviments.gridy = 0;
		 * panel_1.add(lblUltimsMoviments, gbc_lblUltimsMoviments);
		 * 
		 * 
		 * GridBagConstraints gbc_lblLastMoves = new GridBagConstraints();
		 * gbc_lblLastMoves.gridx = 0; gbc_lblLastMoves.gridy = 1;
		 * panel_1.add(lblLastMoves, gbc_lblLastMoves);
		 */

		JButton btnToYourHeart = new JButton("Reiniciar");
		btnToYourHeart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadGameGUIBoard();
			}
		});
		panel_right.add(btnToYourHeart);

	}

	private void inicializeGUIBoard() { // Inicialitzará els botons
		jocLogica = new Taulell();
		for (int i = 0; i < buttonArray.length; i++) { // Crearem tots els boyons amb el seu event corresponent
			for (int j = 0; j < buttonArray[i].length; j++) {
				try {
					Fitxa fitxaCorresponent = jocLogica.mirarPosicio(i, j);
					buttonArray[i][j] = new JButton(Character.toString(fitxaCorresponent.getNomCurt()));

				} catch (Exception ex) {
					buttonArray[i][j] = new JButton("*");

				}

				buttonArray[i][j].addActionListener(new ButtonActionListener(i, j, jocLogica, this));
				// Cridem a un action listener nou que manara quina es la ubicacio del boto a la
				// funcio de comprovar que hi ha
				panel_left.add(buttonArray[i][j]);
			}
		}
	}

	public void gameOver() { // Cuan tot ja ha acabat congela la partida i carrega el missatge final
		freezeGame();
		if (jocLogica.getGameStatus().equals("WHITEWIN")) {
			gameOverPopUp("HAN GUANYAT EL JUGADOR AMB LES PECES BLANQUES ");
		} else {
			gameOverPopUp("HAN GUANYAT EL JUGADOR AMB LES PECES NEGRES ");
		}
	}

	private void freezeGame() { // Fa que no es pugui interactuar amb el board
		for (int i = 0; i < buttonArray.length; i++) {
			for (int j = 0; j < buttonArray[i].length; j++) {
				buttonArray[i][j].setEnabled(false);
			}
		}
	}

	private void reloadGameGUIBoard() { // Comença una nova partida i recarrega la GUI
		jocLogica = new Taulell();
		for (int i = 0; i < buttonArray.length; i++) {
			for (int j = 0; j < buttonArray[i].length; j++) {
				try {
					Fitxa fitxaCorresponent = jocLogica.mirarPosicio(i, j);
					buttonArray[i][j].setText(Character.toString(fitxaCorresponent.getNomCurt()));

				} catch (Exception ex) {
					buttonArray[i][j].setText("*");
				}
				buttonArray[i][j].setEnabled(true);
			}
		}

		setlblTorn(); // Reinicialitza de qui es torn
	}

	// Getter i setters
	public JButton getButtonFromArray(int row, int col) {
		return buttonArray[row][col];
	}

	public boolean isPieceGot() {
		return pieceGot;
	}

	public void setPieceGot(boolean pieceGot) {
		this.pieceGot = pieceGot;
	}

	public String getLastPos() {
		return lastPos;
	}

	public void setLastPos(String lastPos) {
		this.lastPos = lastPos;
	}

	public JButton getLastButt() {
		return lastButt;
	}

	public void setLastButt(JButton lastButt) {
		this.lastButt = lastButt;
	}

	/*
	 * public void setlblLastMoves(String text) { String preText =
	 * lblLastMoves.getText();; lblLastMoves.setText(preText + " " + text); }
	 */
	public void setlblTorn() { // Un if simple que determinara quin sera el texte de a qui li toca
		lblTorn.setText((jocLogica.isTorn()) ? "Blanques(Mayus)" : "Negres(Minus)");
	}

	/// Popups
	public void loadPopUp(String text) {
		JOptionPane.showMessageDialog(null, text);
	}

	public void gameOverPopUp(String text) { // Carregarem les dues opcions i segons quina prengui es fara nova partida
												// o no
		Object stringArray[] = { "Tornar a jugar", "Sortir" };
		int response = JOptionPane.showOptionDialog(null, text, "GAME OVER", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, stringArray, stringArray[0]);

		if (response == 1) {
			System.exit(0);
		}
		reloadGameGUIBoard();

	}
}

class ButtonActionListener implements ActionListener { // Es qui comunica la logica amb els botons
	// corresponents
	private int row;
	private int col;
	private Taulell jocLogica;
	private EscacsGUI jocVisual;
	private JButton boto;

	public ButtonActionListener(int i, int j, Taulell jocLogica, EscacsGUI jocVisual) {
		this.row = i;
		this.col = j;
		this.jocLogica = jocLogica;
		this.jocVisual = jocVisual;
	}

	public void actionPerformed(ActionEvent e) {
		// Carrega que hi ha al boto, a la primera iteracio mira si es posicio amb
		// fitxa, d'alli guardara la posicio com a string per a cridarla despres
		// Al tornar a cridar la funcio li donarem la segona ubicacio i d'alli s'aplica
		// la llogica

		boto = jocVisual.getButtonFromArray(row, col);
		String txtBoto = boto.getText();
		if (!jocVisual.isPieceGot()) { // Primera iteració
			if (txtBoto.equals("*")) {
				jocVisual.loadPopUp("No es una peça");
				return;
			}
			String colT = jocLogica.lletraEquivalent(col);
			String rowT = String.valueOf(row + 1);
			jocVisual.setLastPos(colT + rowT);
			jocVisual.setLastButt(boto);
			jocVisual.setPieceGot(true);
			return;
		}

		String posicioInicial = jocVisual.getLastPos();
		String posicioDesti = jocLogica.lletraEquivalent(col) + String.valueOf(row + 1);
		if (!jocLogica.moure(posicioInicial, posicioDesti)) {
			jocVisual.loadPopUp("Moviment invalid");
		} else { // En cas de valid, li toca al altre jugador
			JButton cacheButt = jocVisual.getLastButt();
			cacheButt.setText("*");
			Fitxa fitxaCorresponent = jocLogica.mirarPosicio(row, col);
			boto.setText(Character.toString(fitxaCorresponent.getNomCurt()));
			// jocVisual.setlblLastMoves(posicioInicial + " " + posicioDesti);

			jocLogica.switchTorn();
			jocVisual.setlblTorn();

		}
		jocVisual.setPieceGot(false); //Reset de que es el primer torn de nou

		if (!jocLogica.getGameStatus().equals("Playing")) { //Si canvia aquest estat saben que ja ha acabat
			jocVisual.gameOver();
		}

	}

}
