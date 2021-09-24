import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import model.Boleto;

public class ProcessarBoletos {

	public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private Function<String, List<Boleto>> leituraRetorno;

	public ProcessarBoletos(Function<String, List<Boleto>> leituraRetorno) {
		this.leituraRetorno = leituraRetorno;
	}

	public static List<Boleto> lerBancoBradesco(String nomeArquivo) {

		List<Boleto> boletos = new ArrayList();

		try {

			BufferedReader br = Files.newBufferedReader(Paths.get(nomeArquivo));

			String linha;
			String divisor = ";";

			while ((linha = br.readLine()) != null) {

				String[] campo = linha.split(divisor);

				Boleto boleto = new Boleto();

				boleto.setId(Integer.parseInt(campo[0]));
				boleto.setCodBanco(campo[1]);
				boleto.setDataVencimento(LocalDate.parse(campo[4], FORMATO_DATA));
				boleto.setDataPagamento(LocalDate.parse(campo[5], FORMATO_DATA_HORA).atTime(0, 0, 0));
				boleto.setCpfCliente(campo[6]);
				boleto.setValor(Double.parseDouble(campo[7]));
				boleto.setMulta(Double.parseDouble(campo[8]));
				boleto.setJuros(Double.parseDouble(campo[9]));
				boleto.setAgencia(campo[2]);
				boleto.setContaBancaria(campo[3]);

				boletos.add(boleto);

			}

			return boletos;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static List<Boleto> lerBancoBrasil(String nomeArquivo) {

		List<Boleto> boletos = new ArrayList();

		try {

			BufferedReader br = Files.newBufferedReader(Paths.get(nomeArquivo));

			String linha;
			String divisor = ";";

			while ((linha = br.readLine()) != null) {

				String[] campo = linha.split(divisor);

				Boleto boleto = new Boleto();

				boleto.setId(Integer.parseInt(campo[0]));
				boleto.setCodBanco(campo[1]);
				boleto.setDataVencimento(LocalDate.parse(campo[2], FORMATO_DATA));
				boleto.setDataPagamento(LocalDate.parse(campo[3], FORMATO_DATA).atTime(0, 0, 0));
				boleto.setCpfCliente(campo[4]);
				boleto.setValor(Double.parseDouble(campo[5]));
				boleto.setMulta(Double.parseDouble(campo[6]));
				boleto.setJuros(Double.parseDouble(campo[7]));

				boletos.add(boleto);

			}

			return boletos;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void processar(String nomeArquivo) {

		List<Boleto> boletos = leituraRetorno.apply(nomeArquivo);

		for (Boleto boleto : boletos) {

			System.out.println(boleto);

		}

	}

}
