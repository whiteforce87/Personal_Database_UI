
package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Service.AppService;
import model.Category;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddCategoryFrame extends JFrame {

	private JPanel contentPane;
	private AddCategoryListener listener;
	private JLabel categoryNameLabel;
	private JLabel idNumberLabel;
	private JTextField categoryNameTxtField;
	private JTextField idNumberTxtField;
	private JButton cancelCategoryBtn;
	private JButton addBtn;

	/**
	 * Create the frame.
	 */
	AppService srv = new AppService();
	public AddCategoryFrame(AddCategoryListener listener) {
		this.listener = listener;
		initGUI();
	}

	private void initGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				do_this_windowOpened(e);
			}
		});
		setTitle("New Category");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		categoryNameLabel = new JLabel("Category Name:");
		categoryNameLabel.setBounds(32, 38, 117, 16);
		contentPane.add(categoryNameLabel);

		idNumberLabel = new JLabel("Category Id Number:");
		idNumberLabel.setBounds(32, 85, 149, 16);
		contentPane.add(idNumberLabel);

		categoryNameTxtField = new JTextField();
		categoryNameTxtField.setBounds(184, 33, 130, 26);
		contentPane.add(categoryNameTxtField);
		categoryNameTxtField.setColumns(10);

		idNumberTxtField = new JTextField();
		idNumberTxtField.setColumns(10);
		idNumberTxtField.setBounds(184, 80, 130, 26);
		contentPane.add(idNumberTxtField);

		cancelCategoryBtn = new JButton("Cancel");
		cancelCategoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_cancelCategoryBtn_actionPerformed(e);
			}
		});
		cancelCategoryBtn.setBounds(64, 163, 117, 29);
		contentPane.add(cancelCategoryBtn);

		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_addBtn_actionPerformed(e);
			}
		});
		addBtn.setBounds(243, 163, 117, 29);
		contentPane.add(addBtn);
	}
	
	protected void do_this_windowOpened(WindowEvent e) {
		idNumberTxtField.setText(String.valueOf((srv.getAllCategories().size())+1));
		idNumberTxtField.setEditable(false);
	}

	protected void do_cancelCategoryBtn_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	protected void do_addBtn_actionPerformed(ActionEvent e) {
		
		Category newCat = new Category(Integer.valueOf(idNumberTxtField.getText()), categoryNameTxtField.getText());
		srv.addCategory(newCat);
		listener.categoryAdded();
		this.dispose();

	}
	
}
