package GUI.Components.Containers;
import java.awt.Color;
import javax.swing.*;

public class SectionVerticalUI extends SectionVertical{
    //Constructors
    public SectionVerticalUI(Color color){
        this.setBackground(color);
    }
    public SectionVerticalUI(){
    }

    public JPanel Get_Component()   {return this; }
}
