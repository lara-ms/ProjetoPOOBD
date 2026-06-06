package br.pizzaria.sistemas.model.pizzaria;

public class Ingrediente {
    private String nome;
    private int quantidade_estoque;
    private String unidade;

    public Ingrediente(String nome, int quantidade_estoque, String unidade) {
        this.nome = nome;
        this.quantidade_estoque = quantidade_estoque;
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public String getUnidade() {
        return unidade;
    }
}
