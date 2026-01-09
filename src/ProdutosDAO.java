/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    // CADASTRAR (INSERT)
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            Connection conn = new conectaDAO().connectDB();
            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            prep.close();
            conn.close();

        } catch (Exception e) {
            // Lança o erro para a VIEW mostrar a mensagem
            throw new RuntimeException(e);
        }
    }

    // LISTAR (SELECT)
    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT id, nome, valor, status FROM produtos";
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try {
            Connection conn = new conectaDAO().connectDB();
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }

            rs.close();
            prep.close();
            conn.close();

            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


