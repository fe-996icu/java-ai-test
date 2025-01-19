package com.icu.jdbc.test;

import com.icu.jdbc.pojo.Employee;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {
    private final String url = "jdbc:mysql://localhost:3306/db01";
    private final String username = "root";
    private final String password = "mysql";
    private final String driver = "com.mysql.cj.jdbc.Driver";


    @Test
    public void testUpdate() throws Exception{
        // 1. 注册驱动
        Class.forName(driver);

        // 2. 获取数据库连接 jdbc:mysql://主机:端口号/数据库名称
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3. 获取SQL语句执行对象
        Statement statement = connection.createStatement();

        // 4. 执行 SQL
        int rowAffected = statement.executeUpdate("update employee set name = '齐天大圣' where id = 1"); // DML
        System.out.println("SQL执行完毕影响的记录数为:" + rowAffected);

        // 5. 释放资源
        statement.close();
        connection.close();
    }
    @Test
    // 执行查询操作
    public void testQueryOne(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. 注册驱动
            Class.forName(driver);

            // 2. 打开数据库连接
            conn = DriverManager.getConnection(url, username, password);

            // 3. 获取SQL语句执行对象，预编译sql对象
            String sql = "select * from employee where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);

            rs = pstmt.executeQuery();

            // 4. 处理结果集
            while (rs.next()) {
                Employee user = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getByte("gender"),
                        rs.getString("phone"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getInt("dept_id"),
                        rs.getInt("job_id"),
                        rs.getDate("join_date").toLocalDate(),
                        rs.getTimestamp("update_date").toLocalDateTime(),
                        rs.getByte("status")
                );
                System.out.println("----- 查询完成 -----");
                System.out.println(pstmt.toString());
                System.out.println(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
