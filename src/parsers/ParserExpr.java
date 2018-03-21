package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;

class ParserExpr extends Parser<Integer>{
  public Integer parse() throws Exception, UnexpectedSymbolException, IOException{
    if(r.is(Sym.INT)) return ((TokenInt)r.pop(Sym.INT)).num;
    
    r.pop(Sym.LPAR);
    Integer a = this.parse();
    Integer res = ((TokenOp)r.pop(Sym.OP)).op.apply(a,this.parse());
    r.pop(Sym.RPAR);
    return res;
  }
}
