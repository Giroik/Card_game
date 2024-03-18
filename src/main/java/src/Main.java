package src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.List;

/**
 * The main class of the application. Handles the preprocessing of the application, and launches the application.
 *
 * @version 1.0
 * @since 3.3.2024
 */
public class Main extends Application {
    private VBox mainScreen; // main screen
    private HBox hand; // hand of cards
    private HBox chois;// buttons
    private VBox bottomScreen; // bottom screen
    private Stage primeryStage; // primary stage
    private DeckOfCards deck; // deck of cards
    private int quantityOfCards = 5;// quantity of cards in the hand


    /**
     * Entry point of application.
     * Run {@code mvn javafx:run} to start the application (GUI).
     *
     * @param primeryStage The root primary stage of the application.
     * @since 1.0
     */
    public void start(Stage primeryStage) {

        setPrimeryStage(primeryStage);


        mainScreen = new VBox();
        //creating of boxes
        chois = createChois();
        hand = new HBox();
        bottomScreen =new VBox();

        update();
    }

    /**
     * Sets the primary stage of the application.
     *
     * @param primeryStage The primary stage of the application.
     * @since 1.0
     */
    private void setPrimeryStage(Stage primeryStage) {
        this.primeryStage = primeryStage;
    }

    /**
     * Creates the buttons for the application. The buttons are used to deal a hand and create a forced flush.
     * @return The HBox containing the buttons.
     */
    public HBox createChois()
    {
        HBox chois = new HBox();
        chois.setAlignment(Pos.CENTER);
        Button dealHands = new Button("Deal hand ");
        Button forsedFlash = new Button("Foresed flash ");

        dealHands.maxWidth(200);
        forsedFlash.maxWidth(200);
        chois.getChildren().addAll(dealHands,forsedFlash);
        chois.setSpacing(20);

        dealHands.setOnAction(e -> {
            List<PlayingCard> deckCards = deck.dealHand(quantityOfCards).toList();
            createHand(deckCards);
            update();
        });

        forsedFlash.setOnAction(e -> {
            List<PlayingCard> deckCards = deck.getOnlyFlash();
            createHand(deckCards);
            update();
        });

        return chois;
    }
    /**
     * Creates a random hand of cards from the deck.
     *
     * @param deckCards The list of cards to create a hand from.
     * @since 1.0
     */
    public void createHand(List<PlayingCard> deckCards)
    {
        HBox handOn = new HBox();


        deckCards.forEach(card->{
            ImageView cardImage= new ImageView();
            Image imageToShow = new Image("file:src/main/java/src/cards/"+card.getAsString()+".png");
            cardImage.setImage(imageToShow);
            cardImage.setFitHeight(PlayingCard.cardHeight);
            cardImage.setFitWidth(PlayingCard.cardWidth);
            handOn.getChildren().add(cardImage);
        });

        this.hand=handOn;

        bottomScreen = createBottomScreen(deckCards);


    }

    /**
     * Creates the bottom screen of the application. The bottom screen contains the sum of the face, the cards of the
     * chosen suit, the flush and the chosen card exist.
     * @param cardsOnHandList The list of cards to check.
     * @return The VBox containing the bottom screen.
     */
    public VBox createBottomScreen(List<PlayingCard> cardsOnHandList)
    {

        VBox space= new VBox();

        //System.out.println(cardsOnHandIterator.hasNext());

        HBox summ= new HBox();
        HBox cardsOfChosenSuit= new HBox();
        HBox flush= new HBox();
        HBox chosenCardExist= new HBox();

        summ.setAlignment(Pos.CENTER);
        cardsOfChosenSuit.setAlignment(Pos.CENTER);
        flush.setAlignment(Pos.CENTER);
        chosenCardExist.setAlignment(Pos.CENTER);

        // Set-up for the sum of the face
        Text sumText= new Text("Sum of the face: ");
        TextField sumField= new TextField();
        sumField.setText(""+HandCheck.checkSumOfFace(cardsOnHandList));
        sumField.setDisable(true);

        //Set-up for the cards of the chosen suit
        String suits="No Hearts";
        if(!HandCheck.getSuits(cardsOnHandList, "H").isEmpty())
        {
            suits="";
            suits=HandCheck.getSuits(cardsOnHandList, "H");
        }


        Text chosenSuitText= new Text("Cards of chosen suit: ");
        TextField chosenSuitField= new TextField();
        chosenSuitField.setText(suits);
        chosenSuitField.setDisable(true);

        //Set-up for the flush
        Text flushText= new Text("Flush: ");
        TextField flushField= new TextField();
        flushField.setText(""+HandCheck.hasFlush(cardsOnHandList));
        flushField.setDisable(true);

        //Set-up for the chosen card exist
        Text chosenCardExistText= new Text("Qeen of spades: ");
        TextField chosenCardExistField= new TextField();
        chosenCardExistField.setText(""+HandCheck.hasCard(cardsOnHandList, "QH"));
        chosenCardExistField.setDisable(true);

        //Set-up of boxes
        summ.getChildren().addAll(sumText, sumField);
        cardsOfChosenSuit.getChildren().addAll(chosenSuitText, chosenSuitField);
        flush.getChildren().addAll(flushText, flushField);
        chosenCardExist.getChildren().addAll(chosenCardExistText, chosenCardExistField);

        space.getChildren().addAll(summ, cardsOfChosenSuit, flush, chosenCardExist);
        space.setSpacing(20);



        return space;
    }

    /**
     * Updates the application. Updating GUI screen after the hand is dealt.
     * @since 1.0
     */
    public void update()
    {
        Group root= new Group();

        mainScreen.getChildren().clear();
        mainScreen.getChildren().addAll(chois, hand, bottomScreen);
        mainScreen.setSpacing(20);

        root.getChildren().add(mainScreen);
        deck=new DeckOfCards();

        Scene scene = new Scene(root, 800, 600);

        this.primeryStage.setTitle("Card Game");

        this.primeryStage.setScene(scene);

        this.primeryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}