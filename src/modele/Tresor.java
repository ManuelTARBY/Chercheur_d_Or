/**
 * 
 */
package modele;

import java.util.Random;
import java.util.HashMap;

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
	 * Liste des profondeurs
	 */
	private int[] lesProf = {50, 41, 35, 25, 12};
	
	/**
	 * Dictionnaire des trésors: profondeurs
	 */
	private HashMap<String, Integer> profTresor = new HashMap<String, Integer>();

	
	/**
	 * Contructeur
	 */
	public Tresor() {
		this.tresor = new JLabel();
		this.tresor.setSize(TAILLETRESOR, TAILLETRESOR);
		this.random = new Random();
		genererDicoProfTresors();
		genererNouveauTresor();
	}
	
	
	/**
	 * Remplit le dictionnaire trésor: profondeur
	 */
	public void genererDicoProfTresors() {
		for (int i = 0; i < this.lesTresors.length; i++) {
			this.profTresor.put(this.lesTresors[i], this.lesProf[i]);
		}
	}
	
	
	/**
	 * Gère la création d'un nouveau trésor
	 */
	public void genererNouveauTresor() {
		genererPosition();
		genererObjet();
	}
	
	/**
	 * Gère l'affectation d'un objet au trésor
	 */
	public void genererObjet() {
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
	
	/**
	 * Récupère la profondeur du tresor
	 */
	public int getProf() {
		return this.profTresor.get(this.getObjet());
	}
	
}
