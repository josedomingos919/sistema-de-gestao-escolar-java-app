/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.contracts;

import java.util.List;

/**
 *
 * @author User
 */
public interface IRepository<TEntity> {
    public TEntity save(TEntity entity);// Update e Create
    public List<TEntity> findAll();
    public void delete(TEntity entity);
}
