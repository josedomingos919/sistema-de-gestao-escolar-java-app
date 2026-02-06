/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;

/**
 *
 * @author User
 */
import  model.Estudante;

public class EstudanteDAO extends BaseRepository<Estudante> {
    public EstudanteDAO(){
        super(Estudante.class, "Estudante");
    }
}
