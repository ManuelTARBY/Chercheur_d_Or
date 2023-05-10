/**
 * 
 */
package modele;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Controle;
import controleur.Global;

/**
 * @author Manuel TARBY
 * Classe Choco
 */
public class Choco implements Global, Runnable {
	
	/**
	 * Propriété de type Controle
	 */
	private Controle controle;
	
	/**
	 * JLabel du Choco
	 */
	private JLabel choco;
	
	/**
	 * JLabel du message
	 */
	private JLabel message;
	
	/**
	 * Propriété gérant l'orientation de Choco
	 */
	private String orientation;
	
	/**
	 * Dictionnaire regroupant les trésors qu'il est possible de trouver et le nombre de trésor déterrés pour chacun d'eux
	 */
	private HashMap<String, String> listeDesTresors;
	
	/**
	 * Propriété de type Thread
	 */
	private Thread threadMessage;
	
	/**
	 * Couleur du message
	 */
	private Color couleurMessage;
	
	
	/**
	 * Constructeur de la classe Choco
	 * @param controle Instance de Controle avec laquelle l'instance de Choco va pouvoir interagir
	 */
	public Choco(Controle controle) {
		this.controle = controle;
		this.orientation = "d";
		this.choco = new JLabel();
		this.choco.setBounds(0, 0, TAILLECHOCO, TAILLECHOCO);
		this.message = new JLabel();
		this.message.setSize(TAILLECHOCO, 20);
		this.message.setHorizontalAlignment(SwingConstants.CENTER);
		this.message.setFont(new Font("Comic", Font.BOLD, 12));
		this.couleurMessage = new Color(100, 40, 180);
		this.message.setForeground(couleurMessage);
		this.message.setVisible(false);
		this.listeDesTresors = new HashMap<String, String>();
		initialiseMapTresors(this.controle.recupListe());
		afficheChoco();
		this.threadMessage = new Thread(this);
	}
	
	
	/**
	 * Remplit le dictionnaire des trésors avec leur quantité trouvés
	 * @param tabTresors Tableau qui va servir à remplir les clés du HashMap général
	 */
	public void initialiseMapTresors(String[] tabTresors) {
		for (String unTresor : tabTresors) {
			this.listeDesTresors.put(unTresor, "0");
		}
////	Affiche la table générale dans la console
//		for (Map.Entry<String, String> m : this.listeDesTresors.entrySet()) {
//			System.out.println(m.getKey() + " : " + m.getValue());
//		}
	}
	
	
	/**
	 * Gère l'affichage de choco
	 */
	public void afficheChoco() {
		this.choco.setIcon(new ImageIcon(getClass().getClassLoader().getResource(CHOCOIMG + this.orientation + EXTIMAGE)));
		afficheMessage();
	}
	
	
	/**
	 * Gère l'affichage du message
	 */
	public void afficheMessage() {
		int posY = this.choco.getY() - this.message.getHeight();
		if (this.choco.getY() < this.message.getHeight()) {
			posY = this.choco.getY() + this.choco.getHeight();
		}
		this.message.setLocation(this.choco.getX(), posY);
	}
	
	
	/**
	 * Gèreles déplacement de choco
	 * @param direction Sens du déplacement
	 */
	public void deplacement(int direction) {
		int posX = this.choco.getX();
		int posY = this.choco.getY();
		switch (direction) {
		case HAUT:
			if (posY >= PAS) {
				posY -= PAS;
			}
			break;
		case BAS:
			if (posY <= TAILLEFOND - this.choco.getHeight() - PAS) {
				posY += PAS;
			}
			break;
		case GAUCHE:
			if (this.orientation == "d") {
				this.orientation = "g";
			}
			else {
				if (posX >= PAS) {
					posX -= PAS;
				}
			}
			break;
		case DROITE:
			if (this.orientation == "g") {
				this.orientation = "d";
			}
			else {
				if (posX <= TAILLEFOND - this.choco.getWidth() - PAS) {
					posX += PAS;
				}
			}
			break;
		}
		this.choco.setLocation(posX, posY);
		afficheChoco();
	}
	
	
	/**
	 * Gère les tentatives de creusage
	 * @param tresor JLabel du trésor à trouver
	 */
	public void creuser(Tresor tresor) {
		JLabel lblTresor = tresor.getLblTresor();
		double laDistance = calculDistance(this.choco, lblTresor);
		if ( laDistance <= PALIERUN) {
			this.message.setText("");
			ajouterAuCompteurTresor(tresor.getObjet());
			this.controle.tresorTrouve(tresor.getObjet(), this.listeDesTresors);
		}
		else if (laDistance <= PALIERDEUX) {
			this.message.setText("Kwouaaah !");
			
		}
		else if (laDistance <= PALIERTROIS) {
			this.message.setText("Kwoaah !?");
		}
		else {
			this.message.setText("Kwoah");
		}
		if (this.threadMessage.isInterrupted() == false && this.threadMessage.isAlive()) {
			this.threadMessage.interrupt();
		}
		this.threadMessage = new Thread(this);
		this.threadMessage.start();
	}
	
	
	/**
	 * Calcule la distance entre choco et le trésor
	 * @param choco JLabel du chocobo
	 * @param tresor JLabel du trésor
	 * @return distance entre Choco et le trésor
	 */
	public double calculDistance(JLabel choco, JLabel tresor) {
		double distance, distanceX, distanceY;
		int chocoX;
		chocoX = this.choco.getX();
		if (this.orientation == "d") {
			chocoX += this.choco.getWidth();
		}
		distanceX = Math.pow(chocoX - tresor.getX(), 2);
		distanceY = Math.pow(choco.getY() + choco.getHeight() - tresor.getY(), 2);
		distance = Math.sqrt(distanceX + distanceY);
		return distance;
	}
	
	
	/**
	 * Incrémente l'objet trouvé de 1 dans le HashMap général
	 * @param tresor Le trésor qu'il faut incrémenter de 1
	 */
	public void ajouterAuCompteurTresor(String tresor) {
		int qte = Integer.parseInt(this.listeDesTresors.get(tresor)) + 1;
		this.listeDesTresors.put(tresor, String.valueOf(qte));
	}
	
	
	/**
	 * Renvoie le nombre de trésors trouvés
	 * @return Quantité totale d'objets trouvés
	 */
	public int nbTresor() {
		int qte = 0;
		for (Map.Entry<String, String> m : this.listeDesTresors.entrySet()) {
			if (m.getValue() != "0") {
				qte += Integer.parseInt(this.listeDesTresors.get(m.getKey()));
			}
		}		
		return qte;
	}

	
	/**
	 * Méthode run pour l'affiche / masquage du message à côté du chocobo
	 */
	@Override
	public void run() {
		this.message.setVisible(true);
		this.pause(1200, 0);
		this.message.setVisible(false);
	}
	
	
	/**
	 * détermine le temps de latence entre chaque affichage
	 * @param millisec nombre de millisecondes
	 * @param nanosec nombre de nanosecondes
	 */
	public void pause(long millisec, int nanosec) {
		try {
			Thread.sleep(millisec, nanosec);			
		}
		catch (Exception e){
//			System.out.println("Erreur sur l'appel de la méthode pause()");
		}
	}
	
	
	/**
	 * Renvoie le tableau général des objets trouvés
	 * @return HashMap des objets trouvés
	 */
	public HashMap<String, String> getTableauGeneral(){
		return this.listeDesTresors;
	}
	
	
	/**
	 * Récupère le Jlabel
	 * @return JLabel du choco
	 */
	public JLabel getLblChoco() {
		return this.choco;
	}
	
	/**
	 * Récupère le Jlabel
	 * @return JLabel du message
	 */
	public JLabel getLblMessage() {
		return this.message;
	}

}
