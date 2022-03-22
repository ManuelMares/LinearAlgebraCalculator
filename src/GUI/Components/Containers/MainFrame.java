package GUI.Components.Containers;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Classes.Utilities.Colors;
import GUI.Components.Containers.*;
import GUI.Components.Tables.ConclusionUI;
import GUI.Components.Tables.MatrixUI;
import GUI.Components.Tables.PivotsUI;
import GUI.Components.Text.*;
import Methods.Controller.CreateMatrix;
import Methods.Controller.GetSize;

import java.awt.Color;

public class MainFrame extends JFrame {
    SectionScrollUI window;
    int[] size = {1100, 800};

    public MainFrame(){
        Set_Properties();
        Set_Window();
    }

    private void Set_Properties(){
        this.setTitle("Linear Algebra Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        this.setSize(size[0], size[1]);  
    }
    private void Set_Window(){
        window = new SectionScrollUI(size);
        window.Set_Size(size);
        //window.Set_BackgroundColor(color);
    }
    
    public void Set_Visible(){
        this.add(window);
        this.setVisible(true);
    }

    public void Clean_Frame(){        
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
        //Set_Properties();
        Set_Window();
        this.add(window);
        SwingUtilities.updateComponentTreeUI(this);
    }


    //Properties
    public void Set_Size(int[] size)                {window.setPreferredSize(new Dimension(size[0], size[1])); }    
    public void Set_BackgroundColor(Color color)    {this.setBackground(color);                              }


    //Methods
    
    public void Add_Component(SectionVerticalUI sectionVerticalUI)    {window.add(sectionVerticalUI.Get_Component()); }
    public void Add_Component(SectionCardinalUI sectionCardinalUI)    {window.add(sectionCardinalUI.Get_Component()); }
    public void Add_Component(SectionScrollUI sectionScrollUI)        {window.add(sectionScrollUI.Get_Component()); }
    public void Add_Component(CreateMatrix sectionVerticalUI)         {window.add(sectionVerticalUI.Get_Component()); }
    public void Add_Component(Division Division)                      {window.add(Division.Get_Component()); }
    public void Add_Component(TextUI textUI)                {window.add(textUI.Get_Component());       }
    public void Add_Component(Subtitle2UI subtitle2UI)      {window.add(subtitle2UI.Get_Component());  }
    public void Add_Component(Subtitle1UI subtitle1tUI)     {window.add(subtitle1tUI.Get_Component()); }
    public void Add_Component(TitleUI sitleUI)              {window.add(sitleUI.Get_Component());      }
    public void Add_Component(MatrixUI matrixUI)            {window.add(matrixUI.Get_Component());     }
    public void Add_Component(PivotsUI pivotsUI)            {window.add(pivotsUI.Get_Component());     }
    public void Add_Component(ConclusionUI conclusionUI)    {window.add(conclusionUI.Get_Component()); }
    public void Add_Component(JPanel conclusionUI)    {window.add(conclusionUI); }
}
