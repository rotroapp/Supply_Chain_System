package com.example.supplychainmngmt;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SupplyChain extends Application {


    public static final int  height = 600, width = 700, upperline = 70;
    Pane bodyPane = new Pane();
    Label loginLabel;
    Boolean loggedIn = false;
    Login login = new Login();
    ProductDetails productDetails = new ProductDetails();
    Button loginButton;
    Button logoutButton;
    Button orderButton;
    public GridPane hearBar()
    {
        GridPane gridPane = new GridPane();


        gridPane.setPrefSize(width,upperline-5);

        TextField searchText = new TextField();
        searchText.setMinWidth(250);
        searchText.setPromptText("Please search here");
        loginLabel = new Label("Please Login!");
        System.out.println("Before if");

        if(loggedIn == false)
        {
            System.out.println("login");
            loginButton = new Button(" LogIn  ");
            logoutButton = new Button("LogOut");

        }else {

            System.out.println("logout");
            gridPane.getChildren().remove(loginButton);
            gridPane.getChildren().remove(loginLabel);
            logoutButton = new Button("LogOut");


        }


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn == false)
                {
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                    bodyPane.setStyle("-fx-background-color: #67baeb");



                }

            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loggedIn = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Logged Out");
                alert.setHeaderText("You are logged out successfully");
                alert.show();
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                    bodyPane.setStyle("-fx-background-color: #67baeb");
                hearBar();


            }
        });
        Button searchButton = new Button("Search Iteam");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


               bodyPane.getChildren().clear();
//               bodyPane.getChildren().add(productDetails.getAllProducts());
                String search = searchText.getText();
                bodyPane.getChildren().add(productDetails.getproductsByName(search));
            }
        });
//        if(flag == 0 )
//        {
//            Label messagelabel = new Label("Please Login first");
//            gridPane.add(messagelabel,0,1);
//            return gridPane;
//        }
        gridPane.add(searchText,7,1);
        gridPane.add(searchButton,8,1);

        gridPane.add(logoutButton,19,0);
        gridPane.add(loginButton,19,0);
        gridPane.add(loginLabel,19,1);
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: #b0dbf5");
        bodyPane.setStyle("-fx-background-color: #ead758");


        return gridPane;
    }
    private GridPane loginPage()
    {
        Label emaillabel = new Label("Email");
        Label passordlabel = new Label("Password");
        Label messagelabel = new Label(null);

        TextField emailfield = new TextField();
        emailfield.setPromptText("Please enter emailID");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Please enter password");
        Button loginButton = new Button("Login");
        Button SignUpButton = new Button("SignUp");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailfield.getText();
                String password = passwordField.getText();
                if(login.customerLogin(email,password)){
                    loginLabel.setText("Welcome :-) "+ email);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());

                    bodyPane.setStyle("-fx-background-color: #0b584f");


                    messagelabel.setText("Login Successful");


                    loggedIn = true;
                    System.out.println("before hear bar");
                    hearBar();
                    System.out.println("After hear bar");
                    loginLabel.setText("Welcome :-) "+ email);


                }else{
                    messagelabel.setText("Invalid User,If new please SignUp!");

                }
//                messagelabel.setText("email - " + email + " & pass - " + password);
            }
        });

        SignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailfield.getText();
                String password = passwordField.getText();
                if(login.customerSignup(email,password))
                {

                    messagelabel.setText("Account Added");
                }else{
                    messagelabel.setText("Account Already Exist or Wrong Input");
                }
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setVgap(10);
        gridPane.setHgap(10);



        gridPane.add(emaillabel, 0, 0);
        gridPane.add(emailfield, 1, 0);
        gridPane.add(passordlabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 0, 3);
        gridPane.add(SignUpButton,1,3);
        gridPane.add(messagelabel,1 ,4);

        return gridPane;
    }

    private GridPane footerBar(){

        GridPane gridPane = new GridPane();
        gridPane.setMinWidth(width);
        gridPane.setMinHeight(90);
//        -fx-background-color: #136392
//        gridPane.setStyle("-fx-background-color: #ddfbf7");
        gridPane.setStyle("-fx-background-color: #b0dbf5");
        orderButton = new Button("Buy Now");
        gridPane.setAlignment(Pos.CENTER);

        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn == false)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Order Status");
                    alert.setHeaderText("Order Not Placed");
                    alert.setContentText("Kindly Login First");
                    alert.show();

//                    bodyPane.getChildren().clear();
//                    bodyPane.getChildren().add(loginPage());
//                    bodyPane.setStyle("-fx-background-color: #67baeb");

                }else {

                Product product = productDetails.getSelectedProduct();
                if(product != null )
                {
                    String email = loginLabel.getText();
                    String kemail = email.substring(12);
                    System.out.println(kemail + " the fetched email");
                    if(Order.placeSingleOrder(product,kemail)){
                        System.out.println("Order Placed!");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Order Status");
                        alert.setHeaderText("Order Placed Successfully");
                        alert.show();
                    }else {
                        System.out.println("Order Failed!");
                    }
                }else{
                    System.out.println("Please select a product");
                }
                //open
                }
            }
        });
        gridPane.add(orderButton,0,0);
        gridPane.setMinWidth(width);
        gridPane.setTranslateY(height+80);
//        gridPane.setStyle("-fx-background-color: #baeb67");
        return gridPane;

    }


    Pane createContent(){
        Image img = new Image("C:\\Users\\rajat\\Desktop\\smake\\SupplyChainMngmt\\src\\rotoapp.png");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
//        boardImage.setFitWidth(width/2);
//        boardImage.setFitHeight(height/2);
        boardImage.setTranslateX(width/5);
        boardImage.setTranslateY((height)/9);

        Pane root = new Pane();
        root.setPrefSize(width, height + upperline + 80);
        bodyPane.setTranslateY(upperline);
        bodyPane.setMinSize(width,height);
        bodyPane.setStyle("-fx-background-color: #031412");
        bodyPane.getChildren().add(boardImage);



        root.getChildren().addAll(bodyPane,hearBar(), footerBar());


        return root;

    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("E kart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}