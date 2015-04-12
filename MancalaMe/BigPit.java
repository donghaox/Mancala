
import java.awt.*;
import java.util.*;
public class BigPit extends Pit{
   
	public BigPit(Mancala mancala){
		this.setPreferredSize(new Dimension(100,150));
	}
	
   public BigPit(){
      
   }
	public void initStones(){
		stones = new ArrayList<Stone>();
	}


}
