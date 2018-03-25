package src;

import src.exceptions.*;
import src.token.*;
import java.awt.Color;

%%
%public
%class Lexer
%unicode
%type Token
%line
%column
%state YYINITIAL, CONST, READ
%debug

%yylexthrow{
    Exception,
    LexerException
%yylexthrow}

%{
  public Token token (Sym s) {
    return new Token<Sym>(s, yyline, yycolumn);
  }

  public Token token (Sym s, String value) throws LexerException {
    if (s == Sym.COLOR) return new Token<Color>(new Color(Integer.decode(value)), yyline, yycolumn);
    if (s == Sym.INT) return new Token<Integer>(Integer.parseInt(value), yyline, yycolumn);
    if (s == Sym.OP) {
      if (value.equals("+")) return new TokenOp((a,b)->a+b, yyline, yycolumn);
      if (value.equals("-")) return new TokenOp((a,b)->a-b, yyline, yycolumn);
      if (value.equals("*")) return new TokenOp((a,b)->a*b, yyline, yycolumn);
      if (value.equals("/")) return new TokenOp((a,b)->a/b, yyline, yycolumn);
    }
    throw new LexerException("Unexcepted symbol "+s,yyline,yycolumn);
  }

  public int yyline(){
    return this.yyline;
  }

  public int yycolumn(){
    return this.yycolumn;
  }
%}

integer = [0-9]+
hex = [0-9A-F]
color = #{hex}{6}
op = "+" | "-" | "/" | "*"

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

%%
"Begin"      { return token(Sym.BEGIN);}
"End"        { return token(Sym.END);}
"Draw"       { return token(Sym.DRAW);}
"Fill"       { return token(Sym.FILL);}
"Rect"       { return token(Sym.RECT);}
"Circle"     { return token(Sym.CIRCLE);}

{color}      { return token(Sym.COLOR, yytext());}
{integer}    { return token(Sym.INT, yytext());}
{op}         { return token(Sym.OP, yytext());}

"("          { return token(Sym.LPAR);}
")"          { return token(Sym.RPAR);}
","          { return token(Sym.COMA);}
";"          { return token(Sym.SEMI);}

{WhiteSpace} {}
[^] {throw new LexerException("Unknown char "+yytext(),yyline,yycolumn);}
