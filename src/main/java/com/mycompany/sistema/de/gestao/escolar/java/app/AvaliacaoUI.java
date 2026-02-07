package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.AvaliacaoDAO;
import dao.MatriculaDAO;
import java.util.List;
import java.util.Scanner;
import model.Avaliacao;
import model.Matricula;

public class AvaliacaoUI {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private static MatriculaDAO matriculaDAO = new MatriculaDAO();

    private static void printOptions() {
        System.out.println("\n===================== Menu Avaliação =================");
        System.out.println("1- Lançar nota");
        System.out.println("2- Editar nota");
        System.out.println("3- Eliminar nota");
        System.out.println("4- Listar todas");
        System.out.println("5- Consultar notas por estudante");
        System.out.println("6- Calcular média final");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }

    private static Matricula escolherMatricula() {
        List<Matricula> matriculas = matriculaDAO.findAll();

        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula cadastrada.");
            return null;
        }

        System.out.println("\nEscolha uma matrícula:");

        for (Matricula m : matriculas) {
            String estudante = m.getEstudante() != null ? m.getEstudante().getNome() : "Sem estudante";
            String disciplina = m.getDisciplina() != null ? m.getDisciplina().getNome() : "Sem disciplina";
            int ano = m.getAnoLectivo();
            int semestre = m.getSemestre();

            System.out.println(m.getId() + " - " + estudante + " | " + disciplina + " | Ano: " + ano + " | Semestre: " + semestre);
        }

        System.out.print("ID da matrícula: ");
        int id = Integer.parseInt(scanner.nextLine());

        return matriculaDAO.findById(id);
    }


    private static String escolherTipoAvaliacao() {
        System.out.println("\nEscolha o tipo de avaliação:");
        System.out.println("1 - Teste");
        System.out.println("2 - Exame");
        System.out.println("3 - Trabalho");
        System.out.print("R: ");
        String resp = scanner.nextLine();

        switch (resp) {
            case "1":
                return "Teste";
            case "2":
                return "Exame";
            case "3":
                return "Trabalho";
            default:
                return "Teste";
        }
    }

    public static void lancarNota() {
        Avaliacao a = new Avaliacao();

        Matricula m = escolherMatricula();
        if (m == null) return;
        a.setMatricula(m);

        a.setTipoAvaliacao(escolherTipoAvaliacao());

        System.out.print("Nota: ");
        a.setNota(Integer.parseInt(scanner.nextLine()));

        avaliacaoDAO.save(a);

        System.out.println("\nNota lançada com sucesso!");
    }

    public static void editarNota() {
        System.out.print("\nDigite o ID da avaliação: ");
        int id = Integer.parseInt(scanner.nextLine());

        Avaliacao a = avaliacaoDAO.findById(id);

        if (a == null) {
            System.out.println("Avaliação não encontrada.");
            return;
        }

        System.out.println("\nDeixe em branco para manter o valor atual.\n");

        System.out.print("Novo tipo de avaliação (" + a.getTipoAvaliacao() + "): ");
        String tipo = scanner.nextLine();
        if (!tipo.isEmpty()) {
            a.setTipoAvaliacao(tipo);
        }

        System.out.print("Nova nota (" + a.getNota() + "): ");
        String notaStr = scanner.nextLine();
        if (!notaStr.isEmpty()) {
            a.setNota(Integer.parseInt(notaStr));
        }

        System.out.print("Alterar matrícula? (s/n): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            Matricula m = escolherMatricula();
            if (m != null) {
                a.setMatricula(m);
            }
        }

        avaliacaoDAO.save(a);

        System.out.println("\nAvaliação atualizada!");
    }

    public static void eliminarNota() {
        System.out.print("\nDigite o ID da avaliação a eliminar: ");
         int id = Integer.parseInt(scanner.nextLine());

        Avaliacao a = avaliacaoDAO.findById(id);

        if (a == null) {
            System.out.println("Avaliação não encontrada.");
            return;
        }

        avaliacaoDAO.delete(a);

        System.out.println("\nAvaliação eliminada!");
    }

    public static void listar() {
        List<Avaliacao> avaliacoes = avaliacaoDAO.findAll();

        if (avaliacoes.isEmpty()) {
            System.out.println("\nNenhuma avaliação cadastrada.");
            return;
        }

        System.out.println("\n=========== Lista de Avaliações ===========\n");

        for (Avaliacao a : avaliacoes) {
            String estudante = a.getMatricula() != null && a.getMatricula().getEstudante() != null 
                ? a.getMatricula().getEstudante().getNome() 
                : "Sem matrícula";
            String disciplina = a.getMatricula() != null && a.getMatricula().getDisciplina() != null 
                ? a.getMatricula().getDisciplina().getNome() 
                : "Sem disciplina";
            String ano = a.getMatricula() != null ? String.valueOf(a.getMatricula().getAnoLectivo()) : "-";
            String semestre = a.getMatricula() != null ? String.valueOf(a.getMatricula().getSemestre()) : "-";

            System.out.println("ID: " + a.getId());
            System.out.println("Estudante: " + estudante);
            System.out.println("Disciplina: " + disciplina);
            System.out.println("Ano letivo: " + ano);
            System.out.println("Semestre: " + semestre);
            System.out.println("Tipo: " + a.getTipoAvaliacao());
            System.out.println("Nota: " + a.getNota());
            System.out.println("----------------------------------");
        }
    }

    public static void consultarPorEstudante() {
       System.out.print("\nDigite o ID da matrícula do estudante: ");
       int id = Integer.parseInt(scanner.nextLine());

       Matricula m = matriculaDAO.findById(id);
       if (m == null) {
           System.out.println("Matrícula não encontrada.");
           return;
       }

       List<Avaliacao> avaliacoes = avaliacaoDAO.findAll();

       System.out.println("\n=========== Avaliações de " + m.getEstudante().getNome() + " ===========\n");

       boolean encontrou = false;
       for (Avaliacao a : avaliacoes) {
           if (a.getMatricula() != null && a.getMatricula().getId() == m.getId()) {
               String disciplina = a.getMatricula().getDisciplina() != null
                   ? a.getMatricula().getDisciplina().getNome()
                   : "Sem disciplina";
               String ano = String.valueOf(a.getMatricula().getAnoLectivo());
               String semestre = String.valueOf(a.getMatricula().getSemestre());

               System.out.println("ID: " + a.getId());
               System.out.println("Disciplina: " + disciplina);
               System.out.println("Ano letivo: " + ano);
               System.out.println("Semestre: " + semestre);
               System.out.println("Tipo: " + a.getTipoAvaliacao());
               System.out.println("Nota: " + a.getNota());
               System.out.println("----------------------------------");
               encontrou = true;
           }
       }

       if (!encontrou) {
           System.out.println("Nenhuma avaliação encontrada para este estudante.");
       }
   }

    public static void calcularMediaFinal() {
        System.out.print("\nDigite o ID da matrícula do estudante: ");
        int id = Integer.parseInt(scanner.nextLine());

        Matricula m = matriculaDAO.findById(id);
        if (m == null) {
            System.out.println("Matrícula não encontrada.");
            return;
        }

        List<Avaliacao> avaliacoes = avaliacaoDAO.findAll();

        int soma = 0;
        int count = 0;
        for (Avaliacao a : avaliacoes) {
            if (a.getMatricula() != null && a.getMatricula().getId() == m.getId()) {
                soma += a.getNota();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Nenhuma avaliação para calcular média.");
        } else {
            double media = (double) soma / count;
            System.out.println("Média final de " + m.getEstudante().getNome() + ": " + media);
        }
    }

    public static void init() {
        do {
            printOptions();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    lancarNota();
                    break;
                case 2:
                    editarNota();
                    break;
                case 3:
                    eliminarNota();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    consultarPorEstudante();
                    break;
                case 6:
                    calcularMediaFinal();
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
