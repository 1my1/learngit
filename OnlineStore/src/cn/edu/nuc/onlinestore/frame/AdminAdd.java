package cn.edu.nuc.onlinestore.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.nuc.onlinestore.action.AddGoodsService;
import cn.edu.nuc.onlinestore.model.Goods;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
/*
 * 管理员添加商品
 */
public class AdminAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;


	/**
	 * Create the frame.
	 */
	public AdminAdd(final DefaultTableModel model,final AddGoodsService ags) {
		setTitle("添加商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品ID:");
		label.setBounds(35, 57, 67, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(99, 54, 269, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("商品名称:");
		label_1.setBounds(35, 100, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 97, 212, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("单位:元");
		label_2.setBounds(321, 100, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("商品价格:");
		label_3.setBounds(35, 143, 54, 15);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(99, 140, 269, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_4 = new JLabel("库存:");
		label_4.setBounds(35, 188, 54, 15);
		contentPane.add(label_4);
		
		textArea = new JTextArea();
		textArea.setBounds(99, 184, 269, 103);
		contentPane.add(textArea);
		
		JButton button = new JButton("确定添加");
		button.setBounds(275, 310, 93, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String gid=textField.getText();
				String gName=textField_1.getText();
				String gPrice=textField_2.getText();
				String gNum=textArea.getText();
				if("".equals(gid) || "".equals(gName) || "".equals(gPrice) || "".equals(gNum)){
					JOptionPane.showMessageDialog(null, "12312312");
					return;
				}
				Object[] obj={gid,gName,gPrice,gNum};
				Goods goods=new Goods();
				goods.setId(gid);
				goods.setName(gName);
				goods.setPrice(Double.parseDouble(gPrice));
				goods.setNum(Integer.parseInt(gNum));
				ags.addGood(goods);
				model.addRow(obj);
				AdminAdd.this.setVisible(false);
				
				
			}
		});
		contentPane.add(button);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				((AdminAdd)e.getSource()).setVisible(false);
			}
		});
	}
}
