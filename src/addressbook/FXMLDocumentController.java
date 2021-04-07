package addressbook;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/*
    * Nathan Power - 101247770
    *
    * Roberto Borges - 101255891
    *
*/
public class FXMLDocumentController implements Initializable {    

    @FXML
    private ListView<String> listContacts;
    @FXML
    private TextField txtContactCity;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private Button btnContactNotes;
    @FXML
    private Button btnEditContact;
    @FXML
    private Button btnDeleteContact;
    @FXML
    private Label lblMessage;
    @FXML
    private Button btnConfirmDel;
    @FXML
    private Button btnCancelDel;
    @FXML
    private BorderPane bottomPane;
    @FXML
    private Pane leftSidePane;
    @FXML
    private BorderPane rightSidePane;
    @FXML
    private Button btnContactCity;
    @FXML
    private Button btnViewContacts;
    @FXML
    private Button btnSubmit;    
    public ContactManager contMan;
    @FXML
    private GridPane contactForm;
    @FXML
    private Button btnAddContact;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtFormFn;
    @FXML
    private TextField txtHp;
    @FXML
    private TextField txtFormLn;
    @FXML
    private TextField txtWp;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker txtBday;
    @FXML
    private TextField txtAdd1;
    @FXML
    private TextField txtAdd2;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtProv;
    @FXML
    private TextField txtPc;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextArea txtNotes;
    @FXML
    private Label lblError;
    public LocalDate birthday;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnConfirm;
    @FXML
    private GridPane viewNotes;
    @FXML
    private Text txtNoteField;
    @FXML
    private VBox viewNotesPanel;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // show the contact list on starting application
        contactForm.setVisible(false);
        viewNotes.setVisible(false);
        // create an instance of Contact Manager
        contMan = new ContactManager();        
        lblMessage.setText("Click on a contact line to edit, delete or view the notes of the contact");
        setBtnsDisabled(); 
        // populate the Contact List
        listContacts.getItems().addAll(contMan.viewContacts()); 
        // show message if contact list is empty
        if (isEmptyList()){
            lblMessage.setText("No Contacts Available");
        }
    }
    
    public void loadData(){
        // used by many methods to retrieve the contact list
        listContacts.getItems().addAll(contMan.viewContacts());
    }

    @FXML
    private void contactByCity(ActionEvent event) {
        lblMessage.setText("Click on a contact line to edit, delete or view the notes of the contact");
        setBtnsDisabled();
        // when user searches for a city, application will search through array of contacts with the specified city in their toString method
        // it will populate the contact list display with all contacts found
        if (!txtContactCity.getText().equals("")) {
            btnViewContacts.setDefaultButton(true);
            listContacts.getItems().clear();
            listContacts.getItems().addAll(contMan.contactsInCity(txtContactCity.getText().toLowerCase()));
            if (listContacts.getItems().isEmpty()) {
                lblMessage.setText("No contacts in the Address Book live in '" + txtContactCity.getText() + "'.");
            }
            txtContactCity.setText("");
        }
        else {
            lblMessage.setText("Field to view contacts by city must be filled out");
        }
        
    }

    @FXML
    private void searchName(ActionEvent event) {
        lblMessage.setText("Click on a contact line to edit, delete or view the notes of the contact");
        setBtnsDisabled(); 
        // check to make sure first and last name fields are both filled out
        if (txtFirstName.getText().equals("") || txtLastName.getText().equals("")) {            
            lblMessage.setText("Both First and Last name must be filled out");
        }
        // when user searches for a contact with their first and last name, application will search through array of contacts with specified first and last name
        // it will populate the contact list display with all contacts found
        else {
            listContacts.getItems().clear();
            listContacts.getItems().addAll(contMan.findContact(txtFirstName.getText().toLowerCase(), txtLastName.getText().toLowerCase()));
            if (listContacts.getItems().isEmpty()) {
                lblMessage.setText("No contacts named '" + txtFirstName.getText() + " " + txtLastName.getText() + "' in the Address Book.");
            }
            txtFirstName.setText("");
            txtLastName.setText("");
        }
    }

    @FXML
    private void viewAllContacts(ActionEvent event) {
        // the button for View All Contacts will hide any other panes in the application and show the view that is shown at the start of the application
        // all contacts will be listed in the contact list display
        btnViewContacts.setDefaultButton(false);
        lblMessage.setText("Click on a contact line to edit, delete or view the notes of the contact");
        bottomPane.setVisible(true);
        leftSidePane.setVisible(true);
        rightSidePane.setVisible(true);
        contactForm.setVisible(false);
        viewNotes.setVisible(false);
        emptyFields();
        listContacts.setVisible(true);
        lblTitle.setText("Address Book");
        setBtnsDisabled();
        listContacts.getItems().clear();
        listContacts.getItems().addAll(contMan.viewContacts());
        if (isEmptyList()){
            lblMessage.setText("No Contacts Available");
        }
    }

    @FXML
    private void displayOptions(MouseEvent event) {
        // if a contact list item is selected, it will enable the buttons used to edit, delete or view notes of the particular contact
        try{
            String contact = listContacts.getSelectionModel().getSelectedItem();
            if (!contact.isEmpty()){
                btnEditContact.setDisable(false);
                btnDeleteContact.setDisable(false);
                btnContactNotes.setDisable(false);
                lblMessage.setText("");
            }
        }
        // if blank space on the list is clicked, it will catch the null exception and change the top message
        catch (Exception e) {
            if (e.getMessage() == null && isEmptyList()){
                // this message shows when there are no contacts in the program
                lblMessage.setText("Must have contacts in the list before you can select them");
            }
            else {
                // this message shows when there are contacts and the user clicked on blank space in the list
                lblMessage.setText("Please click on a valid contact");
            }           
        }        
    }
    
    
    @FXML
    private void setBtnsDisabled(){
        // disables the buttons used to edit, delete or view notes of a contact
        btnEditContact.setDisable(true);
        btnDeleteContact.setDisable(true);
        btnContactNotes.setDisable(true);
        btnConfirmDel.setVisible(false);
        btnCancelDel.setVisible(false);
    }

    @FXML
    private void deleteSelectedContact(ActionEvent event) {
        // gets the contact from the contactList array by comparing the toString method of contact clicked
        bottomPane.setVisible(false);
        leftSidePane.setVisible(false);
        rightSidePane.setVisible(false);
        String contact = listContacts.getSelectionModel().getSelectedItem();
        listContacts.getItems().clear();
        listContacts.getItems().add(contact);
        // will prompt the user to confirm deletion
        lblMessage.setText("Are you sure you want to delete this contact?");
        btnConfirmDel.setVisible(true);
        // if user cancels the deletion, it will call the view all contacts method in this class to bring them back to the start of application
        btnCancelDel.setVisible(true);
    }

    @FXML
    private void confirmDelete(ActionEvent event) {
        // if user confirms deletion, it will call delete contact function in the Contact Manager class
        contMan.deleteContact(listContacts.getItems().get(0));
        viewAllContacts(event);
    }
    
    private boolean isEmptyList(){
        // simple boolean method to check if contact list is empty
        return listContacts.getItems().isEmpty();
    }
    
    @FXML
    private void addContact(ActionEvent event) throws IOException { 
        // brings user to a form to add a contact
        lblMessage.setText("");
        formView();
        lblTitle.setText("Add Contact");
        btnConfirm.setText("Add Contact");
        btnBack.setVisible(true);
    }
    
    private boolean isBlank() {
        // simple boolean method to check if any mandatory field in the add/edit form are empty
        return (txtFormFn.getText().equals("") ||
                txtFormLn.getText().equals("") ||
                txtHp.getText().equals("") ||
                txtAdd1.getText().equals("") ||
                txtCity.getText().equals("") ||
                txtProv.getText().equals("") ||
                txtPc.getText().equals("") ||
                txtCountry.getText().equals("") ||
                txtBday.getValue() == null);
    }
    
    private void emptyFields() {
        // will set all the fields in the add/edit form to empty
        txtFormFn.setText("");
        txtFormLn.setText("");
        txtHp.setText("");
        txtWp.setText("");
        txtAdd1.setText("");
        txtAdd2.setText("");
        txtCity.setText("");
        txtProv.setText("");
        txtPc.setText("");
        txtCountry.setText("");
        txtEmail.setText("");
        txtBday.setValue(null);
        txtNotes.setText("");        
    }

    @FXML
    private void addValidContact(ActionEvent event) {
        String firstName, lastName, homePhone, workPhone, address1, address2, city, postalCode, province,
                country, email, notes;
        int day, month, year;
        
        // will run this code if all of the mandatory fields are filled out
        if (!isBlank()){
            // work phone, email and notes can be blank, and the program will set them to a default value if they are
            // additionally, address line 2 can be blank but can be left as empty string to fit good formatting practice in the contact's toString method
            if (txtWp.getText().equals("")){
                txtWp.setText("N/A");
            }
            if (txtEmail.getText().equals("")){
                txtEmail.setText("N/A");
            }
            if (txtNotes.getText().equals("")){
                txtNotes.setText("No Notes Available");
            } 
            // most fields will be converted to have the first letter in the first word in the string to uppercase
            firstName = capitalize(txtFormFn.getText());
            lastName = capitalize(txtFormLn.getText());                                 
            homePhone = txtHp.getText();
            workPhone = txtWp.getText();
            address1 = txtAdd1.getText();
            address2 = txtAdd2.getText();
            city = capitalize(txtCity.getText());
            postalCode = txtPc.getText().toUpperCase();
            province = capitalize(txtProv.getText());
            country = capitalize(txtCountry.getText());
            email = txtEmail.getText();
            day = txtBday.getValue().getDayOfMonth();
            month = txtBday.getValue().getMonthValue();
            year = txtBday.getValue().getYear();
            notes = txtNotes.getText();
            // create the contact based off of the information provided by user
            contMan.addContact(firstName, lastName, homePhone, workPhone, 
                    new Address(address1, address2, city, postalCode, province, country), 
                    email, new MyDate(day, month, year), notes);
            // this code will run if the user entered this form to edit a contact
            // first the previous contact will be deleted and the new one will take its place with the updated information
            if (btnConfirm.getText().equals("Edit Contact")){
                String contact = listContacts.getSelectionModel().getSelectedItem();
                contMan.deleteContact(contact);
            }
            // bring user back to the start of application to view all contacts in the list after they have added/edited a contact
            viewAllContacts(event);                
        }
        else {
            // this error message is for if any fields are empty
            lblError.setVisible(true);
        }        
    }

    @FXML
    private void editContact(ActionEvent event) {
        // takes user to a form to edit the contact 
        String contact = listContacts.getSelectionModel().getSelectedItem();
        formView();
        lblMessage.setText("");
        lblTitle.setText("Edit Contact");
        btnConfirm.setText("Edit Contact");
        
        //the fields in the form will be populated with the information from the selected contact
        Contact temp = contMan.getContact(contact);
        txtFormFn.setText(temp.getFirstName());
        txtFormLn.setText(temp.getLastName());
        txtHp.setText(temp.getHomePhone());
        txtWp.setText(temp.getWorkPhone());
        txtAdd1.setText(temp.getHomeAddress().streetInfo1);
        txtAdd2.setText(temp.getHomeAddress().streetInfo2);
        txtCity.setText(temp.getHomeAddress().city);
        txtProv.setText(temp.getHomeAddress().province);
        txtPc.setText(temp.getHomeAddress().postalCode);
        txtCountry.setText(temp.getHomeAddress().country);
        txtEmail.setText(temp.getEmail());
        birthday = LocalDate.of(temp.getBirthday().getYear(), getMonth(temp.getBirthday().getMonthShortForm()), temp.getBirthday().getDay());
        txtBday.setValue(birthday);
        txtNotes.setText(temp.getNotes());
    }
    
    private int getMonth(String month){
        // this method is used in the editing form
        // it will take the value of the birthday month in the contacts info and format it to display it in datePicker control in the form
        switch(month){
            case "Jan": {
                return 1;
            }
            case "Feb": {
                return 2;
            }
            case "Mar": {
                return 3;
            }
            case "Apr": {
                return 4;
            }
            case "May": {
                return 5;
            }
            case "Jun": {
                return 6;
            }
            case "Jul": {
                return 7;
            }
            case "Aug": {
                return 8;
            }
            case "Sep": {
                return 9;
            }
            case "Oct": {
                return 10;
            }
            case "Nov": {
                return 11;
            }
            case "Dec": {
                return 12;
            }
            default: {
                return 1;
            }
        }
    }
    
    private void formView() {
        // will hide the contact list and other panes and show the add/edit form
        listContacts.setVisible(false);
        contactForm.setVisible(true);
        bottomPane.setVisible(false);
        leftSidePane.setVisible(false);
        rightSidePane.setVisible(false);
        lblError.setVisible(false);
    }

    @FXML
    private void viewContactNotes(ActionEvent event) { 
        // this button is used to view notes and to bring the user back to the start of application if they are viewing notes
        if (btnContactNotes.getText().equals("Back to Address Book")) {
            viewNotesPanel.toBack();
            viewAllContacts(event);
            btnContactNotes.setText("View Contact Notes");
        }
        // will hide all panes and show the selected contact's notes
        else {
            lblTitle.setText("Contact Notes");
            btnContactNotes.setText("Back to Address Book");
            viewNotesPanel.toFront();
            listContacts.setVisible(false);        
            bottomPane.setVisible(false);
            leftSidePane.setVisible(false);
            viewNotes.setVisible(true);
            lblMessage.setText("");
            
            // will retrive the notes value from the selected contact snd display them in a textarea
            String contact = listContacts.getSelectionModel().getSelectedItem();
            contMan.getContact(contact);
            Contact temp = contMan.getContact(contact);
            txtNoteField.setText(temp.getNotes());
        }      
    }  
    
    private String capitalize(String word) {
        // used by the add contact method to change the noun fields to all lowercase and then capitalize the first letter of the noun
        String formattedWord = word;
        formattedWord = formattedWord.toLowerCase();
        formattedWord = formattedWord.substring(0,1).toUpperCase() + formattedWord.substring(1);
        return formattedWord;
    }
}
