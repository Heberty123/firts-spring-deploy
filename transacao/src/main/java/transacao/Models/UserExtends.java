package transacao.Models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserExtends extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	
	public UserExtends(String username, String password, Collection<? extends GrantedAuthority> authorities, String email) {
		super(username, password, authorities);
		this.email = email;
	}

}
