package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;


public class Lane {

    private Game game;
    private int y;                                                  //position de la voie
    private int speed;                                              //vitesse des voitures de la voie
    private ArrayList<environment.Car> cars = new ArrayList<>();    //array des voitures qui s'y trouvent
    private boolean leftToRight;                                    //true si sens -> , false si <-
    private double density;                                         //proba qu'une voiture entre sur la voie

    private int tmp = 0;

    //constructeur(s)-------------------------------------------------------
    public Lane(Game game, int y, int speed, Boolean leftToRight, double density){
        this.game = game;
        this.y=y;
        this.speed=speed;
        this.leftToRight=leftToRight;
        this.density=density;
    }


    //méthodes---------------------------------------------------------------

    /**
     * déplace voitures présentes & en ajoute peutêtre de nouvelles
     */
    public void update() {
        // Toutes les voitures se déplacent d'une case au bout d'un nombre "tic d'horloge" égal à leur vitesse
        // ---> cette méthode est appelée à chaque tic d'horloge
        int k = 0;
        tmp = (tmp+1)%speed;
        Boolean canMove = tmp==0;

        for(Car c : cars){
            c.addToGraphics();

            if(canMove) c.moveAhead();

            //if(c.isOut()) cars.remove(k);
            k++;
        }
        mayAddCar();         // A chaque tic d'horloge, une voiture peut être ajoutée
    }

    /**
     * Ajoute une voiture au début de la voie avec probabilité égale à la densité
     * si la première case de la voie est vide
     */
    private void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                int length = game.randomGen.nextInt(3);
                cars.add(new environment.Car(game, getBeforeFirstCase(), leftToRight, length));
            }
        }
    }

    /**
     * détermine la première case d'une route selon son sens
     * @return (0,y) si de gauche à droite  /  (game.width-1,y) si de droite à gauche
     */
    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, y);
        } else
            return new Case(game.width - 1, y);
    }

    /**
     * détermine la -1 ième case d'une route selon son sens (case où se génèrent les voitures avant d'apparaître)
     * @return (-1,y) si de gauche à droite / (game.width, y) si de droit à gauche
     */
    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-2, y);
        } else
            return new Case(game.width+1, y);
    }


    /**
     * forme un tableau 2D entre voitures de la route, et toutes les positions qu'elles occupent
     * @return ArrayList des cases que toutes les voitures occupent
     */
    private ArrayList<Case> getUnsafeCases(){
        ArrayList<Case> res = new ArrayList<>();
        for(Car c : this.cars){
            for(Case i : c.getAllCases() )
            res.add(i);
        }
        return res;
    }

    /**
     * teste si une case est safe
     * @param c la case à tester
     * @return true si aucune voiture sur cette case
     */
     public Boolean isSafe(Case c) {
         for (Case i : this.getUnsafeCases()) {
             if(i.equals(c)) return false;
         }
         return true;
     }
}
