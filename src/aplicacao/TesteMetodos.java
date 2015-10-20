package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import dominio.Avaliacao;
import dominio.Bandeirada;
import dominio.Chamado;
import dominio.Cliente;
import dominio.Empresa;
import dominio.MotoTaxista;

public class TesteMetodos {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meujpa");
		EntityManager em = emf.createEntityManager();
		
		MotoTaxista mt1 = em.find(MotoTaxista.class, 2);
		Empresa emp1 = em.find(Empresa.class, 1);
		Cliente cli1 = em.find(Cliente.class, 1);
		Chamado cha1 = em.find(Chamado.class, 1);
		Bandeirada band1 = em.find(Bandeirada.class, 1);
		Avaliacao ava1 = em.find(Avaliacao.class, 2);
		
		System.out.println("Média do MotoTaxista: " + mt1.getNome() + ": " + mt1.media());
		
		System.out.println("Preço total do Chamado: " + cha1.getCodChamado() + ": " + cha1.precoTotal());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt1 = sdf.parse("01/09/2015");
		Date dt2 = sdf.parse("20/10/2015");
		System.out.println("Corridas por período: " + emp1.corridasPorPeriodo(dt1, dt2));
		
		double mediaTeste = 100.0;
		System.out.println("MotoTaxistas com média ABAIXO de " + mediaTeste + ": "
				+ emp1.mototaxistasComMediaAbaixoDe(mediaTeste));
		
		double mediaTeste2 = 5.0;
		System.out.println("MotoTaxistas com média ACIMA de " + mediaTeste2 + ": "
				+ emp1.mototaxistasComMediaAcimaDe(mediaTeste2));

	}
}
