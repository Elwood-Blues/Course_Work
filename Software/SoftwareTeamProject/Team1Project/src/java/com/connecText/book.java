package com.connecText;

/**
 *
 * @author Peter Braband, Michael Ellison, Alexa Chiu, Grant Page, Abigal Reed
 */
public class book {
    // variables of book
    private String Title;
    private String Author;
    private String ForClass;
    private String ISBN;
    private String Edition;
    private String owner; //this will be an email value
    private String price;
    
    public book (String Title, String Author, String ForClass, String ISBN, 
            String Edition, String owner, String price) {
        this.Title = Title;
        this.Author = Author;
        this.ForClass = ForClass;
        this.ISBN = ISBN;
        this.Edition = Edition;
        this.owner = owner;
        this.price = price;
    }
    
    // accessors and mutatos for the book object
    public String getTitle() {
        return Title;
    }
    
    public void setTitle(String Title) {
        this.Title = Title;
    }
    
    public String getAuthor() {
        return Author;
    }
    
    public void setAuthor(String Author) {
        this.Author = Author;
    }
    
    public String getForClass() {
        return ForClass;
    }
    
    public void setForClass(String ForClass) {
        this.ForClass = ForClass;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public String getEdition() {
        return Edition;
    }
    
    public void setEdition(String Edition) {
        this.Edition = Edition;
    }
    
    public void setOwner(String email){
        this.owner = email;
    }
    
    public String getOwner(){
        return this.owner;
    }
    
    public void setPrice(String price){
        this.price = price;
    }
    
    public String getPrice(){
        return this.price;
    }
}