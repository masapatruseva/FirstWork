package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class CreatorOfTables {
    private final Connection connection;
    private final String SQL_USER = "CREATE TABLE Users(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255),age INT)" ;

    private final String SQL_ACCOUNT = "CREATE TABLE Account(no BIGINT PRIMARY KEY AUTO_INCREMENT,type VARCHAR(256),rest NUMBER)" ;

    public CreatorOfTables(Connection connection) {
        this.connection = connection;
    }

    public void createTables() {
        createUserTable();
        createAccountTable();
    }

    private void createUserTable() {
        try (var statement = connection.prepareStatement(SQL_USER)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createAccountTable() {
        try (var statement = connection.prepareStatement(SQL_ACCOUNT)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}