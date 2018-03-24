package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class InstrShape implements Instr {
    public final Shape shape;
    public final Color color;

    public InstrShape(Shape shape, Color color){
        this.shape = shape;
        this.color = color;
    }

    public void eval(Graphics2D g){
        g.setPaint(color);
    }
}
