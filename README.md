# CS611-Assignment 2
## Trianta Ena
---------------------------------------------------------------------------
Ziqi Gong
zgong@bu.edu

## Files
---------------------------------------------------------------------------
1. Card.java
A class that defines a poker card object. It contains rank, suit, id, whether it is fold. 

2. Deck.java
An interface that defines a poker deck including some essential methods that a poker deck must have.

3. PokerDeck.java
A class that defines a poker deck object which implements the Deck interface. It has some functions involving deck operations such as shuffle, pop cards, insert cards, etc. It theoretically should represent the behavior of a standard 52-card deck.  

4. PlayerDeck.java
A class that defines another poker deck that usually starts from empty(a deck that a player holds), which extends from PokerDeck class. It contains more methods besides super methods such as check whether the deck forms natural Trianta Ena, count the current rank of the deck.

5. Player.java
A generic player class that can fit most of the games yet lacks of very specific functionalities. It is also used in TTT game. It has some basic properties of a player such as name and score

6. PokerPlayer.java
A class that defines a poker player at the table(not the banker/dealer) and extends from Player class. It has the ability to hit, stand, get deck and cards, etc.

7. Banker.java
A class that defines a banker(same as dealer in this game) and extends from Player class. It can distribute cards to players, hit for themselves and automatically finish the banker's round(hit until 27 or busted).

8. Game.java
An interface defines a very general game. It must have a start method and an end method.

9. TEGame.java
A Trianta Ena game class that implements the Game interface. It is how every part of the game gets united and linked together and run. It defines the rule of the game including all possible game situations that might occur.

10. Main.java
Entry class that contains the main method where the game starts.

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "Game" after unzipping the files
2. Run the following instructions:
javac Main.java
java Main

## Input/Output Example
---------------------------------------------------------------------------
Output:
How many players(including Banker) do you want?
Input: 4
Output:
Banker p1 added
Player p2 added
Player p3 added
Player p4 added

Game start!

Banker p1's first card: 8 of spades

Player p2, your first card is Ace of spades

How much would you like to bet? (Enter 0 for fold)
You currently have 100 dollars 
Input: 50
Output:
Player p3, your first card is Queen of spades

How much would you like to bet? (Enter 0 for fold)
You currently have 100 dollars 
Input: 70
Output:
Player p4, your first card is 9 of spades

How much would you like to bet? (Enter 0 for fold)
You currently have 100 dollars 
Input: 80
Output:
Player p2, your cards are: 
Ace of spades
6 of hearts
10 of hearts
Value: 27 or 17

Banker's card: 
8 of spades
Folded Card
Folded Card

Would you like to hit or stand? (Enter "hit" or "stand")
Input: hit
Output:
Your cards are: 
Ace of spades
6 of hearts
10 of hearts
3 of spades
Value: 30 or 20

Would you like to hit or stand? (Enter "hit" or "stand")
Input: stand
Output:
Stand! Current card value is 30

Player p3, your cards are: 
Queen of spades
6 of spades
King of hearts
Value: 26

Banker's card: 
8 of spades
Folded Card
Folded Card

Would you like to hit or stand? (Enter "hit" or "stand")

.
.
.
.
.
.

Output:
Banker's card: 
8 of spades
2 of hearts
5 of clubs
8 of diamonds
Jack of hearts

Banker busted! All remaining players win!
Player p2 gets 50 dollars!
Player p3 gets 70 dollars!
Player p4 gets 80 dollars!
Banker p1 loses 200 dollars!

Money summary:
Banker p1: 100 dollars
Player p2: 150 dollars
Player p3: 170 dollars
Player p4: 180 dollars
Enter player's name to cashout, enter 'no' to continue the game
Input: p2
Output:
Player p2 cashout successfully!
Anyone else to cashout? Enter 'no' to continue
Input: no
Output:
Round over
Player p4, you have more money than the banker, would you like to be the banker? (y/N)
Input: N
Output:
Player p3, you have more money than the banker, would you like to be the banker? (y/N)
Input: y
Output:
Player p3 is now the new banker.
Banker p3's first card: 2 of spades

Player p4, your first card is 9 of hearts

How much would you like to bet? (Enter 0 for fold)
You currently have 180 dollars 
Input: 180

.
.
.
.
.
.

Output:
Player p4, your cards are: 
9 of hearts
10 of hearts
5 of clubs
Value: 24

Banker's card: 
2 of spades
Folded Card
Folded Card

Would you like to hit or stand? (Enter "hit" or "stand")
Input: hit
Output:
Your cards are: 
9 of hearts
10 of hearts
5 of clubs
6 of hearts
Value: 30

Would you like to hit or stand? (Enter "hit" or "stand")
Input: hit
Output:
Your cards are: 
9 of hearts
10 of hearts
5 of clubs
6 of hearts
Queen of spades
Value: 40

Busted!

Banker's card: 
2 of spades
6 of diamonds
7 of diamonds
King of diamonds
2 of hearts

Banker's current card value: 27

Money summary:
Banker p3: 350 dollars
Player p4: 0 dollars(Kicked from the game)
Player p1: 100 dollars
Enter player's name to cashout, enter 'no' to continue the game
Input: p1
Output:
Player p1 cashout successfully!
Anyone else to cashout? Enter 'no' to continue
Input: no
Output:
Round over
Game Over
