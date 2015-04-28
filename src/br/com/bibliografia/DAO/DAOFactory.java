package br.com.bibliografia.DAO;

import br.com.bibliografia.DAO.GenericoDAO;
import br.com.bibliografia.conexao.HibernateUtil;

public class DAOFactory {
	private static GenericoDAO dao;
	
	private DAOFactory() {

	}
	
	public static synchronized GenericoDAO getDAO() {
		if (dao == null) {
			dao = new GenericoDAO();
			dao.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
		}
		return dao;
	}
}
