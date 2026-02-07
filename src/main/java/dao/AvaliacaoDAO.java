/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.contracts.BaseRepository;
import model.Avaliacao;

/**
 *
 * @author User
 */
public class AvaliacaoDAO extends BaseRepository<Avaliacao> {
    public AvaliacaoDAO(){
        super(Avaliacao.class, "Curso");
    }
}
