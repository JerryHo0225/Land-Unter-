import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a player in the game.
 * You are not allowed to add any private or public attributes to this class.
 * You are not allowed to add any public methods to this class.
 * You can, however, add private methods to this class.
 */
public class Player {

  /**
   * The number of AI players created so far.
   */
  private static int aiCount = 0;
  /**
   * The number of cards in a player's hand.
   */
  private static final int HAND_SIZE = 12;
  /**
   * The cards in the player's hand.
   */
  private Weather[] cards = new Weather[HAND_SIZE];
  /**
   * The number of life preservers the player has remaining.
   */
  private int remainingLifePreservers;
  /**
   * Whether the player has been eliminated from that round.
   */
  private boolean eliminated;
  /**
   * The tide level of the player.
   */
  private int tideLevel;
  /**
   * The player's game score (accumminated from round 1).
   */
  private int score;
  /**
   * Whether the player is human or AI.
   */
  private boolean isHuman;
  /**
   * The name of the player.
   */
  private String name;

  /**
   * Returns the name of the player.
   *
   * @return the name of the player.
   */
  public String getName() {
    //TODO
    return name;
  }

  /**
   * Returns the tide level of the player.
   *
   * @return the tide level of the player.
   */
  public int getTideLevel() {
    //TODO
    return tideLevel;
  }

  /**
   * Constructor Creates a new player with the given name.
   *
   * @param name the name of the player.
   */
  public Player(String name) {
    //TODO
    this.cards = new Weather[0];
    this.name = name;
    this.score = 0;
    this.eliminated = false;
    this.isHuman = true;
  }

  /**
   * Creates a new AI player with a default name.
   * The name of AI is "AI 1", "AI 2", "AI 3", etc.
   * Their name does not repeat.
   */
  public Player() {
    //TODO
    this("AI " + ++aiCount);
    this.isHuman = false;
  }

  /**
   * Returns whether the player is human or AI.
   *
   * @return whether the player is human or AI.
   */
  public boolean isHuman() {
    //TODO
    return this.isHuman;
  }

  /**
   * Resets the player for a new round.
   * In each new round they should have reset their attributes except
   * their name and score.
   */
  public void resetForNewRound() {
    //TODO
    this.remainingLifePreservers = 0;
    this.eliminated = false;
    this.tideLevel = 0;
  }

  /**
   * Sets the tide level of the player. It will be called if the player
   * needs to take a tide card in a turn.
   *
   * @param tideLevel the tide level of the player.
   */
  public void setTideLevel(int tideLevel) {
    //TODO
    this.tideLevel = tideLevel;
  }

  /**
   * This method is to deal a weather card to the player
   *
   * @param c the card to be added.
   */
  public void addCard(Weather c) {
    //TODO
    if (cards.length == 0) {
      cards = new Weather[0];
    }

    //Enlarging Arrays
    Weather[] newArray = new Weather[this.cards.length + 1];
    for (int i = 0; i < this.cards.length; i++) newArray[i] = this.cards[i];
    newArray[newArray.length - 1] = c;
    this.cards = newArray;
  }

  /**
   * This method is to play a card from the player's hand.
   *
   * @param index the index of the card to be played.
   * @return the card to be played.
   */
  public Weather playCard(int index) {
    //TODO

    Weather c = this.cards[index];
    //Shrinking Arrays
    Weather[] newArray = new Weather[this.cards.length - 1];
    int indexToRemove = index;

    for (int i = 0; i < indexToRemove; i++) newArray[i] = this.cards[i];
    for (int i = indexToRemove; i < newArray.length; i++) newArray[i] =
      this.cards[i + 1];
    //replace the existing reference by the new reference
    this.cards = newArray;

    return c;
  }

  /**
   * Returns the number of cards in the player's hand.
   *
   * @return the number of cards in the player's hand.
   */
  public int getCardCount() {
    //TODO
    return cards.length;
  }

  /**
   * Returns a random card from the player's hand.
   *
   * @return the card to be played.
   */
  public Weather playRandomCard() {
    //TODO
    if (this.cards.length == 0) {
      return null;
    } else {
      int randomNumber = ThreadLocalRandom.current().nextInt(this.cards.length);
      return this.playCard(randomNumber);
    }
  }

  /**
   * To compute the initialize life preservers for the player.
   */
  public void calcLifePreservers() {
    //TODO
    int lifePreservers = 0;

    for (int i = 0; i < this.cards.length; i++) {
      lifePreservers += this.cards[i].getLifePreserver();
    }

    this.remainingLifePreservers = lifePreservers / 2;
  }

  /**
   * Returns the number of life preservers the player has remaining.
   */
  public int getLifePreservers() {
    //TODO
    return remainingLifePreservers;
  }

  /**
   * Decreases the number of life preservers the player has remaining.
   */
  public void decreaseLifePreservers() {
    //TODO
    //When remainingLifePreservers equals to 0, eliminated equals to true
    if (getLifePreservers() > 0) {
      this.remainingLifePreservers--;
    } else {
      this.eliminated = true;
    }
  }

  /**
   * Returns whether the player has been eliminated from that round.
   */
  public boolean isEliminated() {
    //TODO
    return eliminated;
  }

  /**
   * Returns the player's game score (accumminated from round 1).
   */
  public int getScore() {
    //TODO
    return score;
  }

  /**
   * Set the player's game score (accumminated from round 1).
   */
  public void setScore(int score) {
    //TODO
    this.score = score;
  }

  /**
   * Prints the player's hand.
   * Please refer to the sample program for the proper format.
   * The order of the card will not affect the correctness of the program.
   *
   * It prints nothing if the player is AI.
   */
  public void printHand() {
    //TODO
    if (isHuman()) {
      for (int i = 0; i < cards.length; i++) {
        System.out.print(cards[i] + " ");
      }
    }
    System.out.println();
  }
}
