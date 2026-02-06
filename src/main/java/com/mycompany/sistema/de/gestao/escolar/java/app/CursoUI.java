package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.CursoDAO;
import java.util.List;
import java.util.Scanner;
import model.Curso;
import utils.GrauAcademicoEnum;

public class CursoUI {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static CursoDAO cursoDAO = new CursoDAO();
    
    private static String escolherGrau() {
        GrauAcademicoEnum[] graus = GrauAcademicoEnum.values();

        System.out.println("\nEscolha o Grau:");

        for (int i = 0; i < graus.length; i++) {
            System.out.println((i + 1) + " - " + graus[i]);
        }

        System.out.print("R: ");
        int escolha = scanner.nextInt();

        return graus[escolha - 1].toString();
    }

    private static void printOptions() {
        System.out.println("\n===================== Menu Curso =================");
        System.out.println("1- Criar");
        System.out.println("2- Editar");
        System.out.println("3- Eliminar");
        System.out.println("4- Listar");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }

    public static void criar() {

        scanner.nextLine(); // limpar buffer

        System.out.println("\n=========== Criando Curso ===========\n");

        Curso curso = new Curso();

        System.out.print("Nome: ");
        curso.setNome(scanner.nextLine());

        System.out.print("Duração (anos): ");
        curso.setDuracao(scanner.nextInt());

        scanner.nextLine();

        System.out.print("Grau: ");
        curso.setGrau(escolherGrau());

        cursoDAO.save(curso);

        System.out.println("\nCurso criado com sucesso!");
    }

    public static void editar() {
       System.out.print("\nDigite o ID do curso: ");
       int id = scanner.nextInt();
       scanner.nextLine(); // limpar buffer

       Curso curso = cursoDAO.findById(id);

       if (curso == null) {
           System.out.println("Curso não encontrado.");
           return;
       }

       System.out.println("\nDeixe em branco para manter o valor atual.\n");

       System.out.print("Novo nome (" + curso.getNome() + "): ");
       String nome = scanner.nextLine();
       if (!nome.isEmpty()) {
           curso.setNome(nome);
       }

       System.out.print("Nova duração (" + curso.getDuracao() + "): ");
       String duracaoStr = scanner.nextLine();
       if (!duracaoStr.isEmpty()) {
           curso.setDuracao(Integer.parseInt(duracaoStr));
       }

       System.out.print("Alterar grau? (s/n): ");
       String resp = scanner.nextLine();
       if (resp.equalsIgnoreCase("s")) {
           curso.setGrau(escolherGrau());
       }

       cursoDAO.save(curso);

       System.out.println("\nCurso atualizado!");
   }

    public static void eliminar() {

        System.out.print("\nDigite o ID do curso a eliminar: ");
        int id = scanner.nextInt();

        Curso curso = cursoDAO.findById(id);

        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        cursoDAO.delete(curso);

        System.out.println("\nCurso eliminado!");
    }

    public static void listar() {

        List<Curso> cursos = cursoDAO.findAll();

        if (cursos.isEmpty()) {
            System.out.println("\nNenhum curso encontrado.");
            return;
        }

        System.out.println("\n=========== Lista de Cursos ===========\n");

        for (Curso curso : cursos) {
            System.out.println("ID: " + curso.getId());
            System.out.println("Nome: " + curso.getNome());
            System.out.println("Duração: " + curso.getDuracao());
            System.out.println("Grau: " + curso.getGrau());
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
