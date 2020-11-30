package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilisés
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	//constructeur--------------------------------------------------

	/**
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	//méthodes-------------------------------------------------------

	/**
	 * Lie l'objet frog à la partie
	 * @param frog la grenouille
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment à la partie
	 * @param environment l'environnement
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si c'est le cas
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if(!this.environment.isSafe(this.frog.getPosition())) {
			this.graphic.endGameScreen("Vous avez perdu");
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié c'est est le cas
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		if( this.environment.isWinningPosition(this.frog.getPosition()) ){
			this.graphic.endGameScreen("Félicitations!");
			return true;
		}
		return false;
	}


	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de partie
	 */
	public void update() {
		graphic.clear();		//pour pas que tout s'accumule dans le temps
		environment.update();	//update des voitures
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN)); //update graphique frog
		testLose();
		testWin();
	}
}
