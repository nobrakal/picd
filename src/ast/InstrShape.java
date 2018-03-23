package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class InstrShape<S extends Shape> implements Instr{
    public final S shape;
    public final Color color;

    public InstrShape(S shape, Color color){
        this.shape = shape;
        this.color = color;
    }

    public void eval(Graphics2D g){
        g.setPaint(color);
    }
}
