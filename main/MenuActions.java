package com.wyp.main;

import java.awt.event.ActionEvent;


import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import com.wyp.iframe.BookAddIFrame;
import com.wyp.iframe.BookModiAndDelIFrame;
import com.wyp.iframe.BookTypeAddIFrame;
import com.wyp.iframe.BookTypeModiAndDelIFrame;

/**
 * 菜单和按钮的Action对象
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // 子窗体集合
	public static BookModiAction BOOK_MODIFY; // 图书信息修改窗体动作
	public static BookAddAction BOOK_ADD; // 图书信息添加窗体动作
	public static ExitAction EXIT; // 系统退出动作
	public static BookTypeAddAction BOOKTYPE_ADD;
	public static BookTypeModiAction BOOKTYPE_MODIFY;
	static {
		frames = new HashMap<String, JInternalFrame>();
		BOOK_MODIFY = new BookModiAction();
		BOOK_ADD = new BookAddAction();
		BOOKTYPE_ADD = new BookTypeAddAction();
		BOOKTYPE_MODIFY = new BookTypeModiAction();
		EXIT = new ExitAction();
	}
	//图书修改与删除
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("图书修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除图书信息");
			putValue(Action.SHORT_DESCRIPTION, "图书修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书修改")||frames.get("图书修改").isClosed()) {
				BookModiAndDelIFrame iframe=new BookModiAndDelIFrame();
				frames.put("图书修改", iframe);
				Library.addIFame(frames.get("图书修改"));
			}
		}
	}
	private static class BookAddAction extends AbstractAction {				// 图书信息添加－－－已经实现，请参照
		BookAddAction() {
			
			super("图书信息添加", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书信息");
			putValue(Action.SHORT_DESCRIPTION, "图书信息添加");
			
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息添加")||frames.get("图书信息添加").isClosed()) {
				
				BookAddIFrame iframe = new BookAddIFrame();
				
				frames.put("图书信息添加", iframe);
				Library.addIFame(frames.get("图书信息添加"));
			}
		}
	}
	private static class BookTypeAddAction extends AbstractAction {				// 图书信息添加－－－已经实现，请参照
		BookTypeAddAction() {
			
			super("图书类别添加", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书类别");
			putValue(Action.SHORT_DESCRIPTION, "图书类别添加");
			
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类别添加")||frames.get("图书类别添加").isClosed()) {
				
				BookTypeAddIFrame iframe = new BookTypeAddIFrame();
				
				frames.put("图书类别添加", iframe);
				Library.addIFame(frames.get("图书类别添加"));
			}
		}
	}
	private static class BookTypeModiAction extends AbstractAction {
		BookTypeModiAction() {
			super("图书类别修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除图书类别信息");
			putValue(Action.SHORT_DESCRIPTION, "图书类别修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类别修改")||frames.get("图书类别修改").isClosed()) {
				BookTypeModiAndDelIFrame iframe=new BookTypeModiAndDelIFrame();
				frames.put("图书类别修改", iframe);
				Library.addIFame(frames.get("图书类别修改"));
			}
		}
	}
	private static class ExitAction extends AbstractAction { // 退出系统动作
		public ExitAction() {
			super("退出系统", null);
			putValue(Action.LONG_DESCRIPTION, "退出图书馆管理系统");
			putValue(Action.SHORT_DESCRIPTION, "退出系统");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}

}

