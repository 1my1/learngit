package cn.edu.nuc.onlinestore.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.nuc.onlinestore.action.ClientLoginAction;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

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
	private User user;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the frame.
	 */
	public UserLogin(final JButton button_viewCart,final JFrame parent) {
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
		
		
		
		JButton button = new JButton("登录");
		button.setBounds(255, 216, 93, 23);
		button.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    User u=new User();
			    Message<User> message=new Message<User>();
				String userName=textField.getText();
				String password=new String(passwordField.getPassword());
				if("".equals(userName) || "".equals(password) ){
					JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
					return;
				}
				u.setName(userName);
				u.setPass(password);
				//封装要发送的消息
				message.setObj(u);
				message.setMessage("login");
                //连接服务器   （把要登录的用户名和密码序列化   交给服务端来验证）
				ClientLoginAction cla=new ClientLoginAction(message);
				cla.send();
				//接受服务端发过来的提示信息     如果正确     提示登录成功   购物车变为可用
				Result<User> result=cla.get();
				System.out.println(result.getMsg());
				if(!result.equals(null)){
					String tip=result.getMsg();
					User uu=result.getObj();
					setUser(uu);
					if(!"".equals(tip) && !"success".equals(tip)){
						JOptionPane.showMessageDialog(null, tip);
					}else{
						button_viewCart.setEnabled(true);
						parent.setTitle("中北在线商场--当前用户:"+getUser().getName());
						UserLogin.this.setVisible(false);
					}
				}
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
	public User getUser(){
		return user;
	}
	public void setUser(User u){
		this.user=u;
	}
}
