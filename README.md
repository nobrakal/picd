# picd
Projet - Interpréteur de Commandes de Dessin

# Syntaxe
nombre -> [0-9]+
hex -> [0-9A-F]
couleur -> #{hex}{hex}{hex}{hex}{hex}{hex}
opérateur -> "+" | "-" | "/" | "*" | "&" | "|"

programme -> suite-instructions
instruction -> Begin suite-instructions End
  | DrawCircle (expr ,expr ,expr, couleur )
  | FillCircle (expr ,expr ,expr ,couleur )
  | DrawRect (expr , expr , expr ,expr, couleur )
  | FillRect (expr, expr ,expr, expr, couleur )
suite-instructions -> instruction; suite-instructions | espilon
expr -> nombre | (expr opérateur expr) | "true" | "false"
