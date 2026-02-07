package com.mycompany.sistema.de.gestao.escolar.java.app;

import model.Utilizador;

public class SessaoUtilizador {

    private static Utilizador utilizadorLogado;

    public static void setUtilizador(Utilizador u){
        utilizadorLogado = u;
    }

    public static Utilizador getUtilizador(){
        return utilizadorLogado;
    }

    public static boolean isAdmin(){
        return utilizadorLogado.getPerfil().equals("ADMINISTRADOR");
    }

    public static boolean isSecretaria(){
        return utilizadorLogado.getPerfil().equals("SECRETARIA");
    }

    public static boolean isProfessor(){
        return utilizadorLogado.getPerfil().equals("PROFESSOR");
    }
}
