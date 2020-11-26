package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
    private Direction direction;    //direction de la grenouille
    private Case caseFrog;          //case actuelle de la grenouille

    //constructeur------------------------------------------------
    public Frog(Game game){
        this.game = game;
    }

    //méthodes----------------------------------------------------
    /**
     * Donne la position actuelle de la grenouille
     * @return
     */
    public Case getPosition(){
        return this.caseFrog;
    }
    /**
     * Donne la direction de la grenouille, c'est à dire de son dernier mouvement
     * @return
     */
    public Direction getDirection(){
        return this.direction;
    }

    /**
     * ddétermine la case devant la grenouille % direction
     * @return la case devant
     */
    private Case getCaseAhead(){
        switch (direction) {
            case up:
                if(caseFrog.y>0) return new Case(caseFrog.x, caseFrog.y-1);
            case down:
                if(caseFrog.y<game.height-1) return new Case(caseFrog.x, caseFrog.y+1);
            case left:
                if(caseFrog.x>0) return new Case(caseFrog.x-1, caseFrog.y);
            case right:
                if(caseFrog.x< game.width-1) return new Case(caseFrog.x+1, caseFrog.y);
        }
        return caseFrog;
    }

    /**
     * Déplace la grenouille dans la direction donnée et teste la fin de partie
     * @param key
     */
    public void move(Direction key){
        direction = key;
        caseFrog = getCaseAhead();
        //if ... == isWinningPosition() ...
    }

}
