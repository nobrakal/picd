package src;

import src.exceptions.*;
import src.token.*;
import java.awt.Color;
import java.util.function.BiFunction;

%%
%public
%class Lexer
%unicode
%type Token<?>
%line
%column
%state YYINITIAL, CONST, READ

%yylexthrow{
    Exception,
    LexerException
%yylexthrow}

%{
  public Token<Sym> token (Sym s) {
    return new Token<Sym>(s, yyline, yycolumn);
  }

  public TokenOp tokenOp(BiFunction<Integer,Integer,Integer> fun, String value){
    return new TokenOp(fun, value, yyline, yycolumn);
  }

  public Token<?> token (Class<?> c, String value) throws LexerException {
    if (c == Color.class) return new Token<Color>(new Color(Integer.decode(value)), yyline, yycolumn);
    if (c == Integer.class) return new Token<Integer>(Integer.parseInt(value), yyline, yycolumn);
    if (c == String.class){
      return new Token<String>(value, yyline, yycolumn);
    }
    throw new LexerException("Unexcepted symbol "+c,yyline,yycolumn);
  }

  public int yyline(){
    return this.yyline;
  }

  public int yycolumn(){
    return this.yycolumn;
  }
%}

integer = [0-9]+
hex     = [0-9A-F]
color   = #{hex}{6}
id      = [a-z][a-zA-Z_]*

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

%%
"Begin"      { return token(Sym.BEGIN);}
"End"        { return token(Sym.END);}
"Draw"       { return token(Sym.DRAW);}
"Fill"       { return token(Sym.FILL);}
"Rect"       { return token(Sym.RECT);}
"Circle"     { return token(Sym.CIRCLE);}

"Fun"       { return token(Sym.FUN);}
"Run"     { return token(Sym.RUN);}

"If"         { return token(Sym.IF);}
"Then"       { return token(Sym.THEN);}
"Else"       { return token(Sym.ELSE);}

"While"      { return token(Sym.WHILE); }
"Do"         { return token(Sym.DO); }

{color}      { return token(Color.class, yytext());}
{integer}    { return token(Integer.class, yytext());}

"<="         { return tokenOp((a,b)-> (a<=b)?1:0,yytext());}
">="         { return tokenOp((a,b)-> (a>=b)?1:0,yytext());}
"<"          { return tokenOp((a,b)-> (a<b)?1:0,yytext());}
">"          { return tokenOp((a,b)-> (a>b)?1:0,yytext());}

"+"          { return tokenOp((a,b)-> a+b, yytext());}
"-"          { return tokenOp((a,b)-> a-b, yytext());}
"*"          { return tokenOp((a,b)-> a*b, yytext());}
"/"          { return tokenOp((a,b)-> a/b, yytext());}
"&"          { return tokenOp((a,b)-> a*b, yytext());}
"|"          { return tokenOp((a,b)-> a+b -a*b,yytext());}
"True"       { return token(Integer.class, "1");}
"False"      { return token(Integer.class, "0");}

"!"          { return token(Sym.NOT);}
"("          { return token(Sym.LPAR);}
")"          { return token(Sym.RPAR);}
","          { return token(Sym.COMA);}
";"          { return token(Sym.SEMI);}
"="          { return token(Sym.EQ);}
"Const"      { return token(Sym.CONST);}
"Var"        { return token(Sym.VAR); }
{id}         { return token(String.class,yytext());}

{WhiteSpace} {}
[^] {throw new LexerException("Unknown char "+yytext(),yyline,yycolumn);}
