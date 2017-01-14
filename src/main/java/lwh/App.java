package lwh;

import kbt.Config;
import kbt.I18n;
import kbt.Version;

public class App {

    public static void main(String[] args)
    {
        Config.setProperties(System.getProperty("propertiesFile"));
        I18n.setLocale(Config.get("lang"));

        if (args.length > 0 && args[0].startsWith("-v")) {
            printVersion();
            return;
        }

        MainFrame mainFrame = new MainFrame(new Magazyn());

        java.awt.EventQueue.invokeLater(() -> mainFrame.setVisible(true));
    }

    private static void printVersion()
    {
        Version version = new Version();

        System.out.println(version.get());
    }
}
