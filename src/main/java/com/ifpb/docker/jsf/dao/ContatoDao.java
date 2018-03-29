package com.ifpb.docker.jsf.dao;

import com.ifpb.docker.jsf.modelo.Contato;
import com.ifpb.docker.jsf.servico.DaoContato;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jozimar
 */
@Stateless
@Local(DaoContato.class)
public class ContatoDao implements DaoContato {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    @Override
    public void cadastrar(Contato contato) {
        entityManager.persist(contato);
    }

    @Override
    public void atualizar(Contato contato) {
        entityManager.merge(contato);
    }

    @Override
    public Contato buscarPorId(int id) {
        Contato contato = entityManager.find(Contato.class, id);
        return contato;
    }

    @Override
    public Contato consultarPorNome(String nome) {
        TypedQuery<Contato> query = entityManager
                .createQuery("SELECT contato FROM Contato contato "
                        + "WHERE contato.nome=:nome", Contato.class);
        query.setParameter("nome", nome);
        Optional<Contato> contato = query.getResultList().stream().findFirst();
        if (contato.isPresent()) {
            return contato.get();
        } else {
            return null;
        }
    }

    @Override
    public void excluir(Contato contato) {
        if (!entityManager.contains(contato)) {
            contato = entityManager.merge(contato);
        }
        entityManager.remove(contato);
    }

    @Override
    public List<Contato> todosOsContatos() {
        String sqlQuery = "SELECT contato FROM Contato contato "
                + "GROUP BY SUBSTRING(contato.nome, 1, 1), contato "
                + "ORDER BY SUBSTRING(contato.nome, 1, 1)";
        TypedQuery<Contato> query = entityManager.createQuery(sqlQuery, Contato.class);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

    @Override
    public List<Contato> todosOsContatosPorNome(String nome) {
        String sqlQuery = "SELECT contato FROM Contato contato "
                + "WHERE contato.nome=:nome "
                + "ORDER BY contato.nome";
        TypedQuery<Contato> query = entityManager.createQuery(sqlQuery, Contato.class);
        query.setParameter("nome", nome);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }
}
