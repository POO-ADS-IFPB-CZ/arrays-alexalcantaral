package view;

import model.Categoria;
import model.Item;
import model.Produto;
import model.Venda;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Bem-vindo ao sistema de vendas!");
            System.out.println("1. Iniciar nova venda");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> realizarVenda(scanner);
                case 2 -> {
                    running = false;
                    System.out.println("Encerrando o sistema...");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void realizarVenda(Scanner scanner) {
        Venda venda = new Venda();
        boolean realizandoVenda = true;

        while (realizandoVenda) {
            System.out.println("\nGerenciamento da Venda");
            System.out.println("1. Adicionar item");
            System.out.println("2. Remover item");
            System.out.println("3. Ver total da venda");
            System.out.println("4. Finalizar venda");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarItem(scanner, venda);
                case 2 -> removerItem(scanner, venda);
                case 3 -> System.out.printf("Total da venda: R$%.2f%n", venda.getTotal());
                case 4 -> {
                    realizandoVenda = false;
                    System.out.println("Venda finalizada.");
                    System.out.printf("Total final: R$%.2f%n", venda.getTotal());
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarItem(Scanner scanner, Venda venda) {
        System.out.print("Código do produto: ");
        int codigoProduto = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Descrição do produto: ");
        String descricao = scanner.nextLine();

        System.out.print("Preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Categorias disponíveis: ALIMENTICIO, HIGIENE, LIMPEZA, HORTIFRUTI, PADARIA");
        System.out.print("Categoria do produto: ");
        String categoriaInput = scanner.nextLine().toUpperCase();
        Categoria categoria = Categoria.valueOf(categoriaInput);

        System.out.print("Quantidade do item: ");
        double quantidade = scanner.nextDouble();

        Produto produto = new Produto(codigoProduto, descricao, preco, categoria);
        Item item = new Item(codigoProduto, produto, quantidade);
        venda.adicionarItem(item);

        System.out.println("Item adicionado com sucesso!");
    }

    private static void removerItem(Scanner scanner, Venda venda) {
        System.out.print("Informe o código do item a ser removido: ");
        int codigoItem = scanner.nextInt();

        Item[] itens = venda.getItens();
        boolean itemRemovido = false;

        for (int i = 0; i < itens.length; i++) {
            if (itens[i].getCodigo() == codigoItem) {
                for (int j = i; j < itens.length - 1; j++) {
                    itens[j] = itens[j + 1];
                }
                itens[itens.length - 1] = null;
                itemRemovido = true;
                break;
            }
        }

        if (itemRemovido) {
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item não encontrado na venda.");
        }
    }
}
