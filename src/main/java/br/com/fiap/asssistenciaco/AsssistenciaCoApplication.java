package br.com.fiap.asssistenciaco;

import br.com.fiap.asssistenciaco.repository.EquipamentoRepository;
import br.com.fiap.asssistenciaco.repository.OrdemServicoRepository;
import br.com.fiap.asssistenciaco.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class AsssistenciaCoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AsssistenciaCoApplication.class, args);
	}

//	--------------------------------------------TESTE-----------------------------------------

	@Autowired
	EquipamentoRepository equipamentoRepository;

	@Autowired
	OrdemServicoRepository ordemServicoRepository;

	@Autowired
	ServicoRepository servicoRepository;

	public void run(String... args) throws Exception{

		var resultado = servicoRepository.findByValorGreaterThanEqual(new BigDecimal("80"));

		resultado.forEach(i -> System.out.println(i.getDescricao() + " - " + i.getValor()));
	}


}
