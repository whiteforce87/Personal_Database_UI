package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import Dao.ConnectionManager;
import Service.AppService;
import model.Category;
import model.Person;

public class MainFrame extends JFrame implements AddPersonListener, UpdatePersonListener, AddCategoryListener,
		UpdateCategoryListener, DeletePersonListener, PersonUpdateListener {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JList list;
	private JTable table;

	protected static int selectedRowId;
	protected static String selectedCategory;
	protected static String selectedCategoryId;
	protected static String selectedRowid;
	protected static String selectedAddress;
	protected static String selectedDate;
	protected static String selectedCity;
	protected static String selectedEmail;
	protected static String selectedHomephone;
	protected static String selectedLastname;
	protected static String selectedName;
	protected static String selectedWorkphone;

	String[] cols = { "id", "name", "lastname", "workphone", "category_id", "address", "homephone", "email", "city",
			"birthdate" };

	private AppService appService = new AppService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("FATIH - Addressbook DB Administration");
		initGUI();
		refreshCategories();
		showTable();
	}

	List<Category> categories = new ArrayList<Category>();
	List<Person> people = new ArrayList<Person>();

	private JButton deletePersonBtn;

	public void refreshCategories() {

		categories = appService.getAllCategories();

		DefaultComboBoxModel<Category> model = new DefaultComboBoxModel<Category>(
				categories.toArray(new Category[categories.size()]));
		list.setModel(model);
	}

	public void showTable() {

		people = appService.getAllPeople();
		Object[][] data = new Object[people.size()][];

		for (int i = 0; i < people.size(); i++) {
			Person current = people.get(i);
			data[i] = new Object[] { current.getId(), current.getName(), current.getLastname(), current.getWorkphone(),
					current.getCategory_id(), current.getAddress(), current.getHomephone(), current.getEmail(),
					current.getCity(), current.getBirthdate() };
		}

		DefaultTableModel model = new DefaultTableModel(data, cols);
		table.setModel(model);
		/*
		 * We can also choose each value on the row by allowing selection
		 * table.setColumnSelectionAllowed(true); table.setRowSelectionAllowed(true);
		 */
	}

	private void initGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		btnNewButton = new JButton("New Category");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("New Person");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_1_actionPerformed(e);
			}
		});

		panel.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Update Category");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_2_actionPerformed(e);
			}
		});
		panel.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Update Person");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_3_actionPerformed(e);
			}
		});
		panel.add(btnNewButton_3);

		deletePersonBtn = new JButton("Delete Person");
		deletePersonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_deletePersonBtn_actionPerformed(e);
			}
		});
		panel.add(deletePersonBtn);

		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				do_list_valueChanged(e);
			}
		});
		scrollPane.setViewportView(list);

		scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

	}

	public void refreshTable() {

		people = new ArrayList<Person>();

		try (Connection connection = ConnectionManager.getConnection();) {

			String query = "select * from person";

			PreparedStatement psmt = connection.prepareStatement(query);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"),
						rs.getString("workphone"), rs.getInt("category_id"), rs.getString("address"),
						rs.getString("homephone"), rs.getString("email"), rs.getString("city"),
						rs.getDate("birthdate"));
				people.add(person);

			}
			people = appService.getAllPeople();

			Object[][] data = new Object[people.size()][];

			for (int i = 0; i < people.size(); i++) {
				Person current = people.get(i);
				data[i] = new Object[] { current.getId(), current.getName(), current.getLastname(),
						current.getWorkphone(), current.getCategory_id(), current.getAddress(), current.getHomephone(),
						current.getEmail(), current.getCity(), current.getBirthdate() };
			}

			DefaultTableModel model = new DefaultTableModel(data, cols);
			table.setModel(model);
			/*
			 * We can also choose each value on the row by allowing selection
			 * table.setColumnSelectionAllowed(true); table.setRowSelectionAllowed(true);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshCategory() {

		categories = new ArrayList<Category>();

		try (Connection connection = ConnectionManager.getConnection();) {

			String query = "select * from category";

			PreparedStatement psmt = connection.prepareStatement(query);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				Category category = new Category(rs.getInt("id"), rs.getString("name"));
				categories.add(category);

			}
			categories = appService.getAllCategories();

			Object[][] data = new Object[categories.size()][];

			for (int i = 0; i < categories.size(); i++) {
				Category current = categories.get(i);
				data[i] = new Object[] { current.getId(), current.getName() };
			}

			DefaultComboBoxModel<Category> model = new DefaultComboBoxModel<Category>(
					categories.toArray(new Category[categories.size()]));
			list.setModel(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void do_list_valueChanged(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting()) {

			for (int index = 0; index < categories.size(); index++) {

				if (list.getSelectedIndex() == index) {

					people = new ArrayList<Person>();

					try (Connection connection = ConnectionManager.getConnection();) {

						String number = String.valueOf(index + 1);

						String query = "select * from person where category_id =" + number;

						PreparedStatement psmt = connection.prepareStatement(query);

						ResultSet rs = psmt.executeQuery();

						while (rs.next()) {
							Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"),
									rs.getString("workphone"), rs.getInt("category_id"), rs.getString("address"),
									rs.getString("homephone"), rs.getString("email"), rs.getString("city"),
									rs.getDate("birthdate"));
							people.add(person);

						}

						Object[][] data = new Object[people.size()][];

						for (int i = 0; i < people.size(); i++) {
							Person current = people.get(i);
							data[i] = new Object[] { current.getId(), current.getName(), current.getLastname(),
									current.getWorkphone(), current.getCategory_id(), current.getAddress(),
									current.getHomephone(), current.getEmail(), current.getCity(),
									current.getBirthdate() };
						}

						DefaultTableModel model = new DefaultTableModel(data, cols);
						table.setModel(model);

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		}
	}

	@Override
	public void personSaved() {

		if (list.getSelectedIndex() == -1) {
			people = appService.getAllPeople();
			refreshTable();

		} else {
			people = appService.getContactsByCategoryId(((Category) list.getSelectedValue()).getId());
			refreshTable();

		}

	}

	@Override
	public void categoryAdded() {

		categories = appService.getAllCategories();
		refreshCategory();

	}

	@Override
	public void categoryUpdate() {

		categories = appService.getAllCategories();
		refreshCategory();
	}

	@Override
	public void personUpdate() {

		people = appService.getAllPeople();
		refreshTable();

	}

	@Override
	public void personDelete() {

		people = appService.getAllPeople();
		refreshTable();

	}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {

		AddCategoryFrame newCategoryFrame = new AddCategoryFrame(this);
		newCategoryFrame.setVisible(true);

	}

	protected void do_btnNewButton_1_actionPerformed(ActionEvent e) {

		AddPersonFrame newPersonFrame = new AddPersonFrame(this);
		newPersonFrame.setVisible(true);
	}

	protected void do_btnNewButton_2_actionPerformed(ActionEvent e) {

		if (list.getSelectedIndex() >= 0 && list.getSelectedIndex() <= categories.size()) {

			selectedCategory = list.getSelectedValue().toString();

			selectedCategoryId = String.valueOf(list.getSelectedIndex() + 1);

			UpdateCategoryFrame newCategoryUpdateFrame = new UpdateCategoryFrame(this);
			newCategoryUpdateFrame.setVisible(true);
		}
	}

	protected void do_btnNewButton_3_actionPerformed(ActionEvent e) {

		selectedAddress = people.get(table.getSelectedRow()).getAddress();
		selectedDate = people.get(table.getSelectedRow()).getBirthdate().toString();
		selectedCity = people.get(table.getSelectedRow()).getCity();
		selectedEmail = people.get(table.getSelectedRow()).getEmail();
		selectedHomephone = people.get(table.getSelectedRow()).getHomephone();
		selectedLastname = people.get(table.getSelectedRow()).getLastname();
		selectedName = people.get(table.getSelectedRow()).getName();
		selectedWorkphone = people.get(table.getSelectedRow()).getWorkphone();
		selectedCategoryId = String.valueOf(people.get(table.getSelectedRow()).getCategory_id());
		selectedRowid = String.valueOf(people.get(table.getSelectedRow()).getId());

		UpdatePersonFrame newUpdateFrame = new UpdatePersonFrame(this);
		newUpdateFrame.setVisible(true);
	}

	protected void do_deletePersonBtn_actionPerformed(ActionEvent e) {
		if (table.isCellSelected(table.getSelectedRow(), table.getSelectedColumn())) {
			selectedRowId = people.get(table.getSelectedRow()).getId();
			DeleteValidationFrame validFrm = new DeleteValidationFrame(this);
			validFrm.setVisible(true);

		}

	}
}
