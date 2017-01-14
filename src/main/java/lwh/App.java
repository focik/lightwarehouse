package lwh;

import kbt.Config;
import kbt.I18n;

public class App {

    public static void main(String[] args)
    {
        Config.setProperties(System.getProperty("propertiesFile"));

        I18n.setLocale(Config.get("lang"));

        MainFrame mainFrame = new MainFrame(new Magazyn());

        java.awt.EventQueue.invokeLater(() -> mainFrame.setVisible(true));
    }
}
