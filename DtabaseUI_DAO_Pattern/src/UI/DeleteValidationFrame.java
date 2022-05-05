package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Service.AppService;
import model.Person;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DeleteValidationFrame extends JFrame {

	private JPanel contentPane;
	private JLabel areYouSurelbl;
	private JButton cancelSureBtn;
	private JButton noBtn;
	private JButton yesBtn;
	private DeletePersonListener listener;
	private final JLabel label = new JLabel("New label");

	

	/**
	 * Create the frame.
	 */
	AppService srv = new AppService();
	public DeleteValidationFrame(DeletePersonListener listener) {
		this.listener = listener;
		initGUI();
	}
	private void initGUI() {
		setTitle("Select An Option");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 392, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		areYouSurelbl = new JLabel("Are You Sure?");
		areYouSurelbl.setBounds(152, 30, 104, 16);
		contentPane.add(areYouSurelbl);
		
		cancelSureBtn = new JButton("Cancel");
		cancelSureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_cancelSureBtn_actionPerformed(e);
			}
		});
		cancelSureBtn.setBounds(6, 82, 117, 29);
		contentPane.add(cancelSureBtn);
		
		noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_noBtn_actionPerformed(e);
			}
		});
		noBtn.setBounds(139, 82, 117, 29);
		contentPane.add(noBtn);
		
		yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_yesBtn_actionPerformed(e);
			}
		});
		yesBtn.setBounds(269, 82, 117, 29);
		contentPane.add(yesBtn);
		label.setBounds(108, -27, 98, 29);
		contentPane.add(label);
	}
	protected void do_cancelSureBtn_actionPerformed(ActionEvent e) {
		dispose();
	}
	protected void do_noBtn_actionPerformed(ActionEvent e) {
		dispose();
	}
	protected void do_yesBtn_actionPerformed(ActionEvent e) {
		
		Person p = new Person(MainFrame.selectedRowId);
		srv.deletePerson(p);
		listener.personDelete();
		dispose();
		System.out.println("1 person was deleted!");
		
		
	}
}
