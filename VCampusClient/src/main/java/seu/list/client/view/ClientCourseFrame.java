//package VCampusClient.src.main.java.seu.list.client.view;
package seu.list.client.view;

/*
import VCampusClient.src.main.java.seu.list.common.ModuleType;
import main.java.seu.list.common.Course;
import main.java.seu.list.common.Message;
*/
import seu.list.common.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;


public class ClientCourseFrame extends JDialog implements ActionListener{
	JPanel jp1,jp2;
	JTable jtb1;
	JScrollPane jsp;
	JComboBox jcb;
	JTextField jtf,jtf1;
	JButton jco_Search,jco_Add,jco_Delete;
	Socket socket;//浼犻�佹暟鎹�
	JScrollPane scrollPane;
	JLabel jlb;
	private String userID;
	
	int index = 0;
	
	public ClientCourseFrame(String ID, Socket socket) throws ClassNotFoundException, SQLException,IOException, ClassNotFoundException {
		userID=ID;
		this.socket=socket;
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		String [] seOp = {"鍏ㄩ儴","璇剧▼鍙�"};
		jlb = new JLabel("璇剧▼鍚嶇О:");
		jcb = new JComboBox(seOp);
		jtf = new JTextField(20);
		jtf1 = new JTextField(10);
		jco_Search = new JButton("鎼滅储");
		jco_Search.addActionListener(this);
		jco_Search.setActionCommand("search");
		jco_Add =new JButton("澧炲姞璇剧▼");
		jco_Add.addActionListener(this);
		jco_Add.setActionCommand("add");
		jco_Delete =new JButton("绉婚櫎璇剧▼");
		jco_Delete.addActionListener(this);
		jco_Delete.setActionCommand("delete");
		Object[][] courseinformation= {};
        Object[] courselist = {"瀛﹀勾瀛︽湡","涓撲笟","璇剧▼缂栧彿","璇剧▼","鎺堣浜烘暟","鐘舵��","绫诲瀷"};
        DefaultTableModel model;
        model = new DefaultTableModel(courseinformation, courselist);
        
        Message clientReq = new Message();
		clientReq.setMessageType("REQ_SHOW_ALL_LESSON");
		//浼犳暟鎹�
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(clientReq);
		oos.flush();
		//鎺ュ彈鏁版嵁
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		clientReq = (Message) ois.readObject();
		Vector<String> allCourseContents = clientReq.getContent();
		
		Object sigRow[] = new  String[7];
		for(int i=0;i<allCourseContents.size();) {
			for(int j=0;j<7;) {
				sigRow[j++]=allCourseContents.get(i++);
			}
			model.addRow(sigRow);
		}
		
        jtb1=new JTable();
		jtb1.setModel(model);
		scrollPane = new JScrollPane(jtb1);
		

		/*
		lm = new LibModel();
		lm.init(vec);
		jt = new JTable(lm);
		jsp = new JScrollPane(jt);*/
		
		//娣诲姞缁勪欢
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jco_Search);
		
		jp2.add(jco_Add);
		jp2.add(jlb);
		jp2.add(jtf1);
		jp2.add(jco_Delete);
		
		this.setLocationRelativeTo(null);
		this.add(jp1,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="search"){
			if(jcb.getSelectedItem().equals("鍏ㄩ儴")) {
				Message clientReq = new Message();
				clientReq.setModuleType(ModuleType.Course);
				clientReq.setMessageType("REQ_SHOW_ALL_LESSON");
				try {
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(clientReq);
					oos.flush();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						clientReq = (Message) ois.readObject();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/7;
				String[][] allCourseTable = new String[rowNumber][7];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<7;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				jtb1 = new JTable();
				jtb1.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"瀛﹀勾瀛︽湡","涓撲笟","璇剧▼缂栧彿","璇剧▼","鎺堣浜烘暟","鐘舵��","绫诲瀷"
					}
				));
				jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(5).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(6).setPreferredWidth(161);
				scrollPane.setViewportView(jtb1);
				this.setVisible(true);
				this.setLocationRelativeTo(null);
			}else if(jcb.getSelectedItem().equals("璇剧▼鍙�")) {
				Message clientReq = new Message();//鏂板缓鐢宠鐢ㄤ簬浜ゆ崲
				clientReq.setModuleType(ModuleType.Course);
				clientReq.setMessageType("REQ_SEARCH_LESSON");//鏌ユ壘璇剧▼
				Vector<String> reqContent = new Vector<String>();
				Course c = new Course();
				c.setCourseID(jtf.getText());
				reqContent = c.getContent();
				clientReq.setContent(reqContent);//璋冪敤淇℃伅瀛樿繘鐢宠
				//閫氫俊
				try {
				ObjectOutputStream oos5 = new ObjectOutputStream(socket.getOutputStream());//鎶婅繖涓猻ocket閫氳繃OutputStream杈撳嚭缁橲erver绔�
				oos5.writeObject(clientReq);//鍐欒繘鐢宠閲岄潰鍘伙紙搴忓垪鍖栵級
				oos5.flush();//涓婁紶绛夊緟澶勭悊
	            ObjectInputStream ois5 = new ObjectInputStream(socket.getInputStream());//鎶婅繖涓猻ocket閫氳繃InputStream鍐欏洖client绔�
				clientReq = (Message) ois5.readObject();}//璇诲嚭锛堝幓搴忓垪鍖栵級
				catch(Exception e2) {
					e2.printStackTrace();
				}
				Vector<String>	allCourseInfor = clientReq.getContent();
				int rowNumber = allCourseInfor.size()/7;
				String[][] allCourseTable = new String[rowNumber][7];
				int storingPlace = 0;
				for(int i=0;i<rowNumber;i++) {
					for(int j=0;j<7;j++)
						allCourseTable[i][j] = allCourseInfor.get(storingPlace++);
				}
				jtb1 = new JTable();
				jtb1.setModel(new DefaultTableModel(
					allCourseTable,
					new String[] {
							"瀛﹀勾瀛︽湡","涓撲笟","璇剧▼缂栧彿","璇剧▼","鎺堣浜烘暟","鐘舵��","绫诲瀷"
					}
				));
				jtb1.getColumnModel().getColumn(0).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(1).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(2).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(3).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(4).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(5).setPreferredWidth(161);
				jtb1.getColumnModel().getColumn(6).setPreferredWidth(161);
				scrollPane.setViewportView(jtb1);
				this.setVisible(true);
				this.setLocationRelativeTo(null);
			}
		
		}
		else if(e.getActionCommand()=="add") {
			CourseInfor courseInfor = new CourseInfor(userID,socket);
			this.setVisible(false);
		}
		else if(e.getActionCommand()=="delete") {
			Message clientReq = new Message();
			clientReq.setModuleType(ModuleType.Course);
			clientReq.setMessageType("REQ_REMOVE_LESSON");
			Vector<String> reqContent = new Vector<String>();
			reqContent.setSize(7);
			reqContent.set(1,jtf1.getText());
			clientReq.setContent(reqContent);
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(this.socket.getOutputStream());
				oos.writeObject(clientReq);
				oos.flush();
				this.setVisible(false);
				ClientCourseFrame ccf = new ClientCourseFrame(userID,socket);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
	}
}
