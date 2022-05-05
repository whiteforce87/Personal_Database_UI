package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Service.AppService;
import model.Category;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateCategoryFrame extends JFrame {

	private JPanel contentPane;
	private UpdateCategoryListener listener;
	private JLabel preCatNameLabel;
	private JLabel newCatNameLabel;
	private JTextField preCatNameTxt;
	private JTextField newCatNameTxt;
	private JButton cancelButton;
	private JButton saveButton;
	private JLabel newCatIdLabel;
	private JTextField newCatIdTxt;
	private JLabel preCatIdLabel;
	private JTextField preCatIdTxt;


	/**
	 * Create the frame.
	 */
	AppService srv = new AppService();
	public UpdateCategoryFrame(UpdateCategoryListener listener) {
		this.listener = listener;
		initGUI();
	}
	private void initGUI() {
		setTitle("Update Category");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				do_this_windowOpened(e);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		preCatNameLabel = new JLabel("Previous Category Name:");
		preCatNameLabel.setBounds(35, 39, 178, 16);
		contentPane.add(preCatNameLabel);
		
		newCatNameLabel = new JLabel("New Category Name:");
		newCatNameLabel.setBounds(35, 127, 178, 16);
		contentPane.add(newCatNameLabel);
		
		preCatNameTxt = new JTextField();
		
		preCatNameTxt.setBounds(232, 34, 130, 26);
		contentPane.add(preCatNameTxt);
		preCatNameTxt.setColumns(10);
		preCatNameTxt.setEditable(false);
		
		newCatNameTxt = new JTextField();
		newCatNameTxt.setBounds(232, 122, 130, 26);
		contentPane.add(newCatNameTxt);
		newCatNameTxt.setColumns(10);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_cancelButton_actionPerformed(e);
			}
		});
		cancelButton.setBounds(63, 223, 117, 29);
		contentPane.add(cancelButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_saveButton_actionPerformed(e);
			}
		});
		saveButton.setBounds(265, 223, 117, 29);
		contentPane.add(saveButton);
		
		newCatIdLabel = new JLabel("New Category Id Number:");
		newCatIdLabel.setBounds(35, 172, 178, 16);
		contentPane.add(newCatIdLabel);
		
		newCatIdTxt = new JTextField();
		newCatIdTxt.setBounds(232, 172, 130, 26);
		contentPane.add(newCatIdTxt);
		newCatIdTxt.setColumns(10);
		
		preCatIdLabel = new JLabel("Previous Category Id Number:");
		preCatIdLabel.setBounds(35, 86, 201, 16);
		contentPane.add(preCatIdLabel);
		
		preCatIdTxt = new JTextField();
		preCatIdTxt.setBounds(232, 81, 130, 26);
		contentPane.add(preCatIdTxt);
		preCatIdTxt.setColumns(10);
		preCatIdTxt.setEditable(false);
		
	}
	
	protected void do_this_windowOpened(WindowEvent e) {
		preCatNameTxt.setText(MainFrame.selectedCategory);
		preCatIdTxt.setText(MainFrame.selectedCategoryId);
		
	}
	
	protected void do_cancelButton_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	protected void do_saveButton_actionPerformed(ActionEvent e) {
		Category newCat = new Category(Integer.valueOf(newCatIdTxt.getText()), newCatNameTxt.getText());
		
		srv.updateCategory(newCat);
		listener.categoryUpdate();
		this.dispose();
	}
	
	
}
