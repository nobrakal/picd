# picd
Projet - Interpréteur de Commandes de Dessin

# Syntaxe
```
nombre -> [0-9]+
hex -> [0-9A-F]
couleur -> #{hex}{hex}{hex}{hex}{hex}{hex}
opérateur -> "+" | "-" | "/" | "*" | "&" | "|"
identificateur -> [a-z][a-zA-Z_]*
str -> '"' StringCharacter+ '"'

programme -> suite-instructions
instruction -> Begin suite-instructions End
  | DrawCircle (expr ,expr ,expr, couleur )
  | FillCircle (expr ,expr ,expr ,couleur )
  | DrawRect (expr , expr , expr ,expr, couleur )
  | FillRect (expr, expr ,expr, expr, couleur )
  | Const identificateur = expr
  | If expr Then instruction Else instruction
  | Var identificateur = expr
  | identificateur = expr
  | Fun identificateur (argsI) Begin suite-instructions End
  | Run identificateur (args)
  | Import string

args -> expr | , expr | epsilon
argsI -> identificateur | , identificateur | epsilon

suite-instructions -> instruction; suite-instructions | espilon
expr -> nombre | (expr opérateur expr) | "True" | "False" | ! expr | identificateur
```
