package VCampusClient.src.main.java.seu.list.client.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table {
    JTable Table=null;
    JScrollPane jScrollPane;
    DefaultTableModel model= null;
    public Table(Object columns[]){
        Table(columns);
    }
    void Table(Object columns[]){
        Table=getTable(columns);
        jScrollPane=new JScrollPane(Table);//给表格添加滚动条
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//设置滚动条


    }
    JTable getTable(Object columns[]){
        if(Table==null){
            Table=new JTable();
            model=new DefaultTableModel(){
                public boolean isCellEditable(int row,int column){
                    return false;
                }
            };
            model.setColumnIdentifiers(columns);

            Table.setModel(model);
            Table.getTableHeader().setReorderingAllowed(false);
            Table.getTableHeader().setResizingAllowed(false);

        }
        return Table;
    }
    public JTable gettables(){
        return Table;
    }
    public JScrollPane getjScrollPane(){
        return jScrollPane;
    }
    public DefaultTableModel getModel(){
        return model;
    }
}
