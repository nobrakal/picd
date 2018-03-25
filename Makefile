NAME = picd
BIN_FOLDER = bin/
DEPS_FOLDER = ".:"

MAIN_PATH = "src/Main"
MAIN_PATH_MANIFEST = "src.Main"

MANIFEST = "MANIFEST.MF"
JAR = $(NAME).jar

all :
	@mkdir -p $(BIN_FOLDER)
	@jflex -d src/ flex/Lexer.flex
	@find -name "*.java" > sources.txt
	@javac -cp $(DEPS_FOLDER) -d $(BIN_FOLDER) @sources.txt -Xlint
	@echo "Main-Class: "$(MAIN_PATH_MANIFEST) > $(MANIFEST)
	@jar -cvmf $(MANIFEST) $(JAR) -C $(BIN_FOLDER) ./

run:
	@java -jar $(JAR) tests/simple2

clean:
	@rm -rf $(BIN_FOLDER)
	@rm -rf $(MANIFEST)

fclean: clean
	@rm -rf $(JAR)

