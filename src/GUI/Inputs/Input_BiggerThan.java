package GUI.Inputs;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import GUI.Components.Text.TextUI;

public class Input_BiggerThan extends JPanel{
    JTextField input;
    TextUI title;
    int minimumValue;

    public Input_BiggerThan(int minimumValue, String title){
        this.minimumValue = minimumValue;
        set_Layout();
        Set_UI(title);
    }
    public JPanel Get_UI(){
        return this;
    }
    public Double Get_Value(){
        try {
            double value = Integer.parseInt( input.getText() );
            if(value >= minimumValue)
                return value;      
            return null;   
        } catch (Exception e) {
            System.out.println("There has been one mistake getting the input bigger than " + minimumValue);
            return null;
        }

    }

    private void set_Layout() {
        this.setLayout(new GridLayout(1, 2)); 
        Dimension dimension = new Dimension(200, 20);
        this.setMaximumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
    }
    private void Set_UI(String title){
        this.title = new TextUI(title);        
        input = new JTextField(10);

        this.add(this.title);
        this.add(input);
    }
    public  void Set_Title(String title){
        this.title = new TextUI(title);
    }
    

}
