package com.mycompany.sistema.de.gestao.escolar.java.app;

import java.util.List;
import java.util.Scanner;

import dao.UtilizadorDAO;
import model.Utilizador;
import utils.PerfilEnum;

public class UtilizadorUI {

    private static final Scanner scanner = new Scanner(System.in);
    private static UtilizadorDAO utilizadorDAO = new UtilizadorDAO();

    public static void init() {
        while (true) {
            System.out.println("\n===== UTILIZADORES =====");
            System.out.println("1. Listar todos os utilizadores");
            System.out.println("2. Criar novo utilizador");
            System.out.println("3. Editar utilizador");
            System.out.println("4. Remover utilizador");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    listarUtilizadores();
                    break;
                case "2":
                    criarUtilizador();
                    break;
                case "3":
                    editarUtilizador();
                    break;
                case "4":
                    removerUtilizador();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarUtilizadores() {
        List<Utilizador> todos = utilizadorDAO.findAll();
        System.out.println("\nLista de Utilizadores:");
        for (Utilizador u : todos) {
            System.out.println(u.getId() + " | " + u.getUsername() + " | Perfil: " + u.getPerfil());
        }
    }

    private static void criarUtilizador() {
        Utilizador u = new Utilizador();

        System.out.print("Username: ");
        u.setUsername(scanner.nextLine());

        System.out.print("Senha: ");
        u.setSenha(scanner.nextLine());

        u.setPerfil(selecionarPerfil().toString());

        utilizadorDAO.save(u); // salvar no DAO
        System.out.println("Utilizador criado com sucesso!");
    }

    private static void editarUtilizador() {
        System.out.print("Digite o ID do utilizador a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Utilizador u = utilizadorDAO.findById(id);

        if (u == null) {
            System.out.println("Utilizador não encontrado!");
            return;
        }

        System.out.print("Novo username (" + u.getUsername() + "): ");
        String username = scanner.nextLine();
        if (!username.isBlank()) u.setUsername(username);

        System.out.print("Nova senha (oculto): ");
        String senha = scanner.nextLine();
        if (!senha.isBlank()) u.setSenha(senha);

        System.out.println("Perfil atual: " + u.getPerfil());
        System.out.print("Deseja alterar o perfil? (s/n): ");
        String alterar = scanner.nextLine();
        if (alterar.equalsIgnoreCase("s")) {
            u.setPerfil(selecionarPerfil().toString());
        }

        utilizadorDAO.save(u); // atualizar no DAO
        System.out.println("Utilizador atualizado com sucesso!");
    }

    private static void removerUtilizador() {
        System.out.print("Digite o ID do utilizador a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        Utilizador u = utilizadorDAO.findById(id);

        if (u == null) {
            System.out.println("Utilizador não encontrado!");
            return;
        }

        utilizadorDAO.delete(u); // remover do DAO
        System.out.println("Utilizador removido com sucesso!");
    }

    private static PerfilEnum selecionarPerfil() {
        PerfilEnum[] perfis = PerfilEnum.values();
        System.out.println("Selecione o perfil:");
        for (int i = 0; i < perfis.length; i++) {
            System.out.println((i + 1) + ". " + perfis[i]);
        }

        int opcaoPerfil = 0;
        while (opcaoPerfil < 1 || opcaoPerfil > perfis.length) {
            System.out.print("Escolha (1-" + perfis.length + "): ");
            try {
                opcaoPerfil = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcaoPerfil = 0;
            }
        }

        return perfis[opcaoPerfil - 1];
    }
}
