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
	private static  String  Administrators = String.valueOf(110); //ӵ��ɾ�����ݿ��û�Ȩ��
	protected static String Password = null; 

	  // ��̬�����������ݿ�����
      static {
		 try {
			Class.forName(DRIVERCLASS).newInstance();// �������ݿ�����
			DriverManager.getConnection(URL, USERNAME, PASSWORD);// �����µ����ݿ�����
			System.out.println("�������سɹ� ");//����̨��Ϣ��ʾ
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
      // �������ݿ�����
      public static Connection getConnection() {

		 Connection conn = threadLocal.get();// ���߳��л�����ݿ�����
		   if (conn == null) {// û�п��õ����ݿ�����
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// �����µ����ݿ�����
				threadLocal.set(conn);// �����ݿ����ӱ��浽�߳���
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("���ݿ����ӳɹ� "); //����̨��Ϣ�鿴  
		return conn;
	}

	  // �ر����ݿ�����//δ����
	  public static boolean closeConnection() {
		boolean isClosed = true;
		Connection conn = threadLocal.get();// ���߳��л�����ݿ�����
		threadLocal.set(null);// ����߳��е����ݿ�����
		if (conn != null) {// ���ݿ����ӿ���
			try {
				conn.close();// �ر����ݿ�����
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		System.out.println("���ݿ�رճɹ� "); //����̨��Ϣ�鿴  
		return isClosed;
	}
	
	  /*//���ݿ����Ӳ���
	     public static void main(String[] args) {	  
		  selectEmpUseName();
		  System.out.println("���ӳɹ� "); //����̨��Ϣ�鿴      
	    } */  
	
	  //�û���¼-��ѯѧ��
	  public static int selectUserNumber(String selectUserNumber) {
		 Connection conn = getConnection(); // ��ȡ���ݿ�����
	     int id = 0; // ���屣�淵��ֵ��int����
	     ID2 = selectUserNumber;
	     try {
	          Statement  statment = conn.createStatement(); // ��ȡStatement����
	          String sql = "select ѧ�� from Student where ѧ��  = '" + selectUserNumber + "'"; // �����ѯSQL���
	          ResultSet rest = statment.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
	          System.out.println("��ѯ���ݿ⣺" +selectUserNumber + id );//����̨���ݲ鿴
	          while (rest.next()){
	            id = rest.getInt(1); // ��ȡ��ѯ���
	            System.out.println("���ݿ��ѯ���ؽ���� " + id);//����̨���ݲ鿴
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return id; // ���ز�ѯ���
	    }
	  
	  //�û���¼-��ѯ����
	  public static int selectPassword(String selectUserNumber,String Password) {
	      Connection conn = getConnection(); // ��ȡ���ݿ�����
		  int id = 0; // ���屣�淵��ֵ��int����
		   try {
		       Statement statment = conn.createStatement(); // ��ȡStatement����
		       String sql = "select ���� from Student where ���� = '" + Password + "' and ѧ��  = '" + selectUserNumber + "'"; // �����ѯSQL���
		       ResultSet rest = statment.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
		       System.out.println("��ѯ���ݿ⣺ " +Password + id);//����̨���ݲ鿴
		       while (rest.next()){
		            id = rest.getInt(1); // ��ȡ��ѯ���
		            System.out.println("���ݿ��ѯ���ؽ���� " + id);//����̨���ݲ鿴
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return id; // ���ز�ѯ���
		    }
	  
	  //�û���Ϣ��ѯ
	  public static void select(String selectUserNumber) {
	      Connection conn = getConnection(); // ��ȡ���ݿ�����
	      ID2 = selectUserNumber;//�����û�ID
		   try {
		       Statement statment = conn.createStatement(); // ��ȡStatement����
		       String sql = "select * from Student where ѧ��  = '" + selectUserNumber + "'";  // �����ѯSQL���
		       ResultSet rest = statment.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
		       while (rest.next()){
		            ID = rest.getString(1); // ��ȡ��ѯ���
		            Name = rest.getString(2);
		            Sex = rest.getString(3);
		            Password = rest.getString(4);//��������ʾ
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    } 
	  	  
	  //�û���Ϣ����-�ж��û��Ƿ���� 
	  public static int InsertUserNumber1(String InsertuserID,String InsertPassword) {
		   @SuppressWarnings("unused")
		Connection conn = getConnection(); // ��ȡ���ݿ�����
		   if(selectUserNumber(InsertuserID)!=0){
		    	System.out.println("�û��Ѵ���");
		        return 0 ; 
		    }
		    else{
	            return 1;
		    }
	  } 
	  
	  //�û���Ϣ����
	  public static int InsertUserNumber(String InsertuserID,String InsertUserNumber,String InsertUserSex,String InsertPassword) {
		   Connection conn = getConnection(); // ��ȡ���ݿ�����
		   if(selectUserNumber(InsertUserNumber)!=0){
		    	System.out.println("���û��Ѵ���");
		     return 0 ; 
		    }
		    else{
	            try {
	            PreparedStatement statement = conn
	                    .prepareStatement("insert into Student values('" + InsertuserID + "','" + InsertUserNumber + "','" + InsertUserSex + "','" + InsertPassword + "')"); // ����������ݿ��Ԥ�������
	            statement.executeUpdate(); // ִ��Ԥ�������
	            System.out.println("ע�����гɹ�");
	            }catch (SQLException e) {
	            e.printStackTrace();
	            }
	        return 1;
		    }
	  }
    
	  //�û����ݸ���
	  public static int UpdateUserNumber(String updateUserID,String updateUserNumber, String updateUserSex, String updateUserPassword) {
		Connection conn = getConnection(); // ��ȡ���ݿ�����
		if(updateUserID.equals(ID2) == true){
            try {
            	PreparedStatement statement1 = conn
      	                  .prepareStatement("update Student set ���� = '" + updateUserNumber + "' where ѧ�� = '" + updateUserID + "'"); // ����������ݿ��Ԥ�������
            	PreparedStatement statement2 = conn
              	          .prepareStatement("update Student set �Ա�  = '" + updateUserSex + "' where ѧ�� = '" + updateUserID + "'");
              	PreparedStatement statement3 = conn
            	          .prepareStatement("update Student set ����  = '" + updateUserPassword + "' where ѧ�� = '" + updateUserID + "'");
              	statement1.executeUpdate();
              	statement2.executeUpdate();
            	statement3.executeUpdate(); // ִ��Ԥ�������
	            System.out.println("�������гɹ�");
	            }catch (SQLException e) {
	            e.printStackTrace();
	            }
            return 1;
		}
		else
			return 0;

	}
	  
	  //�û�����ɾ��
	  public static int DeleteUserNumber(String DeleteuserID) {
		   Connection conn = getConnection(); // ��ȡ���ݿ�����
			if(Administrators.equals(ID2) == true){
	            try {
	            	PreparedStatement statement1 = conn
	      	                  .prepareStatement("delete from Student where ѧ�� = '" + DeleteuserID + "'"); // ����������ݿ��Ԥ�������
	              	statement1.executeUpdate(); // ִ��Ԥ�������
		            System.out.println("ɾ�����гɹ�");
		            }catch (SQLException e) {
		            e.printStackTrace();
		            }
	            return 1;
			}
			else
				return 0;

		}
	  
      //*********************************************************************	  
	  //�����û���Ϣ��ѯ//2017/6/10//����̨����ʵ�֣����ڹ���δʵ��
	  public static int SelectUser() {
		   Connection conn = getConnection(); // ��ȡ���ݿ�����
				try {
				       Statement statment = conn.createStatement(); // ��ȡStatement����
				       String sql = "select * from Student ";  // �����ѯSQL���
				       ResultSet rest = statment.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
				       while (rest.next()){
				            ID = rest.getString(1); // ��ȡ��ѯ���
				            Name = rest.getString(2);
				            Sex = rest.getString(3);
				            Password = rest.getString(4);
				            System.out.println("ѧ�ţ�" + ID + "   ������" + Name + "        �Ա�" + Sex + "        ���룺" + Password);
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
	            return 1;
			
		}
	   //**********************************************************************  
}
