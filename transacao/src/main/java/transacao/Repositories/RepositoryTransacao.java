package transacao.Repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import transacao.Models.Transacao;

@Repository
public interface RepositoryTransacao extends JpaRepository<Transacao, Long> {

	@Query("SELECT t FROM Transacao t INNER JOIN t.importacao i WHERE i.id = :id")
	List<Transacao> findAllByIdOfImportacao(Long id);

	@Query("SELECT t FROM Transacao t WHERE t.valor >= :value AND MONTH(t.data) = :month AND YEAR(t.data) = :year")
	List<Transacao> findAllSusTransactions(Double value, int month, int year);

	@Query("SELECT t.bancoOrigem, t.agenciaOrigem, t.contaOrigem, SUM(t.valor) FROM Transacao t WHERE MONTH(t.data) = :month AND YEAR(t.data) = :year GROUP BY t.bancoOrigem, t.agenciaOrigem, t.contaOrigem")
	List<String> findOrigemAccWithMonthAndYear(int month, int year);
	
	@Query("SELECT t.bancoDestino, t.agenciaDestino, t.contaDestino, SUM(t.valor) FROM Transacao t WHERE MONTH(t.data) = :month AND YEAR(t.data) = :year GROUP BY t.bancoDestino, t.agenciaDestino, t.contaDestino")
	List<String> findDestinoAccWithMonthAndYear(int month, int year);
	
	@Query("SELECT t.bancoOrigem, t.agenciaOrigem, SUM(t.valor) FROM Transacao t WHERE MONTH(t.data) = :month AND YEAR(t.data) = :year GROUP BY t.bancoOrigem, t.agenciaOrigem")
	List<String> findAllOrigemAgencyWithMonthAndYear(int month, int year);
	
	@Query("SELECT t.bancoDestino, t.agenciaDestino, SUM(t.valor) FROM Transacao t WHERE MONTH(t.data) = :month AND YEAR(t.data) = :year GROUP BY t.bancoDestino, t.agenciaDestino")
	List<String> findAllDestinoAgencyWithMonthAndYear(int month, int year);
	
	
	
	

}
