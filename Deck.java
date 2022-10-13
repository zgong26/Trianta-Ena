
public interface Deck {
	public void resuffle();

	public Card pop();

	public Card popRandom();

	public void insert(int pos, Card card);
}
