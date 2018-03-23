package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.Color;

import java.util.function.BiFunction;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public class ASTParser extends Parser<AST> {

  public ASTParser (){
  }

  public AST parse () {
    try{
    while (!r.isEmpty()) instruction();
    }
    catch(Exception e){}
    return null;
  }

  private void instruction () throws UnexpectedSymbolException, Exception{
    if(r.are(Sym.DRAWR, Sym.FILLR)){//TODO Simplify
        BiFunction<Shape,Color,Instr> bf = (r.is(Sym.DRAWR))?InstrDraw::new:InstrFill::new; 
        r.pops(Sym.DRAWR, Sym.FILLR);
        r.pop(Sym.LPAR);
        int x = new ParserExpr().parse();
        r.pop(Sym.COMA);
        int y = new ParserExpr().parse();
        r.pop(Sym.COMA);
        int w = new ParserExpr().parse();
        r.pop(Sym.COMA);
        int h = new ParserExpr().parse();
        r.pop(Sym.COMA);
        Color c = Color.BLACK;//TODO parse
        AST ast = new AST(bf.apply(new Rectangle2D.Double(x,y,w,h),c));
        r.pop(Sym.LPAR);
        r.pop(Sym.SEMI);
   }
    else if(r.are(Sym.DRAWC, Sym.FILLC)){
        BiFunction<Shape,Color,Instr> bf = (r.is(Sym.DRAWC))?InstrDraw::new:InstrFill::new; 
        r.pops(Sym.DRAWC, Sym.FILLC);
        r.pop(Sym.LPAR);
        int x = new ParserExpr().parse();
        r.pop(Sym.COMA);
        int y = new ParserExpr().parse();
        r.pop(Sym.COMA);
        int rad = new ParserExpr().parse();
        r.pop(Sym.COMA);
        Color c = Color.BLACK;//TODO parse
        AST ast = new AST(bf.apply(new Ellipse2D.Double(x,y,rad,rad),c));
        r.pop(Sym.LPAR);
        r.pop(Sym.SEMI);

    }
    r.pop(Sym.BEGIN);
    instruction();
    r.pop(Sym.END);
  }

  @FunctionalInterface 
  interface DeuxDProducer{
        public RectangularShape create(int x, int y, int w, int h);
  }
}
