
public class Principal {

	public static void main(String[] args) {

		final ProcessarBoletos processarBradesco = new ProcessarBoletos(ProcessarBoletos::lerBancoBradesco);

		final ProcessarBoletos processarBrasil = new ProcessarBoletos(ProcessarBoletos::lerBancoBrasil);

		String arquivoBrasil = "src/main/resources/banco-brasil-1.csv";

		String arquivoBradesco = "src/main/resources/bradesco-1.csv";

		processarBradesco.processar(arquivoBradesco);

		processarBrasil.processar(arquivoBrasil);

	}

}
