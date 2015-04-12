
import java.util.*;
public class Player {
        private String name;
        private boolean turn;
        private BigPit bigPit;
        private ArrayList<Pit> pits;
        private Mancala mancala;
        protected int undoLimits;
        
        public Player(Mancala mancala) {
                this.mancala = mancala;
               undoLimits = 3;
        }
        
        public String getName() {
                return name;
        }
        
        public Mancala getMancala() {
                return mancala;
        }
        
        public void setName(String name) {
                this.name = name;
        }
        
        public boolean isTurn() {
                return turn;
        }
        
        public void startTurn() {
                this.turn = true;
        }
        
        public void endTurn() {
                this.turn = false;
        }
        
        public int getScore() {
                return bigPit.getCounter();
        }
        
        public void setSmallPits(ArrayList<Pit> pits) {
               this.pits = pits;
        }
        
        public ArrayList<Pit> getPits() {
                return pits;
        }
        
        public void setBigPit(BigPit pit) {
                bigPit = pit;
        }
        
        public BigPit getBigPit() {
                return bigPit;
        }
        
        public boolean hasCounters() {
                for(Pit s : pits) {
                        if(s.getCounter() != 0)
                                return true;
                }
                return false;
            

        }

}