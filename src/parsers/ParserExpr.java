package src.parsers;

import java.io.IOException;
import java.util.function.BiFunction;

import src.token.*;
import src.exceptions.*;

class ParserExpr extends Parser<Integer>{

  public Integer parse() throws Exception, UnexpectedSymbolException, IOException{
    if(r.is(Integer.class)) return r.pop(Integer.class).getObject();
    
    r.eat(Sym.LPAR);
    Integer a = this.parse();
    Integer res = r.popOp().apply(a,this.parse());
    r.eat(Sym.RPAR);
    return res;
  }

}
