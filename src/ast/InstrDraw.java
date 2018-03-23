package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class InstrDraw<S extends Shape> extends InstrShape<S>{

    public InstrDraw(S shape, Color c){
        super(shape, c);
    }

    public void eval(Graphics2D g){
        super.eval(g);
        g.draw(shape);
    }
}
