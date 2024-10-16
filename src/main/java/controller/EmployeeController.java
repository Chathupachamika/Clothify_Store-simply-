package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Product;
import repository.EmployeeRepository;
import repository.impl.EmployeeRepositoryImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmployeeCompany;

    @FXML
    private TableColumn<?, ?> colEmployeeEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private JFXTextField txtGetSearchNameorEmail;

    @FXML
    private JFXTextField txtemployeeCompany;

    @FXML
    private JFXTextField txtemployeeEmail;

    @FXML
    private JFXTextField txtemployeeID;

    @FXML
    private JFXTextField txtemployeeName1;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    @FXML
    void btnAddEmployee(ActionEvent event) {
        try {
            String id = txtemployeeID.getText();
            String productName = txtemployeeName1.getText();
            String email = txtemployeeEmail.getText();
            String company = txtemployeeCompany.getText();
            Employee employee = new Employee(id, productName, email, company);
            if (employeeRepository.addEmployee(employee)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee.");
                loadTable();
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        clearFields();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields(){
        txtemployeeCompany.clear();
        txtemployeeID.clear();
        txtemployeeEmail.clear();
        txtemployeeName1.clear();
    }
    @FXML
    void btnRemoveEmployee(ActionEvent event) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeRepository.removeEmployee(selectedEmployee.getEmployeeId());
            employeeList.remove(selectedEmployee);
            employeeTable.setItems(employeeList);
        }
        loadTable();
    }

    @FXML
    void btnSearchEmployee(ActionEvent event) {
        String searchKey = txtGetSearchNameorEmail.getText();
        Employee employee = employeeRepository.searchEmployee(searchKey);
        if (employee != null) {
            txtemployeeID.setText(employee.getEmployeeId());
            txtemployeeName1.setText(employee.getName());
            txtemployeeCompany.setText(employee.getCompany());
            txtemployeeEmail.setText(employee.getEmail());
        }
    }

    @FXML
    void btnUpdateEmployee(ActionEvent event) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployee.setName(txtemployeeName1.getText());
            selectedEmployee.setCompany(txtemployeeCompany.getText());
            selectedEmployee.setEmail(txtemployeeEmail.getText());

            employeeRepository.updateEmployee(selectedEmployee);
            employeeTable.refresh();
            clearFields();
        }
    }
    @FXML
    void btnReloadEmployee(ActionEvent event) {
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
        employeeTable.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        }));
}

    private void loadTable() {
        List<Employee> allEmployees = employeeRepository.getAllEmployees();
        ObservableList<Employee> observableEmployees = FXCollections.observableArrayList(allEmployees);
        System.out.println(allEmployees);
        employeeTable.setItems(observableEmployees);
    }
    private void setTextToValues(Employee newValue) {
        txtemployeeID.setText(newValue.getEmployeeId());
        txtemployeeName1.setText(newValue.getName());
        txtemployeeEmail.setText(newValue.getEmail());
        txtemployeeCompany.setText(newValue.getCompany());
    }
    }
