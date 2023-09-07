package org.example.Assets;

public class Colors {


    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";



    public  static String Banner()
    {
      String banner= MAGENTA+ """
                $$$$$$$\\  $$$$$$$\\  $$$$$$$$\\  $$$$$$\\                                      $$\\ $$\\                    \s
                $$  __$$\\ $$  __$$\\ $$  _____|$$  __$$\\                                     $$ |\\__|                   \s
                $$ |  $$ |$$ |  $$ |$$ |      $$ /  \\__|$$\\   $$\\  $$$$$$\\   $$$$$$\\   $$$$$$$ |$$\\  $$$$$$\\  $$$$$$$\\ \s
                $$$$$$$  |$$ |  $$ |$$$$$\\    $$ |$$$$\\ $$ |  $$ | \\____$$\\ $$  __$$\\ $$  __$$ |$$ | \\____$$\\ $$  __$$\\\s
                $$  ____/ $$ |  $$ |$$  __|   $$ |\\_$$ |$$ |  $$ | $$$$$$$ |$$ |  \\__|$$ /  $$ |$$ | $$$$$$$ |$$ |  $$ |
                $$ |      $$ |  $$ |$$ |      $$ |  $$ |$$ |  $$ |$$  __$$ |$$ |      $$ |  $$ |$$ |$$  __$$ |$$ |  $$ |
                $$ |      $$$$$$$  |$$ |      \\$$$$$$  |\\$$$$$$  |\\$$$$$$$ |$$ |      \\$$$$$$$ |$$ |\\$$$$$$$ |$$ |  $$ |
                \\__|      \\_______/ \\__|       \\______/  \\______/  \\_______|\\__|       \\_______|\\__| \\_______|\\__|  \\__|
                                                                                                                       \s
                                                                                                                       \s
                                                                                                                       \s
                """+RESET;
      return banner;

    }
}
