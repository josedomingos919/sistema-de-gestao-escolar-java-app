package com.mycompany.sistema.de.gestao.escolar.java.app;

import storage.SessaoUtilizador;
import java.util.Scanner;
import model.Utilizador;

public class SistemaDeGestaoEscolarJavaApp {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);

    private static void printOptions() {

       System.out.println("\n========= MENU =========");

       if(SessaoUtilizador.isAdmin()){
           System.out.println("1- Curso");
           System.out.println("2- Estudante");
           System.out.println("3- Disciplina");
           System.out.println("4- Professor");
           System.out.println("5- Utilizador");
           System.out.println("6- Avaliacao");
           System.out.println("7- Matricula");
           System.out.println("8- Relatório");
       }

       else if(SessaoUtilizador.isSecretaria()){
           System.out.println("1- Estudante");
           System.out.println("2- Matricula");
           System.out.println("3- Relatório");
       }

       else if(SessaoUtilizador.isProfessor()){
           System.out.println("1- Disciplina");
           System.out.println("2- Avaliacao");
           System.out.println("3- Relatório");
       }

       System.out.println("0- Sair");
       System.out.print("R: ");
   }

    public static void main(String[] args) {

        System.out.println("=====================Bem-Vindo================");

        Utilizador logado = null;

        do {
            logado = LoginUI.login();
        } while (logado == null);

        do {

            printOptions();
            option = scanner.nextInt();
            scanner.nextLine();

            // ADMIN
            if(SessaoUtilizador.isAdmin()){

                switch(option){
                    case 1: CursoUI.init(); break;
                    case 2: EstudanteUI.init(); break;
                    case 3: DisciplinaUI.init(); break;
                    case 4: ProfessorUI.init(); break;
                    case 5: UtilizadorUI.init(); break;
                    case 6: AvaliacaoUI.init(); break;
                    case 7: MatriculaUI.init(); break;
                    case 8: RelatorioUI.init(); break;
                }
            }

            // SECRETARIA
            else if(SessaoUtilizador.isSecretaria()){

                switch(option){
                    case 1: EstudanteUI.init(); break;
                    case 2: MatriculaUI.init(); break;
                    case 3: RelatorioUI.init(); break;
                    default:
                        System.out.println("Acesso negado!");
                }
            }

            // PROFESSOR
            else if(SessaoUtilizador.isProfessor()){

                switch(option){
                    case 1: DisciplinaUI.init(); break;
                    case 2: AvaliacaoUI.init(); break;
                    case 3: RelatorioUI.init(); break;
                    default:
                        System.out.println("Acesso negado!");
                }
            }

        } while(option != 0);
    }

}
