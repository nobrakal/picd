package src.parsers;

import java.io.IOException;
import java.util.function.BiFunction;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

class ParserExpr extends Parser<Integer>{

  public Integer parse(AST<?> current)
      throws Exception, UnexpectedSymbolException, CannotFindSymbolException, IOException {
    if(r.is(Integer.class)) return r.pop(Integer.class).getObject();
    if(r.is(String.class)) return current.getVar(r.pop(String.class).getObject(), r.getLine(), r.getColumn());
    r.eat(Sym.LPAR);
    Integer a   = parse(current);
    Integer res = r.popOp().apply(a, parse(current));
    r.eat(Sym.RPAR);
    return res;
  }

}
