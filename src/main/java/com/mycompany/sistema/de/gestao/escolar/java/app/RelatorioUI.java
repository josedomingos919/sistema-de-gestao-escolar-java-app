package com.mycompany.sistema.de.gestao.escolar.java.app;

import java.util.List;
import java.util.Scanner;

import dao.EstudanteDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.MatriculaDAO;
import dao.AvaliacaoDAO;

import model.Estudante;
import model.Curso;
import model.Disciplina;
import model.Matricula;
import model.Avaliacao;

public class RelatorioUI {

    private static final Scanner scanner = new Scanner(System.in);

    private static EstudanteDAO estudanteDAO = new EstudanteDAO();
    private static CursoDAO cursoDAO = new CursoDAO();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static MatriculaDAO matriculaDAO = new MatriculaDAO();
    private static AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

    public static void init() {
        while (true) {
            System.out.println("\n===== RELATÓRIOS =====");
            System.out.println("1. Listar estudantes por curso");
            System.out.println("2. Listar estudantes matriculados numa disciplina");
            System.out.println("3. Emitir histórico académico simples");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    listarEstudantesPorCurso();
                    break;
                case "2":
                    listarEstudantesPorDisciplina();
                    break;
                case "3":
                    emitirHistoricoAcademico();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void listarEstudantesPorCurso() {
        System.out.println("\n=== Listar Estudantes por Curso ===");
        List<Curso> cursos = cursoDAO.findAll();
        for (Curso c : cursos) {
            System.out.println(c.getId() + " - " + c.getNome());
        }

        System.out.print("Digite o ID do curso: ");
        int cursoId = Integer.parseInt(scanner.nextLine());

        Curso curso = cursoDAO.findById(cursoId);
        if (curso == null) {
            System.out.println("Curso não encontrado!");
            return;
        }

        List<Estudante> todosEstudantes = estudanteDAO.findAll();
        System.out.println("\nEstudantes do curso " + curso.getNome() + ":");
        for (Estudante e : todosEstudantes) {
            if (e.getCurso() != null && e.getCurso().getId().equals(cursoId)) {
                System.out.println(e.getId() + " - " + e.getNome() + " | Matrícula: " + e.getNumeroMatricula());
            }
        }
    }

    private static void listarEstudantesPorDisciplina() {
        System.out.println("\n=== Listar Estudantes por Disciplina ===");
        List<Disciplina> disciplinas = disciplinaDAO.findAll();
        for (Disciplina d : disciplinas) {
            System.out.println(d.getId() + " - " + d.getNome());
        }

        System.out.print("Digite o ID da disciplina: ");
        int disciplinaId = Integer.parseInt(scanner.nextLine());

        Disciplina disciplina = disciplinaDAO.findById(disciplinaId);
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        List<Matricula> todasMatriculas = matriculaDAO.findAll();
        System.out.println("\nEstudantes matriculados em " + disciplina.getNome() + ":");
        for (Matricula m : todasMatriculas) {
            if (m.getDisciplina() != null && m.getDisciplina().getId().equals(disciplinaId)) {
                Estudante e = m.getEstudante();
                if (e != null) {
                    System.out.println(e.getId() + " - " + e.getNome() + " | Matrícula: " + e.getNumeroMatricula());
                }
            }
        }
    }

    private static void emitirHistoricoAcademico() {
        System.out.println("\n=== Histórico Académico ===");
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = Integer.parseInt(scanner.nextLine());

        Estudante estudante = estudanteDAO.findById(estudanteId);
        if (estudante == null) {
            System.out.println("Estudante não encontrado!");
            return;
        }

        System.out.println("\nHistórico de " + estudante.getNome() + " (Matrícula: " + estudante.getNumeroMatricula() + "):");

        List<Matricula> todasMatriculas = matriculaDAO.findAll();
        boolean encontrou = false;
        for (Matricula m : todasMatriculas) {
            if (m.getEstudante() != null && m.getEstudante().getId().equals(estudanteId)) {
                Disciplina d = m.getDisciplina();
                // Buscar avaliação relacionada filtrando todas as avaliações
                Avaliacao avaliacao = null;
                List<Avaliacao> todasAvaliacoes = avaliacaoDAO.findAll();
                for (Avaliacao a : todasAvaliacoes) {
                    if (a.getMatricula() != null && a.getMatricula().getId().equals(m.getId())) {
                        avaliacao = a;
                        break;
                    }
                }

                Integer nota = (avaliacao != null) ? avaliacao.getNota() : null;
                String status = (nota != null) ? (nota >= 10 ? "Aprovado" : "Reprovado") : "Sem avaliação";

                System.out.println("Disciplina: " + (d != null ? d.getNome() : "-") +
                                   " | Ano: " + m.getAnoLectivo() +
                                   " | Semestre: " + m.getSemestre() +
                                   " | Nota: " + (nota != null ? nota : "-") +
                                   " | Status: " + status);

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma matrícula encontrada para este estudante.");
        }
    }
}
