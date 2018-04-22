package src.ast;

import src.Env;

public class ASTSleep extends AST<Void> {

    private final AST<Integer> time;

    public ASTSleep(AST<Integer> time) {
        this.time = time;
    }

    public Void eval (Env e) {
        try {
            Thread.sleep(time.eval(e));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

}