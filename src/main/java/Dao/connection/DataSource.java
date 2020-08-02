package Dao.connection;

import java.lang.ref.PhantomReference;
import java.security.AccessControlException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    DataSource(){
        try {
            Class.forName(DB_Driver);
        }catch (ClassNotFoundException| IllegalAccessError e){
            e.printStackTrace();
        }
    }

    private final String DB_Driver="com.mysql.cj.jdbc.Driver";
    private final String DB_URL="jdbc:mysql://localhost/lesson?user=Sasha&password=Ищщ23652005&serverTimezone=UTC";
    public Connection createConnection() throws SQLException {
        Connection connection = null;
        if (connection == null){
            connection=DriverManager.getConnection(DB_URL);
        }else {
            System.out.println("can not create connection");
        }
        return connection;
    }
}
