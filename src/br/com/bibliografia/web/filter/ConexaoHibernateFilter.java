package br.com.bibliografia.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.SessionFactory;
import br.com.bibliografia.conexao.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sessionFactory;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws ServletException {
		try {
			//this.sessionFactory.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			//this.sessionFactory.getCurrentSession().getTransaction().commit();
			//this.sessionFactory.getCurrentSession().close();
		}
		catch (Throwable ex) {
			try {
				if (this.sessionFactory.getCurrentSession().getTransaction().isActive()) {
					this.sessionFactory.getCurrentSession().getTransaction().rollback();
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
			throw new ServletException(ex);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

}
