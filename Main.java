import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("How many players(including Banker) do you want?");
		int numOfPlayers = in.nextInt();
		TEGame game = new TEGame(numOfPlayers, 100);
		game.start();
		game.end();
	}
}
