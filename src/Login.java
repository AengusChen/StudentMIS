/**
 *
 * @author Aengus Chen 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	 //��¼��������
	@SuppressWarnings("static-access")
	public void start(Stage primaryStage) {
		
		//������̨�������
        primaryStage.setTitle("Welcome StudentMIS");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);//���ö��뷽��Ϊ����
        grid.setHgap(10);//��֮���ˮƽ���Ŀ��Ϊ10
        grid.setVgap(10);//������֮��Ĵ�ֱ���ĸ߶�Ϊ10
        grid.setPadding(new Insets(25, 25, 25, 25));//�����ң��ײ��������Χ�������������

        //���ó�������
        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        //����ѧ�������
        Label userName = new Label("User ID:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        //���������
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        //���õ�¼��ť
        Button btn = new Button("  Sign  in  ");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);
        
        //����ע�ᰴť
        Button bttn = new Button(" Sign  up ");
        HBox hbBttn = new HBox(10);
        hbBttn.getChildren().add(bttn);
        grid.add(bttn, 1, 4);
        
        //�����˳���ť
        Button btnn = new Button("    Exit   ");
        HBox hbBtnn = new HBox(10);
        hbBtnn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtnn.getChildren().add(btnn);
        grid.add(hbBtnn,0,4);

        //�����ı���ʾ
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 1);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");
        
        //��¼�¼�
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String selectUserNumber = String.valueOf(userTextField.getText()); 
                String UserPassword = String.valueOf((pwBox).getText()); 
                if(userTextField.getText().equals("")||(pwBox).getText().equals("")){
                	actiontarget.setText( "��¼ʧ�ܣ�");
                }
                else{
                	System.out.println("�����û���Ϣ�� ѧ�ţ� " +selectUserNumber+  "   ���룺"+ UserPassword);//����̨��Ϣ�鿴
                    int user = JDBC.selectUserNumber(selectUserNumber);
                    int Password = JDBC.selectPassword(selectUserNumber,UserPassword);
                    JDBC.select(selectUserNumber);
                    if ((user != 0)&&(Password != 0)) {
                    	actiontarget.setText( "��¼�ɹ���");
                    	SignIn (primaryStage);//�򿪵�¼�ɹ���Ϣ��ʾ����
                    	primaryStage.close();//�رյ�¼����
                    }
                    else{
                      	actiontarget.setText( "��¼ʧ�ܣ�");
                    }
                   
                 }
                }
                
        });
        
        //ע���¼�
        bttn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
            	 System.out.println("ע������       ");
        		 String InsertUserNumber = String.valueOf(userTextField.getText()); 
                 String InsertUserPassword = String.valueOf((pwBox).getText()); 
                 if(userTextField.getText().equals("") && pwBox.getText().equals("")){
                	 SignUp (primaryStage);//��ע�ᴰ��
                	 primaryStage.close();//�رյ�¼����
                 }
                 else{
                	 System.out.println("����ע����Ϣ ��ѧ�ţ� " +InsertUserNumber+  "   ���룺"+ InsertUserPassword);//����̨��Ϣ�鿴
            		 int rest = JDBC.InsertUserNumber1( InsertUserNumber,InsertUserPassword) ;
            		 if(rest == 0)
            			 actiontarget.setText("ID�Ѵ��ڣ�");
            		 else
            		 {  
            		     SignUp (primaryStage);//��ע�ᴰ��
            		     primaryStage.close();//�رյ�¼����
            		     }
                 }
            }
        });
        
        //�˳��¼�
        btnn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
            	System.exit(0);//�˳�����
            }
        });
        
        //��������
        Scene scene = new Scene(grid, 400, 260);//��̨����Ϊgrid���֣�400��260��С�ĳ���
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());//ʹ��CSS
        primaryStage.show();
        System.out.println("��¼�������� ");//����̨��Ϣ��ʾ
        //JDBC.SelectUser();
    }

     //��¼�ɹ���Ϣ��ʾ����	
	 public void SignIn (Stage primaryStage) {
		 GridPane grid1 = new GridPane();
		 Stage secondWindow = new Stage();
		 grid1.setId("Login-Successful");
		 Scene scene = new Scene(grid1,400,260);
		 secondWindow.setTitle("Sign In");
		 secondWindow.setScene(scene);
		 secondWindow.show();
		
	     grid1.setAlignment(Pos.CENTER);
	     grid1.setHgap(10);
	     grid1.setVgap(10);
	     grid1.setPadding(new Insets(25, 25, 25, 25));

	     Text scenetitle = new Text("Login Successful");
	     scenetitle.setId("Login-Successful-text");
	     grid1.add(scenetitle, 0, 0, 2, 1);

	     Label userID = new Label("User ID:        " + JDBC.ID);
	     grid1.add(userID, 0, 1);

	     Label userName = new Label("User Name:  " + JDBC.Name);
	     grid1.add(userName, 0, 2);
	        
	     Label userSex = new Label("User Sex:      " + JDBC.Sex);
	     grid1.add(userSex, 0, 3);

	     Button btnn1 = new Button("  Logout ");
		 HBox hbBtnn1 = new HBox(10);
		 hbBtnn1.setAlignment(Pos.BOTTOM_LEFT);
		 hbBtnn1.getChildren().add(btnn1);
		 grid1.add(hbBtnn1,0,5);
		 
		 Button btnn2 = new Button("     Edit     ");
	     HBox hbBtnn2 = new HBox(10);
	     hbBtnn2.setAlignment(Pos.BOTTOM_RIGHT);
	     hbBtnn2.getChildren().add(btnn2);
	     grid1.add(hbBtnn2,1,5);
		 
		 btnn1.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 secondWindow.close();
            	 start(primaryStage);//�򿪵�¼����
             }
         });
		     
		 btnn2.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 Edit (primaryStage);//�򿪱༭����
            	 secondWindow.close();
             }
         });
		 
		 scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
	 }
	 
	 //ע�ᴰ��
     public void SignUp (Stage primaryStage) {
    	 GridPane grid2 = new GridPane();
    	 Stage secondWindow = new Stage();
    	 grid2.setId("SignUp-Successful");
		 Scene scene = new Scene(grid2,400,260);
		 secondWindow.setTitle("Sign Up");
		 secondWindow.setScene(scene);
		 secondWindow.show();
    	 grid2.setAlignment(Pos.CENTER);
	     grid2.setHgap(10);
	     grid2.setVgap(10);
	     grid2.setPadding(new Insets(25, 25, 25, 25));

	     Text scenetitle = new Text("Sign Up");
	     scenetitle.setId("Sign-Up-text");
	     grid2.add(scenetitle, 0, 0, 2, 1);

	     Label userID = new Label("User ID:");
	     grid2.add(userID, 0, 1);
	     
	     TextField userIDTextField = new TextField();
	     grid2.add(userIDTextField, 1, 1);
	        
	     Label Password = new Label("Password:");
	     grid2.add(Password, 0, 2);
	     
	     PasswordField Password1 = new PasswordField();
	     grid2.add(Password1, 1, 2);

	     Label userName = new Label("User Name:");
	     grid2.add(userName, 0, 3);
	     
	     TextField userNameTextField = new TextField();
	     grid2.add(userNameTextField, 1, 3);
	        
	     Label userSex = new Label("user Sex:");
	     grid2.add(userSex, 0, 4);
	     
	     TextField userSexTextField = new TextField();
	     grid2.add(userSexTextField, 1, 4);
	     
	     Button btn2 = new Button(" Sign  Up ");
	     HBox hbBtn = new HBox(10);
	     hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	     hbBtn.getChildren().add(btn2);
	     grid2.add(hbBtn,1,5);
	     
	     Button btnn2 = new Button("  Return ");
	     HBox hbBtnn2 = new HBox(10);
	     hbBtnn2.setAlignment(Pos.BOTTOM_LEFT);
	     hbBtnn2.getChildren().add(btnn2);
	     grid2.add(hbBtnn2,0,5);
	       
	        
	     final Text actiontarget2 = new Text();
	     grid2.add(actiontarget2, 0, 6);
	     actiontarget2.setId("actiontarget2");
         
         btn2.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
             	 System.out.println("ע������       ");
             	 String InsertuserID = String.valueOf(userIDTextField.getText()); 
                 String InsertUserPassword = String.valueOf(Password1.getText()); 
       	         String InsertUserNumber = String.valueOf(userNameTextField.getText()); 
                 String InsertUserSex = String.valueOf(userSexTextField.getText()); 
                  System.out.println("����ע����Ϣ ��ѧ�ţ� " +InsertuserID+"   ������" 
                                      + InsertUserNumber +  "   �Ա�" + InsertUserSex 
                                      + "   ���룺" + InsertUserPassword);//����̨���ݲ鿴
                 if((userIDTextField.getText().equals("") )||( Password1.getText().equals("") )||( userNameTextField.getText().equals("") )||( userSexTextField.getText().equals(""))){
                	 actiontarget2.setText( "���ݲ�Ϊ�գ�");}
                 else
                 {
      		        int rest =JDBC.InsertUserNumber( InsertuserID,InsertUserNumber,InsertUserSex,InsertUserPassword) ;
      		     if(rest == 0)
      			     actiontarget2.setText("�û��Ѵ��ڣ�");
      		     else{
      		         try {
						Thread.sleep(1000);//�ӳٵȴ�1000����
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
      		         secondWindow.close();//�رմ���
      		         start(primaryStage);//�򿪵�¼����
      		        }
      		     }
             }
         });
	        
         btnn2.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 secondWindow.close();
            	 start(primaryStage);//�򿪵�¼����
             }
         });
         
         scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
		 
	 }
     
     //��������
     public void Edit (Stage primaryStage) {
    	 GridPane grid3 = new GridPane();
    	 Stage secondWindow = new Stage();
    	 grid3.setId("Update-Data");
		 Scene scene = new Scene(grid3,400,260);
		 secondWindow.setTitle("Update Data");
		 secondWindow.setScene(scene);
		 secondWindow.show();
    	 grid3.setAlignment(Pos.CENTER);
	     grid3.setHgap(10);
	     grid3.setVgap(10);
	     grid3.setPadding(new Insets(25, 25, 25, 25));

	     Text scenetitle = new Text("Update Data");
	     scenetitle.setId("Update-Data-text");
	     grid3.add(scenetitle, 0, 0, 2, 1);

	     Label userID = new Label("User ID:");
	     grid3.add(userID, 0, 1);
	     
	     TextField userIDTextField = new TextField();
	     grid3.add(userIDTextField, 1, 1);
	        
	     Label Password = new Label("Password:");
	     grid3.add(Password, 0, 2);
	     
	     PasswordField Password1 = new PasswordField();
	     grid3.add(Password1, 1, 2);

	     Label userName = new Label("User Name:");
	     grid3.add(userName, 0, 3);
	     
	     TextField userNameTextField = new TextField();
	     grid3.add(userNameTextField, 1, 3);
	        
	     Label userSex = new Label("user Sex:");
	     grid3.add(userSex, 0, 4);
	     
	     TextField userSexTextField = new TextField();
	     grid3.add(userSexTextField, 1, 4);
	     
	     Button btn3 = new Button("   Save   ");
	     HBox hbBtn3 = new HBox(10);
	     hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
	     hbBtn3.getChildren().add(btn3);
	     grid3.add(hbBtn3,1,5);
	     
	     Button btnn3 = new Button("  Return ");
	     HBox hbBtnn3 = new HBox(10);
	     hbBtnn3.setAlignment(Pos.BOTTOM_LEFT);
	     hbBtnn3.getChildren().add(btnn3);
	     grid3.add(hbBtnn3,0,5);
	       
	     Button bttn3 = new Button("   Delete    ");
	     HBox hbBttn3 = new HBox(10);
	     hbBttn3.getChildren().add(bttn3);
	     grid3.add(bttn3, 1, 5);
	     
	     final Text actiontarget3 = new Text();
	     grid3.add(actiontarget3, 0, 6);
	     actiontarget3.setId("actiontarget3");
         
         btn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
             	 System.out.println("������������       ");
             	 String UpdateuserID = String.valueOf(userIDTextField.getText()); 
                 String UpdateUserPassword = String.valueOf(Password1.getText()); 
       	         String UpdateUserNumber = String.valueOf(userNameTextField.getText()); 
                 String UpdateUserSex = String.valueOf(userSexTextField.getText()); 
                 System.out.println("���������Ϣ ��ѧ�� " + UpdateuserID + "   ������" + UpdateUserNumber +  "   �Ա�" + UpdateUserSex 
                                      + "   ���룺" + UpdateUserPassword);//����̨���ݲ鿴
                 if(( userIDTextField.getText().equals("") )||( Password1.getText().equals("") )&&( userNameTextField.getText().equals("") )&&( userSexTextField.getText().equals(""))){
                	 actiontarget3.setText( "���ݲ�Ϊ�գ�");}
                 else
                 {
      		        int rest = JDBC.UpdateUserNumber( UpdateuserID,UpdateUserNumber,UpdateUserSex,UpdateUserPassword) ;
      		        if(rest == 1){
      			        try {
  					       Thread.sleep(1000);//�ӳٵȴ�1000����
  					       secondWindow.close();
  				        } catch (InterruptedException e1) {
  					       e1.printStackTrace();
  				     }
      			        start(primaryStage);//�򿪵�¼����
      			        }
      		        else{
      		            actiontarget3.setText( "ID����");}
		        }
             }
         });
	        
         btnn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 secondWindow.close();
            	 start(primaryStage);//�򿪵�¼����
             }
         });
     
         bttn3.setOnAction(new EventHandler<ActionEvent>(){
             public void handle(ActionEvent e) {
            	 System.out.println("ɾ����������       ");
             	 String UpdateuserID = String.valueOf(userIDTextField.getText()); 
             	 System.out.println("����ɾ����Ϣ ��ѧ�� " + UpdateuserID );//����̨���ݲ鿴
                 if( userIDTextField.getText().equals("") ){
                	 actiontarget3.setText( "ID��Ϊ�գ�");
                 }
                 else{
                	 int rest = JDBC.DeleteUserNumber(UpdateuserID);
                	 if(rest == 1){
       			        try {
   					       Thread.sleep(1000);//�ӳٵȴ�1000����
   					       secondWindow.close();
   				        } catch (InterruptedException e1) {
   					       e1.printStackTrace();
   				     }
       			        start(primaryStage);//�򿪵�¼����
       			        }
       		         else{
       		            actiontarget3.setText( "Ȩ�޲��㣡");}
 		        }
                 }
            	
         });

         scene.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
		 
	 }  
    
     //��������
     public static void main(String[] args) {
        launch(args);
    }
        
}