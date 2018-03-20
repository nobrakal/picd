package src.token;

import java.awt.Color;

public class TokenColor extends Token {

  private final Color color;

  public TokenColor (String color) {
    super(Sym.COLOR);
    this.color = new Color(Integer.decode(color));
  }

}

