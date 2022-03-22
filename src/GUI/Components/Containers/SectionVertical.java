package GUI.Components.Containers;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

import GUI.Components.Tables.*;
import GUI.Components.Text.*;

public abstract class SectionVertical extends JPanel{
    //Constructors
    public SectionVertical(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    //Properties
    public void Set_Size(int[] size)                {this.setPreferredSize(new Dimension(size[0], size[1])); }    
    public void Set_BackgroundColor(Color color)    {this.setBackground(color);                              }
    public void Set_SizeForced(int[] size)                {
        this.setPreferredSize(new Dimension(size[0], size[1])); 
        this.setMaximumSize(new Dimension(size[0], size[1])); 
        this.setMinimumSize(new Dimension(size[0], size[1])); 
    }  

    //Methods
    public void Add_Component(SectionVerticalUI sectionVerticalUI)    {this.add(sectionVerticalUI.Get_Component()); }
    public void Add_Component(SectionCardinalUI sectionCardinalUI)    {this.add(sectionCardinalUI.Get_Component()); }
    public void Add_Component(SectionScrollUI sectionScrollUI)        {this.add(sectionScrollUI.Get_Component()); }
    public void Add_Component(Division Division)                      {this.add(Division.Get_Component()); }
    public void Add_Component(TextUI textUI)                {this.add(textUI.Get_Component());       }
    public void Add_Component(Subtitle2UI subtitle2UI)      {this.add(subtitle2UI.Get_Component());  }
    public void Add_Component(Subtitle1UI subtitle1tUI)     {this.add(subtitle1tUI.Get_Component()); }
    public void Add_Component(TitleUI sitleUI)              {this.add(sitleUI.Get_Component());      }
    public void Add_Component(MatrixUI matrixUI)            {this.add(matrixUI.Get_Component());     }
    public void Add_Component(PivotsUI pivotsUI)            {this.add(pivotsUI.Get_Component());     }
    public void Add_Component(ConclusionUI conclusionUI)    {this.add(conclusionUI.Get_Component()); }
    public void Add_Component(JPanel conclusionUI)    {this.add(conclusionUI); }
    
}
