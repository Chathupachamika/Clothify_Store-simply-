package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colCreatedAt;

    @FXML
    private TableColumn<?, ?> colImage;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductIddata;

    @FXML
    private TableColumn<?, ?> colProductNamedata;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private DatePicker txtCreatedAt;

    @FXML
    private JFXTextField txtGetSearchNameorEmail;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtProductId1;
    @FXML
    private DatePicker txtUpdatedAt;
    private final ProductService productService = new ProductServiceImpl();
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    void btnAddProduct(ActionEvent event) {
        try {
            int productId = Integer.parseInt(txtProductId.getText());
            String productName = txtProductName.getText();
            String category = (String) cmbCategory.getValue();
            String size = txtSize.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            String image = "image.jpg";
            int supplierId = Integer.parseInt(txtSupplierId.getText());
            LocalDate createdAt = txtCreatedAt.getValue();
            LocalDate updatedAt = txtUpdatedAt.getValue();
            double discount = 0.00;

            Product product = new Product(productId, productName, category, size, price, quantity, image, supplierId, createdAt, updatedAt, discount);
            if (productService.addProduct(product)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully!");
                System.out.println(product);
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add product. Please try again.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }

    }

    @FXML
    void btnReload(ActionEvent event) {
        loadTable();
    }
    @FXML
    void btnSearchProduct(ActionEvent event) {
        try {
            int productId = Integer.parseInt(txtGetSearchNameorEmail.getText());
            Product searchedProduct = productService.searchProduct(productId);

            if (searchedProduct != null) {
                ObservableList<Product> observableProducts = FXCollections.observableArrayList(searchedProduct);
                productTable.setItems(observableProducts);
            } else {
                showAlert(Alert.AlertType.WARNING, "No Results", "No product found with the given product ID.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for Product ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    void btnRemoveProduct(ActionEvent event) {
        try {
            int productId = Integer.parseInt(txtProductId1.getText());
            if (productService.removeProduct(productId)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product removed successfully.");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove product.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for Product ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }


    @FXML
    void btnUpdateProduct(ActionEvent event) {
        try {
            int productId = Integer.parseInt(txtProductId.getText());
            String productName = txtProductName.getText();
            String category = (String) cmbCategory.getValue();
            String size = txtSize.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            String image = "image.jpg";
            int supplierId = Integer.parseInt(txtSupplierId.getText());
            LocalDate createdAt = txtCreatedAt.getValue();
            LocalDate updatedAt = txtUpdatedAt.getValue();
            double discount = 0.00;

            Product product = new Product(productId, productName, category, size, price, quantity, image, supplierId, createdAt, updatedAt, discount);
            if (productService.updateProduct(product)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product updated successfully!");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update product.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadTable() {
        List<Product> allProducts = productService.getAllProducts();
        ObservableList<Product> observableProducts = FXCollections.observableArrayList(allProducts);
        System.out.println(allProducts);
        productTable.setItems(observableProducts);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductIddata.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductNamedata.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadTable();
        productTable.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Product> observable, Product oldValue, Product newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        }));
    }
    private void setTextToValues(Product newValue){
        txtProductId.setText(String.valueOf(newValue.getProductId()));
        txtProductName.setText(newValue.getProductName());
        txtPrice.setText(String.valueOf(newValue.getPrice()));
        txtQuantity.setText(String.valueOf(newValue.getQuantity()));
        txtSize.setText(newValue.getSize());
        txtSupplierId.setText(String.valueOf(newValue.getSupplierId()));
        txtCreatedAt.setValue(newValue.getCreatedAt());
        txtUpdatedAt.setValue(newValue.getUpdatedAt());
    }
}