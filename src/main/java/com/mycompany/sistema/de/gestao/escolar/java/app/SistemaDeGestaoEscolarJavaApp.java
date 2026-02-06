/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema.de.gestao.escolar.java.app;

import dao.EstudanteDAO;
import java.util.List;
import java.util.Scanner;
import model.Estudante;

/**
 *
 * @author User
 */
public class SistemaDeGestaoEscolarJavaApp {

    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    private static void printOptions(){
        System.out.println("=====================Menu Principal================");
        System.out.println("1- Curso");
        System.out.println("2- Estudante");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }
    
    public static void main(String[] args) {
        System.out.println("=====================Bem-Vindo================");
        
        do {
           printOptions();
           option = scanner.nextInt();
           
           switch(option) {
               case 1:
                   CursoUI.init();
                   break;
               case 2: 
                   EstudanteDAO estudanteDAO = new EstudanteDAO();
                   List<Estudante> estudantes = estudanteDAO.findAll();
        
                    for (Estudante es : estudantes) {
                        System.out.print("ID: " + es.getId() + "   ");
                        System.out.println("Nome: " + es.getNome());
                    }
                 break;
           }
           
        } while(option != 0);
    }
}
