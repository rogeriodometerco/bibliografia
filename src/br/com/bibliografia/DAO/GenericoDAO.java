package br.com.bibliografia.DAO;

import java.io.Serializable;
import br.com.bibliografia.modelo.Obra;
import org.hibernate.Session;   
import br.com.bibliografia.excecao.DAOException;
import java.util.List;   
import org.hibernate.*;   
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.bibliografia.conexao.HibernateUtil;

public class GenericoDAO {

	private static final int INCLUSAO = 1;
	private static final int ALTERACAO = 2;
	private static final int EXCLUSAO = 3;
	private Session sessao;
	
	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
	
	public Serializable insere(Serializable objeto) throws DAOException {
		return this.executa(GenericoDAO.INCLUSAO, objeto, null);
	}

	public Serializable altera(Serializable objeto) throws DAOException {
		return this.executa(GenericoDAO.ALTERACAO, objeto, null);
	}

	public Serializable exclui(Serializable objeto) throws DAOException {
		return this.executa(GenericoDAO.EXCLUSAO, objeto, null);
	}
	
	@SuppressWarnings("unchecked")
	public Serializable consulta(Class classe, Serializable id) throws DAOException {
		//Session sessao = null;
		Transaction transacao = null;
		Serializable objeto = null;
		try {
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			transacao = sessao.beginTransaction();
			objeto = (Serializable)sessao.get(classe, id);
			transacao.commit();
			//System.out.println("Operação realizada com sucesso.");
		}
		catch (Exception e) {
			throw new DAOException("Não foi possível realizar a operação com o objeto. Classe: "
					+ classe.getName() + "Erro: " + e.getMessage());
		}
		finally {
			try {
				if (sessao.isOpen()) {
					sessao.close();
				} 
			}
			catch (Throwable e) {
				throw new DAOException("Erro ao fechar operação. Classe: "
						+ classe.getName() + "Erro: " + e.getMessage());
			}
		}
		return (Serializable)objeto;		
	}
	
	public <T> List<T> lista(Class<T> classe) throws DAOException {
		List<T> lista;
		try {
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transacao = sessao.beginTransaction();
			lista = this.sessao.createCriteria(classe).list();
			transacao.commit();
			if (sessao.isOpen()) {
				sessao.close();
			}
		}
		catch (Exception e) {
			throw new DAOException("Não foi possível realizar a operação com o objeto. Classe: "
					+ classe.getName() + "Erro: " + e.getMessage());
		}
		finally {
			try {
				if (sessao.isOpen()) {
					sessao.close();
				} 
			}
			catch (Throwable e) {
				throw new DAOException("Erro ao fechar operação. Classe: "
						+ classe.getName() + "Erro: " + e.getMessage());
			}
		}
		return lista;
	}
	
	private Serializable executa(int operacao, Serializable objeto, Serializable id) throws DAOException {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			transacao = sessao.beginTransaction();
			switch (operacao) {
			case GenericoDAO.INCLUSAO:
				sessao.save(objeto);
				break;
			case GenericoDAO.ALTERACAO:
				sessao.update(objeto);
				break;
			case GenericoDAO.EXCLUSAO:
				sessao.delete(objeto);
				break;
			}
			transacao.commit();
			//System.out.println("Operação realizada com sucesso.");
			//System.out.println("Objeto: " + objeto.toString());
		}
		catch (Exception e) {
			throw new DAOException("Não foi possível realizar a operação com o objeto. Classe: "
					+ objeto.getClass().getName() + "Erro: " + e.getMessage());
		}
		finally {
			try {
				if (sessao.isOpen()) {
					sessao.close();
				} 
			}
			catch (Throwable e) {
				throw new DAOException("Erro ao fechar operação. Classe: "
						+ objeto.getClass().getName() + "Erro: " + e.getMessage());
			}
		}
		return objeto;
	}


	public List<Obra> consultaObras(String titulo, String autor, String palavrasChave, String sinopse,
			String anoInicial, String anoFinal) throws DAOException {
		List<Obra> obras;
		try {
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transacao = sessao.beginTransaction();
			Criteria criteria = sessao.createCriteria(Obra.class);
			criteria.addOrder(Order.asc("titulo"));
			if (titulo.length() > 0) {
				criteria.add(Restrictions.like("titulo", titulo, MatchMode.ANYWHERE).ignoreCase());
			}
			if (autor.length() > 0) {
				criteria.add(Restrictions.like("autor", autor, MatchMode.ANYWHERE).ignoreCase());
			}
			if (palavrasChave.length() > 0) {
				criteria.add(Restrictions.like("palavrasChave", palavrasChave, MatchMode.ANYWHERE).ignoreCase());
			}
			if (sinopse.length() > 0) {
				criteria.add(Restrictions.like("sinopse", sinopse, MatchMode.ANYWHERE).ignoreCase());
			}
			if (anoInicial != null && anoInicial !="") {
				criteria.add(Restrictions.ge("ano", anoInicial));
				criteria.add(Restrictions.isNotNull("ano"));
			}
			if (anoFinal != null && anoFinal != "") {
				criteria.add(Restrictions.le("ano", anoFinal));
				criteria.add(Restrictions.isNotNull("ano"));
			}
			obras = criteria.list();
			transacao.commit();
			if (sessao.isOpen()) {
				sessao.close();
			}
		}
		catch (Exception e) {
			throw new DAOException("Não foi possível realizar a pesquisa de obras." + "Erro: " + e.getMessage());
		}
		finally {
			try {
				if (sessao.isOpen()) {
					sessao.close();
				} 
			}
			catch (Throwable e) {
				throw new DAOException("Erro ao fechar operação de pesquisa." + "Erro: " + e.getMessage());
			}
		}
		return obras;
	}
}
