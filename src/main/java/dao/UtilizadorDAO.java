/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;
import model.Utilizador;

/**
 *
 * @author User
 */
public class UtilizadorDAO extends BaseRepository<Utilizador> {
    public UtilizadorDAO(){
        super(Utilizador.class, "Utilizador");
    }
}
