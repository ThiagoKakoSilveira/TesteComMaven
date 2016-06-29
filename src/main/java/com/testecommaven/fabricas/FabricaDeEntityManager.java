package com.testecommaven.fabricas;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class FabricaDeEntityManager {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("BibliotecaJSFHibernatePU");

	@Produces @RequestScoped
	public EntityManager criarEntityManager() {
            System.out.println("Criando o em ----------------");
		return factory.createEntityManager();
	}
	
	public void fecharEntityManager(@Disposes EntityManager manager){
		manager.close();
	}
}
