package cn.edu.nuc.onlinestore.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.nuc.onlinestore.action.ClientLoginAction;
import cn.edu.nuc.onlinestore.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setTitle("中北线在商场-登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名:");
		label.setBounds(87, 146, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密  码:");
		label_1.setBounds(87, 185, 54, 15);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(151, 146, 197, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 185, 197, 21);
		contentPane.add(passwordField);
		
		JButton button_regist=new JButton("注册");
		button_regist.setBounds(100, 216, 80, 23);
		contentPane.add(button_regist);
		
		JButton button = new JButton("登录");
		button.setBounds(255, 216, 93, 23);
		button.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    User user=new User();
				String userName=textField.getText();
				String password=passwordField.getPassword().toString();
				if("".equals(userName) || "".equals(password) ){
					JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
				}
				user.setName(userName);
				user.setPass(userName);
                //连接服务器   （把要登录的用户名和密码序列化   交给服务端来验证）
				new ClientLoginAction(user).start();
				//如果正确     提示登录成功   购物车变为可用
				
				//如果不正确 提示登录失败  购物车不可用
			}
		});
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon(UserLogin.class.getResource("/image/a.jpg")));
		lblNewLabel.setBounds(208, 54, 86, 82);
		contentPane.add(lblNewLabel);
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
