package javafxapplication5;

import java.io.File;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;

public class MoviePlayer extends Application {
    Stage st ;
    int x,w,h,z;
    Double y;
     String trailer;
      Media media;
      MediaPlayer player;
      MediaView view;
    Slider timeSlider = new Slider();
      public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(final Stage stage) throws Exception {
        
        
        trailer = "videos.mp4";
         media = new Media(new File(trailer).toURI().toString());

        player = new MediaPlayer(media);
       view = new MediaView(player);
        
        
        stage.setTitle("Movie Player");
        st=stage; 
        Group root = new Group();
        HBox topMenus = new HBox();
        VBox vbox = new VBox();
        BorderPane root1 = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu(" File ");
        Menu menu1 = new Menu(" Exit ");
        MenuItem Exit = new MenuItem(" Exit ");
        menu1.getItems().add(Exit);
        MenuItem openMenu = new MenuItem("Open");
        Menu helpMenu = new Menu("Help");
        MenuItem help1 = new MenuItem("Help");
        helpMenu.getItems().add(help1);
        menu.getItems().addAll(openMenu);
        menuBar.getMenus().addAll(menu,menu1,helpMenu);
        topMenus.getChildren().add(menuBar);
        Hyperlink link = new Hyperlink();
        timeSlider.setStyle("-fx-background-color: green");
         helpMenu.setOnAction(new EventHandler<ActionEvent>() {
         @Override
        public void handle(ActionEvent e) {
            System.out.println("This link is clicked");
             getHostServices().showDocument("https://www.facebook.com/profile.php?id=100005135449195");
            }
         });
        
        Exit.setOnAction(e->{
            System.exit(0);
        });
        openMenu.setOnAction((ActionEvent e)-> {
              FileChooser fileChooser = new FileChooser(); 
              File file = fileChooser.showOpenDialog(st);
               String sp = file.toString();
               trailer = sp;
               player.stop();
               media = new Media(new File(trailer).toURI().toString());
               player = new MediaPlayer(media);
                view = new MediaView(player);
                //view.setLayoutY(25);
                //view.setLayoutX(400);
                //view.setLayoutY(200);
                //root.getChildren().addAll(view);
                root1.setCenter(view);
                playPlayer(stage);
                player.play();
        });
        //topMenus.getChildren().addAll(timeSlider);
       // root1.setRight(topMenus);
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
               int w = player.getMedia().getWidth();
                int h = player.getMedia().getHeight();
                x=0;                
              // stage.setMinWidth(w);
              // stage.setMinHeight(h);
                //vbox.setMinSize(w, 100);
                //vbox.setTranslateY(h);
               Duration value = player.getTotalDuration();
               
               System.out.println(" size " + value.toSeconds());
               timeSlider.setValue(0.0);
               timeSlider.setMax( value.toSeconds());
              // player.setAutoPlay(true);
            }
        });
        HBox hbox = new HBox();
        String str = "play143.png";
        Image image = new Image(new File(str).toURI().toString());
        ImageView view1 = new ImageView(image);
        topMenus.setStyle("-fx-background-color: grey");
        Button button = new Button();
        button.setGraphic(view1);
        String str1 = "play142.png";
        Image image1 = new Image(new File(str1).toURI().toString());
        ImageView view2 = new ImageView(image1);
        button.setMaxHeight(50);
        hbox.getChildren().add(button);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        button.setOnAction(e-> {
                if(x==0){
                player.play();
                x=1;
               button.setGraphic(view2);
                }
                else
                {
                   player.pause();
                   x=0;
                   button.setGraphic(view1);
                 }
        });
        
       topMenus.setSpacing(10);
        timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds( timeSlider.getValue()));
            }
        });
        timeSlider.setPrefWidth(500);
        
        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                System.out.println("Player is playing ,value = " + newValue.toSeconds() + " old value = " + oldValue);
                timeSlider.setValue( newValue.toSeconds());
                
            }
        });
        
        String str2 = "play144.png";
        Image image2 = new Image(new File(str2).toURI().toString());
        ImageView view3 = new ImageView(image2);
        Button positive = new Button();
        positive.setGraphic(view3);
         String str3 = "play145.png";
        Image image3 = new Image(new File(str3).toURI().toString());
        ImageView view4 = new ImageView(image3);
        Button negative = new Button();
        negative.setGraphic(view4);
        y=10.0;
        positive.setOnAction(e->{
            positiveaction();
        });
        negative.setOnAction(e->negativeaction());
        //view.setLayoutY(25);
        root1.setTop(topMenus);
        root1.setCenter(view);
       // view.setLayoutX(400);
        //view.setLayoutY(200);
        hbox.setStyle("-fx-background-color: grey");
        hbox.getChildren().addAll(timeSlider,positive,negative);
        root1.setBottom(hbox);
       // vbox.setLayoutX(100);
        //vbox.setLayoutY(300);
        root1.setCenter(view);
        Scene scene = new Scene(root1,1200,700,Color.BLACK);
        stage.setScene(scene);
        root1.setBackground(Background.EMPTY);
        stage.show();
        String str4 = "play146.png";
        Image image4 = new Image(new File(str4).toURI().toString());
        ImageView view5 = new ImageView(image4);
        String str5 = "play147.png";
        Image image5 = new Image(new File(str5).toURI().toString());
        ImageView view6 = new ImageView(image5);
        Button mute = new Button();
        mute.setGraphic(view6);
        z=0;
        mute.setOnAction(e->{
            muteaction(view5,view6,mute);
        });
        hbox.getChildren().add(mute);
    }
    public void playPlayer(Stage stage) {
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                w = player.getMedia().getWidth();
                h = player.getMedia().getHeight();
                x=0;                
               stage.setMinWidth(w);
               stage.setMinHeight(h);
                //vbox.setMinSize(w, 100);
                //vbox.setTranslateY(h);
               Duration value = player.getTotalDuration();  
               System.out.println(" size " + value.toSeconds());
               timeSlider.setValue(0.0);
               timeSlider.setMax( value.toSeconds());
              // player.setAutoPlay(true);
            }
        });
        
        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
              //  System.out.println("Player is playing ,value = " + newValue.toSeconds() + " old value = " + oldValue);
                timeSlider.setValue( newValue.toSeconds());
            }
        });
    }
    public void positiveaction(){
        if(y>10)
            return;
        player.setVolume(y/10.0);
        y=y+1;
    }
    public void negativeaction(){
       if(y<0)
           return;
        player.setVolume(y/10.0);
        y=y-1;
    }
    public void muteaction(ImageView v1,ImageView v2,Button x)
    {
       // player.setMute(!player.isMute());
       if(z==0){
        player.setVolume(0);
        z=1;
        x.setGraphic(v1);
        }
        else{
            player.setVolume(y);
            z=0;
            x.setGraphic(v2);
        }
    }
}
