package src.token;

public enum Sym {
  BEGIN,
  END,

  DRAW,
  FILL,

  CIRCLE,
  RECT,

  LPAR,
  RPAR,
  SEMI, // semicolon
  COMA, 
  OP,    // operator

  CONST,
  VAR,
  EQ,

  IF,
  THEN,
  ELSE,

  WHILE,
  DO,

  NOT,

  FUN,
  RUN,
  IMPORT,
  EOF
};
