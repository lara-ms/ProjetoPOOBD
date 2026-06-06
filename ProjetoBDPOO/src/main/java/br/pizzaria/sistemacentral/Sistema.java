package br.pizzaria.sistemacentral;

import br.pizzaria.sistemas.dao.ClienteDAO;
import br.pizzaria.sistemas.dao.IngredienteDAO;
import br.pizzaria.sistemas.dao.PedidoDAO;
import br.pizzaria.sistemas.dao.PizzaDAO;
import br.pizzaria.sistemas.model.pizzaria.*;
import br.pizzaria.sistemas.model.threads.AtualizaPedido;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private final Scanner sc = new Scanner(System.in);
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PizzaDAO pizzaDAO = new PizzaDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final IngredienteDAO ingredienteDAO = new IngredienteDAO();

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== PIZZARIA =====");
            System.out.println("[ 1 ] Clientes");
            System.out.println("[ 2 ] Pizzas");
            System.out.println("[ 3 ] Pedidos");
            System.out.println("[ 4 ] Ingredientes");
            System.out.println("[ 0 ] Sair");
            System.out.print("Opcao: ");
            opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuPizzas();
                    break;
                case 3:
                    menuPedidos();
                    break;
                case 4:
                    menuIngredientes();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private void menuClientes() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("[ 1 ] Cadastrar cliente");
            System.out.println("[ 2 ] Listar clientes");
            System.out.println("[ 3 ] Atualizar telefone/endereco");
            System.out.println("[ 0 ] Voltar");
            System.out.print("Opcao: ");
            opcao = lerOpcao();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 0:
                    {}
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Endereco: ");
        String endereco = sc.nextLine();

        Cliente cliente = new Cliente(0, nome, email, telefone, endereco);
        if (clienteDAO.inserirCliente(cliente)) {
            System.out.println("Cliente cadastrado com sucesso!");
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteDAO.listarCliente();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(System.out::println);
    }

    private void atualizarCliente() {
        System.out.print("Novo telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Novo endereco: ");
        String endereco = sc.nextLine();


        Cliente cliente = new Cliente(0, "", "", telefone, endereco);
        if (clienteDAO.atualizarCliente(cliente)) {
            System.out.println("Cliente atualizado!");
        }
    }

    private void menuPizzas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- PIZZAS ---");
            System.out.println("[ 1 ] Cadastrar pizza");
            System.out.println("[ 2 ] Listar pizzas");
            System.out.println("[ 0 ] Voltar");
            System.out.print("Opcao: ");
            opcao = lerOpcao();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarPizza();
                    break;
                case 2:
                    listarPizzas();
                    break;
                case 0:
                    {}
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private void cadastrarPizza() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Descricao: ");
        String descricao = sc.nextLine();

        System.out.print("Preco (valor inteioro): ");
        float preco = sc.nextFloat();
        sc.nextLine();
        System.out.print("Tamanho (P/M/G): ");
        String tamanho = sc.nextLine();


        Pizza pizza = new Pizza(nome, descricao, preco, tamanho);
        if (pizzaDAO.inserirPizza(pizza)) {
            System.out.println("Pizza cadastrada com sucesso!");
        }
    }

    private void listarPizzas() {
        List<Pizza> pizzas = pizzaDAO.listarPizza();
        if (pizzas.isEmpty()) {
            System.out.println("Nenhuma pizza cadastrada.");
            return;
        }
        pizzas.forEach(System.out::println);
    }

    private void menuPedidos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("[ 1 ] Criar pedido");
            System.out.println("[ 2 ] Listar pedidos");
            System.out.println("[ 0 ] Voltar");
            System.out.print("Opcao: ");
            opcao = lerOpcao();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    criarPedido();
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 0:
                    {}
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private void criarPedido() {
        List<Pizza> pizzas = pizzaDAO.listarPizza();
        if (pizzas.isEmpty()) {
            System.out.println("Nenhuma pizza cadastrada. Cadastre pizzas primeiro.");
            return;
        }

        System.out.println("Clientes disponíveis:");
        List<Cliente> clientes = clienteDAO.listarCliente();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i));
        }

        System.out.print("Digite o ID do cliente: ");
        int idCliente = lerOpcao();
        sc.nextLine();

        Pedido pedido = new Pedido(java.time.LocalDate.now().toString(), 0f, "Pendente");

        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("Pizzas disponíveis:");

            for (Pizza p : pizzas) {
                System.out.println(p);
            }

            System.out.print("Nome da pizza: ");
            String nomePizza = sc.nextLine();

            Pizza pizzaEncontrada = null;
            for (Pizza p : pizzas) {
                if (p.getNome().equalsIgnoreCase(nomePizza)) {
                    pizzaEncontrada = p;
                    break;
                }
            }

            if (pizzaEncontrada != null) {
                pedido.adicionarPizza(pizzaEncontrada);
            } else {
                System.out.println("Pizza nao encontrada.");
            }

            System.out.print("Adicionar mais pizza? (s/n): ");
            continuar = sc.nextLine();

        }

        System.out.println("Forma de pagamento: 1-Pix  2-Cartao");
        int tipoPag = lerOpcao();
        sc.nextLine();
        Pagamento pagamento;
        if (tipoPag == 1) {
            pagamento = new PagamentoPix(pedido.getValor_total(), 0.05);
        } else {
            pagamento = new PagamentoCartao(pedido.getValor_total(), 0.03);
        }
        pedido.setPagamento(pagamento);

        if (pedidoDAO.inserirPedido(pedido, idCliente)) {
            System.out.println("Pedido criado com sucesso!");
        }
    }

    private void listarPedidos() {
        System.out.println(pedidoDAO.listarPedido());
    }

    private void menuIngredientes() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- INGREDIENTES ---");
            System.out.println("[ 1 ] Cadastrar ingrediente");
            System.out.println("[ 2 ] Listar ingredientes");
            System.out.println("[ 3 ] Atualizar estoque");
            System.out.println("[ 0 ] Voltar");
            System.out.print("Opcao: ");
            opcao = lerOpcao();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarIngrediente();
                    break;
                case 2:
                    listarIngredientes();
                    break;
                case 3:
                    atualizarIngrediente();
                    break;
                case 0:
                    {}
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private void cadastrarIngrediente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        sc.nextLine();
        System.out.print("Quantidade em estoque: ");
        int qtd = lerOpcao();
        sc.nextLine();
        System.out.print("Unidade (kg/g/un): ");
        String unidade = sc.nextLine();
        sc.nextLine();

        Ingrediente ing = new Ingrediente(nome, qtd, unidade);
        if (ingredienteDAO.inserirIngrediente(ing)) {
            System.out.println("Ingrediente cadastrado!");
        }
    }

    private void listarIngredientes() {
        ingredienteDAO.listarIngrediente().forEach(System.out::println);
    }


    private void atualizarIngrediente() {
        System.out.print("Nome do ingrediente: ");
        String nome = sc.nextLine();
        sc.nextLine();
        System.out.print("Nova quantidade: ");
        int qtd = lerOpcao();
        sc.nextLine();

        Ingrediente ing = new Ingrediente(nome, qtd, "");
        if (ingredienteDAO.atualizarIngrediente(ing)) {
            System.out.println("Ingrediente atualizado!");
        }
    }

    private int lerOpcao() {
        String input = sc.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números.");
            return -1;
        }
    }

}

