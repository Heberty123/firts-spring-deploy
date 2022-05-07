package transacao.Service.ExtSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import transacao.Exception.ExceptionEmpty;
import transacao.Models.Transacao;
import transacao.Service.Convert;

@Service
public class ReadCSV {

	public static void read(MultipartFile file, List<Transacao> lista, List<Transacao> duplicadas) throws Exception{
		
		/***
		 * 	Check if file content is empty !
		 */
		fileIsEmpty(file);
			

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		/**
		 * 	Else let's go to read content in file !
		 */
		
		try {
			InputStream input = file.getInputStream();
			
			try(InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8)){
				
				try(BufferedReader br = new BufferedReader(isr)){
					
					br.lines().forEach(line -> {
						String fields[] = line.split(",");
						try {
							Transacao transacao = new Transacao(fields[0], Convert.toInteger(fields[1]), fields[2], fields[3], Convert.toInteger(fields[4]), fields[5], Convert.toDouble(fields[6]), format.parse(fields[7]));
							if(lista.contains(transacao)) {
								duplicadas.add(transacao);
							}
							else {
								lista.add(transacao);
							}
						}catch(ArrayIndexOutOfBoundsException e) {
							throw new ArrayIndexOutOfBoundsException("Look at your file you imported if this error is in the default csv format for transactions");
		
						} catch (Exception e) {
							e.printStackTrace();
						} 
					});
				}
				
			}
			input.close();		
		}	
		catch (IOException e) {
			throw new IOException("erro de leitura !");
		}
	}
	
	
	
	public static void fileIsEmpty(MultipartFile file) throws Exception {
		
		if(file.getSize() <= 5)
			throw new ExceptionEmpty("this file that you imported is content empty or any innutil information for process");
	}
}
