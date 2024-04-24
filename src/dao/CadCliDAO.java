package dao;

import conexao.DAOConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import vo.CadCliVO;

public class CadCliDAO {
    
    public boolean incluirClientes(CadCliVO vo){
        
        boolean retorno = false;
        
        Connection conexao = DAOConnection.getInstance();
       
        if(conexao !=null){
            try{
                PreparedStatement pstmt = conexao.prepareStatement(
                                "Insert Into cliente (nome, endereco, rg, cpf, dtnasc, telefone1, telefone2, "
                                        + "email, responsavel, telefoneresp, modalidade, periodo, horario) Values " +
                        "(?,?,?,?,?,?,?,?,?,?,?,?,?)");


                pstmt.setString(1,vo.getNome());
                pstmt.setString(2,vo.getEndereco());
                pstmt.setString(3,vo.getRg());
                pstmt.setString(4,vo.getCpf());
                pstmt.setString(5,vo.getDtnasc());
                pstmt.setString(6,vo.getTelefone1());
                pstmt.setString(7,vo.getTelefone2());
                pstmt.setString(8,vo.getEmail()); 
                pstmt.setString(9,vo.getResponsavel());
                pstmt.setString(10,vo.getTelefoneResp());
                pstmt.setString(11,vo.getModalidade());
                pstmt.setString(12,vo.getPeriodo());
                pstmt.setString(13,vo.getHorario());

                retorno = pstmt.execute();      
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }

    return retorno;
    }
    
    public Collection<CadCliVO> selectCadCli(String comando,String valor){
        Collection<CadCliVO> retorno = null;
        Connection conexao = DAOConnection.getInstance();
        if(conexao !=null){
            try{
                PreparedStatement pstmt = conexao.prepareStatement(
                                "SELECT * from cliente where "+comando+" =?");
                pstmt.setString(1,valor);
                retorno = criaColecaoCliente(pstmt.executeQuery());
            }catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return retorno;
    }
    public Collection<CadCliVO> criaColecaoCliente(ResultSet cliente){
        ArrayList<CadCliVO> retorno= new ArrayList<CadCliVO>();
        try{
            while(cliente.next()){
                CadCliVO cli = new CadCliVO();
                cli.setCodigo(cliente.getString(1));
                cli.setNome(cliente.getString(2));
                cli.setEndereco(cliente.getString(3));
                cli.setRg(cliente.getString(4));
                cli.setCpf(cliente.getString(5));
                cli.setDtnasc(cliente.getString(6));
                cli.setTelefone1(cliente.getString(7));
                cli.setTelefone2(cliente.getString(8));
                cli.setEmail(cliente.getString(9));
                cli.setResponsavel(cliente.getString(10));
                cli.setTelefoneResp(cliente.getString(11));
                cli.setModalidade(cliente.getString(12));
                cli.setPeriodo(cliente.getString(13));
                cli.setHorario(cliente.getString(14));

                
                retorno.add(cli);
            }
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return retorno;
    }
 public int atualizaCliente(CadCliVO vo){
        int retorno=0;
        Connection conexao=DAOConnection.getInstance();
        if(conexao !=null){
            try{
                PreparedStatement pstmt=conexao.prepareStatement(
                "UPDATE cliente SET nome=?, endereco=?, rg=?, cpf=?, dtnasc=?, telefone1=?, telefone2=?, email=?, responsavel=?, telefoneresp=?, modalidade=?, periodo=?, horario=? WHERE codigo=?");
                pstmt.setString(1,vo.getNome());
                pstmt.setString(2,vo.getEndereco());
                pstmt.setString(3,vo.getRg());
                pstmt.setString(4,vo.getCpf());
                pstmt.setString(5,vo.getDtnasc());
                pstmt.setString(6,vo.getTelefone1());
                pstmt.setString(7,vo.getTelefone2());
                pstmt.setString(8,vo.getEmail());
                pstmt.setString(9,vo.getResponsavel());
                pstmt.setString(10,vo.getTelefoneResp());
                pstmt.setString(11,vo.getModalidade());
                pstmt.setString(12,vo.getPeriodo());
                pstmt.setString(13,vo.getHorario());
                pstmt.setString(14,vo.getCodigo());
                retorno = pstmt.executeUpdate();                
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }

        return retorno;
    }
    public boolean deleteCliente(CadCliVO vo){
        boolean retorno= false;
        Connection conexao= DAOConnection.getInstance();
        if(conexao !=null){
            try{
                PreparedStatement pstmt= conexao.prepareStatement("Delete from cliente where codigo=?");
                pstmt.setString(1,vo.getCodigo());
                retorno= pstmt.execute();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
        return retorno;
    }
}
