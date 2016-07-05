package cn.edu.nuc.onlinestore.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import cn.edu.nuc.onlinestore.utils.MyTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserStore extends JFrame {
	private boolean DEBUG=false;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserStore frame = new UserStore();
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
	public UserStore(){
		setTitle("中北在线商场--当前用户:李四");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 696, 341);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		//自定义的tableModel
		JTable table = new JTable( new MyTableModel() );
		
		JScrollPane pane = new JScrollPane( table );
		
		panel.add(pane);
		contentPane.add(panel);
		
		JButton button_2 = new JButton("查看商品详细信息(或双单击商品列)");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserGoods d = new UserGoods();
				d.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				d.setVisible(true);
			}
		});
		button_2.setBounds(407, 45, 299, 23);
		contentPane.add(button_2);
		//登录
		JButton button_login=new JButton("登录");
		button_login.setBounds(500, 6, 73, 23);
		button_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserLogin ul=new UserLogin();
				ul.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				ul.setVisible(true);
			}
		});
		contentPane.add(button_login);
		//注册新用户
		JButton button_regist=new JButton("注册");
		button_regist.setBounds(573, 6, 73, 23);
		button_regist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserRegist ur=new UserRegist();
				ur.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				ur.setVisible(true);
			}
		});
		contentPane.add(button_regist);
		//退出登录
		JButton button_exit = new JButton("注销");
		button_exit.setBounds(633, 6, 73, 23);
		button_exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				//注销之后  本次服务结束  关闭和服务端的连接
				
				//frame.setVisible(true);
				int exit=JOptionPane.showConfirmDialog(null, "确定退出程序？", "退出程序", JOptionPane.OK_CANCEL_OPTION);
				//System.out.println(exit);
				if(exit==0){
					System.exit(0);
				}
			}
		});
		contentPane.add(button_exit);
		//按商品名称来查找商品
		JLabel lblid = new JLabel("商品名称:");
		lblid.setBounds(10, 46, 65, 15);
		contentPane.add(lblid);
		
		textField = new JTextField();
		textField.setBounds(77, 46, 104, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button_goodsSearch = new JButton("搜索");
		button_goodsSearch.setBounds(188, 45, 60, 23);
		contentPane.add(button_goodsSearch);
		//查询结束
		
		JLabel label = new JLabel("购物车:");
		label.setBounds(10, 10, 90, 15);
		contentPane.add(label);
		//购物车中物品的数量
		JLabel label_cart_goodsNum=new JLabel("8 件商品");
		label_cart_goodsNum.setBounds(55,10,90,15);
		contentPane.add(label_cart_goodsNum);
		//购物车按钮
		JButton button_viewCart = new JButton("查看购物车");
		button_viewCart.setEnabled(false);
		button_viewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserCartFrame cf = new UserCartFrame();
				cf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				cf.setVisible(true);
				
			}
		});
		button_viewCart.setBounds(116, 6, 110, 23);
		contentPane.add(button_viewCart);
	}
}

