/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;
import model.Matricula;

/**
 *
 * @author User
 */
public class MatriculaDAO extends BaseRepository<Matricula> {
    public MatriculaDAO(){
        super(Matricula.class, "Matricula");
    }
}
