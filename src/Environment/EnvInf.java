package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;

public class EnvInf {

    private Game game;
    private ArrayList<Lane> lanes;


    //constructeur(s)-----------------------------------------------

    public EnvInf(Game game){
        this.game = game;
        this.initLanes();
    }

    //méthodes-------------------------------------------------------

    /**
     * initie aléatoirement les paramètres de toutes les Lanes de l'env
     */
    public void initLanes(){
        this.lanes = new ArrayList<>();

        for(int y = 1; y< game.height; y++){ //chaque lane à un Y (sauf pour y=heigth : départ / y=1:arrivée)
            double rand = (-0.5) + game.randomGen.nextDouble();  //double entre -0.5 et 0.5

            double density = game.defaultDensity + (rand/10);
            boolean leftToRight = rand<0;
            int speed = game.minSpeedInTimerLoops + game.randomGen.nextInt(5); //jusqu'à 4

            Lane l = new Lane(this.game, y, speed, leftToRight, density);
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
     * Effectue une étape d'actualisation de l'environnement
     */
    public void update(){
        for(Lane l : this.lanes){
            l.update();
        }
    }

    /**
     * fait descendre le y de chaque Lane
     */
    public void lanesDown(){
        for(Lane l : this.lanes){
            l.moveDown();
        }
    }

    /**
     * ajoute une nouvelle Lane au bout de l'écran
     */
    public void addLane(){
        double rand = (-0.5) + game.randomGen.nextDouble();  //double entre -0.5 et 0.5

        double density = game.defaultDensity + (rand/10);
        boolean leftToRight = rand<0;
        int speed = game.minSpeedInTimerLoops + game.randomGen.nextInt(5); //jusqu'à 4

        Lane l = new Lane(this.game, this.game.height-1, speed, leftToRight, density);
        l.initCars();
        this.lanes.add(l);
    }

}