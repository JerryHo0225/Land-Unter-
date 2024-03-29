/**
 * @author: ______HO Sze Chung 23212381_________
 *
 *          For the instruction of the assignment please refer to the assignment
 *          GitHub.
 *
 *          Plagiarism is a serious offense and can be easily detected. Please
 *          don't share your code to your classmate even if they are threatening
 *          you with your friendship. If they don't have the ability to work on
 *          something that can compile, they would not be able to change your
 *          code to a state that we can't detect the act of plagiarism. For the
 *          first commit of plagiarism, regardless you shared your code or
 *          copied code from others, you will receive 0 with an addition of 5
 *          mark penalty. If you commit plagiarism twice, your case will be
 *          presented in the exam board and you will receive a F directly.
 *
 *          Terms about generative AI:
 *          You are not allowed to use any generative AI in this assignment.
 *          The reason is straight forward. If you use generative AI, you are
 *          unable to practice your coding skills. We would like you to get
 *          familiar with the syntax and the logic of the Java programming.
 *          We will examine your code using detection software as well as
 *          inspecting your code with our eyes. Using generative AI tool
 *          may fail your assignment.
 *
 *          If you cannot work out the logic of the assignment, simply contact
 *          us on Discord. The teaching team is more the eager to provide
 *          you help. We can extend your submission due if it is really
 *          necessary. Just please, don't give up.
 */
import java.util.Scanner;

/**
 * Table class is the main class of the game. It is responsible for the game
 * logic. Complete all TODO parts below
 */
public class Table {

  /**
   * The total number of turns in one round.
   */
  private static final int TOTAL_TURN = 12;
  /**
   * The total number of tide cards. Totally 24.
   */
  private static final int TOTAL_TIDE_CARDS = TOTAL_TURN * 2;
  /**
   * The total number of weather cards. Totally 60.
   */
  private static final int TOTAL_WEATHER_CARD = 60;
  /**
   * The tide cards. Each card has two.
   */
  private int[] tide = new int[TOTAL_TIDE_CARDS];
  /**
   * The weather decks. We divide all 60 Weather cards into several decks.
   * Each deck has 12 cards. There are maximum 5 decks. In each round, a
   * player takes one deck. The decks are shuffled in a deterministic way.
   * After each round, they give the decks to the next player.
   *
   * If there are three players in the game, there should be only three decks.
   * If there are four players in the game, there should be only four decks.
   * If there are five players in the game, there should be five decks.
   * The game does not support two players or less, six players or more.
   */
  private Weather[][] decks;
  /**
   * The players array.
   */
  private Player[] players;

  /**
   * Main method, do not change this method.
   */
  public static void main(String[] arg) {
    System.out.println("Welcome to the Turn the Tile game!");
    int player = 0;
    Scanner scanner = new Scanner(System.in);

    do {
      System.out.println(
        "Please enter the number of players you want to play:"
      );
      player = scanner.nextInt();
    } while (player < 2 || player > 5);

    new Table(player).run();
  }

  /**
   * Constructor of the Table class.
   *
   * You should initialize the players array, the tide cards, and the weather
   * decks. The first player should be a human player called "Anya Forger"
   * The rest of the players should be AI players called "AI 1", "AI 2", etc.
   *
   * @param player The number of players.
   */
  public Table(int player) {
    // prepare players array
    // TODO
    players = new Player[player];
    players[0] = new Player("Anya Forger");

    for (int i = 1; i < player; i++) {
      players[i] = new Player();
    }

    // prepare tide cards, 1 to 12
    // TODO
    int counter = 0;
    for (int i = 0; i < tide.length; i++) {
      if (i % 2 == 0) {
        counter++;
      }
      tide[i] = counter;
    }

    int[][] MAGIC_NUMBERS = {
      { 31, 26, 54, 19, 8, 45, 39, 16, 28, 42, 3, 38 }, //deck1
      { 30, 60, 58, 56, 51, 10, 41, 48, 14, 9, 32, 44 }, //deck2
      { 29, 35, 7, 49, 1, 21, 13, 46, 6, 18, 43, 24 }, //deck3
      { 27, 34, 33, 11, 12, 47, 23, 55, 40, 37, 22, 15 }, //deck4, if any
      { 57, 36, 50, 20, 53, 52, 59, 4, 25, 2, 17, 5 }, //deck5, if any
    };
    // assign weather cards from the array weather to decks according to the
    // MAGIC_NUMBERS
    // TODO
    Weather[] weatherCards = new Weather[TOTAL_WEATHER_CARD];
    this.decks = new Weather[player][TOTAL_TURN];

    for (int i = 1; i <= TOTAL_WEATHER_CARD; i++) {
      weatherCards[i - 1] = new Weather(i);
    }

    for (int i = 0; i < player; i++) {
      for (int j = 0; j < TOTAL_TURN; ++j) {
        this.decks[i][j] = weatherCards[MAGIC_NUMBERS[i][j] - 1];
      }
    }
  }

  /**
   * This method is to shuffle the tide cards. Before each round, you should
   * call this method exactly once.
   *
   * This method has been written for you.
   * You cannot change this method.
   */
  private void shuffleTideCard() {
    // A deterministic shuffle required by the program
    int[] order = {
      4,
      23,
      8,
      11,
      3,
      16,
      21,
      14,
      0,
      6,
      18,
      15,
      19,
      9,
      5,
      12,
      13,
      2,
      17,
      7,
      10,
      20,
      1,
      22,
    };
    int[] newTide = new int[TOTAL_TIDE_CARDS];
    for (int i = 0; i < TOTAL_TIDE_CARDS; i++) {
      newTide[i] = tide[order[i]];
    }
    tide = newTide;
  }

  /**
   * This method is to run the game for N rounds where N is the number of players.
   * Number of players can be retrieved from the size of the array `players`.
   *
   */
  public void run() {
    // TODO
    // The game should be running for N rounds.
    // We write the first round for you to get you started.
    oneRound(0);
  }

  /**
   * This method is to run one round of the game. In each round we will
   * play at most 12 turns. If there are less than 3 players remaining,
   * the round ends immediately.
   *
   * You will need to deal weather cards to each player from the decks.
   * Remember the decks must be passed to the another player in the next round.
   * Therefore you should not shuffle the decks. It is not required to
   * sort the deck.
   *
   * After each round, the score of the each player needs to be tallied.
   *
   * @param round The round number.
   */
  private void oneRound(int round) {
    // TODO
    // this method should do much more things and run at most 12 turns
    // unless there are less than 3 players remaining.
    // We write the first turn for you to get you started.

    while (round < players.length) {
      System.out.println("Round " + (round + 1) + " starts.");
      System.out.println("---------------------------------");

      if (players.length > 2) {
        //initialze tide card in every round
        tide = new int[TOTAL_TIDE_CARDS];
        int counter = 0;
        for (int i = 0; i < tide.length; i++) {
          if (i % 2 == 0) {
            counter++;
          }
          tide[i] = counter;
        }
        shuffleTideCard();

        //change player card in every round
        int indexChangeCard = round;
        for (int i = 0; i < players.length; i++) {
          //when indexChangeCard is equal to no. player
          if (indexChangeCard == players.length) {
            indexChangeCard = 0;
          }
          for (int j = 0; j < decks[i].length; j++) {
            players[i].addCard(decks[indexChangeCard][j]);
          }
          indexChangeCard++;
        }

        oneTurn();

        //Finish one round

        System.out.println();
        //Reset player data
        for (Player player : players) {
          player.resetForNewRound();
        }
      } else {
        System.out.println("Turn 1 starts.");
        System.out.println("Less than three players remain, this turn ends.");

        //change player card in every round
        int indexChangeCard = round;
        for (int i = 0; i < players.length; i++) {
          //when indexChangeCard is equal to no. player
          if (indexChangeCard == players.length) {
            indexChangeCard = 0;
          }
          for (int j = 0; j < decks[i].length; j++) {
            players[i].addCard(decks[indexChangeCard][j]);
          }
          indexChangeCard++;
        }

       
      }

      //When 2/3/4/5 players round is done, round + 1
      round++;
    }
  }

  /**
   * This method is to draw the first tide card from the tide deck.
   * after the card has been drawn, the array should be resized.
   *
   * @return The value of the tide card.
   */
  private int draw() {
    // TODO
    int newTideLevel = this.tide[0];

    int[] newArray = new int[this.tide.length - 1];
    int indexToRemove = 0;
//Shrinking Arrays

    for (int i = indexToRemove; i < newArray.length; i++) newArray[i] = this.tide[i + 1];

    this.tide = newArray;

    return newTideLevel;
  }

  /**
   * This method is to run one turn of the game. In each turn, two tide cards
   * will be drawn. All players will play one weather card. A human player is
   * playing the card at his choice. An AI player is playing the card randomly.
   *
   * You should handle which player will need to be drowned and eliminate them
   * if necessary.
   */
  private void oneTurn() {
    // TODO
    Scanner in = new Scanner(System.in);
    //To store players card in every turn
    Weather[] thisTurnCard = new Weather[this.players.length];

    boolean isFinish = false;
    int isEliminatedPlayer = 0;
    //Compute every player hand card life preservers
    for (Player player : players) {
      player.calcLifePreservers();
    }

    //Run 12 turn
    for (int i = 0; i < TOTAL_TURN; i++) {
      //print Turn 1-12
      System.out.println("Turn " + (i + 1) + " starts.");

      if (players.length - isEliminatedPlayer > 2) {
        //print tide level
      int tideOne = 0;
      int tideTwo = 0;
      int temp = 0;

      //draw card
      tideOne = draw();
      tideTwo = draw();

      //Change two value when tideTwo is lager then tideOne
      if (tideOne < tideTwo) {
        temp = tideOne;
        tideOne = tideTwo;
        tideTwo = temp;
      }

      //print tide level
      System.out.println(
        "The higher tide is " + tideOne + " and the lower tide is  " + tideTwo
      );

      //print player game data
      for (Player p : players) {
        System.out.println(
          p.getName() +
          " has tide level " +
          p.getTideLevel() +
          ", " +
          p.getLifePreservers() +
          " life preservers"
        );
      }

      //print player card
      players[0].printHand();

      while (true) {
        int userinput = 0;

        System.out.print(
          "Player " +
          players[0].getName() +
          ", you have " +
          players[0].getLifePreservers() +
          " life preservers, please select a card to play:"
        );

        userinput = in.nextInt();

        //get a vaild input
        if (players[0].getCardCount() > userinput && userinput >= 0) {
          thisTurnCard[0] = players[0].playCard(userinput);
          break;
        }
      }

      //Ai play card
      for (int j = 1; j < players.length; j++) {
        thisTurnCard[j] = players[j].playRandomCard();
      }

      //Find the player who play the biggest value of the card
      int biggest = findIndexWithBiggestCard(thisTurnCard);
      //Find the player who play the second biggest value of the card
      int second = findIndexWithSecondBiggestCard(thisTurnCard);

      //set tide level
      players[biggest].setTideLevel(tideTwo);
      players[second].setTideLevel(tideOne);

      //The player who has the highest tide level is drown
      drownPlayer();

      //If the player is eliminated, print the player
      for (int j = 0; j < players.length; j++) {
        if (players[j].isEliminated()) {
          System.out.println("Player " + j + " is eliminated.");
          isEliminatedPlayer++;
        }
      }
      }else{
       System.out.println("Less than three players remain, this turn ends.");


        for (Player player : players) {
      if (player.isEliminated()) {
        player.setScore(-1 + player.getScore());
      } else {
        player.setScore(player.getLifePreservers() + player.getScore());
      }
      System.out.println(
        player.getName() + " has " + player.getScore() + " points."
      );
    }
        isFinish = true;
        break;
      }
      
    }

    ///////////////
    //12 turn is finished
    ///////////////

    //set score
    if (!isFinish) {
      
    
    for (Player player : players) {
      if (player.isEliminated()) {
        player.setScore(-1 + player.getScore());
      } else {
        player.setScore(player.getLifePreservers() + player.getScore());
      }
      System.out.println(
        player.getName() + " has " + player.getScore() + " points."
      );
    }}
  }

  /**
   * This method is to find the index of the player who has the biggest card.
   * Because the weather cards are unique, there should be only one player
   * who has the biggest card.
   *
   * Make sure you retain the array `cards` before the method returns.
   *
   * @param cards The cards played by the players. Note, the array may contain
   *              null values. For example, if there are four players while AI 1
   *              has been eliminated, the cards array may looks like
   *              {<34>, null, <12>, <45>}. <34> means the weather card object
   *              with the value 34. In this example, it should return 3.
   * @return The index of the player who has the biggest card.
   */
  private int findIndexWithBiggestCard(Weather[] cards) {
    // TODO

    int cardValue = cards[0].getValue();
    int counter = 0;

    for (int i = 1; i < cards.length; i++) {
      if (cardValue < cards[i].getValue()) {
        counter = i;
      }
    }

    return counter;
  }

  /**
   * This is very similar to the method findIndexWithBiggestCard. This method
   * is to find the index of player who plays the second biggest card.
   *
   * Make sure you retain the array `cards` before the method returns.
   *
   * @param cards The cards played by the players. Note, the array may contain
   *              null values. For example, if there are four players while AI 1
   *              has been eliminated, the cards array may looks like
   *              {<34>, null, <12>, <45>}. <34> means the weather card object
   *              with the value 34. In this example, it should return 0.
   * @return The index of the player who has the second biggest card.
   */
  private int findIndexWithSecondBiggestCard(Weather[] cards) {
    // TODO

    Weather[] newArray = new Weather[cards.length - 1];
    int indexToRemove = findIndexWithBiggestCard(cards);

    if (indexToRemove >= 0) {
      for (int i = 0; i < indexToRemove; i++) newArray[i] = cards[i];
      for (int i = indexToRemove; i < newArray.length; i++) newArray[i] =
        cards[i + 1];
      //replace the existing reference by the new reference
      cards = newArray;
    }

    int counter = 0;

    counter = findIndexWithBiggestCard(cards);

    return counter;
  }

  /**
   * This method is to drown the player who has the highest tide level.
   * If there are more than one player who has the highest tide level,
   * all of them will be drowned.
   *
   * Player that has been eliminated will not be drowned and their tide
   * level will not be used in the comparison.
   */
  private void drownPlayer() {
    // TODO
    int drownNumber = 0;

    //find the highest tide level
    for (int i = 0; i < players.length; i++) {
      int tide = players[i].getTideLevel();
      if (tide > drownNumber) {
        drownNumber = tide;
      }
    }

    for (int i = 0; i < players.length; i++) {
      if (
        drownNumber == players[i].getTideLevel() && !players[i].isEliminated()
      ) {
        players[i].decreaseLifePreservers();
      }
    }
  }
}
