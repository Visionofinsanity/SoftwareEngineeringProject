package createGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;

public class MainMenu {

	private JFrame frame;
	private JTextField tfCap;
	private JTextField tfCourseExtension;
	private JTextField tfTime1;
	private JTextField tfTime2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel OpenGUI = new JPanel();
		frame.getContentPane().add(OpenGUI, "name_475040811766091");
		OpenGUI.setLayout(null);
		OpenGUI.setVisible(true);
		
		JPanel AddNewPrediction = new JPanel();
		frame.getContentPane().add(AddNewPrediction, "name_475040827574537");
		AddNewPrediction.setLayout(null);
		AddNewPrediction.setVisible(false);
		
		JPanel EditSections = new JPanel();
		frame.getContentPane().add(EditSections, "name_475040843275711");
		EditSections.setLayout(null);
		EditSections.setVisible(false);
		
		JPanel DisplayGUI = new JPanel();
		frame.getContentPane().add(DisplayGUI, "name_476082864910080");
		DisplayGUI.setLayout(null);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			AddNewPrediction.setVisible(true);
			OpenGUI.setVisible(false);
			}
		});
		btnNew.setBounds(100, 47, 89, 106);
		OpenGUI.add(btnNew);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayGUI.setVisible(true);
				OpenGUI.setVisible(false);
			}
		});
		btnOpen.setBounds(265, 50, 89, 100);
		OpenGUI.add(btnOpen);
		
		JRadioButton rdbtnField = new JRadioButton("Field");
		rdbtnField.setBounds(90, 33, 80, 23);
		AddNewPrediction.add(rdbtnField);
		
		JRadioButton rdbtnCourse = new JRadioButton("Course");
		rdbtnCourse.setBounds(90, 119, 70, 23);
		AddNewPrediction.add(rdbtnCourse);
		
		JComboBox cbCourseInitials = new JComboBox();
		cbCourseInitials.setModel(new DefaultComboBoxModel(new String[] {"CS"}));
		cbCourseInitials.setBounds(90, 149, 70, 20);
		AddNewPrediction.add(cbCourseInitials);
		
		JComboBox cbFieldInitials = new JComboBox();
		cbFieldInitials.setModel(new DefaultComboBoxModel(new String[] {"CS"}));
		cbFieldInitials.setBounds(90, 63, 70, 20);
		AddNewPrediction.add(cbFieldInitials);
		
		JButton btnAddPrediction = new JButton("Add");
		btnAddPrediction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewPrediction.setVisible(false);
				DisplayGUI.setVisible(true);
			}
		});
		btnAddPrediction.setBounds(132, 227, 89, 23);
		AddNewPrediction.add(btnAddPrediction);
		
		JButton btnNewPrediction = new JButton("New");
		btnNewPrediction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditSections.setVisible(true);
				AddNewPrediction.setVisible(false);
				
			}
		});
		btnNewPrediction.setBounds(277, 227, 89, 23);
		AddNewPrediction.add(btnNewPrediction);
		
		tfCourseExtension = new JTextField();
		tfCourseExtension.setBounds(170, 149, 35, 20);
		AddNewPrediction.add(tfCourseExtension);
		tfCourseExtension.setColumns(10);
				
		JComboBox cbSectionNumber = new JComboBox();
		cbSectionNumber.setEditable(true);
		cbSectionNumber.setBounds(104, 35, 83, 20);
		EditSections.add(cbSectionNumber);
		
		JRadioButton rbClassroom = new JRadioButton("Classroom");
		rbClassroom.setBounds(104, 62, 121, 23);
		EditSections.add(rbClassroom);
		
		JRadioButton rbWeb = new JRadioButton("Web");
		rbWeb.setBounds(282, 62, 53, 23);
		EditSections.add(rbWeb);
		
		tfCap = new JTextField();
		tfCap.setBounds(282, 35, 50, 20);
		EditSections.add(tfCap);
		tfCap.setColumns(10);
		
		JLabel lblCap = new JLabel("Cap:");
		lblCap.setBounds(237, 38, 23, 14);
		EditSections.add(lblCap);
		
		tfTime1 = new JTextField();
		tfTime1.setBounds(104, 106, 86, 20);
		EditSections.add(tfTime1);
		tfTime1.setColumns(10);
		
		tfTime2 = new JTextField();
		tfTime2.setBounds(249, 106, 86, 20);
		EditSections.add(tfTime2);
		tfTime2.setColumns(10);
		
		JLabel label = new JLabel("-");
		label.setBounds(215, 109, 10, 14);
		EditSections.add(label);
		
		JButton btnAddSection = new JButton("Add Section");
		btnAddSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewPrediction.setVisible(true);
				EditSections.setVisible(false);
			}
		});
		btnAddSection.setBounds(149, 152, 151, 23);
		EditSections.add(btnAddSection);
		
		JButton btnSaveSection = new JButton("Save");
		btnSaveSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayGUI.setVisible(true);
				EditSections.setVisible(false);
			}
		});
		btnSaveSection.setBounds(0, 238, 89, 23);
		EditSections.add(btnSaveSection);
		
		JButton btnCancelSection = new JButton("Cancel");
		btnCancelSection.setBounds(98, 238, 89, 23);
		EditSections.add(btnCancelSection);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(242, 25, 17, 209);
		DisplayGUI.add(scrollBar);
		
		JTextArea txtrCs = new JTextArea();
		txtrCs.setText("CS-123 Computer Science 1\t\t\t\tPred#:100\r\n\tSection 1 CAP: 40\tTime/Web\r\n\tSection 2 CAP: 35\tTime/Web");
		txtrCs.setBounds(21, 25, 238, 209);
		DisplayGUI.add(txtrCs);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayGUI.setVisible(false);
				EditSections.setVisible(true);
			}
		});
		btnEdit.setBounds(316, 43, 89, 23);
		DisplayGUI.add(btnEdit);
	}
}