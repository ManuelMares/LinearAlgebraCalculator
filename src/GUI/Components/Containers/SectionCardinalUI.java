package GUI.Components.Containers;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

import java.awt.BorderLayout;

public class SectionCardinalUI extends JPanel{
    //Constructors
    public SectionCardinalUI(){}
    public SectionCardinalUI(int value){ this.setLayout(new BorderLayout(value, value));}

    public JPanel Get_Component()   {return this; }

    //Properties
    public void Set_Size(int[] size)                {this.setPreferredSize(new Dimension(size[0], size[1])); }    
    public void Set_BackgroundColor(Color color)    {this.setBackground(color);                              }


    //Methods
    public void Add_Component(SectionVerticalUI section, String position){
        switch (position.toUpperCase()) {
            case "NORTH" : this.add(section.Get_Component(), BorderLayout.NORTH);  break;
            case "SOUTH" : this.add(section.Get_Component(), BorderLayout.SOUTH);  break;
            case "CENTER": this.add(section.Get_Component(), BorderLayout.CENTER); break;
            case "WEST"  : this.add(section.Get_Component(), BorderLayout.WEST);   break;
            case "EAST"  : this.add(section.Get_Component(), BorderLayout.EAST);   break;        
            default: break;
        }
    }
    public void Add_Component(SectionCardinalUI scrollSection, String position){
        switch (position.toUpperCase()) {
            case "NORTH" : this.add(scrollSection.Get_Component(), BorderLayout.NORTH);  break;
            case "SOUTH" : this.add(scrollSection.Get_Component(), BorderLayout.SOUTH);  break;
            case "CENTER": this.add(scrollSection.Get_Component(), BorderLayout.CENTER); break;
            case "WEST"  : this.add(scrollSection.Get_Component(), BorderLayout.WEST);   break;
            case "EAST"  : this.add(scrollSection.Get_Component(), BorderLayout.EAST);   break;        
            default: break;
        }
    }
    public void Add_Component(SectionScrollUI scrollSection, String position){
        switch (position.toUpperCase()) {
            case "NORTH" : this.add(scrollSection.Get_Component(), BorderLayout.NORTH);  break;
            case "SOUTH" : this.add(scrollSection.Get_Component(), BorderLayout.SOUTH);  break;
            case "CENTER": this.add(scrollSection.Get_Component(), BorderLayout.CENTER); break;
            case "WEST"  : this.add(scrollSection.Get_Component(), BorderLayout.WEST);   break;
            case "EAST"  : this.add(scrollSection.Get_Component(), BorderLayout.EAST);   break;        
            default: break;
        }
    }
    
}
