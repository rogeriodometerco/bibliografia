package br.com.bibliografia.web;
import javax.faces.application.FacesMessage;
import br.com.bibliografia.DAO.DAOFactory;
import br.com.bibliografia.DAO.GenericoDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.coyote.http11.Http11AprProcessor;

import br.com.bibliografia.excecao.RNException;
import br.com.bibliografia.modelo.Autor;
import br.com.bibliografia.modelo.Obra;
import br.com.bibliografia.negocio.AutorNegocio;
import br.com.bibliografia.negocio.ObraNegocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@ManagedBean(name="obraBeanFiltro")
@SessionScoped
public class ObraBeanFiltro {
	private String titulo = "";
	private String autor = "";
	private String palavrasChave = "";
	private String sinopse = "";
	private String anoInicial;
	private String anoFinal;
	private List obras = new ArrayList();
		
	public String ir() {
		if (obras.size() == 0) {
			pesquisa();
		}
		return "obraFiltro";
	}

	public String abrir() {
		return "obra";
	}

	public void limparFiltros() {
		titulo = "";
		titulo = "";
		autor = "";
		palavrasChave = "";
		sinopse = "";
		anoInicial = "";
		anoFinal = "";
}

	public void pesquisa() {
		FacesContext context = FacesContext.getCurrentInstance();
		imprime();
		try {
			GenericoDAO dao = DAOFactory.getDAO();
			obras = new ArrayList();
			obras = dao.consultaObras(this.titulo, this.autor, this.palavrasChave, this.sinopse, this.anoInicial, this.anoFinal);
		}
		catch (Exception e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public List getLista() {
		if (obras.size() == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				GenericoDAO dao = DAOFactory.getDAO();
				obras = new ArrayList();
				obras = dao.consultaObras(this.titulo, this.autor, this.palavrasChave, this.sinopse, this.anoInicial, this.anoFinal);
			}
			catch (Exception e) {
				context.addMessage(null, new FacesMessage(e.getMessage()));
			}
		}
		return obras;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List getObras() {
		System.out.println("Passou em getObras()");
		return obras;
	}

	public void setObras(List obras) {
		this.obras = obras;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getAnoInicial() {
		return anoInicial;
	}

	public void setAnoInicial(String anoInicial) {
		this.anoInicial = anoInicial;
	}

	public String getAnoFinal() {
		return anoFinal;
	}

	public void setAnoFinal(String anoFinal) {
		this.anoFinal = anoFinal;
	}

	private void imprime() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map m = context.getAttributes();
		System.out.println("Imprime(): " + m.toString());

	}

}
