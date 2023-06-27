package alert;

import java.io.*;
import java.util.*;

public class StandaloneApp {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("application.properties");

        try  {
            properties.load(url.openStream());
        } catch (FileNotFoundException fie) {
            fie.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        final String username = properties.getProperty("username");
        final String password = properties.getProperty("password");
        final String executionPath = System.getProperty("user.dir");
        System.out.println(executionPath);
    }


}
