package GUI.Components.Text;
import java.awt.Color;
import java.awt.Font;

public class TextUI extends Text { 
    public TextUI(String message){
        Set_Properties(12, Font.PLAIN, Color.BLACK);
        Set_Message(message);
    }        
    public TextUI(String message, Color fontColor, Color backgroundColor){
        Set_Properties(12, Font.PLAIN, fontColor);
        Set_Background(backgroundColor);
        Set_Message(message);
    }        
}
