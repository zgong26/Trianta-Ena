//a generic player
public class Player {
	protected String name;
	public int score;

	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public Player(String name) {
		this(name, 0);
	}

	public Player() {
		this("Player");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void subScore(int score) {
		this.score -= score;
	}
}