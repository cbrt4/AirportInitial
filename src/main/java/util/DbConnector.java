package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {

    /**
     * WARN: Establishing SSL connection without server's identity verification is not recommended.
     * According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established
     * by default if explicit option isn't set. For compliance with existing applications not using
     * SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly
     * disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server
     * certificate verification.
     * */

    private final String DB_CONFIG_PATH = "src/main/resources/db_config.properties";
    private static DbConnector INSTANCE;
    private Connection connection;
    private String DB_TYPE;
    private String DB_NAME;
    private String HOST;
    private String PORT;
    private String LOGIN;
    private String PASSWORD;

    private DbConnector() {
    }

    public static DbConnector getINSTANCE() {
        return INSTANCE == null ? INSTANCE = new DbConnector() : INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection != null) return this.connection;
        getAndSetProperties(DB_CONFIG_PATH);
        return this.connection = DriverManager.getConnection(DB_TYPE+HOST+":"+PORT+"/"+DB_NAME, LOGIN, PASSWORD);
    }

    private void getAndSetProperties(String configPath) {

        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(configPath)) {

            properties.load(fileInputStream);
            DB_TYPE = properties.getProperty("db_type");
            DB_NAME = properties.getProperty("db_name");
            HOST = properties.getProperty("host_name");
            PORT = properties.getProperty("port");
            LOGIN = properties.getProperty("user_name");
            PASSWORD = properties.getProperty("user_password");

            System.out.println("URL: " + DB_TYPE+HOST+":"+PORT+"/"+DB_NAME +
                    "\nlogin: " + LOGIN +
                    "\npassword: " + PASSWORD);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
