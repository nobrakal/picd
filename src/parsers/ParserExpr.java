package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

class ParserExpr extends Parser<Integer> {

  public AST<Integer> parse()
      throws Exception, UnexpectedSymbolException, CannotFindSymbolException, IOException {
    if(r.is(Integer.class))
      return new ExprLeaf.Int(r.pop(Integer.class).getObject());
    if(r.is(String.class)){
      Token<String> tok = r.pop(String.class);
      return new ExprLeaf.Id(tok.getObject(), tok.line, tok.column);
    }
    if(r.is(Sym.NOT)){
      r.eat(Sym.NOT);
      AST<Integer> res = new ExprOp((a,b)-> a == b ? 1 : 0, "!", new ExprLeaf.Int(0), parse());
      return res;
    }
    r.eat(Sym.LPAR);
    AST<Integer> a   = parse();
    TokenOp op = r.popOp();
    AST<Integer> res = new ExprOp(op.fun,op.str, a,parse());
    r.eat(Sym.RPAR);
    return res;
  }
}
