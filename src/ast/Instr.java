package src.ast;

import java.awt.Graphics2D;

// Will be implemented by InstrBEGIN...
public interface Instr{
  //Eval l'instruction
  public void eval(Graphics2D g);
}
