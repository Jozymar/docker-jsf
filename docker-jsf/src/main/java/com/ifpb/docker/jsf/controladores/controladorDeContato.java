package com.ifpb.docker.jsf.controladores;

import com.ifpb.docker.jsf.modelo.Contato;
import com.ifpb.docker.jsf.servico.DaoContato;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class controladorDeContato implements Serializable {

    private Contato contato = new Contato();

    private Contato contatoBuscado = new Contato();

    private boolean resultadoDaBusca;

    @Inject
    private DaoContato daoContato;

    public String cadastrar() {
        daoContato.cadastrar(contato);
        return "index.xhtml?faces-redirect=true";
    }

    public String editar(Contato contato) {
        Contato buscado = daoContato.buscarPorId(contato.getId());
        if (buscado != null) {
            contatoBuscado = buscado;
            resultadoDaBusca = true;
            return "editar.xhtml";
        } else {
            resultadoDaBusca = false;
            return "editar.xhtml";
        }
    }

    public String buscarPorNome() {
        Contato contatoBusc = daoContato
                .consultarPorNome(contato.getNome());
        if (contatoBusc != null) {
            contatoBuscado = contatoBusc;
            resultadoDaBusca = true;
            return "index.xhtml";
        } else {
            resultadoDaBusca = false;
            return "index.xhtml";
        }

    }

    public List<Contato> buscarContatosPorNome() {
        List<Contato> contatos = daoContato
                .todosOsContatosPorNome(contato.getNome());
        if (contatos != null) {
            resultadoDaBusca = true;
            return contatos;
        } else {
            resultadoDaBusca = false;
            return null;
        }
    }

    public String atualizar() {
        daoContato.atualizar(contatoBuscado);
        return "index.xhtml";
    }

    public String excluir(Contato contato) {
        daoContato.excluir(contato);
        return "index.xhtml";
    }

    public List<Contato> todosOsContatos() {
        return daoContato.todosOsContatos();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Contato getContatoBuscado() {
        return contatoBuscado;
    }

    public void setContatoBuscado(Contato contatoBuscado) {
        this.contatoBuscado = contatoBuscado;
    }

    public boolean isResultadoDaBusca() {
        return resultadoDaBusca;
    }

    public void setResultadoDaBusca(boolean resultadoDaBusca) {
        this.resultadoDaBusca = resultadoDaBusca;
    }
}
