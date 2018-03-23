all:
	jflex -d src/ flex/Lexer.flex
	javac **/*.java

clean:
	rm **/*.class
