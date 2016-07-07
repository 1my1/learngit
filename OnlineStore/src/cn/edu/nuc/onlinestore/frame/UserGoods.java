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

import cn.edu.nuc.onlinestore.action.ClientLoginAction;
import cn.edu.nuc.onlinestore.model.Cart;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
/*
 * 商品详情
 */
public class UserGoods extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private User user=null;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGoods frame = new UserGoods();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public UserGoods(final Goods g,final User u,final JLabel label_cartSize) {
		//得到用户
		this.user=u;
		setTitle("商品详情");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品名称:");
		label.setBounds(23, 57, 66, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("单价:");
		label_1.setBounds(35, 100, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("单位:元");
		label_2.setBounds(321, 100, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_4 = new JLabel("简介:");
		label_4.setBounds(35, 141, 44, 46);
		contentPane.add(label_4);
		
		
		
		final JLabel lblNewLabel = new JLabel(g.getName());
		lblNewLabel.setBounds(99, 57, 162, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label_3 = new JLabel(g.getPrice()+"");
		label_3.setBounds(99, 100, 54, 15);
		contentPane.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("纯天然矿泉水");
		lblNewLabel_1.setBounds(99, 141, 251, 46);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText("2");
		textField.setBounds(99, 244, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("购买数量:");
		lblNewLabel_2.setBounds(35, 247, 71, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_5 = new JLabel("库存: "+g.getNum());
		label_5.setBounds(99, 273, 135, 15);
		contentPane.add(label_5);
		
		JButton button = new JButton("加入购物车");
		button.setBounds(175, 243, 126, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(user);
				if(u==null){
					JOptionPane.showMessageDialog(null, "先登录");
					return;
				}else{
					Cart cart=null;
					if(user.getCart()==null){
						cart=new Cart();
					}else{
						cart=user.getCart();
					}
					Integer quantity=Integer.parseInt(textField.getText());
					if(quantity>g.getNum()){
						JOptionPane.showMessageDialog(null, "数量超出");
						return;
					}
					cart.add(g, quantity);
					user.setCart(cart);
					String tip=save(user);
					if(tip.equals("success")){
						JOptionPane.showMessageDialog(null, "添加成功！");
						label_cartSize.setText(cart.getMaps().size()+"");
						return;
					}else if(tip.equals("error")){
						JOptionPane.showMessageDialog(null, "添加失败！");
						return;
					}
				}
			}
		});
		contentPane.add(button);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				((UserGoods)e.getSource()).setVisible(false);
			}
		});
	}
	public String save(User u){
		u=this.user;
		Message<User> message=new Message<>();
		message.setMessage("save");
		message.setObj(u);
		ClientLoginAction clientLoginAction=new ClientLoginAction(message);
		clientLoginAction.send();
		Result<User> result = clientLoginAction.get();
		return result.getMsg();
		
	}
}
