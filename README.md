# picd
Projet - Interpréteur de Commandes de Dessin

## Syntaxe reconnue
```
nombre -> [0-9]+
hex -> [0-9A-F]
couleur -> #{hex}{hex}{hex}{hex}{hex}{hex}
opérateur -> "+" | "-" | "/" | "*" | "&" | "|" | "!"
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
  | Import str

args -> expr | , expr | epsilon
argsI -> identificateur | , identificateur | epsilon

suite-instructions -> instruction; suite-instructions | espilon
expr -> nombre | (expr opérateur expr) | "True" | "False" | ! expr | identificateur
```

### Ajouts

* Les boucles while 
* Les booléens (traduit en terme d'entiers, "True" étanti différent 1 et "False" étant 0 )
* Les opérations booléennes Et ("&"), Ou ("|") et Non ("!")
* Les fonctions, sans valeurs de retour, définnissables via le mot-clé "Fun", et lançable avec le mot-clé "Run".
* L'import de fichier (c'est à dire des fonctions qu'il contient), via le mot-clé "Import"

## Compilation

Lancez `make` pour compiler le logiciel

## Utilisation

`java -jar picd.jar [run|build] fichier`

* L'option run interprète le fichier
* L'option build va construire le programme java associé dans un fichier Template.java
