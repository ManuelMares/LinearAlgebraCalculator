package GUI.Components.Text;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public abstract class Text extends JLabel {
    JPanel container;

    public void Set_Properties(int fontSize, int style, Color color){
        container = new JPanel();
        container.setPreferredSize(new Dimension(600, fontSize*2));
        this.setPreferredSize(new Dimension(600, fontSize*2 ));
        this.setHorizontalAlignment(JLabel.LEFT); // TOP, LEFT, CENTER, RIGHT
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(new Font("Helvetica", style, fontSize));
    }

    public JPanel Get_Component(){
        container.add(this);
        return container;
    }
    
    public void Set_Message (String message)          { this.setText(message); }     
    public void Set_ContainerSize (int[] size)        { container.setPreferredSize(new Dimension(size[0], size[1]));}
    public void Set_Background (Color backgroundColor){ container.setBackground(backgroundColor); }
    public void Set_FontColor (Color color)           { this.setForeground(color);  }

    
}
