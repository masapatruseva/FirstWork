package main;

import dao.Account;
import dao.Users;
import jdbc.CreatorOfTables;
import jdbc.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db");
        JdbcTemplate<Users> userOrm = new JdbcTemplate<>(connection);
        JdbcTemplate<Account> accountOrm = new JdbcTemplate<>(connection);

        Users users = new Users("masha", 18);
        Account account = new Account("wwwww", new BigDecimal(100));

        new CreatorOfTables(connection).createTables();

        userOrm.create(users);
        users.setName("nastya");
        userOrm.update(users);
        Users loadedUsers = userOrm.load(users.getId(), Users.class);
        System.out.println(loadedUsers);

        accountOrm.create(account);
        account.setType("lllllll");
        accountOrm.update(account);
        Account loadedAccount = accountOrm.load(account.getNo(), Account.class);
        System.out.println(loadedAccount);





    }
}
