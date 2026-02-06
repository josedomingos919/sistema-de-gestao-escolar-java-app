/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.contracts;

import connection.ConnectionFactory;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class BaseRepository<TEntity extends BaseEntity> implements IRepository<TEntity>{
    
    private Class<TEntity> entityClass;
    private String tableName = "";

    public BaseRepository(Class<TEntity> entityClass, String tableName) {
        this.entityClass = entityClass;
        this.tableName = tableName;
    }

    public TEntity save(TEntity object1) {
        EntityManager em = new ConnectionFactory().getConnection();
        
        try {
            em.getTransaction().begin();
            
            if(object1.getId() == null) {
                em.persist(object1); // Criar qual quer entidade: persist
            }else {
                em.merge(object1); // Atualizar qual quer entidade: merge
            }
            em.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e);
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        
        return object1;
    }
    
    // Teste
    public TEntity findById(int id) {
        EntityManager em = new ConnectionFactory().getConnection();
        TEntity object1 = null;
        
        try {
            object1 = em.find( entityClass ,id);
        }catch(Exception e){
            System.out.println(e);
        }finally {
            em.close();
        }
        
        return object1;
    }
         
    public List<TEntity> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<TEntity> objects1 = null;
        
        try {
            objects1 = em.createQuery("from " + this.tableName, entityClass).getResultList();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            em.close();
        }
        
        return objects1;
    }
    
    public void delete(TEntity object1) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            object1 = em.merge(object1); // garante que está gerenciada (existe esse dado)
            em.remove(object1); // Remover

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
