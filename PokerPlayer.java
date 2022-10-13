
public class PokerPlayer extends Player {
	private PlayerDeck playerdeck;// A poker deck that the player gets
	private final int limit = 31;
	private int cardValue;

	public PokerPlayer(String name, int score) {
		this.name = name;
		this.score = score;
		playerdeck = new PlayerDeck();
		cardValue = 0;
	}

	// return value indicates if busted
	public boolean hit(Banker b, boolean fold) {
		Card newCard = b.distribute(fold);
		playerdeck.insert(newCard);
		int small = playerdeck.getCount()[1];
		if (small > limit)
			return false;
		return true;
	}

	// return the current card value sum, can be over 31
	public int stand() {
		int[] res = playerdeck.getCount();
		int big = res[0];
		int small = res[1];
		if (big > limit)
			cardValue = small;
		else
			cardValue = Math.max(small, big);
		playerdeck.clear();
		return cardValue;
	}

	public String getCards() {
		return playerdeck.toString();
	}
}
