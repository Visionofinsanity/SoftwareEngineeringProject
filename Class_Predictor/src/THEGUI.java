

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import DataStructures.Catalog;
import DataStructures.Course;
import GetInput.CatalogConstructor;
import GetInput.DataCompiler;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JList;

public class THEGUI {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private Catalog cat;
	private Predictor pre;
	private String STUDENT_FILE_PATH = "/Portales And Ruidoso Students.xlsx";
	private String COURSES_FILE_PATH = "/Portales And Ruidoso Courses.xlsx";
	private String PREDICT_PATH = "/Students/students.csv";
	private DataCompiler compiler;
	private Course[] courses;
	private int[] predNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					THEGUI window = new THEGUI();
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
	public THEGUI() {
		cat = CatalogConstructor.constructCatalog("/ENMUcatalog.txt");
		pre = new Predictor(PREDICT_PATH, cat);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Class Prediction");
		frame.setBounds(100, 100, 450, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panelPrediction = new JPanel();
		final JPanel panelMainMenu = new JPanel();
		final JPanel panelEdit = new JPanel();
		
		JScrollPane spPrediction = new JScrollPane( 
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spPrediction.setPreferredSize(new Dimension(400, 600));
		
		
		frame.getContentPane().add(spPrediction, "name_243515087660730");
		panelPrediction.setLayout(null);
		
		
		spPrediction.setVisible(false);
		
		
		
		
		frame.getContentPane().add(panelEdit, "name_243515100347004");
		panelEdit.setLayout(null);
		JButton btnAdd = new JButton("Add");
		
		JRadioButton rbField = new JRadioButton("Field");
		rbField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbField.isSelected())
					btnAdd.setEnabled(true);
			}
		});
		buttonGroup.add(rbField);
		rbField.setBounds(54, 44, 109, 23);
		rbField.setSelected(true);
		panelEdit.add(rbField);
		
		JComboBox cbField = new JComboBox(cat.getAllFieldsStrings());
		cbField.setBounds(rbField.getX() + 30, rbField.getY() + rbField.getHeight() + 10, 100, 20);
		panelEdit.add(cbField);
		
		/*JList lsField = new JList();
		lsField.setBounds(rbField.getX() + 30, rbField.getY() + rbField.getHeight() + 10, 134, 90);
		panelEdit.add(lsField);*/
		
		JRadioButton rbCourse = new JRadioButton("Course");
		rbCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbCourse.isSelected())
					btnAdd.setEnabled(false);;
			}
		});
		buttonGroup.add(rbCourse);
		rbCourse.setBounds(54, 137, 109, 23);
		panelEdit.add(rbCourse);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(101, 167, 61, 20);
		panelEdit.add(comboBox);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelMainMenu.setVisible(true);
			panelEdit.setVisible(false);
			}
		});
		btnCancel.setBounds(10, 227, 89, 23);
		panelEdit.add(btnCancel);
		
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEdit.setVisible(false);
				spPrediction.setVisible(true);
				compiler = new DataCompiler(STUDENT_FILE_PATH, COURSES_FILE_PATH, cat);
				compiler.readStudents();
				compiler.readCourses();
				compiler.writeStudents();
				pre.makeStatistics();
				if(rbField.isSelected()) {
					courses = cat.getField((String) cbField.getSelectedItem()).getAllCourses();
					predNum = pre.predict(cat.getField((String) cbField.getSelectedItem()));
				}else if (rbCourse.isSelected()) {
					//TODO if Course selected
				}
				
				panelPrediction.removeAll();
				JTextArea[] lsPredict = new JTextArea[courses.length];
				JButton[] btnEdit = new JButton[courses.length];
				for(int i = 0; i < courses.length; i++) {
					lsPredict[i] = new JTextArea();
					lsPredict[i].setBounds(10, 10 + (60 * i), 300, 50);
					lsPredict[i].setText(courses[i].field.abrName + " " + courses[i].number +
							": " + courses[i].name + "\nPredicted Students: " + predNum[i]);
					panelPrediction.add(lsPredict[i]);
					
					btnEdit[i] = new JButton("Edit");
					btnEdit[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						panelEdit.setVisible(true);
						panelPrediction.setVisible(false);
						}
					});
					btnEdit[i].setBounds(lsPredict[i].getX() + lsPredict[i].getWidth() + 10,
							lsPredict[i].getY(), 75, 25);
					btnEdit[i].setEnabled(false);
					panelPrediction.add(btnEdit[i]);
				}
				
				spPrediction.setViewportView(panelPrediction);
				spPrediction.setPreferredSize(new Dimension(500, 1000));
				
				
				
			}
		});
		
		btnAdd.setBounds(335, 227, 89, 23);
		panelEdit.add(btnAdd);
		
		
		panelEdit.setVisible(false);
		
		
		
		
		final JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, "name_243515111034827");
		panel_3.setLayout(null);
		panel_3.setVisible(false);
		
		
		
		
		
		
		frame.getContentPane().add(panelMainMenu, "name_243515123324435");
		panelMainMenu.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		//menuBar.setBounds(40, 40, 434, 21);
		frame.setJMenuBar(menuBar);;
		
		
		
		
		
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelEdit.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNew);
		panelMainMenu.setVisible(true);
	}
}
