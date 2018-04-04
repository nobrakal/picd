package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.function.Consumer;
import java.util.function.Function;

public class InstrDoGraphic implements Instr<Void> {

  public final Instr<Shape> is;
  public final Function<Graphics2D,Consumer<Shape>> doGraphic;

  public InstrDoGraphic(Function<Graphics2D,Consumer<Shape>> doGraphic, Instr<Shape> is){
    this.is=is;
    this.doGraphic=doGraphic;
  }

  public Void eval(Graphics2D g){
    doGraphic.apply(g).accept(is.eval(g));
    return null;
  }
}
