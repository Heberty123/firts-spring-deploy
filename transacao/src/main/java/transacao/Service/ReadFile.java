package transacao.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import transacao.Exception.ExceptionFileEmpty;
import transacao.Exception.ExceptionSupport;
import transacao.Models.Transacao;
import transacao.Service.ExtSupport.ReadCSV;
import transacao.Service.ExtSupport.ReadXML;


public class ReadFile {
	

	public static Map Ready(MultipartFile file) throws Exception{
		List<Transacao> lista = new ArrayList<>();
		List<Transacao> duplicadas = new ArrayList<>();
		
		if(file.isEmpty()) 
			throw new ExceptionFileEmpty("This input is empty !");
		
		if(file.getContentType().equals("text/csv")) 
			ReadCSV.read(file, lista, duplicadas);
		
		else if(file.getContentType().equals("text/xml")) 
			ReadXML.read(file, lista, duplicadas);
		
		else 
			throw new ExceptionSupport("the system only support file with extensions:  .CSV and .XML");
		
		System.out.println("\n\nO nome do file é: " + file.getOriginalFilename());
		System.out.println("O tamanho em megabytes do file é: " + file.getSize());
		System.out.println("\n\n");
				
		List<Transacao> listaErroNull = Check.findErroNull(lista);
		
		List<Transacao> listaErroDate = Check.findErroByDate(lista);
		
		List<Transacao> novalista = endList(lista, listaErroNull, listaErroDate);
	
		Map map = Map.of("lista", novalista, "duplicidades", duplicadas, "erroNull", listaErroNull, "erroDate", listaErroDate);
		return map;
	}
	
	
	
	
	public static List<Transacao> endList(List<Transacao> lista, List<Transacao> ErroNull, List<Transacao> ErroDate){
		lista.removeAll(ErroNull);
		lista.removeAll(ErroDate);
		return lista;
	}


	
	
	
}
