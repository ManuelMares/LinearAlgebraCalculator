package GUI.Inputs;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import GUI.Components.Text.TextUI;

public class Input_Double extends JPanel{
    JTextField input;
    TextUI title;

    Input_Double(String title){
        set_Layout();
        Set_UI(title);
    }
    public JPanel Get_UI(){
        return this;
    }
    public Double Get_InputValue(int index){
        try {
            if(input.getText().equals(""))
                throw new Exception("At least one input is null");
            
            double value = Double.parseDouble( input.getText() );  
            System.out.println("value:" + value);       
            return value;   
        } catch (Exception e) {
            System.out.println(e);
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
