/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heberthcastro.ca2_software_development.model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author heber
 */
public class MySQLConnection {

    private Connection conn;

    public MySQLConnection() {
        this.startConnection();
    }

    private void startConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ca2", "root", "");

            // Create a statement
            //Statement stmt = con.createStatement();
            // Execute a query
            //ResultSet rs = stmt.executeQuery("SELECT * FROM book");
            // Process the result set
            //while (rs.next()) {
            //    System.out.println(rs.getInt("id") + " - " + rs.getString("title") + " - " + rs.getString("author"));
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            // Close the connection
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertBook(Book book) {
        try {
            String sql = "Insert into book(Title, Author, Genre) Values ("
                    + "'" + book.getTitle() + "','" + book.getAuthor() + "','" + book.getGenre() + "')";
            System.out.println(sql);
            Statement s = this.conn.createStatement();
            s.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public ArrayList<Book> selectBook() {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            String sql = "select * from book";
            //System.out.println(sql);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setGenre(result.getString("genre"));
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public Book selectBook(int id) {
        Book book;
        try {
            String sql = "select * from book where id = " + id;
            //System.out.println(sql);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setGenre(result.getString("genre"));
                return book;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public boolean deleteBook(int id) {
        try {
            String sql = "delete from book where id = " + id;
            //System.out.println(sql);
            Statement s = this.conn.createStatement();
            s.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            //System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean insertMember(Member member) {
        try {
            String sql = "Insert member (Name, Role, Email, Password) Values ("
                    + "'" + member.getName() + "','" + member.getRole() + "','" + member.getEmail() + "','" + member.getPassword() + "')";
            System.out.println(sql);
            Statement s = this.conn.createStatement();
            s.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public ArrayList<Member> selectMember() {
        ArrayList<Member> members = new ArrayList<Member>();
        try {
            String sql = "select * from member";
            //System.out.println(sql);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Member member = new Member();
                member.setId(result.getInt("id"));
                member.setName(result.getString("name"));
                member.setRole(result.getString("role"));
                member.setEmail(result.getString("email"));
                member.setPassword(result.getString("password"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public Member selectMember(int id) {
        Member member;
        try {
            String sql = "select * from member where id = " + id;
            //System.out.println(sql);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                member = new Member();
                member.setId(result.getInt("id"));
                member.setName(result.getString("name"));
                member.setRole(result.getString("role"));
                member.setEmail(result.getString("email"));
                member.setPassword(result.getString("password"));
                return member;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public boolean deleteMember(int id) {
        try {
            String sql = "delete from member where id = " + id;
            //System.out.println(sql);
            Statement s = this.conn.createStatement();
            s.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            //System.out.println(e.toString());
            return false;
        }
    }
}
