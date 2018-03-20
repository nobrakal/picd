%%
%public
%class Lexer
%unicode
%type Token
%line
%column
%state YYINITIAL, CONST, READ

%yylexthrow{
    Exception
%yylexthrow}

nombre = [0-9]+
hex = [0-9A-F]
couleur = #{hex}{hex}{hex}{hex}{hex}{hex}
op = "+" | "-" | "/" | "*"

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

%%
{WhiteSpace} {}
[^] {throw new Exception("Unknown char "+yytext());}

