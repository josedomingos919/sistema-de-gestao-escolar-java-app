/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author User
 */
public class Helper {
    public static void limparConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // Limpa o console no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpa o console no Linux/MacOS
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Tratar exceções
            System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        }
    }
}
