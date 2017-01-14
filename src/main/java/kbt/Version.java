package kbt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Version {
    public String get()
    {
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("version.properties");
        Properties props = new Properties();

        try {
            props.load(inputStream);
        } catch (Exception e) {
            return "UNKNOWN";
        }

        return props.getProperty("version");
    }
}
