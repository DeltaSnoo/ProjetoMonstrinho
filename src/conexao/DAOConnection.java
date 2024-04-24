package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnection {
    private static Connection conexao;
    static{
        if(conexao == null){
            conexao=obtemConexao();
        }
    }
    
    private static Connection obtemConexao(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        
        try{
            String url="jdbc:mysql://localhost:3306/projeto";
            String user="root";
            String pwd="";
            
            return DriverManager.getConnection(url,user,pwd);
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
            
            return null;
        }
    }
    
    private static void encerraConexao(){
        try{
            if((conexao!=null)&&(!conexao.isClosed())){
               conexao.close();
               conexao = null;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    public static Connection getInstance(){
        return conexao;
    }
}
