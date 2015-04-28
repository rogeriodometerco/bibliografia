package br.com.bibliografia.web;
import br.com.bibliografia.excecao.RNException;
import br.com.bibliografia.modelo.Autor;
import br.com.bibliografia.negocio.AutorNegocio;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="autorBean")
@SessionScoped
public class AutorBean {
	private Autor autor = new Autor();
	private List<Autor> autores = new ArrayList();
	private AutorNegocio autorNegocio = new AutorNegocio();
	private List<SelectItem> autoresEscolha = new ArrayList();
	private List<String> autoresEscolha2 = new ArrayList();

	public String ir() {
		return "autor";
	}

	public void novo() {
		autores.add(new Autor());
	}

	public void salva() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			for (int i = 0; i < autores.size(); i++) {
				autorNegocio.salva(autores.get(i));
		 	}
		}
		catch (RNException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		autores.clear();
		//return "autor";
	}
	
	public void exclui() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			autorNegocio.exclui(this.getAutor());
		}
		catch (RNException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		autores.clear();
		//return "autor";
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public List<Autor> getLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (autores.size() == 0) {
			try {
				autores = new AutorNegocio().lista();
			}
			catch (RNException e) {
				context.addMessage(null, new FacesMessage(e.getMessage()));
			}
		}
		if (autores.size() == 0) {
			autores.add(new Autor());
		}
		return autores;
	}

	public List<SelectItem> getAutoresEscolha() {
		FacesContext context = FacesContext.getCurrentInstance();
		List<Autor> lista = new ArrayList();
		if (autoresEscolha.size() == 0) {
			try {
				lista = new AutorNegocio().lista();
				if (lista.size() == 0) {
					lista.add(new Autor());
				}
				for (Autor a : lista) {
					autoresEscolha.add(new SelectItem(a, a.getNome()));
				}
			}
			catch (RNException e) {
				context.addMessage(null, new FacesMessage(e.getMessage()));
			}
		}
		return autoresEscolha;
	}

	public List<String> getAutoresEscolha2() {
		FacesContext context = FacesContext.getCurrentInstance();
		autoresEscolha2.clear();
		autoresEscolha2.add("atuor1");
		autoresEscolha2.add("atuor21");
		autoresEscolha2.add("atuor31");
		return autoresEscolha2;
	}

	public void setAutoresEscolha(List<SelectItem> autoresEscolha) {
		this.autoresEscolha = autoresEscolha;
	}
	
	public void setAutoresEscolha2(List<String> autoresEscolha2) {
		this.autoresEscolha2 = autoresEscolha2;
	}
	
}
