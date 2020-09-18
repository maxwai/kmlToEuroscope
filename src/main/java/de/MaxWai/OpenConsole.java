package de.MaxWai;

import de.MaxWai.Main.KmlToFreeText;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;

public class OpenConsole {

    public static void main(String [] args) throws IOException, URISyntaxException {
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()) {
            String filename = OpenConsole.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        } else {
            KmlToFreeText.main(args);
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
    }
}
