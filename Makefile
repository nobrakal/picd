all:
	jflex -d src/ flex/Lexer.flex
	javac src/Main.java
