package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

class ParserExpr extends Parser<Expr>{

  public Expr parse(AST<?> current)
      throws Exception, UnexpectedSymbolException, CannotFindSymbolException, IOException {
    if(r.is(Integer.class)) return new ExprLeaf(r.pop(Integer.class).getObject());
    if(r.is(String.class)) return new ExprLeaf(current.getVar(r.pop(String.class).getObject(), r.getLine(), r.getColumn()));
    r.eat(Sym.LPAR);
    Expr a   = parse(current);
    TokenOp op = r.popOp();
    Expr res = new ExprOp(op.fun,op.str, a,parse(current));
    r.eat(Sym.RPAR);
    return res;
  }

}
