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
import java.util.List;

    


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
        
        
    
    
    public ArrayList<ProdutosDTO> getProdutos(){
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

    public void venderProduto(int id){
       
        try {
       
        String sql = " UPDATE produtos Set status=? where id=?";
        
       PreparedStatement prep = this.conn.prepareStatement(sql);
        prep.setString(1, "Vendido");
        prep.setInt(2, id);       
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Não foi possivel vender o  produto: " + ex.getMessage());
    
}                
    }
    public List<ProdutosDTO> getProdutosVendidos() {
                String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();            
                    
                    List<ProdutosDTO> listaProdutosVendidos = new ArrayList<>();
                    
                    while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                        ProdutosDTO produtosDTO = new ProdutosDTO();
                        
                        produtosDTO.setId(rs.getInt("id"));
                        produtosDTO.setNome(rs.getString("nome"));
                        produtosDTO.setValor(rs.getInt("valor"));
                        produtosDTO.setStatus(rs.getString("status"));
                        
                        listaProdutosVendidos.add(produtosDTO);    
                    }
                    return listaProdutosVendidos;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
                } catch (SQLException e) {
                    return null;
                }
            }
}
    

    
    
        


