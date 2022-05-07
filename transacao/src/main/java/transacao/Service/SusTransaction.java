package transacao.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import transacao.Models.SuspiciousAccount;
import transacao.Models.SuspiciousAgency;
import transacao.Repositories.RepositoryTransacao;


public class SusTransaction {

	public static List<SuspiciousAccount> Account(RepositoryTransacao repTransacao ,int month, int year, double limit){
		List<SuspiciousAccount> suspicious = new ArrayList<>();
		
		repTransacao.findOrigemAccWithMonthAndYear(month, year).forEach(o -> {
			Scanner read = new Scanner(o);
			
			String fields[] = read.nextLine().split(",");
			if(Double.parseDouble(fields[3]) >= limit) {
				suspicious.add(new SuspiciousAccount(fields[0], Integer.parseInt(fields[1]), fields[2], Double.valueOf(fields[3]), "saída"));
			}
		});
		
		
		repTransacao.findDestinoAccWithMonthAndYear(month, year).forEach(d -> {
			Scanner read = new Scanner(d);
			
			String fields[] = read.nextLine().split(",");
			if(Double.parseDouble(fields[3]) >= limit) {
				suspicious.add(new SuspiciousAccount(fields[0], Integer.parseInt(fields[1]), fields[2], Double.valueOf(fields[3]), "entrada"));
			}
		});

		
		return suspicious;
	}
	
	
	public static List<SuspiciousAgency> Agency(RepositoryTransacao repTransacao ,int month, int year, double limit){
		List<SuspiciousAgency> suspicious = new ArrayList<>();
		
		repTransacao.findAllOrigemAgencyWithMonthAndYear(month, year).forEach(o -> {
			Scanner read = new Scanner(o);
			
			String fields[] = read.nextLine().split(",");
			if(Double.parseDouble(fields[2]) >= limit) {
				suspicious.add(new SuspiciousAgency(fields[0], Integer.parseInt(fields[1]), Double.valueOf(fields[2]), "saída"));
			}
		});
		
		
		repTransacao.findAllDestinoAgencyWithMonthAndYear(month, year).forEach(d -> {
			Scanner read = new Scanner(d);
			
			String fields[] = read.nextLine().split(",");
			if(Double.parseDouble(fields[2]) >= limit) {
				suspicious.add(new SuspiciousAgency(fields[0], Integer.parseInt(fields[1]), Double.valueOf(fields[2]), "entrada"));
			}
		});

		
		return suspicious;
	}
}
