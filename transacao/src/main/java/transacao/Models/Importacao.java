package transacao.Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Importacao implements Comparable<Importacao> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateHours;
	private Date date;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy="importacao")
	private List<Transacao> transacoes = new ArrayList<Transacao>();
	

	public Importacao() {}
	
	public Importacao(Date dateHours, Date date) {
		this.dateHours = dateHours;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateHours() {
		return dateHours;
	}

	public void setDateHours(Date dateHours) {
		this.dateHours = dateHours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int compareTo(Importacao o) {
		
		return this.dateHours.compareTo(o.getDateHours());
	}

/*
	@Override
	public int compare(Importacao o1, Importacao o2) {
		
		return o1.getDateHours().compareTo(o2.getDateHours());
	}
*/	
	
}
