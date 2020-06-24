package br.com.prototipo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class CadastroAluno extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAluno frame = new CadastroAluno();
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
	public CadastroAluno() {
		setTitle("Cadastro de Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(316, 314, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(24, 33, 61, 16);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setBounds(95, 28, 338, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("CPF (Somente números)");
		label.setBounds(24, 74, 148, 16);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(204, 69, 229, 26);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("Email");
		label_1.setBounds(24, 222, 61, 16);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(95, 217, 338, 26);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("Tel.: (Somente números)");
		label_2.setBounds(24, 122, 168, 16);
		contentPane.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(204, 117, 229, 26);
		contentPane.add(textField_3);
		
		JLabel label_3 = new JLabel("Orientação Sexual");
		label_3.setBounds(24, 170, 168, 16);
		contentPane.add(label_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Masculino");
		comboBox.addItem("Feminino");
		comboBox.addItem("Outro");
		comboBox.setSelectedItem(null);
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(204, 166, 229, 27);
		contentPane.add(comboBox);
		
		JLabel label_4 = new JLabel("Senha");
		label_4.setBounds(24, 262, 61, 16);
		contentPane.add(label_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(95, 257, 338, 26);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Mostrar Senha");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('●');
				}
			}
		});
		chckbxNewCheckBox.setBounds(95, 291, 128, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}