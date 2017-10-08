package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/prawo_jazdy?verifyServerCertificate=false&useSSL=false&requireSSL=false", "test", "test");
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void register(User user) {
        String sqlCommand
                = "INSERT INTO USER(PESEL, NAME, SECOND_NAME) "
                + "VALUES(" + user.getPesel() + ",'" + user.getName() + "','" + user.getSecondName() + "')";
        System.out.println(sqlCommand);

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zarejestrowano");
        } finally {
            closeConnection(connection);
        }
    }

    public static boolean findUser(User user) throws SQLException {
        String sqlCommand = "SELECT * FROM USER WHERE PESEL = " + user.getPesel();
        System.out.println(sqlCommand);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } finally {
            closeConnection(connection);
        }

        return false;
    }

    public static boolean login(User user) throws SQLException {
        String sqlCommand = "SELECT * FROM USER WHERE PESEL = " + user.getPesel()
                + " AND NAME = '" + user.getName() + "'"
                + " AND SECOND_NAME = '" + user.getSecondName() + "'";
        System.out.println(sqlCommand);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } finally {
            closeConnection(connection);
        }

        return false;
    }

    public static void saveExam(User user, Exam exam) {
        String sqlCommand = "INSERT INTO EXAM(POINTS, DATE, USER_PESEL) "
                + "VALUES(" + exam.getPoints() + ", STR_TO_DATE('" + exam.getDate() + "', '%d/%m/%Y'), " + user.getPesel() + ")";
        System.out.println(sqlCommand);

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Bład zapisu wyniku");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public static List<Exam> readAllUserExams(User user) throws SQLException {
        List<Exam> result = new ArrayList<>();
        String sqlCommand = "SELECT * FROM EXAM WHERE USER_PESEL = " + user.getPesel();
        System.out.println(sqlCommand);
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exam exam = new Exam();
                exam.setPoints(resultSet.getLong("POINTS"));
                exam.setDate(resultSet.getString("DATE"));
                result.add(exam);
            }
        } finally {
            closeConnection(connection);
        }

        return result;
    }
}
