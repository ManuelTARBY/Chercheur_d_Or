package vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;

/**
 * @author Manuel TARBY
 * Classe ZoneRecherche
 */
public class ZoneRecherche extends JFrame implements Global {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instance de JPanel qui servira de conteneur
	 */
	private JPanel contentPane;
	
	/**
	 * JLabel du fond
	 */
	private JLabel lblFond;
	
	/**
	 * JLabel du tresor
	 */
	private JLabel lblTresor;
	
	/**
	 * Jabel du chocobo
	 */
	private JLabel choco;
	
	/**
	 * Jlabel du message
	 */
	private JLabel message;
	
	/**
	 * Propriété de type Controle
	 */
	private Controle controle;
	
	/**
	 * Label de l'intro de la colonn de droite
	 */
	private JLabel tempsRestant;
	
	/**
	 * JLabel du chrono
	 */
	private JLabel chrono;
	
	/**
	 * Liste des objets dans le bandeau de droite
	 */
	private JLabel listeObjets;
	
	/**
	 * JLabel du message sur la profondeur
	 */
	private JLabel msgProf;


	/**
	 * Constructeur de ZoneRecherche
	 * @param controle Instance de Controle avec laquelle l'instance de ZoneRecherche va pouvoir interagir
	 */
	public ZoneRecherche(Controle controle) {
		this.controle = controle;
		setResizable(false);
		setTitle("Zone de recherche");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGEURZONE + LARGEURBANDEAU, HAUTEURZONE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.requestFocus();
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		// Création du chocobo
		this.choco = new JLabel();
		this.choco = this.controle.envoiLabelVersZone(CHOCO);
		this.contentPane.add(this.choco);
		
		// Création du message de Choco
		this.message = new JLabel();
		this.message = this.controle.envoiLabelVersZone(MESSAGE);
		this.contentPane.add(this.message);
		
		// Création du message indiquant la profondeur
		this.msgProf = new JLabel();
		this.msgProf.setSize(LARGEURMSGPROF, HAUTEURMSGPROF);
		this.msgProf.setVisible(false);
		this.contentPane.add(this.msgProf);

		// Création du trésor
		this.lblTresor = new JLabel();
		this.lblTresor = this.controle.envoiLabelVersZone(TRESOR);

		this.contentPane.add(this.lblTresor);
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		
		// Création du fond
		this.lblFond = new JLabel();
		lblFond.setBounds(0, 0, TAILLEFOND, TAILLEFOND);
		this.lblFond.setIcon(new ImageIcon(getClass().getClassLoader().getResource(CHEMINFOND)));
		this.contentPane.add(this.lblFond);
		
		// Création de l'en-tête des infos de la colonne de droite
		this.tempsRestant = new JLabel();
		this.tempsRestant.setBounds(TAILLEFOND, 2, LARGEURBANDEAU, 20);
		this.tempsRestant.setText("Temps restant :");
		this.tempsRestant.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(this.tempsRestant);
		
		// Création de l'affichage du chrono
		this.chrono = new JLabel();
		this.chrono = this.controle.envoiLabelVersZone(CHRONO);
		this.chrono.setBounds(TAILLEFOND, this.tempsRestant.getY() + this.tempsRestant.getHeight() + 2, LARGEURBANDEAU, 59);
		this.contentPane.add(this.chrono);
		
		// Création de l'affichage des objets dans le bandeau de droite
		this.listeObjets = new JLabel("Trésors trouvés :");
		this.listeObjets.setBounds(TAILLEFOND + 5, this.chrono.getY() + this.chrono.getHeight() + 10, LARGEURBANDEAU - 10, TAILLEFOND - (this.chrono.getY() + this.chrono.getHeight() + 10));
		this.listeObjets.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(this.listeObjets);
	}
	
	
	/**
	 * Gère les actions du joueur
	 * @param e Touche pressée par le joueur
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int toucheAppuyee = e.getKeyCode();
		if (toucheAppuyee != BAS && toucheAppuyee != DROITE && toucheAppuyee != GAUCHE && toucheAppuyee != HAUT && toucheAppuyee != ENTREE) {
			toucheAppuyee = -1;
		}
		else {
			this.controle.evenementTouche(toucheAppuyee);
		}
	}
	
	
	/**
	 * Affiche un message lors de l'obtention d'un trésor
	 * @param tresor Nom du trésor qui vient d'être ramassé
	 * @param tabComplet HashMap des objets avec quantités trouvées
	 */
	public void afficheMessageTresor(String tresor, HashMap<String, String> tabComplet) {
		JOptionPane.showMessageDialog(this, "Vous avez trouvé : \n" + "- " + tresor);
		majListeObjets(tabComplet);
	}
		
		
	/**
	 * Met à jour la liste des trésors trouvés
	 * @param tabGeneral HashMap des objets avec quantités trouvées
	 */
	public void majListeObjets(HashMap<String, String> tabGeneral) {
		String message = "<html>Trésors trouvés :";
		for (Map.Entry<String, String> m : tabGeneral.entrySet()) {
			if (m.getValue() != "0") {
				message += "<br>- " + m.getKey() + " x" + m.getValue();
			}
		}
		message += "</html";
		this.listeObjets.setText(message);
	}
	
	
	/**
	 * Permet de récupérer le message sur la profondeur
	 * @return JLabel sur la profondeur
	 */
	public JLabel getMsgProf() {
		return this.msgProf;
	}
	
	
	/**
	 * Permet de modifier le message sur la profondeur
	 * @param prof Profondeur du trésor
	 */
	public void setMsgProf(String prof) {
		this.msgProf.setText("Profondeur : " + prof);
	}
}
