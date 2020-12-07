package frog;

import gameCommons.Game;
import util.Case;
import util.Direction;

public class FrogInf {

    public Game game;
    private Direction direction;
    private Case caseFrog;

    private int score = 0;

    //constructeur------------------------------------------------
    public FrogInf(Game game){
        this.game = game;
        this.caseFrog = new Case(this.game.width/2, 0);
    }


    //méthodes----------------------------------------------------

    /**
     *
     * @return la position de la grenouille
     */
    public Case getPosition(){
        return this.caseFrog;
    }

    /**
     *
     * @return le score de la grenouille
     */
    public int getScore(){
        return this.score;
    }

    /**
     * ddétermine la case devant la grenouille % sa direction
     * @return la case devant
     */
    private Case getCaseAhead(){
        switch (direction) {
            case left:
                if(caseFrog.x>0) return new Case(caseFrog.x-1, caseFrog.y);
            case right:
                if(caseFrog.x< game.width-1) return new Case(caseFrog.x+1, caseFrog.y);
        }
        return caseFrog;
    }

    /**
     * Déplace la grenouille dans la direction donnée et déroule l'écran si besoin
     * @param key sa direction
     */
    public void move(Direction key){
        direction = key;

        if(direction == Direction.up) {
            score++;
            this.game.getEnv().lanesDown();
            this.game.getEnv().addLane();
        }

        caseFrog = getCaseAhead();
        this.game.testLose() ;
    }

}