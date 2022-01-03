package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtil {
   static Properties properties;

    private PropUtil() {

    }

    public static Properties init() throws IOException {
        if (properties == null){
            properties = new Properties();
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties.properties"));
        }
        return properties;
    }

    public static String getProperty(String key){
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
