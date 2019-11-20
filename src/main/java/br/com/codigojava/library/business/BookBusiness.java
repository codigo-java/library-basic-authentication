package br.com.codigojava.library.business;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BookBusiness {

	@Value("${diretorio.raiz}")
	private String diretorioRaiz;

	@Value("${diretorio.arquivos}")
	private String diretorioArquivos;
    
    public void salvarArquivo(MultipartFile arquivo) {
    	this.salvar(this.diretorioArquivos, arquivo);
    }
    
    private void salvar(String diretorio, MultipartFile arquivo) {
    	Path diretorioPath = Paths.get(this.diretorioRaiz, diretorio);
    	Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
    	try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar o arquivo");
		}
    }
}
