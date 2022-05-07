package transacao.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import transacao.Models.Importacao;
import transacao.Models.Transacao;
import transacao.Models.Usuario;
import transacao.Repositories.RepositoryImportacao;
import transacao.Repositories.RepositoryTransacao;

public class Check {
	
	public static List<Transacao> findErroByDate(List<Transacao> lista) {
		List<Transacao> listaDateErro = new ArrayList<>();
		Date primeiraData = lista.get(0).getData();
		
		for (Transacao transacao : lista) {
			
			if(transacao.getData().getDay() != primeiraData.getDay() || transacao.getData().getMonth() != primeiraData.getMonth() || transacao.getData().getYear() != primeiraData.getYear()) {
				listaDateErro.add(transacao);
			}
		}
		return listaDateErro;
	}
	
	
	public static List<Transacao> findErroNull(List<Transacao> lista){
		List<Transacao> listaErroNull = new ArrayList<>();
		
		for (Transacao transacao : lista) {
			if(transacao.hasNull()) {
				listaErroNull.add(transacao);
			}
		}
		return listaErroNull;
	}
	
	
	public static ModelAndView HasErro(Usuario UsernameExist, Usuario EmailExist) {
		ModelAndView mv = new ModelAndView("redirect:/login");
		if(UsernameExist != null || EmailExist != null) {
			mv = new ModelAndView("Autenticacao/cadastro.html");
			
			if(UsernameExist != null){
				mv.addObject("erroUsername", true);
				System.out.println("tinha erro de username");
			}
			if(EmailExist != null) {
				mv.addObject("erroEmail", true);
				System.out.println("tinah erro de email");
			}
			return mv;
		}
		
		return mv;
	}
	
	
	/**
	 * 
	 * This method is used to get all errors in the generated list (in Argument) and
	 *  return results of all errors to assign in ModelAndView to represent the !
	 */
	
	public static boolean erroDuplicadas(Map<String, List<Transacao>> mapa, ModelAndView mv, RepositoryImportacao rImportacao, RepositoryTransacao rTransacao, Usuario usuario) {
		
		List<Transacao> lista = mapa.get("lista");		
		List<Transacao> listaDateErro = mapa.get("erroDate");
		List<Transacao> listaErroDuplicadas = (List<Transacao>) mapa.get("duplicidades");
		List<Transacao> listaErroNull = mapa.get("erroNull");
		
		
		if(!listaDateErro.isEmpty()) {
			mv.addObject("listaErroDate", listaDateErro);
			mv.addObject("erroDate", true);
		}
		
		if(!listaErroNull.isEmpty()) {
			mv.addObject("listaErroNull", listaErroNull);
			mv.addObject("erroNull", true);
		}
		
		if(!listaErroDuplicadas.isEmpty()) {
			mv.addObject("listaErroDuplicadas", listaErroDuplicadas);
			mv.addObject("isInvalid", true);
			mv.addObject("erroDuplicado", true);
			mv.addObject("erro", "can´t have repeat transaction !");
			return true;
		}
		
		Importacao importacao = null;
		if(!lista.isEmpty()) {
			importacao = new Importacao(new Date(), lista.get(0).getData());
			importacao.setUsuario(usuario);
			rImportacao.save(importacao);
		}
		

		for (Transacao transacao : lista) {
			transacao.setImportacao(importacao);
			rTransacao.save(transacao);
		}
		
		List<Importacao> listaImportacao = rImportacao.findAll();
		Collections.reverse(listaImportacao);
		mv.addObject("listaImportacoes", listaImportacao);
		
		return false;
	}
	
	/**
	 * 
	 * Checking --> Login !
	 */
	
	
	public static boolean hasUserAndEmail(Usuario UsernameExist, Usuario EmailExist, ModelAndView mv, String view) {
		
		if(UsernameExist != null || EmailExist != null) {
			mv = new ModelAndView(view);
			
			if(UsernameExist != null){
				mv.addObject("erroUsername", true);
			}
			if(EmailExist != null) {
				mv.addObject("erroEmail", true);
			}
			return true;
		}
		return false;
	}
}
