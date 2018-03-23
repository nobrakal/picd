package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.Shape;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public class ShapeParser extends Parser<AST> {

  public ShapeParser (){
  }

  public AST parse () {
    try{
    while (!r.isEmpty()) instruction();
    }
    catch(Exception e){}
    return null;
  }

  private void instruction () throws UnexpectedSymbolException{
    if(r.are(Sym.DRAWC, Sym.DRAWR)){

    }
    else if(r.are(Sym.FILLC, Sym.FILLR)){

    }
    r.pop(Sym.BEGIN);
    instruction();
    r.pop(Sym.END);
  }

}
