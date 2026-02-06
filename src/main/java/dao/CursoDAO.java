/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;
import model.Curso;

/**
 *
 * @author User
 */
public class CursoDAO extends BaseRepository<Curso> {
    public CursoDAO(){
        super(Curso.class, "Curso");
    }
}
