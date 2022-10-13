import java.util.ArrayList;
import java.util.Scanner;

public class TEGame {
	private ArrayList<Player> players;
	private int numOfPlayers;
	private int playerMoney;
	private int bankerMoney;
	private int[] bets;
	private int bankerPos;
	Scanner in;

	public TEGame(int num, int playerMoney) {
		numOfPlayers = num;
		players = new ArrayList<>();
		this.playerMoney = playerMoney;
		bankerMoney = 3 * playerMoney;
		bets = new int[num];
		in = new Scanner(System.in);
	}

	public void entry() {
		initialize();

		// distribute the first card
		distributeFirstCard();
		firstBet();
		distributeNextTwoCards();
		gameloop();

		// testing
		Banker b = (Banker) players.get(0);
		PokerPlayer p1 = (PokerPlayer) players.get(1);
		System.out.println(b.getCards());
		System.out.println(p1.getCards());
		System.out.println(p1.getScore());
	}

	private void initialize() {
		bankerPos = 0;
		players.add(new Banker("p1", bankerMoney));
		System.out.println("\nBanker p1 added");
		for (int i = 2; i <= numOfPlayers; i++) {
			String name = "p" + i;
			players.add(new PokerPlayer(name, playerMoney));
			System.out.printf("Player %s added\n", name);
		}
		System.out.println("");
	}

	private void distributeFirstCard() {
		Banker b = (Banker) players.get(bankerPos);
		b.hit(false);
		int i = (bankerPos + 1) % players.size();
		for (int j = (bankerPos + 1) % players.size(); j != bankerPos; j = (j + 1) % players.size()) {
			PokerPlayer p = (PokerPlayer) players.get(j);
			p.hit((Banker) players.get(0), true);
		}
	}

	private void firstBet() {
		// Banker b = (Banker) players.get(bankerPos);
		bets[bankerPos] = -1;
		// int i = (bankerPos + 1) % players.size();
		for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
			Player p = (PokerPlayer) players.get(i);
			System.out.println("How much would you like to bet? (Enter 0 for fold)");
			int bet = in.nextInt();
			while (bet > p.getScore()) {
				System.out.printf("You have only %d dollars, please rebet: \n", p.getScore());
				bet = in.nextInt();
			}
			if (bet <= 0) {
				bets[i] = 0;
			} else {
				bets[i] = bet;
				p.subScore(bet);
			}
		}
	}

	private void distributeNextTwoCards() {
		Banker b = (Banker) players.get(bankerPos);
		for (int i = 0; i < 2; i++) {
			for (int j = (bankerPos + 1) % players.size(); j != bankerPos; j = (j + 1) % players.size()) {
				PokerPlayer p = (PokerPlayer) players.get(j);
				if (bets[j] > 0)
					p.hit(b, false);
			}
		}
	}

	private void gameloop() {
		Banker b = (Banker) players.get(bankerPos);
		for (int i = 0; i < players.size(); i++) {

		}
	}
}
