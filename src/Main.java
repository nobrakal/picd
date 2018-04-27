package src;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.parsers.*;
import src.ast.AST;
import src.ast.ASTFun;

public class Main {

  private static void initAndShow(String filename) {
    JFrame frame = new JFrame("ADS4");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getContentPane().setPreferredSize(new Dimension(800,600));
    frame.getContentPane().add(new MyCanvas(filename));

    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    if(args.length != 2)
      printUsage();
    
    if (args[0].equals("build")) build(args[1]);
    else if (args[0].equals("run")) 
      javax.swing.SwingUtilities.invokeLater(() -> initAndShow(args[1]));
    else printUsage();
  }

  private static void printUsage () {
    System.out.println("Usage: <command> <filename>");
    System.out.println("Commands:");
    System.out.println("build   compile the file to java code");
    System.out.println("run     inteprete the file and directly run the code");
    System.exit(1);
  }

  private static void build (String fname) {
    try {
      Parser.init(fname);
      ASTParser parser = new ASTParser();
      AST<?> ast = parser.parse();

      BufferedReader buff = new BufferedReader(new FileReader(new File("./assets/Template.template")));
      String javaTemplate = "", line = "";
      while ((line = buff.readLine()) != null)
        javaTemplate += line + "\n";

      EnvCompiler e = new EnvCompiler();
      ast.compile(e);
      String javaCode = javaTemplate.
                          replaceAll("@@code@@", e.code).
                          replaceAll("@@funs@@", funcs(e));
      
      PrintWriter writer = new PrintWriter("Template.java", "UTF-8");
      writer.println(javaCode);
      writer.close();

      Runtime rt = Runtime.getRuntime();
      Process p = rt.exec("javac Template.java");

      int exitCode = p.waitFor();
      if (exitCode != 0)
        throw new Exception("Error on compilation");

      rt.exec("rm Template.java");
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  private static String funcs (EnvCompiler e) throws Exception{
    String funs = "";
    for (String id: e.funs.keySet()) {
      EnvCompiler ep = EnvCompiler.mkPartialEnvCompiler(e);
      ASTFun fun = e.funs.get(id);
      ep.code += "public static void " + id + "(Graphics g ";
      for (int i = 0; i < fun.args.size(); i++) {
        ep.code += ", int " + fun.args.get(i);
        ep.addVar(fun.args.get(i));
      }
      ep.code += ")";

      ep.code += "{";
      fun.asts.compile(ep);
      ep.code += "}";
      funs += ep.code;
    }

    return funs;
  }
}

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

  private AST<?> ast;

  public MyCanvas(String fname) {
    ast = null;

    try{
      Parser.init(fname);
      ASTParser parser = new ASTParser();
      ast = parser.parse();
    } catch(Exception e) {
      System.out.println(e.getMessage());
      // e.printStackTrace();
      System.exit(1);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    try {
      ast.eval(new Env((Graphics2D)g));
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}

