package br.com.prototipo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GerarProva extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerarProva frame = new GerarProva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerarProva() {
		setTitle("Gerar Prova");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 43, 225, 27);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Escolha uma Disciplina");
		lblNewLabel.setBounds(24, 47, 162, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEscolhaUmaMatria = new JLabel("Escolha uma Matéria");
		lblEscolhaUmaMatria.setBounds(24, 97, 162, 16);
		contentPane.add(lblEscolhaUmaMatria);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(198, 93, 225, 27);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Gerar Prova");
		btnNewButton.setBounds(167, 214, 117, 29);
		contentPane.add(btnNewButton);
	}
}