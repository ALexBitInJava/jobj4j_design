package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    private void separateMethod(String s) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(s);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table if not exists %s(%s);",
                tableName, "id serial primary key");
        separateMethod(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s", tableName);
        separateMethod(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("alter table %s add column %s %s",
                tableName, columnName, type);
        separateMethod(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s",
            tableName, columnName);
        separateMethod(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s",
            tableName, columnName, newColumnName);
        separateMethod(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("demo_table");
            System.out.println(getTableScheme(tableEditor.connection, "demo_table"));
            tableEditor.dropTable("demo_table");

            tableEditor.createTable("demo_table1");
            System.out.println(getTableScheme(tableEditor.connection, "demo_table1"));

            tableEditor.addColumn("demo_table1", "col_umn", "text");
            System.out.println(getTableScheme(tableEditor.connection, "demo_table1"));

            tableEditor.renameColumn("demo_table1", "col_umn", "co_lu_mn");
            System.out.println(getTableScheme(tableEditor.connection, "demo_table1"));

            tableEditor.dropColumn("demo_table1", "co_lu_mn");
            System.out.println(getTableScheme(tableEditor.connection, "demo_table1"));
        }
    }
}
