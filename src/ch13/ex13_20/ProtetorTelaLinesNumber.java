/*
 * Objetivo     : 13.20 (Protetor de tela para um n�mero aleat�rio de linhas) Modifique sua solu��o da Quest�o 13.19 para permitir que
 * o usu�rio insira o n�mero de linhas aleat�rias que deve ser desenhado antes de o aplicativo apagar seu pr�prio desenho e come�ar a 
 * desenhar linhas novamente. Utilize um JTextField para obter o valor. O usu�rio deve ser capaz de digitar um novo n�mero no JTextField 
 * em qualquer momento durante a execu��o do programa. Utilize uma classe interna para realizar o tratamento de evento para o JTextField.
 *
 * Programador  : Gustavo Alves - gustavo.almeida13@fatec.sp.gov.br
 * Data Cria��o : 7 de mar de 2019
 */

package ch13.ex13_20;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ProtetorTelaLinesNumber extends JPanel implements ActionListener {
	private final Timer timer;
	private static JTextField textField = new JTextField("0");

	public ProtetorTelaLinesNumber() {
		timer = new Timer(1000, this);
		timer.start();

		setBackground(Color.WHITE);
		textField.setToolTipText("Digite a quantidade de linhas");

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		SecureRandom random = new SecureRandom();

		int numberLines;
		try {
			numberLines = Integer.parseInt(textField.getText());
		} catch (Exception e) {
			numberLines = 0;
		}

		for (int i = 0; i < numberLines; i++)
			g.drawLine(random.nextInt(getWidth()), random.nextInt(getHeight()), random.nextInt(getWidth()),
					random.nextInt(getHeight()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Protetor de Tela - Linhas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ProtetorTelaLinesNumber draw = new ProtetorTelaLinesNumber();
		frame.add(draw);

		frame.add(textField, BorderLayout.SOUTH);
		frame.setSize(360, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
