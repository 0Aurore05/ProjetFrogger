package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Environment;
import frog.Frog;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;						//largeur du jeu (x)
		int height = 20;					//hauteur du jeu (y)
		int tempo = 200;					//
		int minSpeedInTimerLoops = 1;		//
		double defaultDensity = 0.15;		//

		//-----------------------------------------------------------------------
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);

		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		IFrog frog = new Frog(game); 				//Cr�ation et liaison de la grenouille
		game.setFrog(frog);
		graphic.setFrog(frog);

		IEnvironment env = new Environment(game); 	//Cr�ation et liaison de l'environnement
		game.setEnvironment(env);

		//------------------------------------------------------------------------

		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});

		timer.setInitialDelay(0);
		timer.start();
	}
}
