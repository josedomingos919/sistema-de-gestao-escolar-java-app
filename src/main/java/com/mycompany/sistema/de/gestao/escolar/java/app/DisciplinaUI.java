package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import java.util.List;
import java.util.Scanner;
import model.Curso;
import model.Disciplina;

public class DisciplinaUI {
    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static CursoDAO cursoDAO = new CursoDAO();

    private static void printOptions() {
        System.out.println("\n===================== Menu Disciplina =================");
        System.out.println("1- Criar");
        System.out.println("2- Editar");
        System.out.println("3- Eliminar");
        System.out.println("4- Listar todas");
        System.out.println("5- Listar por curso");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }

    private static void listarPorCurso() {
        List<Curso> cursos = cursoDAO.findAll();

        if (cursos.isEmpty()) {
            System.out.println("\nNenhum curso cadastrado.");
            return;
        }

        System.out.println("\nEscolha um curso:");

        for (Curso c : cursos) {
            System.out.println(c.getId() + " - " + c.getNome());
        }

        System.out.print("ID do curso: ");
        int cursoId = Integer.parseInt(scanner.nextLine());

        Curso curso = cursoDAO.findById(cursoId);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        List<Disciplina> disciplinas = disciplinaDAO.findAll();

        System.out.println("\n=========== Disciplinas do curso " + curso.getNome() + " ===========\n");

        boolean encontrou = false;
        for (Disciplina d : disciplinas) {
            if (d.getCurso() != null && d.getCurso().getId() == curso.getId()) {
                System.out.println("ID: " + d.getId());
                System.out.println("Nome: " + d.getNome());
                System.out.println("Carga Horária: " + d.getCargaHoraria());
                System.out.println("----------------------------------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma disciplina cadastrada para este curso.");
        }
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
        scanner.nextLine();

        return cursoDAO.findById(id);
    }

    public static void criar() {

        System.out.println("\n=========== Criando Disciplina ===========\n");

        Disciplina d = new Disciplina();
        System.out.println("\nDigite enter para continuar....");
        
        scanner.nextLine(); // limpar buffer

        System.out.print("Nome: ");
        d.setNome(scanner.nextLine());

        System.out.print("Carga horária: ");
        d.setCargaHoraria(scanner.nextInt());
        scanner.nextLine();

        Curso curso = escolherCurso();
        if (curso == null) return;

        d.setCurso(curso);

        disciplinaDAO.save(d);

        System.out.println("\nDisciplina criada com sucesso!");
    }

    public static void editar() {

        System.out.print("\nDigite o ID da disciplina: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Disciplina d = disciplinaDAO.findById(id);

        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.println("\nDeixe em branco para manter o valor atual.\n");

        System.out.print("Novo nome (" + d.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            d.setNome(nome);
        }

        System.out.print("Nova carga horária (" + d.getCargaHoraria() + "): ");
        String cargaStr = scanner.nextLine();
        if (!cargaStr.isEmpty()) {
            d.setCargaHoraria(Integer.parseInt(cargaStr));
        }

        System.out.print("Alterar curso? (s/n): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            Curso curso = escolherCurso();
            if (curso != null) {
                d.setCurso(curso);
            }
        }

        disciplinaDAO.save(d);

        System.out.println("\nDisciplina atualizada!");
    }

    public static void eliminar() {

        System.out.print("\nDigite o ID da disciplina a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Disciplina d = disciplinaDAO.findById(id);

        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        disciplinaDAO.delete(d);

        System.out.println("\nDisciplina eliminada!");
    }

    public static void listar() {

        List<Disciplina> disciplinas = disciplinaDAO.findAll();

        if (disciplinas.isEmpty()) {
            System.out.println("\nNenhuma disciplina encontrada.");
            return;
        }

        System.out.println("\n=========== Lista de Disciplinas ===========\n");

        for (Disciplina d : disciplinas) {
            System.out.println("ID: " + d.getId());
            System.out.println("Nome: " + d.getNome());
            System.out.println("Carga Horária: " + d.getCargaHoraria());
            System.out.println("Curso: " + (d.getCurso() != null ? d.getCurso().getNome() : "Sem curso"));
            System.out.println("----------------------------------");
        }
    }

    public static void init() {

        do {
            printOptions();
            option = scanner.nextInt();
            scanner.nextLine();

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
                case 5:
                listarPorCurso();
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
