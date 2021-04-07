package addressbook;

import java.util.ArrayList;
import java.util.Collections;
/*
    * Nathan Power - 101247770
    *
    * Roberto Borges - 101255891
    *
*/
public class ContactManager {
    private ArrayList<Contact> contactList;
    
    public ContactManager(){
        contactList = new ArrayList<>();
    }
    
    // add contact to contact list array
    public void addContact(String firstName, String lastName, String homePhone, String workPhone, Address homeAddress, String email, MyDate birthday, String notes){
        Contact temp = new Contact(firstName, lastName, homePhone, workPhone, homeAddress, email, birthday, notes);
        contactList.add(temp);
    }
    
    // retrieve contact object based on the objects unique toString
    public Contact getContact(String contactString){
        for (Contact contact : contactList){
            if (contactString.equals(contact.toString())) {                                
                return contact;
            }            
        }
        return null;
    }
    
    // delete contact from contact list based on the objects unique toString
    public boolean deleteContact(String contactInfo){
        for (Contact contact : contactList){
            if (contactInfo.equals(contact.toString())) {
                contactList.remove(contact);
                return true;
            }            
        }
        return false;
    }   
    
    // returns an array of contacts in the contact list to be displayed in the contact list in application
    public ArrayList<String> viewContacts() {
        ArrayList<String> contactInfo = new ArrayList<>();        
        for (Contact contact : contactList){
            contactInfo.add(contact.toString());
        }
        Collections.sort(contactInfo);
        return contactInfo;
    }
    
    // returns an array of contacts in the contact list that have the specified first and last name
    public ArrayList<String> findContact(String firstName, String lastName) {
        ArrayList<String> contactSearch = new ArrayList<>();
        for (Contact contact : contactList){
            if (firstName.equals(contact.getFirstName().toLowerCase()) && lastName.equals(contact.getLastName().toLowerCase())) {
                contactSearch.add(contact.toString());
            }            
        }
        Collections.sort(contactSearch);
        return contactSearch;
        
    }
    
    // returns an array of contacts in the contact list that live in a particular city
    public ArrayList<String> contactsInCity(String city) {
        ArrayList<String> contactCity = new ArrayList<>();
        for (Contact contact : contactList){
            if (city.equals(contact.getHomeAddress().city.toLowerCase())) {
                contactCity.add(contact.toString());
            }            
        }
        // sort contacts alphabetically by first name ascending
        Collections.sort(contactCity);
        return contactCity;
    }
}
