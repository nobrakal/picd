package src.parsers;

import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;
import src.Env;

public abstract class Parser<T> {
  
  protected static LookAhead1 r;
  protected static final ParserExpr parserExpr = new ParserExpr();
  protected static final ShapeParser shapeParser = new ShapeParser();
  protected static final FunParser funParser = new FunParser();

  public abstract AST<T> parse() 
      throws Exception, UnexpectedSymbolException, IOException;

  public static void init (String filename) 
      throws Exception, IOException, LexerException {
    r = new LookAhead1(filename);
  }

  public AST<T> parse(Sym s)
      throws Exception, UnexpectedSymbolException, IOException{
    AST<T> ast = parse();
    r.eat(s);
    return ast;
  }

}

