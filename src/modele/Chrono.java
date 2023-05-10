/**
 * 
 */
package modele;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Controle;
import controleur.Global;

/**
 * @author Manuel TARBY
 * Classe Chrono
 */
public class Chrono implements Global, Runnable {
	
	/**
	 * Proriété de Controle
	 */
	private Controle controle;
	
	/**
	 * Durée de la partie
	 */
	private int tempsRestant;
	
	/**
	 * JLabel du chrono
	 */
	private JLabel chrono;
	
	/**
	 * Font du Chrono
	 */
	private Font fontChrono;
	
	/**
	 * Thread du chrono
	 */
	private Thread threadChrono;
	
	/**
	 * Propriété pour arrêter le Thread
	 */
	private boolean finDemandee;
	
	/**
	 * Couleur du temps restant
	 */
	private Color colorChrono;
	
	
	/**
	 * Constructeur de la classe Chrono
	 * @param controle Instance de Controle avec laquelle l'instance de Chrono pourra communiquer
	 */
	public Chrono(Controle controle) {
		this.controle = controle;
		this.tempsRestant = DUREEPARTIE;
		this.chrono = new JLabel();
		this.fontChrono = new Font("Arial", Font.BOLD, 50);
		this.chrono.setFont(fontChrono);
		this.chrono.setHorizontalAlignment(SwingConstants.CENTER);
		this.chrono.setVerticalAlignment(SwingConstants.TOP);
		this.finDemandee = false;
		this.threadChrono = new Thread(this);
		this.threadChrono.start();
	}
	
	
	/**
	 * Renvoie le JLabel du Chrono
	 * @return JLabel du Chrono
	 */
	public JLabel getLblChrono() {
		return this.chrono;
	}

	
	/**
	 * Méthode run pour le Chrono
	 */
	@Override
	public void run() {
		do  {
			this.chrono.setText(String.valueOf(this.tempsRestant - 1));
			this.colorChrono = new Color(0 + (255 / DUREEPARTIE) * (DUREEPARTIE - this.tempsRestant), 255 - (255 / DUREEPARTIE) * (DUREEPARTIE - this.tempsRestant), 50 - (50 / DUREEPARTIE) * (DUREEPARTIE - this.tempsRestant));
			this.chrono.setForeground(this.colorChrono);
			this.pause(1000, 0);
			this.tempsRestant--;
		} while (this.tempsRestant > 0 && (!this.finDemandee));
		if (this.tempsRestant == 0) {
			this.controle.finDeJeu();
		}
	}
	
	
	/**
	 * Arrêt du chrono
	 */
	public void stop() {
		this.finDemandee = true;
		this.threadChrono.interrupt();
	}
	
	
	/**
	 * Reprise du chrono
	 */
	public void reprise() {
		this.tempsRestant++;
		this.finDemandee = false;
		this.threadChrono = new Thread(this);
		this.threadChrono.start();
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

}
