

import java.sql.*;

public class Main {


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            //add new person
            var preparedStatement = connection.prepareStatement( "INSERT INTO persons ( firstname, lastname, AGE) VALUES ( ?, ?, ? )");
            preparedStatement.setString(1, "Paul" );
            preparedStatement.setString(2, "Paulsen" );
            preparedStatement.setInt(3, 42 );

            //update lastname
            var updateLastname=connection.prepareStatement("UPDATE persons SET lastname=? WHERE lastname=?");
            updateLastname.setString(1,"NOSmith");
            updateLastname.setString(2,"Smith");


            //delete person
            var deleteRaw = connection.prepareStatement("DELETE FROM persons WHERE firstname = ?");
            deleteRaw.setString(1, "Paul");

            ResultSet resultSet = statement.executeQuery( "select * from persons");

            preparedStatement.execute();
            updateLastname.execute();
            deleteRaw.execute();

            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getInt(3));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
