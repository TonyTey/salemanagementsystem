package SaleManagementSystem;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SaleManagementSystem extends Application{
    private String[] itemTitle = 
    {
        "Onion-Package(China)(1kg)",
        "Onion-Package(Pakistan)(1kg)",
        "Potato-Package(Pakistan)(500g)",
        "Potato-Package(Pakistan)(1kg)",
        "Potato-Package(China)(500g)",
        "Potato-Package(China)(1kg)",
        "Small Onion-Package(India)(500g)",
        "Small Onion-Package(India)(1kg)",
        "Small Onion-Package(Thailand)(500g)",
        "Small Onion-Package(Thailand)(1kg)",
        "RedBean-Package(30kg)",
        "GreenBean-Package(30kg)",
        "Sugar-Package(50kg)",
        "Salted Fish(100g)",
        "Mealt's Curry Powder(In bulk)(Babas)(100g)",
        "Mealt's Curry Powder(Box)(Babas)(500g)",
        "Mealt's Curry Powder(Box)(Babas)(1kg)",
        "Fish's Curry Powder(In bulk)(Babas)(100g)",
        "Fish's Curry Powder(Box)(Babas)(500g)",
        "Fish's Curry Powder(Box)(Babas)(1kg)",
        "Turmeric powder(Box)(Adabi)(50g)",
        "Turmeric powder(Box)(Adabi)(100g)",
        "Turmeric powder(Box)(Adabi)(500g)",
        "Ginger-Box(China)(500g)",
        "Ginger-Box(China)(1kg)",
        "Bihun-Package(Chili)(1kg)",
        "Bihun-Package(Chili)(2.5kg)"
    };
    
    double priceCalculated = 0;
    double quantityCalculated = 0;
    String priceAfterMutiply;
    
    TextField  tfQuantity = new TextField();
    TextField  tfPrice = new TextField();
    TextField tfTotalPrice = new TextField();
    
    int totalSaleItem;
    
    TableView<Person> tableViewList = new TableView<>();
    
    

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        ObservableList<String> cobList = (FXCollections.observableArrayList(itemTitle));
        ObservableList<Person> itemList = (FXCollections.observableArrayList());
        
        
        tableViewList.setEditable(true);
        tableViewList.setPrefWidth(800);
        tableViewList.setPrefHeight(500);
        
       
        tfTotalPrice.setEditable(false);
        tfTotalPrice.setText("0");
        TextField tfItemQuantity = new TextField();
        tfItemQuantity.setEditable(false);
        tfItemQuantity.setText("0");
        
        
        
        
        
        Label lbItem = new Label("Item List                       : ");
        Label lbQuantity = new Label("Quantity                      : ");
        Label lbPrice = new Label("Item Unit Price(RM) : ");
        Label lbTotalItem = new Label("Total Sold Item :");
        Label lbTotaPrice = new Label("Total Price(RM) :");
        
        
        
        
        lbItem.setFont(Font.font("Serif", FontWeight.BOLD, 
           FontPosture.ITALIC, 20));
        lbQuantity.setFont(Font.font("Serif", FontWeight.BOLD, 
           FontPosture.ITALIC, 20));
        lbPrice.setFont(Font.font("Serif", FontWeight.BOLD, 
           FontPosture.ITALIC, 20));
        
        //create Text
        Text title = new Text("Southern Grocery Shop");
        title.setFont(Font.font("Serif", FontWeight.BOLD, 
           FontPosture.ITALIC, 40));
        title.setFill(Color.PURPLE);
        StackPane textPane = new StackPane(title);
        textPane.setPadding(new Insets(20,0,20,0));

        ComboBox<String> cob = new ComboBox<>();
        cob.getItems().addAll(cobList);
        
        TableColumn  itemColumn = new TableColumn("Sold Item");
        itemColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("itemList"));
        itemColumn.setPrefWidth(500);
        TableColumn quantityColumn = new TableColumn("Quantity");
        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("quantityList"));
        quantityColumn.setPrefWidth(100);
        TableColumn priceColumn = new TableColumn("Price(RM)");
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("priceList"));
        priceColumn.setPrefWidth(200);
        
        
        
        
        Button add = new Button("Add");
        add.setOnAction((t) -> {
            quantityCalculated = Double.parseDouble(tfQuantity.getText());
            priceCalculated = Double.parseDouble(tfPrice.getText());
            priceAfterMutiply = Double.toString(quantityCalculated * priceCalculated);
            itemList.add(
                    new Person(cob.getSelectionModel().getSelectedItem(), tfQuantity.getText(),priceAfterMutiply));
            calculatedPrice();
            tfPrice.clear();
            tfQuantity.clear();
            totalSaleItem += 1;
            tfItemQuantity.setText(Integer.toString(totalSaleItem));
        });
        
        
        
        Button delete = new Button("Delete");
        delete.setOnAction((t) -> {
            minusPrice();
            if(tableViewList.getItems().removeAll(tableViewList.getSelectionModel().getSelectedItems())) 
            {
                tfItemQuantity.setText(Integer.toString(totalSaleItem -= 1 ));
                tableViewList.getSelectionModel().clearSelection();
            }
            
            if(tableViewList.getItems().isEmpty() == true)
            {
                tfTotalPrice.setText("0");
            }

        });
        
        
        Button clean = new Button("Clean All");
        clean.setOnAction((t) -> {
            tableViewList.getItems().clear();
            tfTotalPrice.clear();
            tfTotalPrice.setText("0");
            cob.getSelectionModel().clearSelection();
            tfPrice.clear();
            tfQuantity.clear();
            tfItemQuantity.setText("0");
            totalSaleItem = 0;
            
        });
        
        
        
        
        
        
        tableViewList.getColumns().addAll(itemColumn,quantityColumn,priceColumn);
        tableViewList.setItems(itemList);
        
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(add,clean,delete);
        
        HBox itemBox = new HBox(10);
        itemBox.getChildren().addAll(lbTotalItem,tfItemQuantity);
        
        HBox priceBox = new HBox(10);
        priceBox.getChildren().addAll(lbTotaPrice,tfTotalPrice);
        
        HBox combineBox = new HBox(50);
        combineBox.getChildren().addAll(itemBox,priceBox);
        
        
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.add(lbItem, 0, 0);
        gridpane.add(cob, 1, 0);
        gridpane.add(lbQuantity,0,1);
        gridpane.add(tfQuantity,1,1);
        gridpane.add(lbPrice, 0, 2);
        gridpane.add(tfPrice, 1, 2);
        gridpane.add(buttonBox,1,3);
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        
        
        GridPane tablePane = new GridPane();
        tablePane.add(tableViewList, 0, 0);
        tablePane.add(combineBox, 0, 1);
        tablePane.setPadding(new Insets(0,0,10,0));
        tablePane.setHgap(10);
        tablePane.setVgap(10);
        tablePane.setPadding(new Insets(0,20,20,0));
        
        BackgroundFill bgColor = new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgColor);
        
        
        BorderPane pane = new BorderPane();
        pane.setCenter(gridpane);
        pane.setRight(tablePane);
        pane.setTop(textPane);
        pane.setBackground(bg);
        
        Scene scene = new Scene(pane);
        
        
        stage.setTitle("Sale Management System");
        stage.setScene(scene);
        stage.setWidth(1500);
        stage.setHeight(500);
        stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDGPNLvZjODV94Ac2Q5o4Fc9B10hqZx5XvbA&usqp=CAU"));
        stage.setResizable(false);
        stage.show();
          
    }
    
    public static class Person {
        private final SimpleStringProperty itemList;
        private final SimpleStringProperty priceList;
        private final SimpleStringProperty quantityList;
        
        private Person(String item, String quantity, String price) {
            this.itemList = new SimpleStringProperty(item);
            this.quantityList = new SimpleStringProperty(quantity);
            this.priceList = new SimpleStringProperty(price);
        }
        
        public String getItemList() {
            return itemList.get();
        }
        
        public void setItem(String item) {
            itemList.set(item);
        }
        
        public String getPriceList() {
            return priceList.get();
        }
        
        public void setPrice(String price) {
            priceList.set(price);
        }
        
        public String getQuantityList() {
            return quantityList.get();
        }
        
        public void setQuantityList(String quantity) {
            quantityList.set(quantity);
        }
        
    }
    
        private void calculatedPrice() {
        
        double price = Double.parseDouble(tfPrice.getText());
        int quantity = Integer.parseInt(tfQuantity.getText());
        
        PriceCalculate calculated = new PriceCalculate(price,quantity);
       
        
         double totalPrice = Double.parseDouble(tfTotalPrice.getText());
        
        tfTotalPrice.setText(Double.toString(calculated.getCalculatePrice() + totalPrice));
        
    }
        private void minusPrice() {
    
    double minusForPrice = Double.parseDouble(tableViewList.getSelectionModel().getSelectedItem().getPriceList());
    double priceNow = Double.parseDouble(tfTotalPrice.getText());
    
    MinusPrice priceMinus = new MinusPrice(minusForPrice,priceNow);
    
    tfTotalPrice.setText(Double.toString(priceMinus.getMinusTotalPrice()));
    
}
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
