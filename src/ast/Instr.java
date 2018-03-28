package src.ast;

import java.awt.Graphics2D;

// Will be implemented by InstrBEGIN...
public interface Instr<O>{
  //Eval l'instruction
  public O eval(Graphics2D g);
}
