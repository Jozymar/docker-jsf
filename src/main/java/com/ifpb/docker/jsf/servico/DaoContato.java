package com.ifpb.docker.jsf.servico;

import com.ifpb.docker.jsf.modelo.Contato;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface DaoContato {

    public void cadastrar(Contato contato);

    public Contato buscarPorId(int id);

    public Contato consultarPorNome(String nome);

    public List<Contato> todosOsContatosPorNome(String nome);

    public void atualizar(Contato contato);

    public void excluir(Contato contato);

    public List<Contato> todosOsContatos();
}
