package br.com.ppcacws.resources;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    @PersistenceUnit(unitName = "quizminado")
    private EntityManagerFactory factory;
 
    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
 
    public void closeEntityManager(@Disposes EntityManager em) {
        if (em.isOpen())
            em.close();
    }
 
}
