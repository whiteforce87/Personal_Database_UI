package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Service.AppService;
import model.Category;
import model.Person;
import java.awt.event.WindowAdapter;

public class UpdatePersonFrame extends JFrame implements PersonUpdateListener {

	private JPanel contentPane;
	private JLabel nameLabel;
	private JLabel lastnameLabel;
	private JLabel workphoneLabel;
	private JLabel categoryidLabel;
	private JLabel addressLabel;
	private JLabel homephoneLabel;
	private JLabel emailLabel;
	private JLabel cityLabel;
	private JLabel birthdateLabel;
	private JButton cancelBtn;
	private JButton saveBtn;
	private JTextField nameTxtField;
	private JTextField lastnameTxtFeild;
	private JTextField workphoneTxtFeild;
	private JTextField categoryidTxtField;
	private JTextField addressTxtField;
	private JTextField homephoneTxtField;
	private JTextField emailTxtField;
	private JTextField cityTxtFeield;
	public JTextField birthdateTxtField;
	private JComboBox cmbCategory;
	private PersonUpdateListener listener;

	/**
	 * Create the frame.
	 */
	AppService srv = new AppService();
	List<Category> categories = new ArrayList<Category>();
	private JLabel idTxtLabel;
	private JTextField idTxtField;

	public UpdatePersonFrame(PersonUpdateListener listener) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				do_this_windowOpened(e);
			}
		});
		setTitle("Update Person");
		this.listener = listener;
		initGUI();
		categories = srv.getAllCategories();
		DefaultComboBoxModel<Category> model = new DefaultComboBoxModel<Category>(
				categories.toArray(new Category[categories.size()]));
		cmbCategory.setModel(model);

	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(20, 62, 61, 16);
		contentPane.add(nameLabel);

		lastnameLabel = new JLabel("Last Name:");
		lastnameLabel.setBounds(20, 100, 77, 16);
		contentPane.add(lastnameLabel);

		workphoneLabel = new JLabel("Workphone:");
		workphoneLabel.setBounds(20, 133, 77, 16);
		contentPane.add(workphoneLabel);

		categoryidLabel = new JLabel("Category Name:");
		categoryidLabel.setBounds(20, 173, 107, 16);
		contentPane.add(categoryidLabel);

		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(20, 212, 61, 16);
		contentPane.add(addressLabel);

		homephoneLabel = new JLabel("Homephone:");
		homephoneLabel.setBounds(20, 248, 100, 16);
		contentPane.add(homephoneLabel);

		emailLabel = new JLabel("Email:");
		emailLabel.setBounds(20, 287, 61, 16);
		contentPane.add(emailLabel);

		cityLabel = new JLabel("City:");
		cityLabel.setBounds(20, 322, 61, 16);
		contentPane.add(cityLabel);

		birthdateLabel = new JLabel("BirthDate:");
		birthdateLabel.setBounds(20, 355, 77, 16);
		contentPane.add(birthdateLabel);

		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_cancelBtn_actionPerformed(e);
			}
		});
		cancelBtn.setBounds(25, 408, 117, 29);
		contentPane.add(cancelBtn);

		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_saveBtn_actionPerformed(e);
			}
		});
		saveBtn.setBounds(174, 408, 117, 29);
		contentPane.add(saveBtn);

		nameTxtField = new JTextField();
		nameTxtField.setColumns(10);
		nameTxtField.setBounds(132, 57, 159, 26);
		contentPane.add(nameTxtField);

		lastnameTxtFeild = new JTextField();
		lastnameTxtFeild.setColumns(10);
		lastnameTxtFeild.setBounds(132, 95, 159, 26);
		contentPane.add(lastnameTxtFeild);

		workphoneTxtFeild = new JTextField();
		workphoneTxtFeild.setColumns(10);
		workphoneTxtFeild.setBounds(132, 133, 159, 26);
		contentPane.add(workphoneTxtFeild);

		// categoryidTxtField = new JTextField();
		// categoryidTxtField.setColumns(10);
		// categoryidTxtField.setBounds(132, 173, 159, 26);
		// contentPane.add(categoryidTxtField);

		cmbCategory = new JComboBox();
		cmbCategory.setBounds(132, 173, 159, 26);
		contentPane.add(cmbCategory);

		addressTxtField = new JTextField();
		addressTxtField.setColumns(10);
		addressTxtField.setBounds(132, 212, 159, 26);
		contentPane.add(addressTxtField);

		homephoneTxtField = new JTextField();
		homephoneTxtField.setColumns(10);
		homephoneTxtField.setBounds(132, 248, 159, 26);
		contentPane.add(homephoneTxtField);

		emailTxtField = new JTextField();
		emailTxtField.setColumns(10);
		emailTxtField.setBounds(132, 287, 159, 26);
		contentPane.add(emailTxtField);

		cityTxtFeield = new JTextField();
		cityTxtFeield.setColumns(10);
		cityTxtFeield.setBounds(132, 322, 159, 26);
		contentPane.add(cityTxtFeield);

		birthdateTxtField = new JTextField();
		birthdateTxtField.setColumns(10);
		birthdateTxtField.setBounds(132, 355, 159, 26);
		contentPane.add(birthdateTxtField);

		idTxtLabel = new JLabel("Id Number:");
		idTxtLabel.setBounds(20, 22, 83, 16);
		contentPane.add(idTxtLabel);

		idTxtField = new JTextField();
		idTxtField.setBounds(132, 17, 159, 26);
		contentPane.add(idTxtField);
		idTxtField.setColumns(10);
		idTxtField.setEditable(false);

	}

	protected void do_this_windowOpened(WindowEvent e) {

		idTxtField.setText(MainFrame.selectedRowid);
		//categoryidTxtField.setText(MainFrame.selectedCategoryId);
		cmbCategory.setSelectedItem(cmbCategory.getItemAt(Integer.valueOf(MainFrame.selectedCategoryId)-1));
		nameTxtField.setText(MainFrame.selectedName);
		lastnameTxtFeild.setText(MainFrame.selectedLastname);
		workphoneTxtFeild.setText(MainFrame.selectedWorkphone);
		addressTxtField.setText(MainFrame.selectedAddress);
		homephoneTxtField.setText(MainFrame.selectedHomephone);
		emailTxtField.setText(MainFrame.selectedEmail);
		cityTxtFeield.setText(MainFrame.selectedCity);
		birthdateTxtField.setText(MainFrame.selectedDate);

	}

	protected void do_saveBtn_actionPerformed(ActionEvent e) {

		int cat_id = ((Category) cmbCategory.getSelectedItem()).getId();

		Date date;
		try {
			date = new java.sql.Date(
					((java.util.Date) new SimpleDateFormat("yyy-MM-dd").parse(birthdateTxtField.getText())).getTime());

			Person p = new Person(Integer.valueOf(idTxtField.getText()), nameTxtField.getText(),
					lastnameTxtFeild.getText(), workphoneTxtFeild.getText(), cat_id, addressTxtField.getText(),
					homephoneTxtField.getText(), emailTxtField.getText(), cityTxtFeield.getText(), date);

			UpdateValidationFrame personUpdate = new UpdateValidationFrame((MainFrame) listener);
			personUpdate.setVisible(true);

			srv.UpdatePerson(p);
			listener.personUpdate();
			this.personUpdate();

		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		this.dispose();
	}

	protected void do_cancelBtn_actionPerformed(ActionEvent e) {

		this.dispose();
	}

	@Override
	public void personUpdate() {
		System.out.println("Person updated");
	}
}
