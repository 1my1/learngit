package cn.edu.nuc.onlinestore.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import cn.edu.nuc.onlinestore.action.AddGoodsService;
import cn.edu.nuc.onlinestore.action.ClientShowGoods;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.model.User;
import cn.edu.nuc.onlinestore.utils.MyTableModel;
import cn.edu.nuc.onlinestore.vo.Message;
import cn.edu.nuc.onlinestore.vo.Result;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
/*
 * 用户主页面
 */
public class UserStore extends JFrame {
	private boolean DEBUG=false;
	private JPanel contentPane;
	private JTextField textField;
	private User user=null;
	private GoodsStore goodsStore;
	private AddGoodsService ags;
	private UserLogin ul=null;
	private JLabel label_cart_goodsNum=null;
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
		setTitle("中北在线商场--当前用户:null");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 696, 341);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		/*//自定义的tableModel
		JTable table = new JTable( new MyTableModel() );*/
		//得到服务端的商品
		getResult();
		//表格模型
		final DefaultTableModel model = new DefaultTableModel(){
			public boolean isCellEditable(int row, int col) {
		           //Note that the data/cell address is constant,
		           //no matter where the cell appears onscreen.
		           if (col < 4) {
		               return false;
		           } else {
		               return true;
		           }
		       }
		};
		
		model.addColumn("商品编号");
		model.addColumn("名称");
		model.addColumn("单价(人民币)");
		model.addColumn("库存");
		//商品列表
		if(!goodsStore.equals(null)){
			if(!goodsStore.getGs().equals(null)){
				for(Goods g:goodsStore.getGs()){
					Object[] obj={g.getId(),g.getName(),g.getPrice(),g.getNum()};
					model.addRow(obj);
				}
			}
		}
		
		final JTable table = new JTable( model );
		//model.;
		JScrollPane pane = new JScrollPane( table );
		
		panel.add(pane);
		contentPane.add(panel);
		
		JLabel label = new JLabel("购物车:");
		label.setBounds(10, 10, 90, 15);
		contentPane.add(label);
		//购物车中物品的数量
		label_cart_goodsNum=new JLabel("0");
		label_cart_goodsNum.setBounds(55,10,90,15);
		contentPane.add(label_cart_goodsNum);
		//购物车按钮
		final JButton button_viewCart = new JButton("查看购物车");
		button_viewCart.setEnabled(false);
		button_viewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("查看购物车:"+ul);
//				System.out.println("查看购物车："+ul.getUser());
				UserCartFrame cf = new UserCartFrame(ul);
				cf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				cf.setVisible(true);
			}
		});
		button_viewCart.setBounds(116, 6, 110, 23);
		contentPane.add(button_viewCart);
		
		//登录
		JButton button_login=new JButton("登录");
		button_login.setBounds(500, 6, 73, 23);
		button_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ul=new UserLogin(button_viewCart,UserStore.this);
				System.out.println("userLogin:"+ul.getUser());
				setUser(ul.getUser());
				ul.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				ul.setVisible(true);
			}
		});
		contentPane.add(button_login);
		
		JButton button_2 = new JButton("查看商品详细信息(或双单击商品列)");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				int col=table.getColumnCount();
				String goodsId=(String)model.getValueAt(row, 0);
				String goodsName=(String)model.getValueAt(row, 1);
				System.out.println("11"+goodsId);
				System.out.println("11"+goodsName);
				Goods goods=new Goods();
				goods.setId(goodsId);
				goods.setName(goodsName);
				User uuu=null;
				if(ul==null){
				}else{
					uuu=ul.getUser();
				}
				System.out.println("查看商品详细信息："+uuu);
				//ClientShowGoods csg=new ClientShowGoods(message)
				Goods g=getGoods(goods);
				UserGoods d = new UserGoods(g,uuu,label_cart_goodsNum);
				d.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				d.setVisible(true);
			}
		});
		button_2.setBounds(407, 45, 299, 23);
		contentPane.add(button_2);
		
		
		
		
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
		button_goodsSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Set<Goods> set=goodsStore.getGs();
				String goodName=textField.getText().trim();
				//搜索内容为空
				if("".equals(goodName) || goodName.equals(null)){
					int rows=model.getRowCount();
					table.removeAll();
					for(int i=0;i<rows;i++){
						model.removeRow(0);
					}
					for(Goods goods:set){
						Object[] obj={goods.getId(),goods.getName(),goods.getPrice(),goods.getNum()};
						model.addRow(obj);
					}
					return;
				}
				//搜索内容不为空
				Goods goods=null;
				for(Goods g:set){
					if (goodName.equals(g.getName())) {
						goods=g;
					}
				}
				if(goods!=null){
					
					//table.removeAll();
					int row=model.getRowCount();
					for(int i=0;i<row;i++){
						model.removeRow(0);
					}
					Object[] obj={goods.getId(),goods.getName(),goods.getPrice(),goods.getNum()};
					model.addRow(obj);
					
				}
			}
		});
		contentPane.add(button_goodsSearch);
		//查询结束
	}
	public User getUser(){
		return user;
	}
	public void setUser(User u){
		this.user=u;
	}
	public GoodsStore getResult(){
		Message<GoodsStore> message=new Message<GoodsStore>();
		message.setMessage("viewGoodsStore");
		ClientShowGoods csg=new ClientShowGoods(message);
		csg.send();
		Result<GoodsStore> result_viewGoods=csg.get();
		goodsStore=result_viewGoods.getObj();
		return goodsStore;
	}
	public Goods getGoods(Goods g){
		Message<GoodsStore> message=new Message<GoodsStore>();
		message.setMessage("viewDetail,"+g.getId()+","+g.getName());
		ClientShowGoods csg=new ClientShowGoods(message);
		csg.send();
		Result<GoodsStore> result=csg.get();
		GoodsStore store=result.getObj();
		System.out.println("UserStore-->getGoods"+store);
		if(!store.getGs().isEmpty()){
			for(Goods goods:store.getGs()){
				return goods;
			}
		}
		return null;
		
	}
}

