package src.ast;

import java.awt.Color;

import java.awt.Shape;
import src.Env;
import src.EnvCompiler;

public class ASTFill extends AST<Void> {

  public final ASTShape is;
 
  public ASTFill(ASTShape is){
    this.is = is;
  }

  public Void eval(Env e) throws Exception{
    e.g.fill(is.eval(e));
    return null;
  }

  public void compile (EnvCompiler e) throws Exception {
    e.code += getColor(is.color) + "g.fill";
    is.compile(e);
  }

  public static String getColor (Color c) {
    String red = Integer.toHexString(c.getRed()),
           green = Integer.toHexString(c.getGreen()),
           blue = Integer.toHexString(c.getBlue());

    if (red.length() == 1) red = "0" + red;
    if (green.length() == 1) green = "0" + green;
    if (blue.length() == 1) blue = "0" + blue;

    return "g.setColor(new Color(0x" + red + green + blue + "));";
  }
}
