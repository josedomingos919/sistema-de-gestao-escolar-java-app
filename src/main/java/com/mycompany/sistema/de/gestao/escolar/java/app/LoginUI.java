package com.mycompany.sistema.de.gestao.escolar.java.app;

import storage.SessaoUtilizador;
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

        List<Utilizador> todos = utilizadorDAO.findAll();

        for (Utilizador u : todos) {
            if (u.getUsername().equals(username) && u.getSenha().equals(senha)) {

                SessaoUtilizador.setUtilizador(u); // ⭐ AQUI

                System.out.println("Login efetuado com sucesso!");
                System.out.println("Bem-vindo " + u.getUsername());
                System.out.println("Perfil: " + u.getPerfil());

                return u;
            }
        }

        System.out.println("Usuário ou senha incorretos!");
        return null;
    }
}
