import java.util.ArrayList;
import java.util.Arrays;
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
		while (players.size() > 1) {
			distributeFirstCard();
			firstBet();
			if (!distributeNextTwoCards()) {
				body();
			}
			boolean over = gameover();
			if (over)
				break;
			rotate();
		}
		System.out.println("Game Over");

		// testing
		// Banker b = (Banker) players.get(0);
		// PokerPlayer p1 = (PokerPlayer) players.get(1);
		// System.out.println("\n\n" + b.getCards());
		// System.out.println(p1.getCards());
		// System.out.println(p1.getScore());
	}

	private void initialize() {
		bankerPos = 0;
		players.add(new Banker("p1", bankerMoney));
		System.out.println("Banker p1 added");
		for (int i = 2; i <= numOfPlayers; i++) {
			String name = "p" + i;
			players.add(new PokerPlayer(name, playerMoney));
			System.out.printf("Player %s added\n", name);
		}
		System.out.println("\nGame start!\n");
	}

	private void distributeFirstCard() {
		Banker b = (Banker) players.get(bankerPos);
		b.hit(false);
		System.out.printf("Banker %s's first card: %s\n", b.getName(), b.getCards());
		int i = (bankerPos + 1) % players.size();
		for (int j = (bankerPos + 1) % players.size(); j != bankerPos; j = (j + 1) % players.size()) {
			PokerPlayer p = (PokerPlayer) players.get(j);
			p.hit(b, true);
			// System.out.printf("Player %s's first card: %s\n" ,p.getName(),p.getCards());
		}
	}

	private void firstBet() {
		// Banker b = (Banker) players.get(bankerPos);
		bets[bankerPos] = -1;
		// int i = (bankerPos + 1) % players.size();
		for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
			PokerPlayer p = (PokerPlayer) players.get(i);
			System.out.printf("Player %s, your first card is %s\n", p.getName(), p.getFaceupCards());
			System.out.println("How much would you like to bet? (Enter 0 for fold)\nYou currently have " + p.getScore()
					+ " dollars ");
			int bet = in.nextInt();
			while (bet > p.getScore()) {
				System.out.printf("You have only %d dollars, please rebet: \n", p.getScore());
				bet = in.nextInt();
			}
			in.nextLine();
			if (bet <= 0) {
				bets[i] = 0;
			} else {
				bets[i] = bet;
				// p.subScore(bet);
			}
		}
	}

	private boolean distributeNextTwoCards() {
		Banker b = (Banker) players.get(bankerPos);
		for (int i = 0; i < 2; i++) {
			for (int j = (bankerPos + 1) % players.size(); j != bankerPos; j = (j + 1) % players.size()) {
				PokerPlayer p = (PokerPlayer) players.get(j);
				if (bets[j] > 0)
					p.hit(b, false);
			}
			b.hit(true);
		}
		// return true if the banker gets trianta ena, and game over
		boolean over = b.getDeck().isNaturalTE();
		if (over) {
			System.out.println("The dealer gets Trianta Ena! All players lose");
			for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
				PokerPlayer p = (PokerPlayer) players.get(i);
				p.subScore(bets[i]);
				b.addScore(bets[i]);
			}
		}
		return over;
	}

	private void body() {
		Banker b = (Banker) players.get(bankerPos);
		for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
			if (bets[i] <= 0) {
				continue;
			}
			PokerPlayer p = (PokerPlayer) players.get(i);
			int[] arr = p.getDeck().getCount();
			String val = arr[0] == arr[1] ? String.valueOf(arr[0])
					: String.valueOf(arr[0]) + " or " + String.valueOf(arr[1]);
			System.out.printf("Player %s, your cards are: \n%sValue: %s\n\n", p.getName(), p.getFaceupCards(), val);
			if (p.getDeck().isNaturalTE()) {
				System.out.println("Congratulations, you got Trianta Ena!!\n");
				p.addScore(bets[i]);
				b.subScore(bets[i]);
				continue;
			} else {
				System.out.printf("Banker's card: \n%s\n", b.getCards());
				System.out.println("Would you like to hit or stand? (Enter \"hit\" or \"stand\")");
				String res = in.nextLine().toLowerCase();
				// res = in.nextLine().toLowerCase();
				boolean busted = false;
				while (!res.equals("stand")) {
					busted = p.hit(b, false);
					arr = p.getDeck().getCount();
					val = arr[0] == arr[1] ? String.valueOf(arr[0])
							: arr[0] <= 31 ? String.valueOf(arr[0]) + " or " + String.valueOf(arr[1])
									: String.valueOf(arr[1]);
					System.out.printf("Your cards are: \n%sValue: %s\n\n", p.getFaceupCards(), val);
					if (busted)
						break;
					System.out.println("Would you like to hit or stand? (Enter \"hit\" or \"stand\")");
					res = in.nextLine().toLowerCase();
				}
				if (busted) {
					System.out.println("Busted!\n");
					p.subScore(bets[i]);
					b.addScore(bets[i]);
					bets[i] = 0;
				} else {
					System.out.println("Stand! Current card value is "
							+ (p.getDeck().getCount()[0] <= 31 ? p.getDeck().getCount()[0] : p.getDeck().getCount()[1])
							+ "\n");
				}
			}
		}
		int bankerValue = b.BankerTurn();
		System.out.printf("Banker's card: \n%s\n", b.getFaceupCards());
		if (bankerValue > 31) {
			System.out.println("Banker busted! All remaining players win!");
			int loss = 0;
			for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
				if (bets[i] <= 0)// skip folded player and busted player
					continue;
				PokerPlayer p = (PokerPlayer) players.get(i);
				System.out.printf("Player %s gets %d dollars!\n", p.getName(), bets[i]);
				p.addScore(bets[i]);
				b.subScore(bets[i]);
				loss += bets[i];
			}
			System.out.printf("Banker %s loses %d dollars!\n", b.getName(), loss);
		} else {
			System.out.println("Banker's current card value: " + bankerValue);
			for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
				if (bets[i] <= 0)
					continue;
				PokerPlayer p = (PokerPlayer) players.get(i);
				int pVal = p.getValue();
				if (bankerValue >= pVal) {
					System.out.printf("Player %s loses(%d against %d). Lose %d dollars!\n", p.getName(), pVal,
							bankerValue, bets[i]);

					p.subScore(bets[i]);
					b.addScore(bets[i]);
				} else {
					System.out.printf("Player %s wins(%d against %d). Win %d dollars!\n", p.getName(), pVal,
							bankerValue, bets[i]);
					p.addScore(bets[i]);
					b.subScore(bets[i]);
				}
			}
		}
	}

	private boolean gameover() {
		// reset the game
		Banker b = (Banker) players.get(bankerPos);
		System.out.printf("\nMoney summary:\nBanker %s: %d dollars\n", b.getName(), b.getScore());
		for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
			PokerPlayer p = (PokerPlayer) players.get(i);
			if (p.getScore() > 0) {
				System.out.printf("Player %s: %d dollars\n", p.getName(), p.getScore());
				p.clear();
			} else {
				System.out.printf("Player %s: %d dollars(Kicked from the game)\n", p.getName(), p.getScore());
				kick(i);
				i--;
			}
		}
		if (players.size() <= 1) {
			return true;
		}
		System.out.println("Enter player's name to cashout, enter 'no' to continue the game");
		String cashout = in.nextLine();
		while (!cashout.equals("no")) {
			while (!cashOut(cashout) && !cashout.equals("no")) {
				System.out.println("Invalid player, please re-enter the name, enter 'no' to stop");
				cashout = in.nextLine();
			}
			if (cashout.equals("no"))
				break;
			System.out.println("Anyone else to cashout? Enter 'no' to continue");
			cashout = in.nextLine();
		}

		System.out.println("Round over");
		Arrays.fill(bets, 0);
		b.clear();
		return players.size() <= 1;
	}

	private void kick(int pos) {
		players.remove(pos);
	}

	private boolean cashOut(String name) {
		for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
			PokerPlayer p = (PokerPlayer) players.get(i);
			if (name.equals(p.getName())) {
				System.out.printf("Player %s cashout successfully!\n", name);
				kick(i);
				return true;
			}
		}
		return false;
	}

	private void rotate() {
		Banker b = (Banker) players.get(bankerPos);
		int bankerMoney = b.getScore();
		boolean exist = true;
		int maxMoney = Integer.MIN_VALUE;
		int index = 0;
		boolean[] visited = new boolean[players.size()];
		Arrays.fill(visited, false);
		while (exist) {
			maxMoney = Integer.MIN_VALUE;
			for (int i = (bankerPos + 1) % players.size(); i != bankerPos; i = (i + 1) % players.size()) {
				PokerPlayer p = (PokerPlayer) players.get(i);
				if (p.getScore() > maxMoney && !visited[i]) {
					maxMoney = p.getScore();
					index = i;
				}
			}
			if (maxMoney <= bankerMoney) {
				exist = false;
			} else {
				PokerPlayer p = (PokerPlayer) players.get(index);
				System.out.printf(
						"Player %s, you have more money than the banker, would you like to be the banker? (y/N)\n",
						p.getName());
				String ans = in.nextLine().toLowerCase();
				if (ans.equals("y")) {
					Banker pre = (Banker) players.get(bankerPos);
					players.set(bankerPos, new PokerPlayer(pre.getName(), pre.getScore()));
					bankerPos = index;
					players.set(bankerPos, new Banker(p.getName(), p.getScore()));
					System.out.printf("Player %s is now the new banker.\n", p.getName());
					exist = false;
				} else {
					visited[index] = true;
				}
			}
		}
	}
}
