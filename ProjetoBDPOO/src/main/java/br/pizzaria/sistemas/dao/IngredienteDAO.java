package br.pizzaria.sistemas.dao;

import br.pizzaria.sistemas.model.pizzaria.Ingrediente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO extends ConnectionDAO{
        public boolean inserirIngrediente(Ingrediente ingrediente){
            connectToDb(); // Abre conexao
            String sql = "INSERT INTO ingrediente(nome, quantidade_estoque, unidade) VALUES (?, ?, ?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, ingrediente.getNome());
                pst.setInt(2, ingrediente.getQuantidade_estoque());
                pst.setString(3, ingrediente.getUnidade());
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

        public List<Ingrediente> listarIngrediente(){
            List<Ingrediente> ingredientes = new ArrayList<>();
            connectToDb();
            String sql = "SELECT * FROM ingrediente";

            try{
                st = connection.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Ingrediente ingrediente = new Ingrediente(
                            rs.getString("nome"),
                            rs.getInt("quantidade_estoque"),
                            rs.getString("unidade")

                    );
                    ingredientes.add(ingrediente);
                }
            } catch (SQLException e){
                System.out.println("Erro ao buscar Ingredientes: " + e.getMessage());
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

            return ingredientes;
        }

        public boolean atualizarIngrediente(Ingrediente ingrediente){
            connectToDb();
            String sql = "UPDATE ingrediente SET quantidade_estoque=?";

            try{
                pst = connection.prepareStatement(sql);
                pst.setInt(1, ingrediente.getQuantidade_estoque());
                pst.execute();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar Ingrediente: " + e.getMessage());
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
