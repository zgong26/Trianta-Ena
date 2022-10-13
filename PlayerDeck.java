
public class PlayerDeck extends PokerDeck {
	public PlayerDeck() {
		super();
	}

	public boolean isNaturalTE() {
		if (deck.size() != 3)// can only occur when there are exact 3 cards
			return false;
		int c1 = deck.get(0).getRank();
		int c2 = deck.get(1).getRank();
		int c3 = deck.get(2).getRank();
		if (c1 + c2 == 2 || c1 + c3 == 2 || c2 + c3 == 2 || c1 + c2 + c3 != 31)// can't have two aces to exclude corner
																				// case such as Ace Ace 9
			return false;
		return true;
	}

	public int[] getCount() {
		int[] count = { 0, 0 };// first one big, second one small, for example, A A 3 would give value of {25,
								// 15}
		int ace = 0;
		int rank;
		for (Card c : deck) {
			rank = c.getRank();
			if (rank >= 11 && rank <= 13) {
				count[0] += 10;
				count[1] += 10;
			} else if (rank != 1) {
				count[0] += rank;
				count[1] += rank;
			} else {
				count[0] += 11;
				count[1] += ace >= 1 ? 11 : 1;
				ace++;
			}
		}
		return count;
	}
}
