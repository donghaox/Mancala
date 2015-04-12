
import javax.swing.*;
import java.awt.event.*;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
public class Pit extends JComponent{
	protected ArrayList<Stone> stones = new ArrayList<Stone>();
	private boolean initialized;
	private boolean mouseOver;
   private Mancala mancala;
   private int counter;
   private Pit next;//linked list structure 
   private Player owner;
   private Player opponent;
   private Pit opposite;//empty the opposite pit
	
   public Pit(Mancala mancala){
      this.mancala = mancala;
		initialized = false;
		mouseOver = false;
		createListener();
	}

   public Pit(){
      
   }
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(mouseOver)
			g2.setColor(Color.green);
		g2.drawOval((int) (getWidth() * 0.1), (int) (getHeight() * 0.1), 
				(int) (getWidth() * 0.8), (int) (getHeight() * 0.8));	
		g2.setColor(Color.blue);
		if(!initialized){
		initStones();
		initialized = true;
		}
		for(Stone bean : stones)
		{
			int x = (int)(bean.getX() * getWidth());
			int y = (int)(bean.getY() * getHeight());
			g2.fillOval(x, y, 10, 10);
		}
	}
	
	public void addBeans(int num)
	{
		Random r = new Random();
		int x;
		int y;
		for(int i = 0; i < num; i++) {
            x = (int) ((getWidth() - 15) * 0.25) + r.nextInt((int) ((getWidth() -15) * 0.5));
            y = (int) ((getHeight() - 15) * 0.25) + r.nextInt((int) ((getHeight() -15) * 0.5));

            Stone bean = new Stone(1.0 * x / getWidth(), 1.0 * y / getHeight());
            	if(isSuitable(bean))
            		stones.add(bean);
            	else
            		i--;
		}
	}
	
    private boolean isSuitable(Stone bean) {
        for(Stone b : stones) {
                if(b.distanceFrom(bean, getWidth(), getHeight()) < 10.0) {
                        return false;
                }
        }
        return true;
    } 
      
    protected void initStones() {
            stones = new ArrayList<Stone>();
            addBeans(counter);
    }
   
    public void removeBeans()
    {
    	stones.clear();
    	this.repaint();
    }
        
    private void createListener()
    {
    	this.addMouseListener(new MouseAdapter(){
    		public void mouseEntered(MouseEvent e){
               if(clickable()){
    			mouseOver = true;
    			repaint();
               }
    		}
    		
    		public void mouseExited(MouseEvent e){
    			mouseOver = false;
    			repaint();
    		}
            
            public void mouseClicked(MouseEvent e){
               if (clickable()) {
               mancala.rememberLastCounters();
               move();
               if (mancala.checkWin()) {
                  
               }
               }
            }
    	});
    	
    		
    	
    }
   
   public void update(int counter)
   {
      this.counter = counter;
      removeBeans();
      addBeans(counter);
      repaint();
   }

   private void move(){
      Pit tempPot = next;
      Pit lastPosition = this;
      for (int i = 0; i < counter; i++) {
         tempPot.setCounter(1); 
         tempPot.update(tempPot.getCounter());
         lastPosition = tempPot;
         tempPot = tempPot.next;
         
      }
      counter = 0;
      removeBeans();
      if(lastPosition != owner.getBigPit()){//to check if last hit is bigPot
      owner.endTurn();
      opponent.startTurn();
      }
      
      if(lastPosition != owner.getBigPit() && lastPosition.getCounter() == 1 
      && lastPosition.getOpposite().getCounter() != 0){
         int c = lastPosition.getOpposite().getCounter();
         lastPosition.getOpposite().update(0);//clear the opposite stones
         //add the opposite stone to the owner's pit
         owner.getBigPit().setCounter(c);
         owner.getBigPit().update(owner.getBigPit().getCounter());
      }
   }
   
   public void setNext(Pit nextPot){
      next = nextPot;
   }
   
   public void setCounter(int n){
      this.counter += n;
   }
   
   public int getCounter(){
      return counter;
   }
   
   private boolean clickable(){
      return owner.isTurn() && getCounter() != 0;
   }
   
   public void setOwner(Player owner){
      this.owner = owner;
   }
   
   public void setOpponent(Player opponent){
      this.opponent = opponent;
   }
   
   public void setOpposite(Pit pot){
      opposite = pot;
   }
   
   public Pit getOpposite(){
      return opposite;
   }
}

