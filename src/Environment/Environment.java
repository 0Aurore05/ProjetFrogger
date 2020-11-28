package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

    private Game game;
    private ArrayList<Lane> lanes;

    //constructeur(s)-----------------------------------------------

    public Environment(Game game){
        this.game = game;
        this.initLanes();
    }

    //méthodes-------------------------------------------------------

    private void initLanes(){
        this.lanes = new ArrayList<>();

        for(int y = 1; y< game.height-1; y++){ //chaque lane à un Y (sauf pour y=heigth : départ / y=1:arrivée)
            double rand = (-0.5) + game.randomGen.nextDouble();  //double entre -0.5 et 0.5

            double density = game.defaultDensity+(rand/4);

            boolean leftToRight = rand<0;

            int speed = game.minSpeedInTimerLoops + game.randomGen.nextInt(7); //de 3 à 10

            Lane l = new Lane(y, speed, leftToRight, density);
            this.lanes.add(l);
        }
    }

    /**
     * Teste si une case est sûre, c'est à dire que la grenouille peut s'y poser sans mourir
     * @param c la case à tester
     * @return vrai s'il n'y a pas danger
     */
    public boolean isSafe(Case c){
        for(Lane l : this.lanes){
            if(!l.isSafe(c)) return false;
        }
        return true;
    }

    /**
     * Teste si la case est une case d'arrivee
     * @param c
     * @return vrai si la case est une case de victoire
     */
    public boolean isWinningPosition(Case c){
        return (c.y == 0 );
    }

    /**
     * Effectue une étape d'actualisation de l'environnement
     */
    public void update(){
        for(Lane l : this.lanes){
            l.update();
        }
    }


}