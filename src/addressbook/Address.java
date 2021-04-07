package addressbook;
/*
    * Nathan Power - 101247770
    *
    * Roberto Borges - 101255891
    *
*/
public class Address {
    public String streetInfo1;
    public String streetInfo2;
    public String city;
    public String postalCode;
    public String province;
    public String country;
    
    public Address(String st1, String st2, String city, String postCode, String prov, String country){
        streetInfo1 = st1;
        streetInfo2 = st2;
        this.city = city;
        postalCode = postCode;
        province = prov;
        this.country = country;
    }
    
    // use this formatted string in the contact list in application
    @Override
    public String toString(){
        return streetInfo1 + " " + streetInfo2 + ", " + city + ", " + postalCode + ", " + province + ", " + country;
    }
}
