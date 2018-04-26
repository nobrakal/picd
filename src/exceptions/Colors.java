package src.exceptions;

class Colors {

  private static final String
    HEADER = "\033[95m",
    OKBLUE = "\033[94m",
    OKGREEN = "\033[92m",
    WARNING = "\033[93m",
    FAIL = "\033[91m",
    ENDC = "\033[0m",
    BOLD = "\033[1m",
    UNDERLINE = "\033[4m";

  public static String header (String s) {
    return w (HEADER, s);
  }

  public static String okBlue (String s) {
    return w (OKBLUE, s);
  }

  public static String okGreen (String s) {
    return w (OKGREEN, s);
  }

  public static String warning (String s) {
    return w (WARNING, s);
  }

  public static String fail (String s) {
    return w (FAIL, s);
  }

  public static String bold (String s) {
    return w (BOLD, s);
  }

  public static String underline (String s) {
    return w (UNDERLINE, s);
  }

  private static String w (String color, String content) {
    return color + content + ENDC;
  }

}