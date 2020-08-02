package Dao.connection;
import Model.User;

import java.sql.SQLException;

public interface UserDao  {
    User findUserByEmail(String email) throws SQLException;
    User save(User userDataBase) throws SQLException;
}
