package Dao.connection;
import Model.User;
import fr.it.cluster.Singleton;

import java.sql.*;

public class UserDaoImpl implements UserDao{

    @Override
    public User findUserByEmail(String email) {
        DataSource dataSource = new DataSource();
        try {
            Connection connection = dataSource.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee  WHERE  employee.email=\"" + email + "\";");{
                if (resultSet.next()){
                    return new User(
                        resultSet.getLong("ID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("email"),
                            resultSet.getString("Password")
                    );
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User save(User userDataBase1) {

        DataSource dataSource = new DataSource();
        try {
            Connection connection = dataSource.createConnection();
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO employee(FirstName,LastName,email,Password) VALUE (\""+userDataBase1.getName()+"\"," +
                    "\""+userDataBase1.getLastName()+"\"," +
                    "\""+userDataBase1.getEmail()+"\"," +
                    "\""+userDataBase1.getPassword()+"\")");


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }




}
