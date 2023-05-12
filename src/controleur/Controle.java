/**
 * 
 */
package controleur;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import modele.Choco;
import modele.Chrono;
import modele.Tresor;
import vue.EntreeJeu;
import vue.ZoneRecherche;

/**
 * Classe Controle
 * @author Manuel TARBY
 * 
 */
public class Controle implements Global {

	/**
	 * Propriété de type EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu;
	
	/**
	 * Propriété de type ZoneRecherche
	 */
	private ZoneRecherche laZone;
	
	/**
	 * Propriété de type Tresor
	 */
	private Tresor tresor;
	
	/**
	 * Propriété de type Choco
	 */
	private Choco choco;
	
	/**
	 * Propriété de type Chrono
	 */
	private Chrono chrono;
	
	
	/**
	 * @param args Argument du main
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	
	/**
	 * Construteur de Controle
	 */
	public Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}
	
	
	/**
	 * Envoi un JLabel vers la Zone de Recherche
	 * @param message Nom du JLabel qu'on va renvoyer
	 * @return JLabel (tresor, chocobo ou message)
	 */
	public JLabel envoiLabelVersZone(String message) {
		switch (message) {
			case TRESOR:
				return this.tresor.getLblTresor();
			case CHOCO:
				return this.choco.getLblChoco();
			case MESSAGE:
				return this.choco.getLblMessage();
			case CHRONO:
				return this.chrono.getLblChrono();
			default:
				return null;
		}
	}
	
	
	/**
	 * Lance le jeu
	 */
	public void lancerJeu() {
		this.tresor = new Tresor();
		this.choco = new Choco(this);
		this.chrono = new Chrono(this);
		this.laZone = new ZoneRecherche(this);
		this.laZone.setVisible(true);
	}
	
	
	/**
	 * Gère l'appui sur une touche d'action
	 * @param touche Valeur de la touche
	 */
	public void evenementTouche(int touche) {
		switch (touche) {
			case ENTREE:
				this.choco.creuser(this.tresor);
				break;
			default:
				this.choco.deplacement(touche);
				break;
		}
	}
	
	
	/**
	 * Récupère la liste des trésors possibles
	 * @return Liste des trésors qu'il est possible de trouver
	 */
	public String[] recupListe() {
		return this.tresor.getListe();
	}
	
	
	/**
	 * Gère la découverte d'un trésor
	 * @param tresor Nom du dernier trésor ramassé
	 * @param lesTresors HashMap des objets avec quantités trouvées
	 */
	public void tresorTrouve(String tresor, HashMap<String, String> lesTresors) {
		// Arrêt du thread de Chrono
		this.chrono.stop();
		// Affiche le trésor qui vient d'être trouvé
		this.laZone.afficheMessageTresor(tresor, lesTresors);
		// Vérifie que le nombre max de trésor n'ait pas été atteint pour savoir s'il faut générer un nouveau trésor ou terminer la partie
		if (this.choco.nbTresor() < MAXTRESOR) {
			this.tresor.genereNouveauTresor();
			// Reprise du thread de Chrono
			this.chrono.reprise();
		}
		else {
			finDeJeu();
		}
	}
	
	
	/**
	 * Ferme l'application
	 */
	public void finDeJeu() {
		int nbTresors = this.choco.nbTresor();
		// Récupère le tableau général
		HashMap<String, String> lesTresors = this.choco.getTableauGeneral();
		String message = "Fin de partie !";
		// Vérifie combien de trésors ont été trouvés au cours de la partie
		// Si aucun : simple message
		if (nbTresors == 0) {
			message += "\n Vous n'avez pas trouvé de trésors.";
		}
		// Si plusieurs : message composé de la liste des trésors avec leurs quantités
		else {
			message += "\nVous avez trouvé :";
			for (Map.Entry<String, String> m : lesTresors.entrySet()) {
				if (m.getValue() != "0") {
					message += "\n- " + m.getKey() + " x" + m.getValue();
				}
			}
		}
		JOptionPane.showMessageDialog(this.laZone, message);
		// Fermeture de la fenêtre de Zone de Recherche
		this.laZone.dispose();
	}

}
