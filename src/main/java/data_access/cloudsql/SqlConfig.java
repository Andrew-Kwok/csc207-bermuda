package data_access.cloudsql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Config;
import org.jetbrains.annotations.NotNull;

import javax.sql.DataSource;

public class SqlConfig {
    public static DataSource NewSQL() {
        HikariConfig config = new HikariConfig();

        String ip = Config.getEnv("SQL_IP");
        String port = Config.getEnv("SQL_PORT");
        String database = Config.getEnv("SQL_DATABASE");
        String user = Config.getEnv("SQL_USER");
        String password = Config.getEnv("SQL_PASSWORD");
        String instanceConnectionName = Config.getEnv("SQL_INSTANCE_CONNECTION_NAME");

        return getDataSource(config, ip, port, database, user, password, instanceConnectionName);
    }

    public static DataSource NewTestSQL() {
        HikariConfig config = new HikariConfig();

        String ip = Config.getEnv("SQL_IP");
        String port = Config.getEnv("SQL_PORT");
        String database = Config.getEnv("SQL_TEST_DATABASE");
        String user = Config.getEnv("SQL_USER");
        String password = Config.getEnv("SQL_PASSWORD");
        String instanceConnectionName = Config.getEnv("SQL_INSTANCE_CONNECTION_NAME");

        return getDataSource(config, ip, port, database, user, password, instanceConnectionName);
    }

    @NotNull
    private static DataSource getDataSource(HikariConfig config, String ip, String port, String database, String user, String password, String instanceConnectionName) {
        String jcbcUrl = String.format("jdbc:mysql://%s:%s/%s", ip, port, database);

        config.setJdbcUrl(jcbcUrl);
        config.setUsername(user);
        config.setPassword(password);
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", instanceConnectionName);

        return new HikariDataSource(config);
    }
}
