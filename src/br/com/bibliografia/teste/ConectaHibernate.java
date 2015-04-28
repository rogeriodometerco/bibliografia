package br.com.bibliografia.teste;

import org.hibernate.Session;
import br.com.bibliografia.conexao.HibernateUtil;

public class ConectaHibernate {

	public static void main(String[] args) {
		Session sessao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Conectou");
		}
		finally {
			sessao.close();
		}
	}

}
