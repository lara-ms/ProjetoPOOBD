package br.pizzaria.sistemas.dao;

import br.pizzaria.sistemas.model.pizzaria.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends ConnectionDAO {

    public boolean inserirPedido(Pedido pedido, int idCliente) {
        connectToDb();
        String sql = "INSERT INTO pedido(data_pedido, valor_total, status, id_cliente) VALUES (?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, pedido.getData_pedido());
            pst.setFloat(2, pedido.getValor_total());
            pst.setString(3, pedido.getStatus());
            pst.setInt(4, idCliente); // O ID vem do Sistema, não de um objeto Cliente
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pedido: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    public List<Pedido> listarPedido() {
        List<Pedido> pedidos = new ArrayList<>();
        connectToDb();
        // JOIN entre pedido e cliente para trazer o nome do cliente na mesma consulta
        String sql = "SELECT p.*, c.nome as nome_cliente FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getString("data_pedido"),
                        rs.getFloat("valor_total"),
                        rs.getString("status")
                );

                // Exibe no console ou trata como precisar
                System.out.println("Pedido de: " + rs.getString("nome_cliente") +
                        " | Total: R$ " + pedido.getValor_total());

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        } finally {
            closeResources();
        }
        return pedidos;
    }

    // Método auxiliar para fechar tudo (adicione no ConnectionDAO se ainda não tiver)
    private void closeResources() {
        try {
            if (pst != null) pst.close();
            if (connection != null) connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}