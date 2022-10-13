import java.util.*;

public class PokerDeck implements Deck {
	ArrayList<Card> deck;

	public PokerDeck() {
		deck = new ArrayList<Card>();
	}

	public void initializeDeck() {
		for (int i = 1; i <= 52; i++) {
			deck.add(new Card(i));
		}
	}

	@Override
	public void resuffle() {
		// Fisher–Yates shuffle
		for (int i = deck.size() - 1; i > 0; i--) {
			int max = i;
			int min = 0;
			int rand = randInt(min, max);
			Collections.swap(deck, rand, i);
		}
	}

	@Override
	public Card pop() {
		return deck.remove(0);
	}

	@Override
	public Card popRandom() {
		int min = 0;
		int max = deck.size() - 1;
		int rand = randInt(min, max);
		return deck.remove(rand);
	}

	@Override
	public void insert(int pos, Card card) {
		deck.add(pos, card);
	}

	public void insert(Card card) {
		deck.add(card);
	}

	public void clear() {
		deck.clear();
	}

	public int size() {
		return deck.size();
	}

	private int randInt(int min, int max) {
		// min and max inclusive
		int range = max - min + 1;
		int rand = (int) (Math.random() * range) + min;
		return rand;
	}

	public String toString() {
		String res = "";
		for (Card c : deck) {
			res += c + "\n";
		}
		return res;
	}
}
