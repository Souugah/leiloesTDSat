/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

    


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset; 
    conectaDAO conecta;
    
    
     public ProdutosDAO(){
        this.conecta = new conectaDAO();
        this.conn = this.conecta.connectDB();
    
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        try {
       
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
       PreparedStatement prep = this.conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
    } {
            }
}                 
        
        
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
       
       String sql = "SELECT * FROM produtos"; 
    
    try {
        PreparedStatement prep = this.conn.prepareStatement(sql);
        ResultSet resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagem.add(produto);
        }
        
        resultset.close();
        prep.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Não foi possível listar os produtos: " + ex.getMessage());
    }   
     
    return listagem;
}

}
    
    
        


