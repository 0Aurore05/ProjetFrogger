package graphicalElements;
import java.awt.*;
import util.Case;

//permet d'ajouter des couleurs aux cases

public class Element extends Case {

    public final Color color;

    public Element(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    
    public Element(Case c, Color color) {
        super(c.x, c.y);
        this.color = color;
    }
    
}
