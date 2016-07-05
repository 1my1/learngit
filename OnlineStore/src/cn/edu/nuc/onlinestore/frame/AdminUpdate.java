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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AdminUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private AddGoodsService ags;

	/**
	 * Create the frame.
	 */
	public AdminUpdate(final DefaultTableModel model,final Goods good) {
		setTitle("修改商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品ID:");
		label.setBounds(23, 57, 66, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(99, 54, 269, 21);
		textField.setText(good.getId());
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JLabel label_1 = new JLabel("商品名称:");
		label_1.setBounds(35, 100, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 97, 212, 21);
		textField_1.setText(good.getName());
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JLabel label_2 = new JLabel("单位:元");
		label_2.setBounds(321, 100, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("商品价格:");
		label_3.setBounds(35, 143, 54, 15);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(99, 140, 212, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_4 = new JLabel("库存:");
		label_4.setBounds(35, 188, 54, 15);
		contentPane.add(label_4);
		
		textArea = new JTextArea();
		textArea.setBounds(99, 184, 269, 103);
		contentPane.add(textArea);
		
		JButton button = new JButton("确定修改");
		button.setBounds(275, 310, 93, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Goods g=good;
				ags=new AddGoodsService();
				String gId=textField.getText();
				String gName=textField_1.getText();
				String gPrice=textField_2.getText();
				String gNum=textArea.getText();
				System.out.println(gId);
				System.out.println(gName);
				System.out.println(gPrice);
				System.out.println(gNum);
				System.out.println(good);
				g.setId(gId);
				g.setName(gName);
				g.setNum(Integer.parseInt(gNum));
				g.setPrice(Double.parseDouble(gPrice));
			    ags.modifyGood(g);
			    Object[] obj={gId,gName,gPrice,gNum};
			    model.addRow(obj);
			}
		});
		contentPane.add(button);
		
		JLabel label_5 = new JLabel(">=0");
		label_5.setBounds(321, 143, 43, 15);
		contentPane.add(label_5);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				((AdminUpdate)e.getSource()).setVisible(false);
			}
		});
	}
}
