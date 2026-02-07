package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.CursoDAO;
import dao.EstudanteDAO;
import java.util.List;
import java.util.Scanner;
import model.Curso;
import model.Estudante;

public class EstudanteUI {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static EstudanteDAO estudanteDAO = new EstudanteDAO();
    private static CursoDAO cursoDAO = new CursoDAO();

    private static void printOptions() {
        System.out.println("\n===================== Menu Estudante =================");
        System.out.println("1- Criar");
        System.out.println("2- Editar");
        System.out.println("3- Eliminar");
        System.out.println("4- Listar");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }

    private static Curso escolherCurso() {

        List<Curso> cursos = cursoDAO.findAll();

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado!");
            return null;
        }

        System.out.println("\nEscolha um Curso:");

        for (Curso curso : cursos) {
            System.out.println(curso.getId() + " - " + curso.getNome());
        }

        System.out.print("ID do curso: ");
        int id = scanner.nextInt();

        return cursoDAO.findById(id);
    }

    public static void criar() {

        scanner.nextLine();

        System.out.println("\n=========== Criando Estudante ===========\n");

        Estudante e = new Estudante();

        System.out.print("Nome: ");
        e.setNome(scanner.nextLine());

        System.out.print("Número de matrícula: ");
        e.setNumeroMatricula(scanner.nextInt());

        System.out.print("Idade: ");
        e.setIdade(scanner.nextInt());

        scanner.nextLine();

        System.out.print("Genero (M ou F): ");
        e.setGenero(scanner.nextLine());

        System.out.print("Ano de ingresso: ");
        e.setAnoIngresso(scanner.nextInt());

        scanner.nextLine();

        System.out.print("Contacto: ");
        e.setContacto(scanner.nextLine());

        System.out.print("Email: ");
        e.setEmail(scanner.nextLine());

        Curso curso = escolherCurso();

        if (curso == null) {
            System.out.println("\nEstudante não criado!");
            return;
        }

        e.setCurso(curso);

        estudanteDAO.save(e);

        System.out.println("\nEstudante criado com sucesso!");
    }

    public static void editar() {

        System.out.print("\nDigite o ID do estudante: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        Estudante e = estudanteDAO.findById(id);

        if (e == null) {
            System.out.println("Estudante não encontrado.");
            return;
        }

        System.out.println("\nDeixe em branco para manter o valor atual.\n");

        System.out.print("Novo nome (" + e.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            e.setNome(nome);
        }

        System.out.print("Nova matrícula (" + e.getNumeroMatricula() + "): ");
        String matriculaStr = scanner.nextLine();
        if (!matriculaStr.isEmpty()) {
            e.setNumeroMatricula(Integer.parseInt(matriculaStr));
        }

        System.out.print("Nova idade (" + e.getIdade() + "): ");
        String idadeStr = scanner.nextLine();
        if (!idadeStr.isEmpty()) {
            e.setIdade(Integer.parseInt(idadeStr));
        }
        
        System.out.print("Novo genero (" + e.getGenero() + "): ");
        String genero = scanner.nextLine();
        if (!genero.isEmpty()) {
            e.setGenero(genero);
        }

        System.out.print("Novo ano ingresso (" + e.getAnoIngresso() + "): ");
        String anoStr = scanner.nextLine();
        if (!anoStr.isEmpty()) {
            e.setAnoIngresso(Integer.parseInt(anoStr));
        }

        System.out.print("Novo contacto (" + e.getContacto() + "): ");
        String contacto = scanner.nextLine();
        if (!contacto.isEmpty()) {
            e.setContacto(contacto);
        }

        System.out.print("Novo email (" + e.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            e.setEmail(email);
        }

        System.out.print("Alterar curso? (s/n): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            Curso curso = escolherCurso();
            if (curso != null) {
                e.setCurso(curso);
            }
        }

        estudanteDAO.save(e);

        System.out.println("\nEstudante atualizado!");
    }

    public static void eliminar() {

        System.out.print("\nDigite o ID do estudante a eliminar: ");
        int id = scanner.nextInt();

        Estudante e = estudanteDAO.findById(id);

        if (e == null) {
            System.out.println("Estudante não encontrado.");
            return;
        }

        estudanteDAO.delete(e);

        System.out.println("\nEstudante eliminado!");
    }

    public static void listar() {

        List<Estudante> estudantes = estudanteDAO.findAll();

        if (estudantes.isEmpty()) {
            System.out.println("\nNenhum estudante encontrado.");
            return;
        }

        System.out.println("\n=========== Lista de Estudantes ===========\n");

        for (Estudante e : estudantes) {

            System.out.println("ID: " + e.getId());
            System.out.println("Nome: " + e.getNome());
            System.out.println("Matrícula: " + e.getNumeroMatricula());
            System.out.println("Idade: " + e.getIdade());
            System.out.println("Genero: " + e.getGenero());
            System.out.println("Ano Ingresso: " + e.getAnoIngresso());
            System.out.println("Curso: " + (e.getCurso() != null ? e.getCurso().getNome() : "Sem curso"));
            System.out.println("Contacto: " + e.getContacto());
            System.out.println("Email: " + e.getEmail());
            System.out.println("----------------------------------");
        }
    }

    public static void init() {

        do {
            printOptions();
            option = scanner.nextInt();

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
