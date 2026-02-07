package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.ProfessorDAO;
import java.util.List;
import java.util.Scanner;
import model.Professor;

public class ProfessorUI {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static ProfessorDAO professorDAO = new ProfessorDAO();

    private static void printOptions() {
        System.out.println("\n===================== Menu Professor =================");
        System.out.println("1- Criar");
        System.out.println("2- Editar");
        System.out.println("3- Eliminar");
        System.out.println("4- Listar");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }

    public static void criar() {

        System.out.println("\n=========== Criando Professor ===========\n");

        Professor p = new Professor();

        System.out.print("Nome: ");
        p.setNome(scanner.nextLine());

        System.out.print("Especialidade: ");
        p.setEspecialidade(scanner.nextLine());

        System.out.print("Genero: ");
        p.setGenero(scanner.nextLine());

        System.out.print("Contacto: ");
        p.setContacto(scanner.nextLine());

        System.out.print("Email: ");
        p.setEmail(scanner.nextLine());

        professorDAO.save(p);

        System.out.println("\nProfessor criado com sucesso!");
    }

    public static void editar() {

        System.out.print("\nDigite o ID do professor: ");
        int id = Integer.parseInt(scanner.nextLine());

        Professor p = professorDAO.findById(id);

        if (p == null) {
            System.out.println("Professor não encontrado.");
            return;
        }

        System.out.println("\nDeixe em branco para manter o valor atual.\n");

        System.out.print("Novo nome (" + p.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            p.setNome(nome);
        }

        System.out.print("Nova especialidade (" + p.getEspecialidade() + "): ");
        String especialidade = scanner.nextLine();
        if (!especialidade.isEmpty()) {
            p.setEspecialidade(especialidade);
        }

        System.out.print("Novo genero (" + p.getGenero() + "): ");
        String genero = scanner.nextLine();
        if (!genero.isEmpty()) {
            p.setGenero(genero);
        }

        System.out.print("Novo contacto (" + p.getContacto() + "): ");
        String contacto = scanner.nextLine();
        if (!contacto.isEmpty()) {
            p.setContacto(contacto);
        }

        System.out.print("Novo email (" + p.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            p.setEmail(email);
        }

        professorDAO.save(p);

        System.out.println("\nProfessor atualizado!");
    }

    public static void eliminar() {

        System.out.print("\nDigite o ID do professor a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Professor p = professorDAO.findById(id);

        if (p == null) {
            System.out.println("Professor não encontrado.");
            return;
        }

        professorDAO.delete(p);

        System.out.println("\nProfessor eliminado!");
    }

    public static void listar() {

        List<Professor> professores = professorDAO.findAll();

        if (professores.isEmpty()) {
            System.out.println("\nNenhum professor encontrado.");
            return;
        }

        System.out.println("\n=========== Lista de Professores ===========\n");

        for (Professor p : professores) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Especialidade: " + p.getEspecialidade());
            System.out.println("Genero: " + p.getGenero());
            System.out.println("Contacto: " + p.getContacto());
            System.out.println("Email: " + p.getEmail());
            System.out.println("----------------------------------");
        }
    }

    public static void init() {

        do {
            printOptions();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    criar();
                    break;
                case 2:
                    editar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 4:
                    listar();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 0);
    }
}
