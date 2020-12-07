package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

//import environment.Environment;
//import frog.Frog;
import environment.EnvInf;
import frog.FrogInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 150;
		int minSpeedInTimerLoops = 1;
		double defaultDensity = 0.06;

		//-----------------------------------------------------------------------
		IFroggerGraphics graphic = new FroggerGraphic(width, height);

		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		//IFrog frog = new Frog(game);
		FrogInf frog = new FrogInf(game);
		game.setFrog(frog);
		graphic.setFrog(frog);

		//IEnvironment env = new Environment(game);
		EnvInf env = new EnvInf(game);
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
