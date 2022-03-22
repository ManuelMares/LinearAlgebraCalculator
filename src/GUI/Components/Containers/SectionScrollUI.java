package GUI.Components.Containers;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.Color;

import javax.swing.JScrollPane;

public class SectionScrollUI extends SectionVertical{
    int[] size;
    public SectionScrollUI(){}
    public SectionScrollUI(int[] size){
        this.size = size;
    }

    @Override
    public void Set_Size(int[] size){
        this.size = size;
        this.setMinimumSize  (new Dimension(size[0], size[1]));
        this.setMaximumSize  (new Dimension(size[0], size[1]));
        this.setPreferredSize(new Dimension(size[0], size[1]));
    }
    public void Set_MinSize (int[] size){ this.setMinimumSize  (new Dimension(size[0], size[1]));}
    public void Set_MaxSize (int[] size){ this.setMaximumSize  (new Dimension(size[0], size[1]));}
    public void Set_PrefSise(int[] size){ this.setPreferredSize(new Dimension(size[0], size[1]));}
    
    public JScrollPane Get_Component(){
        JScrollPane panel = new JScrollPane(this, 
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.setMaximumSize(new Dimension(size[0], size[1]));
        panel.setPreferredSize(new Dimension(size[0], size[1]));
        return panel;
    }

}
