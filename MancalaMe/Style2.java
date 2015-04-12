
import javax.swing.*;
import java.awt.*;
public class Style2 extends JPanel
{
   protected void paintComponent(Graphics g){
      Image image = (new ImageIcon("happyFace.png")).getImage();
      g.drawImage(image, 0, 0, null);
      super.paintComponent(g);
   }
}