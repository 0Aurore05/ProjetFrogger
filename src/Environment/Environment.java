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
    }

    //méthodes-------------------------------------------------------

    /**
     * Teste si une case est sûre, c'est à dire que la grenouille peut s'y poser sans mourir
     * @param c la case à tester
     * @return vrai s'il n'y a pas danger
     */
    public boolean isSafe(Case c){
        //méthode douteuse : ArrayList de Lanes
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
        //TODO
        //méthode update() de Lane, qui doit utiliser un update des voitures dans Car jsp
    }

}