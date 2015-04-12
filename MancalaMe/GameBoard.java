
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameBoard extends JFrame {
	private Pit[] pits;
	private BigPit bigPit1, bigPit2;
	final private Mancala mancala;
	private TextField enterNumberOfPits = new TextField(
			"Please enter max of 4 pits");
	private JButton jbtOk = new JButton("OK");
	private JButton jbtUndo = new JButton("Undo");
	protected int count = 0;

	public GameBoard(final Mancala mancala) {
		this.mancala = mancala;
		setTitle("Mancala");
		setSize(900, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// put pots into a container
		setLayout(new BorderLayout());
		JPanel center = new JPanel(new GridLayout(2, 6));
		 center.setOpaque(false);

		JPanel p2 = new JPanel(new BorderLayout());
      p2.add(center, BorderLayout.CENTER);

		// creates and add pots here
		createPits();
		int j = 6;
		for (int i = 11; i > 5; i--) {
			JPanel temp = new JPanel();
           temp.setLayout(new BorderLayout());
           temp.setOpaque(false);
			temp.add(pits[i], BorderLayout.CENTER);
			temp.add(new JLabel("B" + j), BorderLayout.NORTH);
			center.add(temp);
			j--;
		}
		j = 1;
		for (int i = 0; i < 6; i++) {
			JPanel temp = new JPanel();
           temp.setLayout(new BorderLayout());
          temp.setOpaque(false);
			temp.add(pits[i], BorderLayout.CENTER);
			temp.add(new JLabel("A" + j), BorderLayout.SOUTH);
			center.add(temp);
			j++;
		}

		// create big pots
       mancala.style.setOpaque(false);
       mancala.style.setLayout(new BorderLayout());
       mancala.style.add(bigPit1, BorderLayout.CENTER);
		add(mancala.style, BorderLayout.EAST);

        mancala.style2.setOpaque(false);
       mancala.style2.setLayout(new BorderLayout());
       mancala.style2.add(bigPit2, BorderLayout.CENTER);
      add(mancala.style2, BorderLayout.WEST);

		add(p2, BorderLayout.CENTER);
		JPanel p3 = new JPanel();
		p3.add(enterNumberOfPits);
		p3.add(jbtOk);
		p3.add(jbtUndo);
		add(p3, BorderLayout.SOUTH);
		// listener for jbtOk
		jbtOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = enterNumberOfPits.getText();
				count = Integer.parseInt(str);
				mancala.counterChanged(count);
			}
		});

		jbtUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mancala.notifyObservers();
			}
		});
		setVisible(true);
	}

	private void createPits() {
		// bigPot1 = new BigPot(mancala);
		// bigPot2 = new BigPot(mancala);
		pits = new Pit[12];
		/*
		 * for(int i = 0; i < pots.length; i++){ pots[i] = new Pot(mancala);
		 * mancala.addObserver(pots[i]); }
		 */

		for (int i = 0; i < 6; i++) {
			pits[i] = new Pit(mancala);
			mancala.addObserver(pits[i]);
		}
		bigPit1 = new BigPit(mancala);
		mancala.addObserver(bigPit1);

		for (int i = 6; i < 12; i++) {
			pits[i] = new Pit(mancala);
			mancala.addObserver(pits[i]);
		}
		bigPit2 = new BigPit(mancala);
		mancala.addObserver(bigPit2);

		// set nexts for all pots
		for (int i = 0; i < 11; i++) {
			pits[i].setNext(pits[i + 1]);
		}

		pits[5].setNext(bigPit1);
		bigPit1.setNext(pits[6]);
		pits[11].setNext(bigPit2);
		bigPit2.setNext(pits[0]);

	}
}
