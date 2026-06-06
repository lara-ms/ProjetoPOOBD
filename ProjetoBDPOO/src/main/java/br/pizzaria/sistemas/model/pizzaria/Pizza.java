package br.pizzaria.sistemas.model.pizzaria;

import java.util.List;

public class Pizza {
    private String nome;
    private String descricao;
    private float preco;
    private String tamanho;
    private int idPizza;
    private List<Ingrediente> ingredientes;

    public Pizza(String nome, String descricao, float preco, String tamanho) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPreco() {
        return preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public int getIdPizza() {
        return idPizza;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void adicioarIngrediente(Ingrediente ing){

    }
}
