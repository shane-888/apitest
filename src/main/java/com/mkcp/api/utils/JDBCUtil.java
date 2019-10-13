package com.mkcp.api.utils;

import com.mkcp.api.beans.ApiDataBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author js_shane
 * @date 2019/9/10 19:24
 */
public class JDBCUtil {

  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/cpdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
  // 数据库的用户名与密码，需要根据自己的设置
  private static final String USER = "root";
  private static final String PASS = "12345678";


  // 添加jdbc 链接数据库  获取需要测试的接口列表
  public static List<ApiDataBean> ApiDataBean() {
    List<ApiDataBean> list = new ArrayList<ApiDataBean>();
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName(JDBC_DRIVER);
      System.out.println("连接数据库...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println(" 实例化Statement对象...");
      stmt = conn.createStatement();
      String sql = "SELECT * FROM frontend";
      ResultSet rs = stmt.executeQuery(sql);
      ApiDataBean apiDataBean1 = null;
      while (rs.next()) {
        // 通过字段检索
        Boolean run = rs.getBoolean("运行用例");
        String desc = rs.getString("用例描述");
        String url = rs.getString("请求地址");
        String method = rs.getString("请求方式");
        String param = rs.getString("参数");
        Boolean contains = rs.getBoolean("contains");
        Integer status = rs.getInt("status");
        String verify = rs.getString("verify");
        String save = rs.getString("save");
        String preParam = rs.getString("pre_param");
        Integer sleep = rs.getInt("sleep");
        String type = rs.getString("提交方式");
        apiDataBean1 = new ApiDataBean();
        apiDataBean1.setRun(run);
        apiDataBean1.setDesc(desc);
        apiDataBean1.setUrl(url);
        apiDataBean1.setMethod(method);
        apiDataBean1.setParam(param);
        apiDataBean1.setContains(contains);
        apiDataBean1.setStatus(status);
        apiDataBean1.setVerify(verify);
        apiDataBean1.setSave(save);
        apiDataBean1.setPreParam(preParam);
        apiDataBean1.setSleep(sleep);
        apiDataBean1.setType(type);
        list.add(apiDataBean1);
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      se.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se2) {
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    System.out.println("Goodbye!");
    return list;
  }

}
