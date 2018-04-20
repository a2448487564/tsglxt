package com.wyp.iframe;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.wyp.JComPz.Item;
import com.wyp.dao.BookInfoDao;
import com.wyp.model.BookType;
import com.wyp.util.CreatecdIcon;
import com.wyp.util.MyDocument;


/**
 * 名称：图书添加窗体
 * 
 */
public class BookAddIFrame extends JInternalFrame {
	private JComboBox publisher;
	private JTextField   price;
	private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField writer;
	private JTextField ISBN;
	private JTextField bookName;
	private JComboBox bookType;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel bookTypeModel;
	
	Map map=new HashMap();
	public BookAddIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();//创建边框布局管理器
		getContentPane().setLayout(borderLayout);			//设置布局
		setIconifiable(true);							// 设置窗体可最小化
		setClosable(true);								// 设置窗体可关闭
		setTitle("图书信息添加");						// 设置窗体标题
		setBounds(100, 100, 396, 260);					// 设置窗体位置和大小

		final JPanel mainPanel = new JPanel();			//创建中心面板
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
		final GridLayout gridLayout = new GridLayout(0, 4);//创建表格布局管理器
		gridLayout.setVgap(5);					//设置组件之间垂直距离
		gridLayout.setHgap(5);					//设置组件之间平行距离
		mainPanel.setLayout(gridLayout);		//设置布局
		getContentPane().add(mainPanel);		//将中心面板加入到窗体

		final JLabel ISBNLabel = new JLabel();	//创建图书编号标签
		ISBNLabel.setText("图书编号：");			//设置标签文本
		mainPanel.add(ISBNLabel);				//添加到中心面板

		ISBN = new JTextField("请输入13位书号",13);//创建书号文本框
		ISBN.setDocument(new MyDocument(13)); //设置书号文本框最大输入值为13
		
		ISBN.setColumns(13);//设置文本框长度
		ISBN.addKeyListener(new ISBNkeyListener());//注册监听器
		ISBN.addFocusListener(new ISBNFocusListener());//注册监听器
		mainPanel.add(ISBN);

		final JLabel bookTypeLabel = new JLabel();//创建书籍类别标签
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		bookTypeLabel.setText("类别：");//设置标签文本
		mainPanel.add(bookTypeLabel);//添加到中心面板

		bookType = new JComboBox();//创建书籍类别下拉框
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();//设置类别模型
		
		
		List list=BookInfoDao.selectBookCategory();//从数据库中取出图书类别
		for(int i=0;i<list.size();i++){		//遍历图书类别
			BookType booktype=(BookType)list.get(i);//获得图书类别
			Item item=new Item();//实例化图书类别选项
			item.setId((String)booktype.getId());//设置图书类别编号
			item.setName((String)booktype.getTypeName());//设置图书类别名称
			bookTypeModel.addElement(item);//添加图书类别元素
		}
		mainPanel.add(bookType);//添加到中心面板

		final JLabel bookNameLabel = new JLabel();//创建书名标签
		bookNameLabel.setText("书名：");//设置标签文本
		mainPanel.add(bookNameLabel);//添加到中心面板

		bookName = new JTextField();//创建书名文本框
		mainPanel.add(bookName);	//添加到中心面板

		final JLabel writerLabel = new JLabel();//创建作者标签
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		writerLabel.setText("作者：");//设置标签文本
		mainPanel.add(writerLabel);//添加到中心面板

		writer = new JTextField();//创建作者文本框
		writer.setDocument(new MyDocument(10));//设置作者文本框最大输入值为10
		mainPanel.add(writer);//添加到中心面板

		final JLabel publisherLabel = new JLabel();//创建出版社标签
		publisherLabel.setText("出版社：");//设置标签文本
		mainPanel.add(publisherLabel);//添加到中心面板

		publisher = new JComboBox();//创建出版社下拉框
		String[] array=new String[]{"北京出版社","天津出版社",
					"上海出版社","重庆出版社"};//出版社列表
		publisher.setModel(new DefaultComboBoxModel(array));//设置下拉框模型
		mainPanel.add(publisher);//添加到中心面板

		final JLabel translatorLabel = new JLabel();//创建译者标签
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		translatorLabel.setText("译者：");//设置标签文本
		mainPanel.add(translatorLabel);//添加到中心面板

		translator = new JTextField();//创建译者文本框
		translator.setDocument(new MyDocument(10));//设置译者文本框最大输入值为10
		mainPanel.add(translator);//添加到中心面板

		final JLabel pubDateLabel = new JLabel();//创建出版日期标签
		pubDateLabel.setText("出版日期：");//设置标签文本
		mainPanel.add(pubDateLabel);//添加到中心面板

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		pubDate= new JFormattedTextField(myfmt.getDateInstance());//创建日期输入框
		pubDate.setValue(new java.util.Date());		//设置日期为当前系统时间
		mainPanel.add(pubDate);//添加到中心面板
		
		final JLabel priceLabel = new JLabel();//创建价格标签
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置平行对齐方式
		priceLabel.setText("单价：");//设置标签文本
		mainPanel.add(priceLabel);//添加到中心面板
		
		price=   new   JTextField();//创建价格文本框
		price.setDocument(new MyDocument(5));//设置价格文本框最大输入值为5
		price.addKeyListener(new NumberListener());//注册监听器
		mainPanel.add(price);//添加到中心面板

		final JPanel bottomPanel = new JPanel();//创建底部面板
		bottomPanel.setBorder(new LineBorder(SystemColor.
							activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);//添加到窗体中
		final FlowLayout flowLayout = new FlowLayout();//流布局管理器
		flowLayout.setVgap(2);	//设置组件之间垂直距离
		flowLayout.setHgap(30);//设置组件之间平行距离
		flowLayout.setAlignment(FlowLayout.RIGHT);//设置对齐方式
		bottomPanel.setLayout(flowLayout);//设置底部面板布局
		
		buttonadd= new JButton();//创建添加按钮
		buttonadd.addActionListener(new AddBookActionListener());//注册监听器
		buttonadd.setText("添加");//设置按钮文本
		bottomPanel.add(buttonadd);//添加到底部面板
		
		buttonclose = new JButton();//创建关闭按钮
		buttonclose.addActionListener(new CloseActionListener());//注册监听器
		buttonclose.setText("关闭");//设置按钮文本
		bottomPanel.add(buttonclose);//添加到底部面板

		final JLabel imageLabel = new JLabel();//图片标签
		ImageIcon bookAddIcon=CreatecdIcon.add("bookAdd.jpg");//图片图标
		imageLabel.setIcon(bookAddIcon);//设置标签显示图片
		imageLabel.setPreferredSize(new Dimension(400, 80));//设置标签的大小
		imageLabel.setBorder(new LineBorder(SystemColor.
						activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(imageLabel, BorderLayout.NORTH);//添加到窗体中
		imageLabel.setText("图书信息添加(LOGO图片)");//设置标签文本
		
		setVisible(true);											// 显示窗体可见
	}
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!BookInfoDao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
		}
	}
	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13){
				buttonadd.doClick();
			}
		
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class AddBookActionListener implements ActionListener {		// 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){//判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			if(ISBN.getText().length()!=13){//判断书籍编号的长度是否为13
				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
				return;
			}
			if(bookName.getText().length()==0){//判断是否输入了书籍名称
				JOptionPane.showMessageDialog(null, "图书名称文本框不可以为空");
				return;
			}
			if(writer.getText().length()==0){//判断是否输入了作者
				JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
				return;
			}
			if(pubDate.getText().length()==0){//判断是否输入了出版日期
				JOptionPane.showMessageDialog(null, "出版日期文本框不可以为空");
				return;
			}
			if(price.getText().length()==0){//判断是否输入了书籍价格
				JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
				return;
			}
			String ISBNs=ISBN.getText().trim();//获得书籍编号
			Object selectedItem = bookType.getSelectedItem();//书籍类别选项
			if (selectedItem == null) return;
			Item item = (Item) selectedItem;	//获得所选类别
			String bookTypes=item.getId();		//获得类别编号
			String translators=translator.getText().trim();//获得译者信息
			String bookNames=bookName.getText().trim();//获得书籍名称
			String writers=writer.getText().trim();//获得作者信息
			String publishers=(String)publisher.getSelectedItem();//获得出版社信息
			String pubDates=pubDate.getText().trim();//获得出版日期
			String prices=price.getText().trim();//获得价格信息
			int i=BookInfoDao.Insertbook(ISBNs,bookTypes, bookNames, writers, translators, 
					publishers, java.sql.Date.valueOf(pubDates),Double.parseDouble(prices));
			if(i==1){	//如果返回更新记录数为1，表示添加成功
				JOptionPane.showMessageDialog(null, "添加成功");
				doDefaultCloseAction();
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
