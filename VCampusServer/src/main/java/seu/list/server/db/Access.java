//package seu.list.server.db;
//
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//
//public class Access {
//
//    /**
//     * Access数据库Connection
//     */
//
//    public static Connection connection;
//
//    public Access(String path, String user, String pwd) {
//        try {
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//加载ucanaccess驱动
//            System.out.println("加载驱动成功");
//        } catch (Exception e) {
//            System.out.println("加载驱动失败");
//            throw new RuntimeException(e.getMessage());
//
//        }
//
//        try {
//            //获取Access数据库连接(Connection)
//            this.connection = DriverManager.getConnection("jdbc:ucanaccess://" + path, user, pwd);
//            System.out.println("连接数据库成功");
//        } catch (Exception e) {
//            System.out.println("连接数据库失败");
//            throw new RuntimeException(e.getMessage());
//
//        }
//    }
//
//}
//
//
//
//
//   /* public Connection getAccessConnection(String path, String user, String pwd) {
//        try {
//            //获取Access数据库连接(Connection)
//            this.connection = DriverManager.getConnection("jdbc:ucanaccess://" + path, user, pwd);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        return this.connection;
//    }
//
//    public static void main(String[] args) throws Exception {
//        Access access=new Access();
//        Connection connection = access.getAccessConnection("D:\\ideaprojects\\vcampus\\src\\main\\lib\\exercise1.accdb", "" , "123456");
//        access.select(connection);
//    }
//
//
//    public int insert(Connection connection) throws Exception {
//        // ? 是 JDBC 预编译的占位符
//        PreparedStatement statement=connection.prepareStatement("insert into student(id,name,address,age) values(?,?,?,?)");
//        statement.setInt(1, 1);//学生编号
//        statement.setString(2, "赵六");//学生姓名
//        statement.setString(3, "湖南省、衡阳市、珠晖区1");//学生住址
//        statement.setInt(4, 20);//学生年龄
//        int result = statement.executeUpdate();
//        statement.close();
//        connection.close();
//        return result;
//    }
//
//
//    public int delete(Connection connection) throws Exception {
//        Statement statement = connection.createStatement();
//        int result = statement.executeUpdate("delete from student where id=3");
//        statement.close();
//        connection.close();
//        return result;
//    }
//
//    public int update(Connection connection) throws Exception {
//        Statement statement = connection.createStatement();
//        int result = statement.executeUpdate("update student set address='湖南省、衡阳市、珠晖区' where id=1");
//        statement.close();
//        connection.close();
//        return result;
//    }
//
//
//    public void select(Connection connection) throws Exception {
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery("select * from student");
//        while (result.next()) {
//            System.out.print(result.getString("id") + "\t");
//            System.out.print(result.getString("姓名") + "\t");
//            System.out.print(result.getString("籍贯") + "\t");
//            System.out.print(result.getString("年龄") + "\t");
//            System.out.print(result.getString("生日") + "\t");
//            System.out.println();
//        }
//        statement.close();
//        connection.close();
//    }
//
//}*/
//
//
