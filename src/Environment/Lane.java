package environment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import util.Case;
import gameCommons.Game;


public class Lane {

    private Game game;
    private int y;                                                  //position de la voie
    private int speed;                                              //vitesse des voitures de la voie
    private ArrayList<environment.Car> cars = new ArrayList<>();    //array des voitures qui s'y trouvent
    private boolean leftToRight;                                    //true si sens -> , false si <-
    private double density;                                         //proba qu'une voiture entre sur la voie

    //constructeur(s)-------------------------------------------------------
    public Lane(int y, int speed, Boolean leftToRight, double density){
        this.y=y; this.speed=speed;
        this.leftToRight=leftToRight;
        this.density=density;
        this.cars = new ArrayList<environment.Car>();
    }


    //méthodes---------------------------------------------------------------

    /**
     * déplace voitures présentes & en ajoute peutêtre de nouvelles
     */
    public void update() {
        // TODO
        // Toutes les voitures se déplacent d'une case au bout d'un nombre "tic d'horloge" égal à leur vitesse
        // ---> cette méthode est appelée à chaque tic d'horloge

        // Les voitures doivent être ajoutées à l interface graphique même quand elle ne bougent pas

        // A chaque tic d'horloge, une voiture peut être ajoutée
    }

    /**
     * Ajoute une voiture au début de la voie avec probabilité égale à la densité
     * si la première case de la voie est vide
     */
    private void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new environment.Car(game, getBeforeFirstCase(), leftToRight));
            }
        }
    }

    /**
     * détermine la première case d'une route selon son sens
     * @return (0,this.y) si -->  /  (game.width-1, this.y) si <--
     */
    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, y);                //y : celle de la route (this.y)
        } else
            return new Case(game.width - 1, y);   //car x va de 0 à game.width-1 (j'imagine du coup)
    }

    /**
     * détermine la -1 ième case d'une route selon son sens (case où se génèrent les voitures avant d'apparaître)
     * @return (-1, this.y) si --> / (game.width, this.y) si <--
     */
    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, y);
        } else
            return new Case(game.width, y);
    }


    /**
     * forme un tableau 2D entre voitures de la route, et toutes les positions qu'elles occupent
     * @return HashMap avec key:voiture / value:ArrayList des cases qu'elle occupe
     */
    private HashMap<Car, ArrayList<Case>> getCasesCars(){
        HashMap<Car, ArrayList<Case>> res = new HashMap<>();
        for(Car i : this.cars){
            res.put(i, i.getAllCases());
        }
        return res;
    }

    /**
     * teste si une case est safe
     * @param c la case à tester
     * @return true si aucune voiture sur cette case
     */
    public Boolean isSafe(Case c){
        return !(this.getCasesCars().containsValue(c));
    }

}
