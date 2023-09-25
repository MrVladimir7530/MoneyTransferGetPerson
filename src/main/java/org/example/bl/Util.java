//package org.example.bl;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.EntityManager;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@NoArgsConstructor
//public class Util {
//    private static EntityManager entityManager;
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Payments";
//    private static final String DB_USERNAME = "AccountTest";
//    private static final String DB_PASSWORD = "1234567890";
//
//
//
//    private static HikariConfig config = new HikariConfig();
//    private static HikariDataSource ds;
//
//    static{
//        config.setJdbcUrl(DB_URL);
//        config.setUsername(DB_USERNAME);
//        config.setPassword(DB_PASSWORD);
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
//        ds = new HikariDataSource( config );
//    }
//
//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }
//
//    public static EntityManager getEntityManager() throws SQLException {
//        return entityManager;
//    }
//}
