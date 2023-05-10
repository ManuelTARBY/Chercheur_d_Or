/**
 * 
 */
package modele;

import java.util.Random;

import javax.swing.JLabel;

import controleur.Global;

/**
 * @author Manuel TARBY
 * Classe Tresor
 */
public class Tresor implements Global {
	
	/**
	 * JLabel du trésor
	 */
	private JLabel tresor;
	
	/**
	 * Propriété Random
	 */
	private Random random;
	
	/**
	 * Proriété qui va stocker le trésor
	 */
	private String unTresor;
	
	/**
	 * Liste des trésors
	 */
	private String[] lesTresors = {"Diamant", "Pépite d'or", "Pépite d'argent", "Pépite de cuivre", "Morceau de charbon"};

	
	/**
	 * Contructeur
	 */
	public Tresor() {
		this.tresor = new JLabel();
		this.tresor.setSize(TAILLETRESOR, TAILLETRESOR);
		this.random = new Random();
		genereNouveauTresor();
	}
	
	/**
	 * Gère la création d'un nouveau trésor
	 */
	public void genereNouveauTresor() {
		genererPosition();
		genereObjet();
	}
	
	/**
	 * Gère l'affectation d'un objet au trésor
	 */
	public void genereObjet() {
		int nb = this.random.nextInt(100);
		int indice = this.lesTresors.length - 1;
		if (nb < 5) {
			indice = 0;
		}
		else if (nb < 18) {
			indice = 1;
		}
		else if (nb < 40) {
			indice = 2;
		}
		else if (nb < 65) {
			indice = 3;
		}
		this.unTresor = this.lesTresors[indice];
	}
	
	/**
	 * Génère une position aléatoire pour le JLabel du trésor
	 */
	public void genererPosition() {
		int posX = this.random.nextInt(TAILLEFOND - TAILLETRESOR);
		int posY = this.random.nextInt(TAILLEFOND - TAILLECHOCO - TAILLETRESOR) + TAILLECHOCO;
		this.tresor.setLocation(posX, posY);
	}
	
	/**
	 * Permet de récupérer le Jlabel du trésor
	 * @return JLabel du trésor
	 */
	public JLabel getLblTresor() {
		return this.tresor;
	}
	
	/**
	 * Permet de récupérer l'objet
	 * @return Objet du trésor
	 */
	public String getObjet() {
		return this.unTresor;
	}
	
	/**
	 * Récupère la liste complète des trésors possibles
	 * @return Liste de tous les trésors
	 */
	public String[] getListe() {
		return this.lesTresors;
	}
	
}
