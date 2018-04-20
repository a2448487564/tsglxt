package com.wyp.iframe;
import java.awt.BorderLayout;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.wyp.JComPz.Item;
import com.wyp.JComPz.MapPz;
import com.wyp.dao.BookInfoDao;
import com.wyp.dao.BookTypeDao;
import com.wyp.model.BookInfo;
import com.wyp.model.BookType;
import com.wyp.util.CreatecdIcon;
import com.wyp.util.MyDocument;



/**
 * 名称：图书修改窗体
 *
 */
public class BookTypeModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	
	
	private JTextField fk;
	
	private JTextField days;
	private JTextField id;
	private JTextField booktypename;

	DefaultComboBoxModel bookTypeModel;
	private Item item;
	Map map=new HashMap();
	private String[] columnNames;
	private Map m=MapPz.getMap();

	private Object[][] getFileStates(List list){//取数据库中图书相关信息放入表格中
		String[] columnNames = { "类别编号", "图书类别", "可借天数", "罚款"};
		Object[][]results=new Object[list.size()][columnNames.length];//二维数组用来保存所有记录
		
		for(int i=0;i<list.size();i++){//遍历list
			BookType booktype=(BookType)list.get(i);//取出图书记录
			results[i][0]=booktype.getId();//设置图书编号
			results[i][1]=booktype.getTypeName();//设置图书类别名称
			results[i][2]=booktype.getDays();//设置图书名称
			results[i][3]=booktype.getFk();	//设置图书作者
			
		}
		return results;//范围二维数组的书籍记录
	         		
	}
	public BookTypeModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();//边框布局管理器
		getContentPane().setLayout(borderLayout);//使用边框布局管理器
		setIconifiable(true);// 设置窗体可最小化
		setClosable(true);// 设置窗体可关闭
		setTitle("图书信息修改");// 设置窗体标题
		setBounds(100, 100, 593, 406);// 设置窗体位置和大小

		

		final JPanel mainPanel = new JPanel();//主面板
		final BorderLayout borderLayout_1 = new BorderLayout();//边框布局管理器
		borderLayout_1.setVgap(5);//设置组件之间垂直距离
		mainPanel.setLayout(borderLayout_1);//使用边框布局管理器
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
		getContentPane().add(mainPanel);//将主面板添加到窗体中

		final JScrollPane scrollPane = new JScrollPane();//滚动面板
		mainPanel.add(scrollPane);//将滚动面板添加到主面板中

		Object[][] results=getFileStates(BookTypeDao.selectbooktype());//获得书籍记录
		columnNames = new String[]{"类别编号", "图书类别", "可借天数", "罚款"};//列名列表
		table = new JTable(results,columnNames);//创建表格
		scrollPane.setViewportView(table);//将表格添加到滚动面板中
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//自适应窗体
		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);//将表格添加到滚动面板中

		final JPanel bookPanel = new JPanel();//书籍修改面板
		mainPanel.add(bookPanel, BorderLayout.SOUTH);//添加到主面板底端
		final GridLayout gridLayout = new GridLayout(0, 6);//网格布局
		gridLayout.setVgap(5);//设置组件之间垂直距离
		gridLayout.setHgap(20);//设置组件之间平行距离
		bookPanel.setLayout(gridLayout);//设置书籍添加面板布局
		
		final JLabel ISBNLabel = new JLabel();//创建图书编号标签
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		ISBNLabel.setText("类别编号：");//设置标签文本
		bookPanel.add(ISBNLabel);//添加到书籍修改面板
		id = new JTextField();//创建书号文本框
		id.setDocument(new MyDocument(6)); //书号文本框最大输入值为13
		bookPanel.add(id);//添加到书籍修改面板
		
		
		final JLabel bookNameLabel = new JLabel();//创建书名标签
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		bookNameLabel.setText("图书类别：");//设置标签文本
		bookPanel.add(bookNameLabel);//添加到书籍修改面板
		booktypename = new JTextField();//书名文本框
		bookPanel.add(booktypename);//添加到书籍修改面板
		
		final JLabel writerLabel = new JLabel();//创建作者标签
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		writerLabel.setText("可借天数：");//设置标签文本
		bookPanel.add(writerLabel);//添加到书籍修改面板
		days = new JTextField();//作者文本框
		bookPanel.add(days);
		
		
		final JLabel translatorLabel = new JLabel();//创建译者标签
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);//水平居中
		translatorLabel.setText("罚款：");//设置标签文本
		bookPanel.add(translatorLabel);//添加到书籍修改面板
		fk = new JTextField();//译者文本框
		bookPanel.add(fk);//添加到书籍修改面板
		
		final JPanel bottomPanel = new JPanel();//创建底部面板
		bottomPanel.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体底端
		final FlowLayout flowLayout = new FlowLayout();//流布局管理器
		flowLayout.setVgap(2);//设置组件之间垂直距离
		flowLayout.setHgap(30);//设置组件之间平行距离
		flowLayout.setAlignment(FlowLayout.RIGHT);//设置向右对齐
		bottomPanel.setLayout(flowLayout);//设置底部面板布局
		
		final JButton updateButton = new JButton();//创建修改按钮
		updateButton.addActionListener(new UpdateBookActionListener ());//注册监听器
		updateButton.setText("修改");//设置按钮文本
		bottomPanel.add(updateButton);//添加到底部面板
		
		final JButton closeButton= new JButton();//创建关闭按钮
		closeButton.addActionListener(new ActionListener() {//注册监听器
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();//关闭窗体
			}
		});
		closeButton.setText("关闭");//设置按钮文本
		bottomPanel.add(closeButton);//添加到底部面板
		
		final JLabel headLogo = new JLabel();//图片标签
		ImageIcon bookModiAndDelIcon=CreatecdIcon.add("bookmodify.jpg");//图片图标
		headLogo.setIcon(bookModiAndDelIcon);//设置标签显示图片
		headLogo.setOpaque(true);//设置图片标签不透明
		headLogo.setBackground(Color.CYAN);//设置标签背景颜色
		headLogo.setPreferredSize(new Dimension(400, 80));//设置标签的大小
		headLogo.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//设置标签边框
		getContentPane().add(headLogo, BorderLayout.NORTH);//添加到窗体上端
		
		setVisible(true);//显示窗体可见
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ids, booktypenames, dayss,fks;//声明变量
			int selRow = table.getSelectedRow();//获得所选行号
			ids = table.getValueAt(selRow, 0).toString().trim();//获得书号
			booktypenames = table.getValueAt(selRow, 1).toString().trim();//获得类别编号
			dayss = table.getValueAt(selRow, 2).toString().trim();//获得书名
			fks = table.getValueAt(selRow, 3).toString().trim();//获得作者
			
			id.setText(ids);//设置书号文本框为获得的书号信息
			booktypename.setText(booktypenames);//设置书名文本框为获得的书名信息
			days.setText(dayss);//设置作者文本框为获得的作者信息
			fk.setText(fks);//设置译者文本框为获得的译者信息
		}
	}
	class UpdateBookActionListener  implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if(id.getText().length()==0){//判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			if(id.getText().length()!=6){//判断书籍编号的长度是否为13
				JOptionPane.showMessageDialog(null, "书号文本框输入位数为6位");
				return;
			}
			if(booktypename.getText().length()==0){//判断是否输入了书籍名称
				JOptionPane.showMessageDialog(null, "图书名称文本框不可以为空");
				return;
			}
			if(days.getText().length()==0){//判断是否输入了作者
				JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
				return;
			}
			if(fk.getText().length()==0){//判断是否输入了出版社
				JOptionPane.showMessageDialog(null, "出版人文本框不可以为空");
				return;
			}
			String ids=id.getText().trim();//获得书籍编号
			String booktypenames=booktypename.getText().trim();//获得译者信息
			String dayss=days.getText().trim();//获得书籍名称
			String fks=fk.getText().trim();//获得作者信息
			int i=BookTypeDao.Updatebooktype(Integer.parseInt(ids),booktypenames, Integer.parseInt(dayss), Float.parseFloat(fks));
			if(i==1){//如果返回更新记录数为1，表示修改成功
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(BookTypeDao.selectbooktype());//重新获得书籍信息
				DefaultTableModel model=new DefaultTableModel();//获得表格模型
				table.setModel(model);//设置表格模型
				model.setDataVector(results, columnNames);//设置模型数据和列名
			}
			
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	

}
