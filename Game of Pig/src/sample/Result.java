package sample;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Result implements Serializable {

    String timestamp;
    String playerOneName;
    String playerTwoName;
    int playerOneScore;
    int playerTwoScore;


    GameOfPig game;

    @FXML AnchorPane anchorPane;
    @FXML Label scoreLabel1;
    @FXML Label scoreLabel2;
    @FXML Label winnerLabel;

    @Override
    public String toString() {
        return "Result{"
                + "timestamp="
                + timestamp
                + ", playerOneName='"
                + playerOneName
                + '\''
                + ", playerTwoName='"
                + playerTwoName
                + '\''
                + ", playerOneScore="
                + playerOneScore
                + ", playerTwoScore="
                + playerTwoScore
                + '}';
    }

    public void displayWinner(GameOfPig game) {
        this.game = game;

        if (game.p1.score > game.p2.score ) {
            game.winner = true;
        } else {
            game.winner = false;
        }

        if (game.winner) {
            winnerLabel.setText(game.p1.name + " is the winner of PIG!");
        } else {
            winnerLabel.setText(game.p2.name + " is the winner of PIG!");
        }

        scoreLabel1.setText(game.p1.name + "'s score is: " + game.p1.score);
        scoreLabel2.setText(game.p2.name + "'s score is: " + game.p2.score);
    }

    public String format() {
        String format = "Winner: ";
        if (game.winner) {
            format += game.p1.name + " - " + game.p1.score +", Loser: " + game.p2.name + " - " + game.p2.score;
        } else {
            format += game.p2.name + " - " + game.p2.score + ", Loser: " + game.p1.name + " - " + game.p1.score;
        }
        return format;
    }

    public Result() {
        this.timestamp =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.playerOneName = game.p1.name;
        this.playerOneScore = game.p1.score;
        this.playerTwoName = game.p2.name;
        this.playerTwoScore = game.p2.score;
    }


    public void backButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewIntro.fxml"));
        Parent gameViewParent = loader.load();
        Scene gameViewScene = new Scene(gameViewParent);
        //This line gets the Stage information
        Stage window = (Stage) anchorPane.getScene().getWindow();

        window.setScene(gameViewScene);
        window.show();
    }

    public void saveButton() throws IOException {
        String record = format();
        System.out.println(record);
        writeToFile(record);
        winnerLabel.setText("The game record was saved!");
    }

    public void writeToFile(String record) throws IOException {
        String fileName = "recordFile.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append("\n");
        writer.append(record);
        writer.close();
    }
    public String getTimestamp() {
        return timestamp;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }


}