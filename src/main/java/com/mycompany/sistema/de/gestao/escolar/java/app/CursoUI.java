/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.de.gestao.escolar.java.app;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class CursoUI {
    private static int option = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    private static void printOptions(){
        System.out.println("=====================Menu Curso================");
        System.out.println("1- Criar");
        System.out.println("2- Editar");
        System.out.println("3- Eliminar");
        System.out.println("4- Listar");
        System.out.println("0- Sair");
        System.out.print("R: ");
    }
    
    public static void criar() {
    }
    
    public static void editar() {
    }
    
    public static void eliminar() {
    }
    
    public static void listar() {
    }
        
    public static void init(){
        do {
            printOptions();
            option = scanner.nextInt();

            switch(option) {
                case 1:
                     criar();
                    break;
            }
      } while(option != 0);
    }
}
