package GUI.Controllers;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import Classes.Utilities.Colors;
import GUI.Components.Text.*;

public class InverseUI extends JPanel{
    
    public InverseUI(){
        this.setBackground(Colors.green1);
        this.setPreferredSize(new Dimension(100,100));
    }


    
    public void Set_Background(Color color){
        this.setBackground(color);
    }


    public JPanel Get_Component(){
        return this;
    }

    
    public void Add_Component(TextUI text){
        this.add(text.Get_Component());
    }
    
}
