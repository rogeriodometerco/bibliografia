package br.com.bibliografia.negocio;

import java.util.List;
import br.com.bibliografia.DAO.DAOFactory;
import br.com.bibliografia.DAO.GenericoDAO;
import br.com.bibliografia.modelo.Autor;
import br.com.bibliografia.excecao.RNException;

public class AutorNegocio {
	private GenericoDAO dao;
	
	public AutorNegocio() {
		this.dao = DAOFactory.getDAO(); 
	}
	
	public Autor consulta(Integer id) throws RNException {
		try {
			return (Autor)this.dao.consulta(Autor.class, id);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
	
	public void salva(Autor autor) throws RNException {
		try {			
			if (autor.getId() == null || autor.getId() == 0) {
				this.dao.insere(autor);
			} 
			else {
				this.dao.altera(autor);
			}
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}

	public void exclui(Autor autor) throws RNException {
		try {
			this.dao.exclui(autor);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
	
	public List<Autor> lista() throws RNException {
		try {
			return dao.lista(Autor.class);
		}
		catch (Exception e) {
			throw new RNException(e);
		}
	}
}
