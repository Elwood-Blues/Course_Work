package java8;

import java.util.stream.Stream;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A lab exercise to introduce Java 8 streams and JavaFX
 * @author Your name here
 */
public class Java8 extends Application {
    
    public static final int ROWS = 4;
    public static final int COLS = 5;
    public static final int CELL_SIZE = 100;
    
    
    @Override
    public void start(Stage primaryStage) {
        root = new VBox();
        canvas = new Pane();
        starter = new Button("Circles");
        
        configure();         // You must write
        addButtonHandler();  // You must write
        
        this.root.getChildren().addAll(canvas, starter);
        
        primaryStage.setTitle("Java 8 Lab Exercise");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //makeRow().forEach(x -> System.out.println(x));
        //makeAllRows().forEach(r -> r.forEach(x -> System.out.println(x)));
    }
    
    /**
     * This method sets the alignment of the root container
     * and the size of the canvas.
     */
    private void configure() {
        // You must write
        canvas.setPrefSize((double) COLS * CELL_SIZE, (double) ROWS * CELL_SIZE);
        this.root.setAlignment(Pos.BOTTOM_CENTER);
        
    }
       
    /**
     * This method adds the handler to the button that gives
     * this application its behavior.
     */
    private void addButtonHandler() {
        // You must write
        this.starter.setOnAction(e -> {canvas.getChildren().clear();
            this.addAllRowsToCanvas(makeAllRows());
                });
                //addToCanvas(new Circle((double)CELL_SIZE/4)));
        
    }
    
    private void addToCanvas(Circle c){
        c.setFill(new Color(Math.random(), Math.random(), Math.random(), 1.0));
        double toX = this.col * 100 + 50;
        double toY = this.row *100 + 50;
        double fromX = 450.0;
        double fromY = 350.0;
        c.setCenterX(fromX); 
        c.setCenterY(fromY);
        TranslateTransition tt = new TranslateTransition(Duration.millis(500));
        tt.setNode(c);
        tt.setByX(toX - fromX);
        tt.setByY(toY - fromY);
        tt.play();
        ScaleTransition st = new ScaleTransition(Duration.millis((double)1000 * Math.random() + 400));
        st.setNode(c);
        st.setByX(circleScale);
        st.setByY(circleScale);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
        this.canvas.getChildren().add(c);
    }
    
    private void addAllRowsToCanvas(Stream<Stream<Circle>> s){
        this.row = 0;
        s.forEach(r -> {this.addRowToCanvas(r); ++this.row;});
    }
    
    private Stream<Circle> makeRow(){ 
        return Stream.generate(()-> new Circle(radius)).limit(COLS); 
    }
    
    private Stream<Stream<Circle>> makeAllRows(){
        return Stream.generate(() -> this.makeRow()).limit(ROWS);
    }
    
    private void addRowToCanvas(Stream<Circle> s){
        this.col = 0;
        s.forEach(c -> {this.addToCanvas(c);
        ++this.col;
        });
        
    }
    
    
    private VBox root;
    private Pane canvas;
    private Button starter;
    private int row;
    private int col;
    final private double radius = 25.0;
    final private double circleScale = 2.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
