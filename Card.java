
public class Card {
	private int rank;
	private String suit;
	private int id;
	private boolean fold;

	public Card(int rank, String suit, int id) {
		this.rank = rank;// 1-13
		this.suit = suit;
		this.id = id;
		fold = true;
	}

	public Card(int id) {
		this.id = id;// 1-52
		rank = id % 13 + 1;
		switch ((id - 1) / 13) {
		case 0:
			suit = "clubs";
			break;
		case 1:
			suit = "diamonds";
			break;
		case 2:
			suit = "hearts";
			break;
		case 3:
			suit = "spades";
			break;
		default:
			break;
		}
		fold = true;
	}

	public int getID() {
		return id;
	}

	public int getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	// flip the card and return if fold
	public boolean flip() {
		fold = !fold;
		return fold;
	}

	public boolean isFold() {
		return fold;
	}

	public String toString() {
		if (fold) {
			return "Folded Card";
		}
		String r = String.valueOf(rank);
		if (rank == 1)
			r = "Ace";
		if (rank == 11)
			r = "Jack";
		if (rank == 12)
			r = "Queen";
		if (rank == 13)
			r = "King";
		return r + " of " + suit;
	}
}
