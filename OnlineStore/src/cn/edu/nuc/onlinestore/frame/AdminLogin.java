package cn.edu.nuc.onlinestore.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.nuc.onlinestore.action.LoginActionProcess;
import cn.edu.nuc.onlinestore.model.Admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    private LoginActionProcess lap;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setTitle("中北线在商场管理系统-管理员登录");
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
		
		JButton button = new JButton("登录系统");
		button.setBounds(255, 216, 93, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lap=new LoginActionProcess();
				
				
				String adminName=textField.getText();
				String password=new String (passwordField.getPassword());
				if("".equals(adminName)||"".equals(password)){
					JOptionPane.showMessageDialog(null, "账号或密码不能为空！");
					return;
				}
				Admin a=new Admin();
				a.setName(adminName);
				a.setPass(password);
				//验证账号和密码是否正确
				boolean result=lap.check(a);
				//如果正确进入adminStore页面
				if(result){
					AdminStore as=new AdminStore();
					as.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					as.setVisible(true);
					AdminLogin.this.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "登录失败！");
					return;
				}
				//如果不正确弹出输入不正确的提示框
				
			}
		});
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("");
		//lblNewLabel.setIcon(new ImageIcon(AdminLogin.class.getResource("/image/a.jpg")));
		lblNewLabel.setBounds(208, 54, 86, 82);
		contentPane.add(lblNewLabel);
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
