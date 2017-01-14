package lwh;

import kbt.Config;
import kbt.I18n;

import java.io.File;

public class App {

    public static void main(String[] args)
    {
        String workdir;
        Upgrade upg = new Upgrade();

        if (args.length > 0) {
            //tryb debug - jawnie podany katalog aplikacji
            workdir = args[0];
        }
        else {
            workdir = getWorkDir();
        }

        Config.setProperties(System.getProperty("propertiesFile"));
        Config.setWorkDir(workdir);

        I18n.setLocale(Config.get("lang"));

        upg.updateDb();

        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    private static String getWorkDir()
    {
        String jarPath = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String dir;

        dir = new File(jarPath).getParent();

        return dir;
    }
}
