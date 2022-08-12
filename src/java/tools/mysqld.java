package tools;
import java.sql.*;

public class mysqld {
    public static Connection connection;
    public static int insert(Connection connection,int id,String name,String address,int age) throws Exception {
        // ? 是 JDBC 预编译的占位符
        PreparedStatement statement=connection.prepareStatement("insert into student(id,student_name,Account,Password) values(?,?,?,?)");
        statement.setInt(1, id);//学生编号
        statement.setString(2, name);//学生姓名
        statement.setString(3, address);//学生住址
        statement.setInt(4, age);//学生年龄
        int result = statement.executeUpdate();
        statement.close();
        connection.close();
        return result;
    }

    public static int delete(Connection connection,int id) throws Exception {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("delete from student where id="+id);
        statement.close();
        connection.close();
        return result;
    }

    public int update(Connection connection,String index,String content, int id) throws Exception {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("update student set"+index+"="+content+" where id="+id);
        statement.close();
        connection.close();
        return result;
    }

    public static ResultSet select(Connection connection,String sql) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        statement.close();

        return result;
    }
}
