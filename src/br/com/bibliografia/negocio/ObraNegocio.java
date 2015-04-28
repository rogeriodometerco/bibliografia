package br.com.bibliografia.negocio;

import java.util.List;
import br.com.bibliografia.DAO.DAOFactory;
import br.com.bibliografia.DAO.GenericoDAO;
import br.com.bibliografia.modelo.Obra;
import br.com.bibliografia.excecao.RNException;

public class ObraNegocio {
	private GenericoDAO dao;
	
	public ObraNegocio() {
		this.dao = DAOFactory.getDAO(); 
	}
	
	public Obra consulta(Integer id) throws RNException {
		try {
			return (Obra)this.dao.consulta(Obra.class, id);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
	
	public void salva(Obra obra) throws RNException {
		try {			
			if (obra.getId() == null || obra.getId() == 0) {
				this.dao.insere(obra);
			} 
			else {
				this.dao.altera(obra);
			}
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}

	public void exclui(Obra obra) throws RNException {
		try {
			this.dao.exclui(obra);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
	
	public List<Obra> lista() throws RNException {
		try {
			return dao.lista(Obra.class);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
	
	public List<Obra> lista(String titulo) throws RNException {
		try {
			return dao.lista(Obra.class);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
}
