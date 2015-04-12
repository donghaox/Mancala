
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Mancala {
   private GameBoard gameBoard;
   private Player playerOne;
   private Player playerTwo;
   private int[] counters;
   private ArrayList<Pit> observers;
   protected JPanel style;
   protected JPanel style2;
   
   public void setStyle(JPanel style, JPanel style2){
      this.style = style;
      this.style2 = style2;
   }
   public Mancala()
   {
      //counters from 0 - 6 belongs to playerOne, 7 - 13 belongs to playerTwo
      counters = new int[14];// 6 is mancalaOne, and 13 is mancalaTwo
      observers = new ArrayList<Pit>();
    //  gameBoard = new GameBoard(this);
     // initPlayers();
   }
   
   public void startGame(){
      gameBoard = new GameBoard(this);
      initPlayers();
   }
   public int[] getCounters()
   {
      return counters;
   }
   
   public void addObserver(Pit pit)
   {
      observers.add(pit);
   }
   
   public void notifyObservers()
   {
      for(int i = 0; i < observers.size(); i++)
      {
         observers.get(i).update(counters[i]);
      }
   }
   
   public void counterChanged(int counter){
      for (int i = 0 ; i < 6;i++) {
         counters[i] = counter;
      }
      counters[6] = 0;
      counters[13] = 0; //set the bigpPits to 0
      for (int i = 7; i < 13; i++) {
         counters[i] = counter;
      }
      notifyObservers();
   }
   
     public boolean checkWin() {
             if(playerOne.hasCounters() && playerTwo.hasCounters())
                     return false;
             else {
                     if(playerOne.hasCounters()) {
                             for(Pit s : playerOne.getPits()) {
                                     playerOne.getBigPit().setCounter(s.getCounter());
                                    s.update(0);
                             }
                           BigPit bigPot = playerOne.getBigPit();
                           bigPot.update(bigPot.getCounter());
                     } else if(playerTwo.hasCounters()) {
                             for(Pit s : playerTwo.getPits()) {
                                     playerTwo.getBigPit().setCounter(s.getCounter());
                                    s.update(0);
                             }
                           BigPit bigPot = playerTwo.getBigPit();
                           bigPot.update(bigPot.getCounter());
                     }
             }
             
             if(playerOne.getScore() > playerTwo.getScore()) {
                     JOptionPane.showMessageDialog(null, "Player one won", "Game Over", JOptionPane.INFORMATION_MESSAGE);
             } else if(playerOne.getScore() < playerTwo.getScore()) {
                     JOptionPane.showMessageDialog(null, "Player two won", "Game Over", JOptionPane.INFORMATION_MESSAGE);
             } else {
                     JOptionPane.showMessageDialog(null, "It was a tie", "Game Over", JOptionPane.INFORMATION_MESSAGE);
             }
             
             return true;
     }
   
   public void initPlayers(){
      playerOne = new Player(this);
      playerTwo = new Player(this);
      //assign bigpot to each player
      playerOne.setBigPit((BigPit)observers.get(6));
      playerTwo.setBigPit((BigPit)observers.get(13));
      
      ArrayList<Pit> pots1 = new ArrayList<Pit>();
      ArrayList<Pit> pots2 = new ArrayList<Pit>();
      //assign normal pots to each player;
      int j = 12;
      for(int i = 0; i < 6; i++)
      {
      pots1.add(observers.get(i));
      observers.get(i).setOwner(playerOne);
      observers.get(i).setOpponent(playerTwo);
      observers.get(i).setOpposite(observers.get(j));
      j--;

      }
      
      j = 5;
      for(int i = 7; i < 13; i++)
      {
      pots2.add(observers.get(i));
      observers.get(i).setOwner(playerTwo);
      observers.get(i).setOpponent(playerOne);
      observers.get(i).setOpposite(observers.get(j));
      j--;
      }
      
      playerOne.setSmallPits(pots1);
      playerTwo.setSmallPits(pots2);
      
      playerOne.startTurn();
      playerTwo.endTurn();
      
   }
   
   public void rememberLastCounters(){
      for (int i = 0; i < 14; i++) {
         counters[i] = observers.get(i).getCounter();
      }
   }
}
