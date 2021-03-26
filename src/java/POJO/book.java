/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Charl3s
 */
public class book {
    private String book_id,book_title,book_author;

    public String getBook_id() {
        return book_id;
    }

  

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }



    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }





    @Override
    public String toString() {
       
        return "book{" + "status: " + "200 " + ", book_name=" + book_title + ", book_author=" + book_author + '}';
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
    
    
}
