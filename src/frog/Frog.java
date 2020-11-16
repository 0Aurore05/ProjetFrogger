package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
    private Direction direction;
    private Case caseFrog;

    public Frog(Game game){
        this.game = game;
    }

    public Case getPosition(){
        return this.caseFrog;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void move(Direction key){
        //avec méthode pour définir case selon case0 et direction
        //et celle pour vérifier si case sûre
        //et celle pour tester fin de partie
    }

}
