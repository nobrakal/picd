package src.ast;

import java.awt.Graphics2D;

public class InstrCond implements Instr<Boolean>{
  public final int test;

  public InstrCond(int test){
    this.test = test;
  }

  public Boolean eval(Graphics2D g){
    return test == 0;
  }
}
