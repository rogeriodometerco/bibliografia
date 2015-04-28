package br.com.bibliografia.web;
import br.com.bibliografia.excecao.RNException;
import br.com.bibliografia.modelo.Obra;
import br.com.bibliografia.negocio.ObraNegocio;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.awt.image.BufferedImage;
import java.io.*;
import org.apache.myfaces.custom.fileupload.UploadedFile;

@ManagedBean(name="obraBean")
@SessionScoped
public class ObraBean {
	private Obra obra = new Obra();
	private ObraNegocio obraNegocio = new ObraNegocio();
	private UploadedFile upFile;
	boolean rendFailure=false;
	private String nomeCompleto;
	public String msgUpload;

	public String ir() {
		return "obra";
	}

	public void novo() {
		obra = new Obra();
	}

	public void salva() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			obraNegocio.salva(obra);
			context.addMessage(null, new FacesMessage("Obra salva com sucesso."));
			this.novo();
		}
		catch (RNException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void exclui() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			obraNegocio.exclui(this.getObra());
			context.addMessage(null, new FacesMessage("Obra excluída com sucesso."));
			this.novo();
		}
		catch (RNException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}


	public UploadedFile getUpFile(){
		return upFile;
	}

	public void setUpFile(UploadedFile upFile){
		this.upFile = upFile;
	}

	public boolean getRendFailure(){
		return rendFailure;
	}

	public void setRendFailure(boolean rendFailure){
		this.rendFailure = rendFailure;
	}

	public void upload() {
		try {
			InputStream stream = upFile.getInputStream();
			long size = upFile.getSize();
			byte[] buffer = new byte[(int)size];
			stream.read(buffer, 0, (int)size);
			stream.close();
			gravaArquivo(buffer);
			rendFailure=false;
			System.out.println("File Upload Successful.");
			
		}
		catch (IOException ioe) {
			System.out.println("File Upload Unsuccessful. " + ioe.getMessage());
			rendFailure=true;
		}
	}

	private void gravaArquivo(byte[]b) throws IOException {
		File f;
		String arquivo = System.currentTimeMillis() + ".jpg";

		nomeCompleto = FacesContext.getCurrentInstance().getExternalContext().
				getRealPath("/imagem") + "/" + arquivo;
		String nomeRelativo = "/imagem" + "/" + arquivo;
		f = new File(nomeCompleto);
		try {
			FileOutputStream saida = new FileOutputStream(f);   
			saida.write(b);   
			saida.close();
			obra.setCaminhoImagem(nomeRelativo);
			msgUpload = "Imagem carregada com sucesso.";
			System.out.println("Arquivo gerado: " + nomeCompleto);
		}
		catch (IOException e) {
			msgUpload = "Erro ao carregar imagem.";
			System.out.println("Erro gerando arquivo " + nomeCompleto +": " + e.getMessage());
		}

		// Cópia de segurança.
		nomeCompleto = "c:/imagem/" + arquivo;
		f = new File(nomeCompleto);
		try {
			FileOutputStream saida = new FileOutputStream(f);   
			saida.write(b);   
			saida.close();
			//obra.setCaminhoImagem(nomeRelativo);
			//msgUpload = "Imagem carregada com sucesso.";
			System.out.println("Arquivo de backup gerado: " + nomeCompleto);
		}
		catch (IOException e) {
			//msgUpload = "Erro ao carregar imagem.";
			System.out.println("Erro gerando arquivo de backup " + nomeCompleto +": " + e.getMessage());
		}

		 
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getMsgUpload() {
		return msgUpload;
	}

	public void setMsgUpload(String msgUpload) {
		this.msgUpload = msgUpload;
	}
}
