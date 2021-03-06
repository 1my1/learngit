package cn.edu.nuc.onlinestore.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import cn.edu.nuc.onlinestore.action.AddGoodsService;
import cn.edu.nuc.onlinestore.action.ServerCheck;
import cn.edu.nuc.onlinestore.model.Admin;
import cn.edu.nuc.onlinestore.model.Goods;
import cn.edu.nuc.onlinestore.model.GoodsStore;
import cn.edu.nuc.onlinestore.utils.MyTableModel;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
/*
 * 管理员主页
 */
public class AdminStore extends JFrame {
   
	private JPanel contentPane;
	private JTextField textField;
	private AddGoodsService ags=new AddGoodsService();;
	private GoodsStore goodsStore=new GoodsStore();
	/**
	 * Create the frame.
	 */
	public AdminStore(Admin a) {
		ServerCheck server=new ServerCheck();
		setTitle("中北商场后台管理系统--当前用户:"+a.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 696, 341);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		final DefaultTableModel model = new DefaultTableModel();
		model.addColumn("商品编号");
		model.addColumn("名称");
		model.addColumn("单价(人民币)");
		model.addColumn("库存");
		//model.addColumn("操作");
		System.out.println(ags.showGoods().getGs());
		goodsStore=ags.showGoods();
		if(!goodsStore.getGs().equals(null)){
			for(Goods g:goodsStore.getGs()){
				Object[] obj={g.getId(),g.getName(),g.getPrice(),g.getNum()};
				model.addRow(obj);
			}
		}
		/*model.addRow(new String[]{"1","水杯","15.00","200"});
		model.addRow(new String[]{"2","水瓶","35.00","200"});
		model.addRow(new String[]{"3","天堂伞","55.00","200"});
		model.addRow(new String[]{"4","男袜","8.00","200"});
		model.addRow(new String[]{"5","农夫山泉","2.00","200"});
		model.addRow(new String[]{"6","毛巾","9.90","200"});
		model.addRow(new String[]{"7","牙刷","15.00","200"});
		model.addRow(new String[]{"8","洗发水","15.00","200"});
		model.addRow(new String[]{"9","牙膏","15.00","200"});
		model.addRow(new String[]{"10","海尔全自动洗衣机","2,699.00","200"});*/
		final JTable table = new JTable( model );
		contentPane.add(panel);
		
		JButton button = new JButton("添加商品");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAdd add = new AdminAdd(model,ags);
				add.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				add.setVisible(true);
			}
		});
		button.setBounds(386, 45, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("修改商品");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String goodsName=(String)table.getValueAt(row, 1);
				System.out.println(goodsName);
				model.removeRow(row);
				
				Goods good=ags.findGoodByName(goodsName);
				AdminUpdate u = new AdminUpdate(model,good);
				u.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				u.setVisible(true);
			}
		});
		button_1.setBounds(489, 45, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("删除选中商品");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String goodsName=(String)table.getValueAt(row, 1);
			    int remove=JOptionPane.showConfirmDialog(null, "确定删除"+goodsName+"吗?","删除商品",JOptionPane.OK_CANCEL_OPTION);
			    if(remove==0){
			    	
			    	System.out.println(goodsName);
			    	Goods good=ags.findGoodByName(goodsName);
			    	System.out.println(good);
			    	ags.delete(good);
			    	model.removeRow(row);
			    	
			    }
				
			}
		});
		button_2.setBounds(587, 45, 119, 23);
		contentPane.add(button_2);
		
		JScrollPane pane = new JScrollPane( table );
		panel.add(pane);
		
		JButton button_3 = new JButton("退出登录");
		button_3.setBounds(613, 6, 93, 23);
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int exit=JOptionPane.showConfirmDialog(null, "确定要退出吗？", "退出系统", JOptionPane.OK_CANCEL_OPTION);
				if(exit==0){
					System.exit(0);
				}
			}
		});
		contentPane.add(button_3);
		
		JLabel label = new JLabel("当前在线用户数:"+server.getSize());
		label.setBounds(10, 10, 162, 15);
		contentPane.add(label);
		
		JLabel lblid = new JLabel("商品编号:");
		lblid.setBounds(10, 53, 54, 15);
		contentPane.add(lblid);
		
		textField = new JTextField();
		textField.setBounds(68, 50, 104, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button_4 = new JButton("搜索");
		button_4.setBounds(180, 49, 93, 23);
		button_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GoodsStore gs=ags.showGoods();
				Set<Goods> set=gs.getGs();
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
				Goods goods=ags.findGoodByName(goodName);
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
		contentPane.add(button_4);
	}
}
