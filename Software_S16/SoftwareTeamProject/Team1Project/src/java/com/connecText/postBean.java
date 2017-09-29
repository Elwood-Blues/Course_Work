/*
 * @author Michael Ellison, Grant Page, Alexa Chiu, Abigail Reed, Peter Braband
 * 04/13/16
 */

package com.connecText;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import sql.SQL;

@ManagedBean
@Named
@SessionScoped

public class postBean {

  @Resource(name = "jdbc/projectdb")
  DataSource dataSource;
  
  private String author;
  private String forClass;
  private String title;
  private String ISBN;
  private String edition;
  private String price;
  
  public String postAction(user CurrentUser) throws Exception {
    
      int rowCount = 0;
      try(Connection dbConnection = dataSource.getConnection()){
          String update = SQL.getSQL("book-insert");
          PreparedStatement pStmt = dbConnection.prepareStatement(update);
          pStmt.setString(1, this.author);
          pStmt.setString(2, this.title);
          pStmt.setString(3, this.forClass);
          pStmt.setString(4, this.ISBN);
          pStmt.setString(5, this.edition);
          pStmt.setString(6, this.price);
          pStmt.setString(7, CurrentUser.email());
          rowCount = pStmt.executeUpdate();
          
          // update ownership table
          /*
update = SQL.getSQL("ownership-insert");
          PreparedStatement ownerStatement = dbConnection.prepareStatement(update);
          ownerStatement.setString(1, CurrentUser.email());
          ownerStatement.setString(2, this.title);
          rowCount = ownerStatement.executeUpdate
*/
      	}
      	catch (SQLException se){
		return se.getMessage();
      	} 
      	catch (Exception exc) {
               return exc.getMessage();
	} 
    return "profile";
  }
  
  private int post(){
      int rowCount=0;
      
      return rowCount;
  }
  
  // Accessors
  public String getAuthor() {
      return author;
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
  
  public String getPrice() {
  	return price;
  }
  
  //Mutators 
  public void setAuthor(String author) {
      this.author = author;
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
  
  public void setPrice(String price) {
  	this.price = price;
  }

}
