package br.pizzaria.sistemas.dao;

import br.pizzaria.sistemas.model.pizzaria.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConnectionDAO{
    public boolean inserirCliente(Cliente cliente){
        connectToDb(); // Abre conexao
        String sql = "INSERT INTO cliente(nome, email, telefone, endereco) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getTelefone());
            pst.setString(4, cliente.getEndereco());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar conexão: "+ e.getMessage());
            }
        }

        return false;
    }

    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM cliente";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("endereco")

                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar CLientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return clientes;
    }

    public boolean atualizarCliente(Cliente cliente) {
        connectToDb();
        String sql = "UPDATE cliente SET telefone=?, endereco=? WHERE idCliente=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cliente.getTelefone());
            pst.setString(2, cliente.getEndereco());
            pst.setInt(3, cliente.getIdCliente()); // Importante!
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Cliente: " + e.getMessage());
            return false;
        } finally {
            try{
                if(pst != null){
                    pst.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }


}
