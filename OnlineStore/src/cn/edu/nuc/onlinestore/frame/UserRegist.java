package cn.edu.nuc.onlinestore.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

public class UserRegist extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public UserRegist() {
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
		
		
		
		JButton button = new JButton("注册");
		button.setBounds(255, 216, 93, 23);
		button.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    User user=new User();
				String userName=textField.getText();
				String password=new String(passwordField.getPassword());
				if("".equals(userName) || "".equals(password) ){
					
					JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
					return;
				}
				user.setName(userName);
				user.setPass(userName);
                Message<User> message=new Message<User>(); 
                message.setMessage("regist");
                message.setObj(user);
                //连接服务端
                ClientLoginAction cla=new ClientLoginAction(message);
                //发送被验证消息
                cla.send();
                //服务端返回消息
                Result<User> result=cla.get();
                //处理返回消息
				if(!result.equals(null)){
					String tip=result.getMsg();
					user=result.getObj();
					if(!"".equals(tip) && "success".equals(tip)){
						JOptionPane.showMessageDialog(null, tip);
					}else if(!"".equals(tip)&& "exist".equals(tip)){
						JOptionPane.showMessageDialog(null, tip);
					}else if(!"".equals(tip)&& "error".equals(tip)){
						JOptionPane.showMessageDialog(null, tip);
					}
				}
                
                
				
				
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
