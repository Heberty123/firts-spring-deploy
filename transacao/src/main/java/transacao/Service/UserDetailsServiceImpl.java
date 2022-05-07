package transacao.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import transacao.Models.UserExtends;
import transacao.Models.Usuario;
import transacao.Repositories.RepositoryUser;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private RepositoryUser repositoryUser;
	
	public UserDetailsServiceImpl() {}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repositoryUser.findByEmail(email);
		if(usuario != null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRole());
			Set<GrantedAuthority> authorities = new HashSet<>();
			authorities.add(authority);
			UserExtends user = new UserExtends(usuario.getUsername(), usuario.getPassword(), authorities, usuario.getEmail());
			return user;
		}
		return null;
	}

}
