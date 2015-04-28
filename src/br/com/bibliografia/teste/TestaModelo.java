package br.com.bibliografia.teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.bibliografia.modelo.*;
import br.com.bibliografia.DAO.DAOFactory;
import br.com.bibliografia.DAO.GenericoDAO;


public class TestaModelo {

	public static void main(String[] args) {
		try {
			
		GenericoDAO dao = DAOFactory.getDAO();
/*
		Autor autor = new Autor();
		autor.setNome("Rogério");

		Autor autor2 = new Autor();
		autor2.setNome("Rogério2");

		Editora editora = new Editora();
		editora.setNome("Editora teste");

		TipoDeObra tipoDeObra = new TipoDeObra();
		tipoDeObra.setDescricao("Tipo de obra");
		
		Tradutor tradutor = new Tradutor();
		tradutor.setNome("Tradutor");

		Obra obra = new Obra();
		obra.setTitulo("Obra teste");
		
		PalavraChave palavraChave = new PalavraChave();
		palavraChave.setPalavra("palavra");

		List autores = new ArrayList();
		autores.add(autor);
		autores.add(autor2);

		List tradutores = new ArrayList();
		tradutores.add(tradutor);

		palavraChave.setObra(obra);

		List palavrasChave = new ArrayList();
		palavrasChave.add(palavraChave);

		obra.setAutores(autores);
		obra.setTipoDeObra(tipoDeObra);
		obra.setEditora(editora);
		obra.setTradutores(tradutores);
		obra.setPalavrasChave(palavrasChave);
		dao.insere(obra);
*/
		long tempo = new java.util.Date().getTime();
		
		for (int i=0; i<10000; i++) {
			Autor autor = new Autor();
			//autor = (Autor)dao.consulta(autor.getClass(), Integer.parseInt("247"));
			autor.setNome("Autor " + i);
			dao.insere(autor);
		}
		tempo = new java.util.Date().getTime() - tempo;
		System.out.println("Tempo usando hibernate:"+ tempo);

		/*	
		palavraChave = new PalavraChave();
		palavraChave.setPalavra("palavra 2");
		obra = new Obra();
		palavraChave.setObra(obra);
		obra.setTitulo("obra2");
		List l = new ArrayList();
		l.add(palavraChave);
		obra.setPalavrasChave(l);
	
		obra.setId(180);
		*/

		/*
		Obra obra = new Obra();
		obra = (Obra)dao.consulta(obra.getClass(), 237);
		System.out.println("Obra recuperada: " + obra.getTitulo());
		*/
		
		//dao.insere(obra);
//		palavraChave.setId(189); //190
//		dao.exclui(palavraChave);

		/*
		List<Autor> lista = dao.lista(Autor.class);
		System.out.println(lista);
		*/
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
			
	}

}
