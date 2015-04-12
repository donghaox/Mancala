
import javax.swing.*;
import java.awt.*;
public class Style1 extends JPanel
{
   protected void paintComponent(Graphics g){
      Image image = (new ImageIcon("sheep.jpg")).getImage();
      g.drawImage(image, 0, 0, null);
      super.paintComponent(g);
   }
}