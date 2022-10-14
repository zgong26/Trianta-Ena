
public class PokerPlayer extends Player {
	private PlayerDeck playerdeck;// A poker deck that the player gets
	private final int bust = 31;
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
		if (small > bust)
			return true;
		return false;
	}

	// return the current card value sum
	public int stand() {
		int[] res = playerdeck.getCount();
		int big = res[0];
		int small = res[1];
		if (big > bust)
			cardValue = small;
		else
			cardValue = Math.max(small, big);
		playerdeck.clear();
		return cardValue;
	}

	public int getValue() {
		int[] res = playerdeck.getCount();
		int big = res[0];
		int small = res[1];
		if (big > bust)
			cardValue = small;
		else
			cardValue = Math.max(small, big);
		playerdeck.clear();
		return cardValue;
	}

	public PlayerDeck getDeck() {
		return playerdeck;
	}

	public String getCards() {
		return playerdeck.toString();
	}

	public String getFaceupCards() {
		return playerdeck.getFaceupCards();
	}
	
	public void clear() {
		playerdeck.clear();
	}
}
