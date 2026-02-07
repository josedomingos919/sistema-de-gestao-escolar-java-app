/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;
import model.Disciplina;

/**
 *
 * @author User
 */
public class DisciplinaDAO extends BaseRepository<Disciplina> {
     public DisciplinaDAO(){
        super(Disciplina.class, "Disciplina");
    }
}
