package com.mycompany.sistema.de.gestao.escolar.java.app;

import java.util.List;
import java.util.Scanner;

import dao.UtilizadorDAO;
import model.Utilizador;

public class LoginUI {

    private static Scanner scanner = new Scanner(System.in);
    private static UtilizadorDAO utilizadorDAO = new UtilizadorDAO();

    public static Utilizador login() {
        System.out.println("===== LOGIN =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Buscar todos os utilizadores e validar
        List<Utilizador> todos = utilizadorDAO.findAll();
        for (Utilizador u : todos) {
            if (u.getUsername().equals(username) && u.getSenha().equals(senha)) {
                System.out.println("Login efetuado com sucesso! Perfil: " + u.getPerfil());
                return u; // retorna o utilizador logado
            }
        }

        System.out.println("Usuário ou senha incorretos!");
        return null;
    }
}
