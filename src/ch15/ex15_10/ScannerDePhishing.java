/*
 * Objetivo: 15.10 (Scanner de phishing) Phishing � uma forma de roubo de identidade pela qual, em um e-mail, um remetente fingindo ser uma fonte
 * confi�vel tenta adquirir informa��es privadas, como nomes de usu�rio, senhas, n�meros de cart�es de cr�dito e n�mero de previd�ncia
 * social. E-mails contendo phishing fingindo ser de bancos populares, empresas de cart�es de cr�dito, sites de leil�o, redes sociais e servi�os
 * de pagamento on-line podem parecer bem leg�timos. Essas mensagens fraudulentas geralmente fornecem links para sites falsos nos quais
 * voc� � solicitado a inserir informa��es sigilosas.
 * Fa�a uma pesquisa on-line sobre golpes de phishing. Verifique tamb�m o Anti-Phishing Working Group (<www.antiphishing.org>) e o
 * site Cyber Investigations do FBI (<www.fbi.gov/about-us/investigate/cyber/cyber>), nos quais voc� encontrar� informa��es sobre os golpes
 * mais recentes e como se proteger.
 * Crie uma lista de 30 palavras, frases e nomes de empresas comumente encontrados em mensagens de phishing. Atribua um ponto a
 * cada uma com base na sua estimativa da probabilidade de estar em uma mensagem desse g�nero (por exemplo, um ponto se � pouco prov�vel,
 * dois pontos se moderadamente prov�vel ou tr�s pontos se altamente prov�vel). Elabore um aplicativo que verifica em um arquivo de
 * texto esses termos e frases. Para cada ocorr�ncia de uma palavra-chave ou frase no arquivo de texto, some o ponto atribu�do aos totais para
 * essa palavra ou frase. A cada palavra-chave ou frase, gere uma linha com elas, o n�mero de ocorr�ncias e os pontos totais. Ent�o, mostre os
 * pontos totais para a mensagem inteira. Seu programa atribui um total de pontos altos a alguns dos e-mails de phishing reais que voc� recebeu?
 * Ele atribui uma total de pontos altos a alguns e-mails leg�timos que voc� recebeu?
 * s
 * Autor: Gustavo Alves
 */

package ch15.ex15_10;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScannerDePhishing {

	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null, "Selecione o arquivo (.txt) que deseja verificar.", "Scanner de Phishing",
				JOptionPane.INFORMATION_MESSAGE);
		try {
			checkFile(chooseFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Path chooseFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos de texto", "txt"));
		chooser.setAcceptAllFileFilterUsed(false);

		int message = chooser.showOpenDialog(null);

		if (message == JFileChooser.CANCEL_OPTION)
			System.exit(1);

		return chooser.getSelectedFile().toPath();

	}

	public static void checkFile(Path path) throws IOException {
		String[] list = { "Globo" };
		int[] points = new int[list.length];
		int total = 0;

		Scanner input = new Scanner(path);

		while (input.hasNext()) {
			String string = (String) input.next();

			String[] tokens = string.split(" ");

			for (int i = 0; i < tokens.length; i++) {
				for (int j = 0; j < list.length; j++) {
					if (tokens[i].equals(list[j])) {
						points[j]++;
						total++;
					}
				}
			}
		}
		input.close();

		display(list, points, total);
	}

	public static void display(String[] list, int[] points, int total) {
		String message = "";

		for (int i = 0; i < list.length; i++)
			message += String.format("%s %10d%n", list[i], points[i]);

		message += String.format("%n%s %10d", "TOTAL", total);

		JOptionPane.showMessageDialog(null, message);
	}

}
