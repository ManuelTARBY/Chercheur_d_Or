/**
 * 
 */
package controleur;

import java.awt.event.KeyEvent;

/**
 * @author Manuel TARBY
 *
 */
public interface Global {
	
	/**
	 * Largeur de la fenêtre EntreeJeu
	 */
	int LARGEURENTREEJEU =  350;
	
	/**
	 * Hauteur de la fenêtre EntreeJeu
	 */
	int HAUTEURENTREEJEU =  270;
	
	/**
	 * Largeur de la zone de recherche
	 */
	int LARGEURZONE =  570;
	
	/**
	 * Hauteur de la zone de recherche
	 */
	int HAUTEURZONE =  597;
	
	/**
	 * Taille du fond
	 */
	int TAILLEFOND = 560;
	
	/**
	 *  Largeur du bandeau d'info de la zone de recherche
	 */
	int LARGEURBANDEAU = 200;
	
	/**
	 * Durée d'une partie en secondes
	 */
	int DUREEPARTIE = 30;
	
	/**
	 * Maximum de trésor à trouver
	 */
	int MAXTRESOR = 8;
	
	/**
	 * Puissance du bec
	 */
	int BEC = 8;
	
	/**
	 * Extension image
	 */
	String EXTENSION = ".jpg";
	
	/**
	 * Titre du fond
	 */
	String NOMFOND = "Prairie";
	
	/**
	 * Chemin de l'image de fond
	 */
	String CHEMINFOND = NOMFOND + EXTENSION;
	
	/**
	 * Chemin de l'image du trésor
	 */
	String NOMTRESOR = "Carre_rouge" + EXTENSION;
	
	/**
	 * Extension des images
	 */
	String EXTIMAGE = ".png";
	
	/**
	 * En tête du nom de fichier de l'image de Chocobo
	 */
	String CHOCOIMG = "Chocobo";
	
	/**
	 * Valeur pour envoyer le trésor
	 */
	String TRESOR = "Trésor";
	
	/**
	 * Valeur pour envoyer le chocobo
	 */
	String CHOCO = "Choco";
	
	/**
	 * Valeur pour envoyer le message
	 */
	String MESSAGE = "Message";
	
	/**
	 * Valeur pour envoyer le message de profondeur
	 */
	String MSGPROF = "MSGPROF";
	
	/**
	 * Valeur pour envoyer le chrono
	 */
	String CHRONO = "Chrono";
	
	/**
	 * Largeur message profondeur
	 */
	int LARGEURMSGPROF = 90;
	
	/**
	 * Hauteur messagr profondeur
	 */
	int HAUTEURMSGPROF = 20;
	
	/**
	 * Taille du chocobo
	 */
	int TAILLECHOCO = 70;
	
	/**
	 * Taille du trésor
	 */
	int TAILLETRESOR = 1;
	
	/**
	 * gauche
	 */
	int GAUCHE = KeyEvent.VK_LEFT;
	
	/**
	 * haut
	 */
	int HAUT = KeyEvent.VK_UP;
	
	/**
	 * droite
	 */
	int DROITE = KeyEvent.VK_RIGHT;
	
	/**
	 * bas
	 */
	int BAS = KeyEvent.VK_DOWN;
	
	/**
	 * touche entrée (tentative de creusage)
	 */
	int ENTREE = KeyEvent.VK_ENTER;
	
	/**
	 * valeur du pas
	 */
	int PAS = 10;
	
	/**
	 * Palier 1 de recherche
	 */
	int PALIERUN = 50;
	
	/**
	 * Palier 1 de recherche
	 */
	int PALIERDEUX = 120;
	
	/**
	 * Palier 1 de recherche
	 */
	int PALIERTROIS = 200;

}
