/**
 *
 * @author Aengus Chen 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	private static final String DRIVERCLASS = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private static final String URL = "jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=Student";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "";
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	protected static String ID = null;
	private static  String ID2 ;
	protected static String Name = null;
	protected static String Sex = null;
	private static  String  Administrators = String.valueOf(110); //拥有删除数据库用户权限
	protected static String Password = null; 

	  // 静态方法加载数据库驱动
      static {
		 try {
			Class.forName(DRIVERCLASS).newInstance();// 加载数据库驱动
			DriverManager.getConnection(URL, USERNAME, PASSWORD);// 创建新的数据库连接
			System.out.println("驱动加载成功 ");//控制台信息显示
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
      // 创建数据库连接
      public static Connection getConnection() {

		 Connection conn = threadLocal.get();// 从线程中获得数据库连接
		   if (conn == null) {// 没有可用的数据库连接
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 创建新的数据库连接
				threadLocal.set(conn);// 将数据库连接保存到线程中
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("数据库连接成功 "); //控制台信息查看  
		return conn;
	}

	  // 关闭数据库连接//未调用
	  public static boolean closeConnection() {
		boolean isClosed = true;
		Connection conn = threadLocal.get();// 从线程中获得数据库连接
		threadLocal.set(null);// 清空线程中的数据库连接
		if (conn != null) {// 数据库连接可用
			try {
				conn.close();// 关闭数据库连接
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		System.out.println("数据库关闭成功 "); //控制台信息查看  
		return isClosed;
	}
	
	  /*//数据库连接测试
	     public static void main(String[] args) {	  
		  selectEmpUseName();
		  System.out.println("连接成功 "); //控制台信息查看      
	    } */  
	
	  //用户登录-查询学号
	  public static int selectUserNumber(String selectUserNumber) {
		 Connection conn = getConnection(); // 获取数据库连接
	     int id = 0; // 定义保存返回值的int对象
	     ID2 = selectUserNumber;
	     try {
	          Statement  statment = conn.createStatement(); // 获取Statement对象
	          String sql = "select 学号 from Student where 学号  = '" + selectUserNumber + "'"; // 定义查询SQL语句
	          ResultSet rest = statment.executeQuery(sql); // 执行查询语句获取查询结果集
	          System.out.println("查询数据库：" +selectUserNumber + id );//控制台数据查看
	          while (rest.next()){
	            id = rest.getInt(1); // 获取查询结果
	            System.out.println("数据库查询返回结果： " + id);//控制台数据查看
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return id; // 返回查询结果
	    }
	  
	  //用户登录-查询密码
	  public static int selectPassword(String selectUserNumber,String Password) {
	      Connection conn = getConnection(); // 获取数据库连接
		  int id = 0; // 定义保存返回值的int对象
		   try {
		       Statement statment = conn.createStatement(); // 获取Statement对象
		       String sql = "select 密码 from Student where 密码 = '" + Password + "' and 学号  = '" + selectUserNumber + "'"; // 定义查询SQL语句
		       ResultSet rest = statment.executeQuery(sql); // 执行查询语句获取查询结果集
		       System.out.println("查询数据库： " +Password + id);//控制台数据查看
		       while (rest.next()){
		            id = rest.getInt(1); // 获取查询结果
		            System.out.println("数据库查询返回结果： " + id);//控制台数据查看
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return id; // 返回查询结果
		    }
	  
	  //用户信息查询
	  public static void select(String selectUserNumber) {
	      Connection conn = getConnection(); // 获取数据库连接
	      ID2 = selectUserNumber;//保留用户ID
		   try {
		       Statement statment = conn.createStatement(); // 获取Statement对象
		       String sql = "select * from Student where 学号  = '" + selectUserNumber + "'";  // 定义查询SQL语句
		       ResultSet rest = statment.executeQuery(sql); // 执行查询语句获取查询结果集
		       while (rest.next()){
		            ID = rest.getString(1); // 获取查询结果
		            Name = rest.getString(2);
		            Sex = rest.getString(3);
		            Password = rest.getString(4);//窗口无显示
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    } 
	  	  
	  //用户信息插入-判断用户是否存在 
	  public static int InsertUserNumber1(String InsertuserID,String InsertPassword) {
		   @SuppressWarnings("unused")
		Connection conn = getConnection(); // 获取数据库连接
		   if(selectUserNumber(InsertuserID)!=0){
		    	System.out.println("用户已存在");
		        return 0 ; 
		    }
		    else{
	            return 1;
		    }
	  } 
	  
	  //用户信息插入
	  public static int InsertUserNumber(String InsertuserID,String InsertUserNumber,String InsertUserSex,String InsertPassword) {
		   Connection conn = getConnection(); // 获取数据库连接
		   if(selectUserNumber(InsertUserNumber)!=0){
		    	System.out.println("该用户已存在");
		     return 0 ; 
		    }
		    else{
	            try {
	            PreparedStatement statement = conn
	                    .prepareStatement("insert into Student values('" + InsertuserID + "','" + InsertUserNumber + "','" + InsertUserSex + "','" + InsertPassword + "')"); // 定义插入数据库的预处理语句
	            statement.executeUpdate(); // 执行预处理语句
	            System.out.println("注册运行成功");
	            }catch (SQLException e) {
	            e.printStackTrace();
	            }
	        return 1;
		    }
	  }
    
	  //用户数据更新
	  public static int UpdateUserNumber(String updateUserID,String updateUserNumber, String updateUserSex, String updateUserPassword) {
		Connection conn = getConnection(); // 获取数据库连接
		if(updateUserID.equals(ID2) == true){
            try {
            	PreparedStatement statement1 = conn
      	                  .prepareStatement("update Student set 姓名 = '" + updateUserNumber + "' where 学号 = '" + updateUserID + "'"); // 定义插入数据库的预处理语句
            	PreparedStatement statement2 = conn
              	          .prepareStatement("update Student set 性别  = '" + updateUserSex + "' where 学号 = '" + updateUserID + "'");
              	PreparedStatement statement3 = conn
            	          .prepareStatement("update Student set 密码  = '" + updateUserPassword + "' where 学号 = '" + updateUserID + "'");
              	statement1.executeUpdate();
              	statement2.executeUpdate();
            	statement3.executeUpdate(); // 执行预处理语句
	            System.out.println("更新运行成功");
	            }catch (SQLException e) {
	            e.printStackTrace();
	            }
            return 1;
		}
		else
			return 0;

	}
	  
	  //用户数据删除
	  public static int DeleteUserNumber(String DeleteuserID) {
		   Connection conn = getConnection(); // 获取数据库连接
			if(Administrators.equals(ID2) == true){
	            try {
	            	PreparedStatement statement1 = conn
	      	                  .prepareStatement("delete from Student where 学号 = '" + DeleteuserID + "'"); // 定义插入数据库的预处理语句
	              	statement1.executeUpdate(); // 执行预处理语句
		            System.out.println("删除运行成功");
		            }catch (SQLException e) {
		            e.printStackTrace();
		            }
	            return 1;
			}
			else
				return 0;

		}
	  
      //*********************************************************************	  
	  //所有用户信息查询//2017/6/10//控制台功能实现，窗口功能未实现
	  public static int SelectUser() {
		   Connection conn = getConnection(); // 获取数据库连接
				try {
				       Statement statment = conn.createStatement(); // 获取Statement对象
				       String sql = "select * from Student ";  // 定义查询SQL语句
				       ResultSet rest = statment.executeQuery(sql); // 执行查询语句获取查询结果集
				       while (rest.next()){
				            ID = rest.getString(1); // 获取查询结果
				            Name = rest.getString(2);
				            Sex = rest.getString(3);
				            Password = rest.getString(4);
				            System.out.println("学号：" + ID + "   姓名：" + Name + "        性别：" + Sex + "        密码：" + Password);
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
	            return 1;
			
		}
	   //**********************************************************************  
}
