package GUI.Components.Tables;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import Classes.Utilities.Colors;
import GUI.Components.Text.TextUI;
import java.awt.Color;

public abstract class Table extends JPanel {
    protected Color BackgroundColor;
    protected TextUI      title;
    protected String[]    categories;
    protected String[][]  content;
    protected JTable      table;

    //General Properties
    public void Set_Layout(String title){    
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.title = new TextUI(title, Color.BLACK, BackgroundColor);
        //this.setPreferredSize(new Dimension(250,75));
        this.setMaximumSize(new Dimension(450,300));
        this.setMinimumSize(new Dimension(250,50));
    }

    
    public void Set_TableColor(Color color){
        BackgroundColor = color;
        title.Set_Background(color);
        Set_BackgroundColor(color);
    }
    public void Set_BackgroundColor(Color color){
        title.Set_Background(color);
        this.setBackground(color);
    }
    public void Set_Title(TextUI newTitle){
        this.add(newTitle.Get_Component());
    }
    public void Set_Size(int[] size){
        this.setMinimumSize  (new Dimension(size[0], size[1]));
        this.setMaximumSize  (new Dimension(size[0], size[1]));
        this.setPreferredSize(new Dimension(size[0], size[1]));
    }
    public void Set_MinSize (int[] size){ this.setMinimumSize  (new Dimension(size[0], size[1]));}
    public void Set_MaxSize (int[] size){ this.setMaximumSize  (new Dimension(size[0], size[1]));}
    public void Set_PrefSise(int[] size){ this.setPreferredSize(new Dimension(size[0], size[1]));}
    public void Set_TableColorPattern(){
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Colors.gray1 : Colors.white);
                return c;
            }
        });
    }


    protected void   Set_Table(){ 
        table = new JTable(content, categories){
            public boolean isCellEditable(int data, int categories){
                return false;
            }
        };
        //table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Colors.blue1);
        table.setShowGrid(false);
        table.setPreferredScrollableViewportSize(new Dimension(250, 75));
        table.setFillsViewportHeight(true);
    }
    public    JPanel Get_Component(){
        this.add(title);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        return this;
    }
    
}
