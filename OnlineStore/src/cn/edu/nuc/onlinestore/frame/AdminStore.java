package cn.edu.nuc.onlinestore.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import cn.edu.nuc.onlinestore.utils.MyTableModel;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminStore extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminStore frame = new AdminStore();
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
	public AdminStore() {
		setTitle("中北商场后台管理系统--当前用户:张三");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 696, 341);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		
		contentPane.add(panel);
		MyTableModel mtm=new MyTableModel();
		JButton button = new JButton("添加商品");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAdd add = new AdminAdd();
				add.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				add.setVisible(true);
				System.out.println(mtm.getColumnCount());
			}
		});
		button.setBounds(386, 45, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("修改商品");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUpdate u = new AdminUpdate();
				u.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				u.setVisible(true);
			}
		});
		button_1.setBounds(489, 45, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("删除选中商品");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//得到当前选中商品项
				new MyTableModel();
				JOptionPane.showConfirmDialog(null, "确定要删除\"水杯\"么?" );
			}
		});
		button_2.setBounds(587, 45, 119, 23);
		contentPane.add(button_2);
		
        JTable table = new JTable( mtm );
		JScrollPane pane = new JScrollPane( table );
		panel.add(pane);
		
		JButton button_3 = new JButton("退出登录");
		button_3.setBounds(613, 6, 93, 23);
		contentPane.add(button_3);
		
		JLabel label = new JLabel("当前在线用户数: 5");
		label.setBounds(10, 10, 162, 15);
		contentPane.add(label);
		
		JLabel lblid = new JLabel("商品编号:");
		lblid.setBounds(10, 53, 54, 15);
		contentPane.add(lblid);
		
		textField = new JTextField();
		textField.setBounds(68, 50, 104, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button_4 = new JButton("搜索");
		button_4.setBounds(180, 49, 93, 23);
		contentPane.add(button_4);
	}
}