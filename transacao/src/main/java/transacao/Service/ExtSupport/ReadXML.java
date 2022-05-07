package transacao.Service.ExtSupport;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import transacao.Service.Convert;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import transacao.Exception.ExceptionEmpty;
import transacao.Models.Transacao;

public class ReadXML {
	
	public static void read(MultipartFile file, List<Transacao> lista, List<Transacao> duplicadas) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		 //Get Document Builder
        // Load the input XML document, parse it and return an instance of the
        // Document class.
        Document document;
		try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(file.getInputStream());
			
			hasException(document);
			
			NodeList nodeListTransacoes = document.getElementsByTagName("transacoes");
			
			
	        NodeList nodeList = document.getElementsByTagName("transacao");
			
	        
	        for (int i = 0; i < nodeList.getLength(); i++) {
	               Node node = nodeList.item(i);

	               
	               if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element elem = (Element) node;
	                    
	                    
	                    NodeList origemList = elem.getElementsByTagName("origem");
	                   
	                    	if(origemList.getLength() == 0)
	                    		throw new ExceptionEmpty("Any element don't have tag <origem>");
	                    
	                    	Node origemNode = origemList.item(0);
	                    	Element oElem = (Element) origemNode;
	                    	
	                    	if(oElem.getElementsByTagName("banco").getLength() == 0)
	                    		throw new ExceptionEmpty("Inside of element <origem> in any local, don't have tag<banco>");
	                    	
	                    	if(oElem.getElementsByTagName("agencia").getLength() == 0)
	                    		throw new ExceptionEmpty("Inside of element <origem> in any local, don't have tag<agencia>");
	                    	
	                    	if(oElem.getElementsByTagName("conta").getLength() == 0)
	                    		throw new ExceptionEmpty("Inside of element <origem> in any local, don't have tag<conta>");
	                    	
	                    	String bancoOrigem = oElem.getElementsByTagName("banco").item(0).getTextContent();
	                    	String agenciaOrigem = oElem.getElementsByTagName("agencia").item(0).getTextContent();
	                    	String contaOrigem = oElem.getElementsByTagName("conta").item(0).getTextContent();

	                    
	                    	NodeList destinoList = elem.getElementsByTagName("destino");
	                    	
	                    	
	                    	if(destinoList.getLength() == 0)
	                    		throw new ExceptionEmpty("Any element don't have tag <destino>");
	                    

	                    	Node destinoNode = destinoList.item(0);
	                    	Element dElem = (Element) destinoNode;
	                    	
	                    	if(dElem.getElementsByTagName("banco").getLength() == 0)
	                    		throw new ExceptionEmpty("Inside of element <destino> in any local, don't have tag<banco>");
	                    	
	                    	if(dElem.getElementsByTagName("agencia").getLength() == 0)
	                    		throw new ExceptionEmpty("Inside of element <destino> in any local, don't have tag<agencia>");
	                    	
	                    	if(dElem.getElementsByTagName("conta").getLength() == 0)
	                    		throw new ExceptionEmpty("Inside of element <destino> in any local, don't have tag<conta>");
	                    	
	                    	String bancoDestino = dElem.getElementsByTagName("banco").item(0).getTextContent();
	                    	String agenciaDestino = dElem.getElementsByTagName("agencia").item(0).getTextContent();
	                    	String contaDestino = dElem.getElementsByTagName("conta").item(0).getTextContent();
	                    
	                    
	                    	if(elem.getElementsByTagName("valor").getLength() == 0)
	                    		throw new ExceptionEmpty("In any tag transacao we don't find tag <valor> ");
	                    	
	                    	if(elem.getElementsByTagName("data").getLength() == 0)
	                    		throw new ExceptionEmpty("In any tag transacao we don't find tag <data> ");
	                    	
	                    	String valor = elem.getElementsByTagName("valor").item(0).getTextContent();
	                    	String data = elem.getElementsByTagName("data").item(0).getTextContent();
	                    	
	                    	if(data.isEmpty())
	                    		throw new ExceptionEmpty("More tags we identified that haven't data, nothing process identity without data !");
	                    			
	                    
	                    	Transacao transacao = new Transacao(bancoOrigem, Convert.toInteger(agenciaOrigem), contaOrigem, bancoDestino, Convert.toInteger(agenciaDestino), contaDestino, Convert.toDouble(valor), format.parse(data));
	                    	
	                    	if(lista.contains(transacao)) {
	                    		duplicadas.add(transacao);
	                    	}
	                    	else {
	                    		lista.add(transacao);
	                    	}
	               
	               }
	          }
				
		}
		catch(NumberFormatException e) {
			throw new Exception("some tags transacao's chield values are empty");
			
		}
		catch(ExceptionEmpty e) {
			throw new Exception(e.getMessage());
			
		}
		
		catch(ParseException e) {
			throw new Exception("any transaction's date can't be empty !");
		}
		
		catch (Exception e) {
			throw new Exception(e.getMessage());
		} 

		
		
		
	}


	private static void hasException(Document document) throws ExceptionEmpty {
		 if(document.getElementsByTagName("transacoes").getLength() == 0)
				throw new ExceptionEmpty("We see at this file haven't content a important tag called 'transacoes' !");
	 
		 if(!(document.getElementsByTagName("transacao").getLength() > 0))
	        	throw new ExceptionEmpty("we identify that your file is content empty, as: <transacao>"); 
	 }

	
}
