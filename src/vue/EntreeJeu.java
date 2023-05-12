package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import javax.swing.JButton;

/**
 * Classe EntreeJeu
 * @author Don_F
 *
 */
public class EntreeJeu extends JFrame implements Global {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Propriété Controle
	 */
	private Controle controle;
	
	/**
	 * Propriété JPanel utilisée pour le contentPane
	 */
	private JPanel contentPane;
	
	/**
	 * Propriété JLabel pour le message d'accueil
	 */
	private JLabel messageBienvenue;
	
	/**
	 * Propriété JLabel pour le message de présentation
	 */
	private JLabel presentation;


	/**
	 * Constructeur de EntreeJeu
	 * @param controle Instance de Controle avec laquelle l'instance de EntreeJeu pourra interagir
	 */
	public EntreeJeu(Controle controle) {
		this.controle = controle;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Bienvenue | Chercheur d'or");
		setSize(LARGEURENTREEJEU, HAUTEURENTREEJEU);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.messageBienvenue = new JLabel();
		this.messageBienvenue.setBounds(5, 5, LARGEURENTREEJEU - 20, 20);
		this.messageBienvenue.setText("Bienvenue !");
		this.messageBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		Font fontEntreeJeu = new Font("Arial", Font.BOLD, 13);
		this.messageBienvenue.setFont(fontEntreeJeu);
		contentPane.add(this.messageBienvenue);
		this.presentation = new JLabel();
		this.presentation.setBounds(5, 30, 330, 130);
		contentPane.add(this.presentation);
		
		// Création du bouton "Compris"
		JButton btnCompris = new JButton("Compris !");
		btnCompris.setSize(100, 30);
		btnCompris.setLocation(this.getWidth() / 2 - btnCompris.getWidth() / 2, this.presentation.getY() + this.presentation.getHeight() + 15);
		// Ecouteur au clic sur le bouton
		btnCompris.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lancementJeu();
			}
		});
		
		// Ecouteur sur touche pressée
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		contentPane.add(btnCompris);
		afficherMessage();
	}
	
	
	/**
	 * Insère le message d'information dans la fenêtre d'accueil
	 */
	public void afficherMessage() {
		String message = "<html> Parcourez toute la zone à la recherche de"
				+ "<br> ses trésors ! Les messages de votre chercheur"
				+ "<br> vous donnent des indications sur la distance"
				+ "<br> qui vous sépare du trésor."
				+ "<br> Temps de recherche : " + DUREEPARTIE + " secondes."
				+ "<br> Nombre maximum de trésors : " + MAXTRESOR + "."
				+ "<br> Touches directionnelles : se déplacer."
				+ "<br> Touche entrée : creuser.</html>";
		this.presentation.setVerticalAlignment(SwingConstants.TOP);
		this.presentation.setHorizontalAlignment(SwingConstants.CENTER);
		this.presentation.setText(message);
	}
	
	
	/**
	 * Gère la pression sur la touche Entrée
	 * @param e touchée pressée par le joueur
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int toucheAppuyee = e.getKeyCode();
		if (toucheAppuyee == ENTREE) {
			lancementJeu();
		}
		else {
			toucheAppuyee = -1;
		}
	}
	
	
	/**
	 * Envoi la demande de lancement du jeu au controle lorsque le bouton "Compris !" est cliqué
	 */
	public void lancementJeu() {
		this.dispose();
		this.controle.lancerJeu();
	}
}
