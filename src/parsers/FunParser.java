package src.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public class FunParser extends Parser<Void>{

  private final ASTParser astp;

  public FunParser(ASTParser astp){
    this.astp=astp;
  }

  public AST<Void> parse() throws Exception{
    String id = r.pop(String.class).getObject();
    ArrayList<String> ids = r.parseSuiteObject(String.class);
    return new ASTFun(id,astp.beginEnd(), ids);
  }
}
