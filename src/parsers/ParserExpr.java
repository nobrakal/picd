package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

class ParserExpr extends Parser<Integer> {

  public Expr parse()
      throws Exception, UnexpectedSymbolException, CannotFindSymbolException, IOException {
    if(r.is(Integer.class))
      return new ExprLeaf.Int(r.pop(Integer.class).getObject());
    if(r.is(String.class))
      return new ExprLeaf.Id(r.pop(String.class).getObject());
    r.eat(Sym.LPAR);
    Expr a   = parse();
    TokenOp op = r.popOp();
    Expr res = new ExprOp(op.fun,op.str, a,parse());
    r.eat(Sym.RPAR);
    return res;
  }

}
