package br.com.prototipo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class MenuAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
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
	public MenuAdmin() {
		setTitle("Menu Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Disciplina:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 164, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 164, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Visualizar");
		btnNewButton.setBounds(184, 35, 109, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Novo");
		btnNewButton_1.setBounds(303, 35, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblMatria = new JLabel("Mat\u00E9ria:");
		lblMatria.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatria.setBounds(10, 67, 164, 14);
		contentPane.add(lblMatria);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 92, 164, 20);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("Visualizar");
		btnNewButton_2.setBounds(184, 91, 109, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Novo");
		btnNewButton_1_1.setBounds(303, 91, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblQuestes = new JLabel("Quest\u00F5es:");
		lblQuestes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuestes.setBounds(10, 123, 164, 14);
		contentPane.add(lblQuestes);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 148, 164, 20);
		contentPane.add(comboBox_2);
		
		JButton btnNewButton_3 = new JButton("Visualizar");
		btnNewButton_3.setBounds(184, 147, 109, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_1_2 = new JButton("Novo");
		btnNewButton_1_2.setBounds(303, 147, 89, 23);
		contentPane.add(btnNewButton_1_2);
	}
}
