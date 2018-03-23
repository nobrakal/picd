package src.ast;

import java.util.LinkedList; // Implement Queue

public class AST extends LinkedList<AST>{
  public final Instr instr;

  public AST(Instr instr){
    super();
    this.instr=instr;
  }

  public AST(Instr instr, AST...asts){
    for(AST a : asts) add(a);
    this.instr=instr;
  }
}
