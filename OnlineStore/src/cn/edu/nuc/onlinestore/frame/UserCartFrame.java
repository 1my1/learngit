package cn.edu.nuc.onlinestore.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.nuc.onlinestore.action.ClientLoginAction;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
/*
 * 购物车
 */
public class UserCartFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private User user=null;
	/**
	 * Create the frame.
	 */
	public UserCartFrame(final UserLogin ul) {
		setUser(ul.getUser());
		setTitle("购物车详情");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("商品编号");
		model.addColumn("名称");
		model.addColumn("单价(￥)");
		model.addColumn("数量");
		model.addColumn("总价格(￥)");
		//model.addColumn("操作");
		
		/*model.addRow(new String[]{"1","水杯","15.00","2","30.00"});
		model.addRow(new String[]{"5","农夫山泉","2.00","5","10"});
		model.addRow(new String[]{"6","毛巾","9.90","1","9.90"});*/
		//System.out.println("购物车："+getUser().getCart());
		int totalQuantity=0;
		double totalPrice=0;
        if(getUser().getCart()==null){
        	//model.addRow(new String[]{"","","","",""});
        }else if(getUser().getCart()!=null){
        	for(Map.Entry<Goods, Integer> entry:getUser().getCart().getMaps().entrySet()){
            	Goods goods=entry.getKey();
            	int quantity=entry.getValue();
    			model.addRow(new String[]{goods.getId(),goods.getName(),goods.getPrice()+"",quantity+"",quantity*goods.getPrice()+""});
    		}
        	totalQuantity=getUser().getCart().getTotalQuantity();
        	totalPrice=getUser().getCart().getTotalPrice();
        }
		table = new JTable( model );
		table.setBounds(10, 38, 543, 184);
		
		JScrollPane pane = new JScrollPane( table );
		pane.setVisible(true);
		contentPane.add( table );
		
		JLabel label = new JLabel("总商品数量: "+totalQuantity);
		label.setBounds(195, 232, 116, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("总金额: "+totalPrice);
		lblNewLabel.setBounds(342, 232, 211, 15);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("结账");
		button.setBounds(456, 283, 93, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Message<User> message=new Message<>();
				message.setMessage("checkOut");
				message.setObj(getUser());
				ClientLoginAction cla=new ClientLoginAction(message);
				cla.send();
				Result<User> result=cla.get();
				String tip=result.getMsg();
				User user=result.getObj();
				ul.setUser(user);
				if(!tip.equals("success") || "".equals(tip)){
					JOptionPane.showMessageDialog(null,"error");
					return;
				}else if("success".equals(tip)){
					JOptionPane.showMessageDialog(null,"success");
				}
			}
		});
		contentPane.add(button);
	}
	public void setUser(User u){
		this.user=u;
	}
	public User getUser(){
		return user;
	}

}
