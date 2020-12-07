package gameCommons;

import java.awt.Color;
import java.util.Random;

import environment.EnvInf;

import frog.FrogInf;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	public Boolean lostGame = false ;

	//private IEnvironment environment;
	private EnvInf environment;
	//private IFrog frog;
	private FrogInf frog;
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
	public void setFrog(FrogInf frog){
		this.frog = frog;
	}
	/* public void setFrog(IFrog frog) {
		this.frog = frog;
	}*/

	/**
	 * Lie l'objet environment à la partie
	 * @param environment l'environnement
	 */
	public void setEnvironment(EnvInf environment){
		this.environment = environment;
	}
	/* public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}
	 */

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
			//graphic.endGameScreen("Vous avez perdu");
			graphic.endGameScreen(String.valueOf("Score : "+this.frog.getScore()));
			lostGame = true;
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié c'est est le cas
	 * @return true si la partie est gagnée
	 */
	/*public boolean testWin() {
		if( this.environment.isWinningPosition(this.frog.getPosition())){
			graphic.endGameScreen("Félicitations!");
			return true;
		}
		return false;
	}*/

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de partie
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		//testWin();
	}

	/**
	 *
	 * @return l'environment associé
	 */
	public EnvInf getEnv(){
		return this.environment;
	}

}
