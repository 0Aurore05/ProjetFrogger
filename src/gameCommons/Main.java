package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Environment;
import frog.Frog;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 26;						//largeur du jeu (x)
		int height = 20;					//hauteur du jeu (y)
		int tempo = 100;					//
		int minSpeedInTimerLoops = 3;		//
		double defaultDensity = 0.2;		//
		
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		//Cr�ation et liaison de la grenouille
		IFrog frog = new Frog(game);
		game.setFrog(frog);
		graphic.setFrog(frog);
		//Cr�ation et liaison de l'environnement
		IEnvironment env = new Environment(game);
		game.setEnvironment(env);

		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		//ActionListener : invoqu�e quand clic de souris / touche entr�e
        //l'interface ActionListener envoie des �v�nements � actionPerformed( )
		timer.setInitialDelay(0);
		timer.start();

	}
}
