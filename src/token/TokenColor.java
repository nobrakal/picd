package src.token;

import java.awt.Color;

public class TokenColor extends Token {

  public final Color color;

  public TokenColor (String color, int line, int col) {
    super(Sym.COLOR, line, col);
    this.color = new Color(Integer.decode(color));
  }

}

