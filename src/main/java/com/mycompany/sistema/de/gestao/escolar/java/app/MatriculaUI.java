package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.MatriculaDAO;
import dao.EstudanteDAO;
import dao.DisciplinaDAO;
import java.util.List;
import java.util.Scanner;
import model.Matricula;
import model.Estudante;
import model.Disciplina;

public class MatriculaUI {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static MatriculaDAO matriculaDAO = new MatriculaDAO();
    private static EstudanteDAO estudanteDAO = new EstudanteDAO();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    private static void printOptions() {
        System.out.println("\n===================== Menu Matrícula =================");
        System.out.println("1- Matricular estudante");
        System.out.println("2- Editar matrícula");
        System.out.println("3- Eliminar matrícula");
        System.out.println("4- Listar todas");
        System.out.println("5- Consultar matrículas por estudante");
        System.out.println("6- Consultar estudantes por disciplina");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }

    private static Estudante escolherEstudante() {
        List<Estudante> estudantes = estudanteDAO.findAll();
        if (estudantes.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado.");
            return null;
        }
        System.out.println("\nEscolha um estudante:");
        for (Estudante e : estudantes) {
            System.out.println(e.getId() + " - " + e.getNome());
        }
        System.out.print("ID do estudante: ");
        int id = Integer.parseInt(scanner.nextLine());
        return estudanteDAO.findById(id);
    }

    private static Disciplina escolherDisciplina() {
        List<Disciplina> disciplinas = disciplinaDAO.findAll();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return null;
        }
        System.out.println("\nEscolha uma disciplina:");
        for (Disciplina d : disciplinas) {
            System.out.println(d.getId() + " - " + d.getNome());
        }
        System.out.print("ID da disciplina: ");
        int id = Integer.parseInt(scanner.nextLine());
        return disciplinaDAO.findById(id);
    }

    public static void matricular() {
        Matricula m = new Matricula();

        Estudante e = escolherEstudante();
        if (e == null) return;
        m.setEstudante(e);

        Disciplina d = escolherDisciplina();
        if (d == null) return;
        m.setDisciplina(d);

        System.out.print("Ano letivo: ");
        m.setAnoLectivo(Integer.parseInt(scanner.nextLine()));

        System.out.print("Semestre: ");
        m.setSemestre(Integer.parseInt(scanner.nextLine()));

        matriculaDAO.save(m);

        System.out.println("\nEstudante matriculado com sucesso!");
    }

    public static void editar() {
        System.out.print("\nDigite o ID da matrícula: ");
        int id = Integer.parseInt(scanner.nextLine());

        Matricula m = matriculaDAO.findById(id);
        if (m == null) {
            System.out.println("Matrícula não encontrada.");
            return;
        }

        System.out.println("\nDeixe em branco para manter o valor atual.\n");

        System.out.print("Alterar estudante? (s/n): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            Estudante e = escolherEstudante();
            if (e != null) m.setEstudante(e);
        }

        System.out.print("Alterar disciplina? (s/n): ");
        resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            Disciplina d = escolherDisciplina();
            if (d != null) m.setDisciplina(d);
        }

        System.out.print("Ano letivo (" + m.getAnoLectivo() + "): ");
        String anoStr = scanner.nextLine();
        if (!anoStr.isEmpty()) m.setAnoLectivo(Integer.parseInt(anoStr));

        System.out.print("Semestre (" + m.getSemestre() + "): ");
        String semStr = scanner.nextLine();
        if (!semStr.isEmpty()) m.setSemestre(Integer.parseInt(semStr));

        matriculaDAO.save(m);

        System.out.println("\nMatrícula atualizada!");
    }

    public static void eliminar() {
        System.out.print("\nDigite o ID da matrícula a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Matricula m = matriculaDAO.findById(id);
        if (m == null) {
            System.out.println("Matrícula não encontrada.");
            return;
        }

        matriculaDAO.delete(m);

        System.out.println("\nMatrícula eliminada!");
    }

    public static void listar() {
        List<Matricula> matriculas = matriculaDAO.findAll();
        if (matriculas.isEmpty()) {
            System.out.println("\nNenhuma matrícula cadastrada.");
            return;
        }

        System.out.println("\n=========== Lista de Matrículas ===========\n");

        for (Matricula m : matriculas) {
            System.out.println("ID: " + m.getId());
            System.out.println("Estudante: " + (m.getEstudante() != null ? m.getEstudante().getNome() : "Sem estudante"));
            System.out.println("Disciplina: " + (m.getDisciplina() != null ? m.getDisciplina().getNome() : "Sem disciplina"));
            System.out.println("Ano letivo: " + m.getAnoLectivo());
            System.out.println("Semestre: " + m.getSemestre());
            System.out.println("----------------------------------");
        }
    }

    public static void consultarPorEstudante() {
        Estudante e = escolherEstudante();
        if (e == null) return;

        List<Matricula> matriculas = matriculaDAO.findAll();
        System.out.println("\n=========== Matrículas de " + e.getNome() + " ===========\n");

        boolean encontrou = false;
        for (Matricula m : matriculas) {
            if (m.getEstudante() != null && m.getEstudante().getId() == e.getId()) {
                System.out.println("ID: " + m.getId());
                System.out.println("Disciplina: " + (m.getDisciplina() != null ? m.getDisciplina().getNome() : "Sem disciplina"));
                System.out.println("Ano letivo: " + m.getAnoLectivo());
                System.out.println("Semestre: " + m.getSemestre());
                System.out.println("----------------------------------");
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("Nenhuma matrícula encontrada para este estudante.");
    }

    public static void consultarPorDisciplina() {
        Disciplina d = escolherDisciplina();
        if (d == null) return;

        List<Matricula> matriculas = matriculaDAO.findAll();
        System.out.println("\n=========== Estudantes da disciplina " + d.getNome() + " ===========\n");

        boolean encontrou = false;
        for (Matricula m : matriculas) {
            if (m.getDisciplina() != null && m.getDisciplina().getId() == d.getId()) {
                System.out.println("ID: " + m.getId());
                System.out.println("Estudante: " + (m.getEstudante() != null ? m.getEstudante().getNome() : "Sem estudante"));
                System.out.println("Ano letivo: " + m.getAnoLectivo());
                System.out.println("Semestre: " + m.getSemestre());
                System.out.println("----------------------------------");
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("Nenhum estudante matriculado nesta disciplina.");
    }

    public static void init() {
        do {
            printOptions();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    matricular();
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
                    consultarPorEstudante();
                    break;
                case 6:
                    consultarPorDisciplina();
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
