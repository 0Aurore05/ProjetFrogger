package environment;

import java.awt.Color;
import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {

    private Game game;
    private Case leftPosition;                      //case from laquelle la taille est calculée (la case la plus à gauche)
    private boolean leftToRight;                    //si true alors sens ->, si false <- (recup sens de la route)
    private int length;                             //taille de la voiture
    private final Color colorLtR = Color.BLACK;     //couleur: black si ->
    private final Color colorRtL = Color.BLUE;      //couleur: blue si <-


    //constructeur(s)-------------------------------------------------------
    public Car(Game game, Case leftPosition, boolean leftToRight, int length){
        this.game = game;
        this.leftPosition = leftPosition;
        this.leftToRight = leftToRight;
        this.length = length;
    }

    //méthodes----------------------------------------------------------------

    public Case getLeftPosition(){
        return this.leftPosition;
    }
    public int getLength(){
        return this.length;
    }

    /**
     * détermine case devant la voiture selon sens de marche
     * @return la case devant en tenant compte de sa taille
     */
    public Case getCaseAhead(){
        if(leftToRight) return new Case(leftPosition.x+length, leftPosition.y);
        else return new Case(leftPosition.x-1,leftPosition.y);
    }

    /**
     * Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture
     */
    public void addToGraphics() {
        for (int i = 0; i < length; i++) {
            Color color = colorRtL;
            if (this.leftToRight){
                color = colorLtR;
            }
            game.getGraphic()
                    .add(new Element(leftPosition.x + i, leftPosition.y, color));
        }
    }

    /**
     * forme un tableau de toutes les cases occupées par une voiture selon sa taille & sa position la + à gauche
     * @return ArrayList de ses cases occupées
     */
    public ArrayList<Case> getAllCases() {
        ArrayList<Case> res = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            Case tmp = leftPosition;
            res.add(new Case(tmp.x+i, tmp.y));
        }
        return res;
    }

    /**
     * bouge la voiture
     * @param c la nouvelle position de la voiture
     */
    public void move(Case c){
        this.leftPosition = c;
    }

    public void moveAhead(){
        this.move(this.getCaseAhead());
    }

    /**
     * teste si la voiture est completement sortie de l'écran
     * @return true si c'est le cas
     */
    public Boolean isOut(){
        if(leftToRight) return (leftPosition.x-(length-1) > game.width);
        else return (leftPosition.x+(length-1) < 0);
    }

}