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
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
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
    } finally {
            }
}                 
        
        
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

