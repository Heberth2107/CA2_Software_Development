/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package heberthcastro.ca2_software_development;

import java.util.Scanner;
import heberthcastro.ca2_software_development.model.*;
import java.util.ArrayList;

/**
 *
 * @author heber
 */
public class Principal {

    public static void main(String[] args) {
        MySQLConnection conn = new MySQLConnection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Library Management System ***");
            System.out.println("1. Admin Panel");
            System.out.println("2. Member Panel");
            System.out.println("3. Notifications");
            System.out.println("-1. Quit");
            System.out.print("\nSelect an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    adminPanel(scanner, conn);
                    break;
                case 2:
                    //readAnimalDataFromFile();
                    break;
                case 3:
                    //findAnimalsByType(scanner);
                    break;
                case -1:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void adminPanel(Scanner scanner, MySQLConnection conn) {
        System.out.println("\n\na: Manage Books");
        System.out.print("b: Manage Users ");
        System.out.print("\nSelect an option: ");

        String choice = scanner.next();
        scanner.nextLine();  // Consume newline
        switch (choice) {
            case "a":
                bookPanel(scanner, conn);
                break;
            case "b":
                memberPanel(scanner, conn);
                break;
            case "c":
                memberPanel(scanner, conn);
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    private static void bookPanel(Scanner scanner, MySQLConnection conn) {
        System.out.println("\n\na: Insert Book");
        System.out.print("b: Delete Book");
        System.out.print("\nSelect an option: ");

        String choice = scanner.next();
        scanner.nextLine();  // Consume newline
        switch (choice) {
            case "a":
                insertBook(scanner, conn);
                break;
            case "b":
                deleteBook(scanner, conn);
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    private static void insertBook(Scanner scanner, MySQLConnection conn) {
        Book book = new Book();
        System.out.println("\n\na: Insert title of the book");
        String title = scanner.next();
        book.setTitle(title);
        scanner.nextLine();  // Consume newline
        System.out.println("\n\na: Insert the author");
        String author = scanner.next();
        book.setAuthor(author);
        scanner.nextLine();  // Consume newline
        System.out.println("\n\na: Insert the Genre");
        String genre = scanner.next();
        book.setGenre(genre);

        conn.insertBook(book);
    }

    private static void deleteBook(Scanner scanner, MySQLConnection conn) {
        System.out.println("\n\nList of books:");
        ArrayList<Book> books = conn.selectBook();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getId() + " - " + books.get(i).getTitle() + " - " + books.get(i).getAuthor() + " - " + books.get(i).getGenre());
        }

        System.out.println("\n\nselect id of book to delete: ");
        int id = scanner.nextInt();
        if (conn.selectBook(id) == null) {
            System.out.println("Book not found!");
            return;
        } 
        if (conn.deleteBook(id)) {
            System.out.println("Book deleted!");
        } else {
            System.out.println("Book not deleted!");
        }
    }
    
    private static void memberPanel(Scanner scanner, MySQLConnection conn) {
        System.out.println("\n\na: Insert Member");
        System.out.print("b: Delete Member");
        System.out.print("\nSelect an option: ");

        String choice = scanner.next();
        scanner.nextLine();  // Consume newline
        switch (choice) {
            case "a":
                insertMember(scanner, conn);
                break;
            case "b":
                deleteMember(scanner, conn);
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
    
    private static void insertMember(Scanner scanner, MySQLConnection conn) {
        Member member = new Member();
        System.out.println("\n\na: Insert Name");
        String name = scanner.next();
        member.setName(name);
        scanner.nextLine();  // Consume newline
        System.out.println("\n\na: Insert role");
        String role = scanner.next();
        member.setRole(role);
        scanner.nextLine();  // Consume newline
        System.out.println("\n\na: Insert the Email");
        String email = scanner.next();
        member.setEmail(email);
        scanner.nextLine();  // Consume newline
        System.out.println("\n\na: Insert the Password");
        String password = scanner.next();
        member.setPassword(password);

        conn.insertMember(member);
    }
    
    private static void deleteMember(Scanner scanner, MySQLConnection conn) {
        System.out.println("\n\nList of members:");
        ArrayList<Member> members = conn.selectMember();
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).getId() + " - " + members.get(i).getName() + " - " + members.get(i).getRole() + " - " + members.get(i).getEmail());
        }

        System.out.println("\n\nselect id of the member: ");
        int id = scanner.nextInt();
        if (conn.selectMember(id) == null) {
            System.out.println("Member not found!");
            return;
        } 
        if (conn.deleteMember(id)) {
            System.out.println("Member deleted!");
        } else {
            System.out.println("Member not deleted!");
        }
    }
}
