package MainSrc;

import PlayerPack.Player;
import Props.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 *  Game class that runs the whole game using other classes and components.
 * @author ilkan mert okul, 1801042649
 * */
public class Game extends Application{

    /**This is the pane we are going to use in our game. (javafx.scene.layout.Pane)*/
    private Pane root = new Pane();

    /**Player component who is also a RECTANGLE to put in our pane*/
    private Player player = new Player(25,400,50,50,Color.CRIMSON);

    /**Label shows us the logs: player jumps, change modes, collides with powerup/obstacle.(javafx.scene.control.Label)*/
    private Label logLabel = new Label();

    /**This is the LOG we are going to show in label logLabel.*/
    private Log log = new Log();

    /**This is fixed total health points the player going to have.*/
    private final int TOTAL_HP = 3;

    /**This variable shows the current hp.*/
    private int currentHP;

    /**This shows total lifes left visually.*/
    private ImageView[] heartImage = new ImageView[TOTAL_HP];

    /**Final variable that shows total colliders that game screen can have.*/
    private final int TOTAL_COLLIDERS = 15;

    /**An array that sizes TOTAL_COLLIDERS.*/
    private ColliderProps[] colliderInstances = new ColliderProps[TOTAL_COLLIDERS];

    /**Since total colliders are finite, this variable helps to delete the first collider when
     * other comes into play.*/
    private int currentIndex = 0;

    /**Label to show score on the scene*/
    private Label scoreLabel = new Label();

    /**Multiplier PowerUpProp to keep multipliers.*/
    private PowerUpProp multiplier = new ConcreteMultiplier();

    /**This holds the total score, resets when the game restarts.*/
    private int score = 0;

    /**Boolean variable to check if game is over.*/
    private boolean gameOver = false;

    /**This shows up when the game is over to scene.*/
    private Label gameOverLabel = new Label();

    /**Boolean variable to check if game is paused*/
    private boolean gamePause = false;

    /**Pause button to pause the game*/
    private Button pauseButton = new Button();

    /**Restart button to restart the game*/
    private Button restartButton = new Button();

    /**This label shows the user the jump mode it is using.*/
    private Label jumpMode = new Label();

    /**This method creates the preferences
     * @return Parent(javafx.scene.Parent) to put in our scene.*/
    private Parent createContent() throws FileNotFoundException {

        //Sets the size of the screen.
        root.setPrefSize(800,700);

        //Sets the log.
        logLabel.setText(log.toString());
        logLabel.setTranslateX(20);
        logLabel.setTranslateY(470);

        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(3);

        gameOverLabel.setText(null);
        gameOverLabel.setTranslateX(300);
        gameOverLabel.setTranslateY(150);
        gameOverLabel.setFont(Font.font("Arial",25));

        jumpMode.setText("JumpMode = Small\t\t(press 'TAB' to change jumpMode)");
        jumpMode.setTranslateY(50);
        jumpMode.setTranslateX(25);

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                restartGame();
            }
        });
        restartButton.setText("Restart");
        restartButton.setScaleY(1);
        restartButton.setScaleX(2);
        restartButton.setTranslateX(700);
        restartButton.setTranslateY(25);
        restartButton.setFocusTraversable(false);

        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pauseGame();
            }
        });
        pauseButton.setText("Pause");
        pauseButton.setScaleY(1);
        pauseButton.setScaleX(2);
        pauseButton.setTranslateX(700);
        pauseButton.setTranslateY(75);
        pauseButton.setFocusTraversable(false);

        root.getChildren().add(jumpMode);
        root.getChildren().add(pauseButton);
        root.getChildren().add(restartButton);
        root.getChildren().add(gameOverLabel);
        root.getChildren().add(player);
        root.getChildren().add(logLabel);
        root.getChildren().add(scoreLabel);

        Line seperatorLine = new Line(0, 460 , 800,460);
        seperatorLine.setStroke(Color.BLACK);
        seperatorLine.setScaleY(20);

        root.getChildren().add(seperatorLine);

        //This loop creates HEARTs, and adds it to scene.
        Image[] heart = new Image[TOTAL_HP];
        for(int i = 0 ; i < TOTAL_HP ; i++){
            heart[i] = new Image(Game.class.getResourceAsStream("heart.png"));
            heartImage[i] = new ImageView(heart[i]);

            heartImage[i].setX(25 + 25*i);
            heartImage[i].setY(25);

            heartImage[i].setFitWidth(20);
            heartImage[i].setFitHeight(20);

            root.getChildren().add(heartImage[i]);
        }

        for(int i = 0 ; i <TOTAL_COLLIDERS ; i++){
            colliderInstances[i] = new ColliderProps(0,0,0,0,Color.WHITE);
            root.getChildren().add(colliderInstances[i]);
        }

        return root;
    }

    /**This method sets up the stage we are going to see.*/
    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene sc = new Scene(createContent());

        //This sets the onClick actions.
        sc.setOnKeyPressed(input->{
            switch (input.getCode()){
                case SPACE:
                    if(!gameOver && !gamePause){
                        log.logMessage(player.jump());
                        logLabel.setText(log.toString());
                    }
                    break;
                case TAB:
                    if(!gameOver && !gamePause){
                        if(multiplier.canBigJump()){
                            log.logJumpChange(player.setJumpmode());
                            if(!player.getJumpmode())
                                jumpMode.setText("JumpMode = Small\t\t(press 'TAB' to change jumpMode)");
                            else
                                jumpMode.setText("JumpMode = Big\t\t(press 'TAB' to change jumpMode)");
                        }
                        else
                            log.logMessage("Your character cannot bigJump.. You need to pick the powerup!");
                        logLabel.setText(log.toString());
                    }
                    break;
                case D:
                    if(!gameOver && !gamePause){
                        spawner(root);
                        score = score + multiplier.getMultiplier();
                        scoreLabel.setText("Score: " + score + "   Multiplier: " + multiplier.getMultiplier());
                        for(int i =0  ;i < TOTAL_COLLIDERS; i++) colliderInstances[i].moveLeft();
                    }
                    break;
            }
        });

        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.show();

        game(primaryStage);
    }

    /**This method just starts the collision timer.*/
    private void game(Stage primaryStage){
        collisionTimer.start();
        currentHP = TOTAL_HP-1;
    }

    /**Restarts the whole game*/
    private void restartGame(){

        log.logMessage("Restarting the game. Score :" + score);
        logLabel.setText(log.toString());

        score = 0;
        multiplier = new ConcreteMultiplier();

        currentHP = TOTAL_HP-1;
        gameOver = false;

        gameOverLabel.setText(null);

        if(player.getJumpmode()) {
            player.setJumpmode();
            jumpMode.setText("JumpMode = Small\t\t(press 'TAB' to change jumpMode)");
        }
        Image[] heart = new Image[TOTAL_HP];
        for(int i = 0 ; i < TOTAL_HP ; i++){
            heart[i] = new Image(Game.class.getResourceAsStream("heart.png"));
            heartImage[i].setImage(heart[i]);

            heartImage[i].setX(25 + 25*i);
            heartImage[i].setY(25);

            heartImage[i].setFitWidth(20);
            heartImage[i].setFitHeight(20);
        }
    }

    /**Pauses the game*/
    private void pauseGame(){
        if(gamePause){ //Game was paused, it will continue now.
            gamePause = false;
            log.logMessage("Your game will continue now.");
            logLabel.setText(log.toString());
            pauseButton.setText("Pause");
        }else{ //Game was not paused, it will pause now.
            gamePause = true;
            log.logMessage("Your game will pause now.");
            logLabel.setText(log.toString());
            pauseButton.setText("Continue");
        }
    }

    /**Collision handler*/
    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            for(int i=0 ; i< TOTAL_COLLIDERS ; i++){
                checkCollision(player,colliderInstances[i]);
            }
        }
    };

    /**This method gets the colliders and checks if player hits to powerup or obstacle.
     * @param s1 player instance
     * @param s2 collider.*/
    public void checkCollision(Player s1,ColliderProps s2){
        if(s1.getBoundsInParent().intersects(s2.getBoundsInParent())){
            if(!s2.getIsColided()){
                s2.setIsColided();

                //If collision happened with obstacle, 1 hp is gone. Else, get multiplier or jumper powerup.
                if(s2 instanceof Obstacle){
                    if(currentHP > 0){
                        //Remove a heart from player.
                        heartImage[currentHP--].setImage(null);
                    }else{
                        heartImage[currentHP].setImage(null);
                        gameOverLabel.setText("GAME OVER");
                        gameOver = true;
                    }
                }else if(s2 instanceof PowerUp){
                    switch (((PowerUp) s2).getPowerUpType()){
                        case TWOX:
                            multiplier = new PowerUpProp2X(multiplier);
                            break;
                        case FIVEX:
                            multiplier = new PowerUpProp5X(multiplier);
                            break;
                        case TENX:
                            multiplier = new PowerUpProp10X(multiplier);
                            break;
                        case BIGJUMP:
                            multiplier = new PowerUpPropJump(multiplier);
                            break;
                    }
                    log.logMessage("Powerup acquired! Current multiplier is : " + multiplier.getMultiplier());
                    logLabel.setText(log.toString());
                    scoreLabel.setText("Score: " + score + "   Multiplier: " + multiplier.getMultiplier());
                }
            }
        }
    }

    /**This method spawns a new collider (powerup or obstacle)*/
    private void spawner(Pane root){
        if(true){
            //Spawns a new random object.

            if(currentIndex >= TOTAL_COLLIDERS-1) currentIndex = 0;
            else currentIndex++;

            //Delete old collider.
            root.getChildren().remove(colliderInstances[currentIndex]);

            if((int) Math.floor(Math.random()*5) != 0) //This means powerup has 1/5 chance to appear.
                root.getChildren().add(colliderInstances[currentIndex] = new Obstacle(700,400,50,50));
            else{

                PowerUpProp.PowerUpType tempType;
                String strType;
                int a = 3;
                switch ((int) Math.floor(Math.random()*4)%4){
                    case 0:
                        tempType = PowerUpProp.PowerUpType.TWOX;
                        strType = tempType.toString();
                        break;
                    case 1:
                        tempType = PowerUpProp.PowerUpType.FIVEX;
                        strType = tempType.toString();
                        break;
                    case 2:
                        tempType = PowerUpProp.PowerUpType.TENX;
                        strType = tempType.toString();
                        break;
                    case 3:
                        tempType = PowerUpProp.PowerUpType.BIGJUMP;
                        strType = tempType.toString();
                        break;
                    default:
                        tempType = null;
                        strType = tempType.toString();
                }

                log.logMessage("A wild powerup appeared: " + strType);
                logLabel.setText(log.toString());
                root.getChildren().add(colliderInstances[currentIndex] = new PowerUp(700,400,50,50,Color.CYAN,tempType));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
