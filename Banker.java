
public class Banker extends Player {
	private PokerDeck pokerdeck;// A poker deck that needs to be distributed
	private PlayerDeck playerdeck;// A poker deck that the banker gets
	private int cardValue;
	private final int bust = 31;
	private final int limit = 27;

	// situation when another player becomes banker, inherit info
	public Banker(String name, int score, PokerDeck pokerdeck) {
		this.name = name;
		this.score = score;
		this.pokerdeck = pokerdeck;
		playerdeck = new PlayerDeck();
		cardValue = 0;
	}

	// situation when a new banker is created
	public Banker(String name, int score) {
		super.name = name;
		super.score = score;
		pokerdeck = new PokerDeck();
		pokerdeck.initializeDeck();
		pokerdeck.resuffle();
		playerdeck = new PlayerDeck();
		cardValue = 0;
	}

	public Card distribute(boolean fold) {
		Card c = pokerdeck.pop();
		if (!fold)
			c.flip();
		return c;
	}

	public void hit(boolean fold) {
		Card c = distribute(fold);
		playerdeck.insert(c);
	}

	public int BankerTurn() {
		// banker hit on soft 27
		int[] arr = playerdeck.getCount();
		cardValue = (arr[0] == 27 && arr[1] == 17) ? 17 : arr[0];
		while (cardValue < limit) {
			playerdeck.insert(distribute(false));
			int[] res = playerdeck.getCount();
			int big = res[0];
			int small = res[1];
			cardValue = big > bust ? small : big;
		}
		return cardValue;
	}

	public String getCards() {
		return playerdeck.toString();
	}

}
