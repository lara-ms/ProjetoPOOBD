package br.pizzaria.sistemas.dao;

import br.pizzaria.sistemas.model.pizzaria.Pizza;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO extends ConnectionDAO{
    public boolean inserirPizza(Pizza pizza){
        connectToDb(); // Abre conexao
        String sql = "INSERT INTO pizza(nome, descricao, preco, tamanho) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, pizza.getNome());
            pst.setString(2, pizza.getDescricao());
            pst.setFloat(3, pizza.getPreco());
            pst.setString(4, pizza.getTamanho());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pizza: " + e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar conexão: "+ e.getMessage());
            }
        }

        return false;
    }

    public List<Pizza> listarPizza(){
        List<Pizza> pizzas = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM pizza";

        try{
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Pizza pizza = new Pizza(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getFloat("preco"),
                        rs.getString("tamanho")

                );
                pizzas.add(pizza);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar Pizza: " + e.getMessage());
        } finally {
            try{
                if(rs != null) {
                    rs.close();
                }
                if(st != null){
                    st.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return pizzas;
    }
}
