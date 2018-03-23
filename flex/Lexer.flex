package src;

import src.exceptions.*;
import src.token.*;

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
  public static Token token (Sym s) {
    return new Token(s);
  }

  public Token token (Sym s, String value) {
    if (s == Sym.COLOR) return new TokenColor(value);
    if (s == Sym.INT) return new TokenInt(Integer.parseInt(value));
    if (s == Sym.OP) {
      if (value.equals("+")) return new TokenOp((a,b)->a+b);
      if (value.equals("-")) return new TokenOp((a,b)->a-b);
      if (value.equals("*")) return new TokenOp((a,b)->a*b);
      if (value.equals("/")) return new TokenOp((a,b)->a/b);
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
"DrawCircle" { return token(Sym.DRAWC);}
"FillCircle" { return token(Sym.FILLC);}
"DrawRect"   { return token(Sym.DRAWR);}
"FillRect"   { return token(Sym.FILLR);}

{color}      { return token(Sym.COLOR, yytext());}
{integer}    { return token(Sym.INT, yytext());}
{op}         { return token(Sym.OP, yytext());}

"("          { return token(Sym.LPAR);}
")"          { return token(Sym.RPAR);}
","          { return token(Sym.COMA);}
";"          { return token(Sym.SEMI);}

{WhiteSpace} {}
[^] {throw new LexerException("Unknown char "+yytext(),yyline,yycolumn);}
