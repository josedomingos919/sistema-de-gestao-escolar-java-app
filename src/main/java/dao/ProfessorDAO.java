/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;
import model.Professor;

/**
 *
 * @author User
 */
public class ProfessorDAO extends BaseRepository<Professor> {
    public ProfessorDAO(){
        super(Professor.class, "Professor");
    }
}
