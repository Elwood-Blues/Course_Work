package com.connecText;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import sql.SQL;


/**
 *
 * @author Peter Braband, Michael Ellison, Alexa Chiu, Grant Page, Abigal Reed
 */

@ManagedBean
@Named
@SessionScoped
//@Resource(name="jdbc/projectdb")

public class searchBean {
    
    private List<book> books;
    private String Author;
    private String forClass;
    private String title;
    private String searchBy;
    private String keyword;
    private String ISBN;
    private String edition;
    private Connection dbConn = null;
    private CallableStatement searchStatement = null;
    @Resource(name="jdbc/projectdb")
    private DataSource source;

    private static String DB_URL = "jdbc:derby://ukko.d.umn.edu:53155/projectDB";
    
    // TODO: Modify so that the user and pass are set to the active user once we have that functionality
    private static final String USER = "app";
    private static final String PASS = "turtles";
   
    /**
     * FUNCTION DESCRIPTION: This function reads the requested information from 
     *                       the database into the resultSet (rs) class property. 
     * LAST UPDATED: 3/30/16 
     * UPDATER: ALEXA
     * NOTE: Action methods must always return type String in order to navigate 
     *       to the next XHTML page. Cannot return a resultSet. :(
     *       Instead, I'm trying to read the retrieved information into rs, which 
     *       can in turn be accessed with a getter function in a results xhtml page.
     * @return the next page to navigate to.
     * @throws Exception 
     */

    private ResultSet search() throws Exception{
       ResultSet rs = null;
       //cleanBooks();
       try(Connection dbConnection = source.getConnection(USER, PASS)){
           if(Author == null && title == null && forClass == null && ISBN == null){
               String query = SQL.getSQL("book-select-all");
               PreparedStatement alexaStmt=dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
               rs = alexaStmt.executeQuery();
           }
           else{
               String query = SQL.getSQL("book-query");
           
        // prepare the parameters
           PreparedStatement alexaStmt=dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          
           alexaStmt.setString(1, this.Author);
           alexaStmt.setString(2, this.title);
           alexaStmt.setString(3, this.forClass);
           alexaStmt.setString(4, this.ISBN);
           
           rs = alexaStmt.executeQuery();
           }
       } catch (SQLException se){
            if (books == null) books = new ArrayList<book>();
            books.add(new book(se.getMessage(), se.getLocalizedMessage(), "SQLEXCEPTION line99", "12345", "1", "Alexa", "0"));    
        } 
       catch (Exception exc) {
            if (books == null) books = new ArrayList<book>();
            books.add(new book(exc.getMessage(), exc.getLocalizedMessage(), "line 141", "12345", "1", "Alexa", "0"));    
	} 
       finally {
            //close(dbConn, searchStatement, null);
           
            try {
                if (searchStatement != null) {
                    dbConn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (dbConn != null) {
                    dbConn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
           
	}
        
        return rs;
    }
    
    private ResultSet searchBy() throws Exception{
       ResultSet rs = null;
      cleanBooks();
       try(Connection dbConnection = source.getConnection(USER, PASS)){
           PreparedStatement alexaStmt;
           String query = null;
           if(this.searchBy != null){
           switch(this.searchBy){
               case "author":
                   query = SQL.getSQL("SBAuthor-query");
                   break;
               case "title":
                   query = SQL.getSQL("SBTitle-query");
                   break;
               case "forClass":
                   query = SQL.getSQL("SBClass-query");
                   break;
               case "isbn":
                   query = SQL.getSQL("SBIsbn-query");
                   break;
           }
           alexaStmt=dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
           alexaStmt.setString(1, this.keyword);
           }
           else{// searchBy was not determined
               query = SQL.getSQL("book-query");
               alexaStmt=dbConnection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
               alexaStmt.setString(1, this.keyword);
               alexaStmt.setString(2, this.keyword);
               alexaStmt.setString(3, this.keyword);
               alexaStmt.setString(4, this.keyword);
           } 
          
           rs = alexaStmt.executeQuery();
          
       } catch (SQLException se){
            if (books == null) books = new ArrayList<book>();
            books.add(new book(se.getMessage(), se.getLocalizedMessage(), "SQLEXCEPTION line 136", "12345", "1", "Alexa", "0"));    
            se.printStackTrace();
        } 
       catch (Exception exc) {
            if (books == null) books = new ArrayList<book>();
            books.add(new book(exc.getMessage(), exc.getLocalizedMessage(), "line 141", "12345", "1", "Alexa", "0"));    
            exc.printStackTrace();
	} finally {
            //close(dbConn, searchStatement, null);
           
            try {
                if (searchStatement != null) {
                    dbConn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (dbConn != null) {
                    dbConn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
           
	}
        
        return rs;
    }
    
    private void cleanBooks() {
      
        if(books != null){
        for(int i = 0; i < books.size(); i++){
            books.remove(i);
        }
        }
    }

    /* THIS FUNCTION IS FOR ADVANCED SEARCHES
    
    */
   public String searchAction(){
       try (ResultSet results = this.search()) {
            this.cleanBooks();
            while(results.next()) {
            if(books == null){
                    books = new ArrayList<book>();
                }
                    books.add(new book(
                    results.getString("title"),
                    results.getString("author"),
                    results.getString("forClass"),
                    results.getString("isbn"),
                    results.getString("edition"),
                    results.getString("owner"),
                    results.getString("price")
                    ));
            }

        }
        
        catch (Exception e) {
            if (books == null) books = new ArrayList<book>();
            books.add(new book(e.getMessage(), e.getLocalizedMessage(), "SEARCHACTION line 237", "12345", "1", "Alexa", "0"));
            e.printStackTrace();
        }
        return "results";
   
   }
   
    public String searchByAction() {
        
        try {
            ResultSet results = this.searchBy();
            cleanBooks();
            while(results.next()) {
                if(books == null){
                    books = new ArrayList<book>();
                }
                    books.add(new book(
                    results.getString("title"),
                    results.getString("author"),
                    results.getString("forClass"),
                    results.getString("isbn"),
                    results.getString("edition"),
                    results.getString("owner"),
                    results.getString("price")
                    ));
            }
            results.close();

        }
        
        catch (Exception e) {
            if (books == null) books = new ArrayList<book>();
            books.add(new book(e.getMessage(), e.getLocalizedMessage(), "SEARCHACTION", "12345", "1", "Alexa", "0"));
            e.printStackTrace();
        }
        return "results";
    }
    
    
    
    public List<book> getBooks() {
        if(books == null){
        books = new ArrayList<book>();
        }
       return books;
    }
    
    // Setters and getters
    public String getAuthor() {
        return Author;
    }
    
    public String getForClass() {
        return forClass;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public String getEdition() {
        return edition;
    }
    
    public void setAuthor(String author) {
        this.Author = author;
    }
    
    public void setForClass(String forClass) {
        this.forClass = forClass;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    public void setSearchBy(String searchType){
        searchBy = searchType;
    }
    
    public String getSearchBy(){
        return searchBy;
    }
    
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    
    public String getKeyword(){
        return this.keyword;
    }
}    