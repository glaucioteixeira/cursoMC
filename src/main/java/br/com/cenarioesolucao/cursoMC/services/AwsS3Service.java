package br.com.cenarioesolucao.cursoMC.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.cenarioesolucao.cursoMC.services.exceptions.ArquivoExcecao;

@Service
public class AwsS3Service {

	private Logger LOG = LoggerFactory.getLogger(AwsS3Service.class);
	
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multipartFile) {
		
		try {
			String fileName = multipartFile.getOriginalFilename();		// Nome do arquivo que esta sendo enviado
			InputStream is = multipartFile.getInputStream();			// Encapsula o processo de leitura a partir de uma origem
			String contentType = multipartFile.getContentType();		// Tipo do arquivo que esta sendo enviado
			
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new ArquivoExcecao("Erro de IO" + e.getMessage());
		}
			
			
	}
	
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		
		try {
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType(contentType);
			
			LOG.info("Iniciando upload ...");
			amazonS3.putObject(bucketName, fileName, is, metaData);
			LOG.info("Upload finalizado!");
				
			return amazonS3.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new ArquivoExcecao("Erro ao converter URL para URI");
		}
		
	}
	
}
