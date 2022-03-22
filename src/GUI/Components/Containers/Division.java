package GUI.Components.Containers;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import Classes.Utilities.Colors;
import java.awt.BorderLayout;

public class Division extends JPanel{
    
    public Division(Color color){
        this.setBackground(color);
        this.setPreferredSize(new Dimension(750,30));
        this.setLayout(new BorderLayout());
    }
    
    public Division(){
        this.setBackground(Colors.white);
        this.setPreferredSize(new Dimension(750,30));
        this.setLayout(new BorderLayout());
    }
    public void     Set_Color(Color color){
        this.setBackground(color);
    }
    public void Set_Padding(int value){
        this.setLayout(new BorderLayout(value, value));
    }

    public void Set_Size(int[] size){
        this.setPreferredSize(new Dimension(size[0], size[1]));
    }
    
    public void Set_Background(Color color){
        this.setBackground(color);
    }


    public JPanel Get_Component(){
        return this;
    }

    
}
