package com.example.supplychainmngmt;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails{

    public TableView<Product> productTable;
//    Product product = new Product();

    public Pane getAllProducts(){

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        TableColumn quantity = new TableColumn("Quantity");
//        quantity.setCellFactory(new PropertyValueFactory<>("quantity"));

//        final ObservableList<Product> data = FXCollections.observableArrayList();
//
//        data.add(new Product(1,"lenovo", 66000));
//        data.add(new Product(2,"Mac", 90000));
//        productTable = new TableView<>();
//        productTable.setItems(data);
//        productTable.getColumns().addAll(id,name,price);

        ObservableList<Product> iteams = Product.getAllProduct();

        productTable = new TableView<>();
        productTable.setItems(iteams);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);


        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);
        return tablePane;

    }

    public Pane getproductsByName(String searchName){

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        TableColumn quantity = new TableColumn("Quantity");
//        quantity.setCellFactory(new PropertyValueFactory<>("quantity"));



        ObservableList<Product> iteams = Product.getProductByName(searchName);

        productTable = new TableView<>();
        productTable.setItems(iteams);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width, SupplyChain.height);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);
        return tablePane;

    }

    public Product getSelectedProduct(){
        if(productTable == null)
        {
            System.out.print("Object Not Found!");
            return null;

        }
        if(productTable.getSelectionModel().getSelectedIndex()!= -1)
        {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId() + " " +
                      selectedProduct.getName()+ " " +
                       selectedProduct.getPrice()
            );
                    return selectedProduct;
        }else{
            System.out.print("Nothing Selected");
            return null;
        }


    }
}
