package data_access.cloudsql;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import config.Config;

public class SqlConfig {
    public static DataSource NewSQL() {
        HikariConfig config = new HikariConfig();

        String ip = Config.getEnv("SQL_IP");
        String port = Config.getEnv("SQL_PORT");
        String database = Config.getEnv("SQL_DATABASE");
        String user = Config.getEnv("SQL_USER");
        String password = Config.getEnv("SQL_PASSWORD");
        String instanceConnectionName = Config.getEnv("SQL_INSTANCE_CONNECTION_NAME");

        String jcbcUrl = String.format("jdbc:mysql://%s:%s/%s", ip, port, database);

        config.setJdbcUrl(jcbcUrl);
        config.setUsername(user);
        config.setPassword(password);
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", instanceConnectionName);

        return new HikariDataSource(config);
    }
}
