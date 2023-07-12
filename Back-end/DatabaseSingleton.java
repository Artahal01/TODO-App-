import java.sql.*;
public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private Connection connection;
    private PreparedStatement preparedStatement;

    private DatabaseSingleton() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + Settings.DATABASE_NAME, Settings.USER, Settings.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
