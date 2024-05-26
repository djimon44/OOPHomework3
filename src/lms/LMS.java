package lms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LMS {

    List<Book> books = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<BorrowedBook> borrowedBooks = new ArrayList<>();


    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                return;
            }
        }
        books.add(book);
    }

    public void removeBook(Book book) {
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                books.remove(b);
                return;
            }
        }
    }

    public void borrowBook(Book book, Student student) {
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                for (BorrowedBook borrowedBook : borrowedBooks) {
                    if (borrowedBook.getBook().getTitle().equals(book.getTitle())) {
                        continue;
                    }
                }
                borrowedBooks.add(new BorrowedBook(b, student));
                return;
            }
        }
    }

    public void returnBook(Book book) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().getTitle().equals(book.getTitle())) {
                borrowedBooks.remove(borrowedBook);
                return;
            }
        }
    }

    public void saveState(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");

            // Write book info
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor());
            }

            // Write an empty line
            writer.println();

            // Write borrowed book info
            for (BorrowedBook borrowedBook : borrowedBooks) {
                writer.println(borrowedBook.getBook().getTitle() + "," + borrowedBook.getBook().getAuthor() + "," + borrowedBook.getStudent().getName() + "," + borrowedBook.getStudent().getSurname() + "," + borrowedBook.getStudent().getPersonalNumber());
            }

            writer.close();
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the state.");
            e.printStackTrace();
        }
    }

    public void loadState(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Clear state
            students.clear();
            books.clear();
            borrowedBooks.clear();

            // Load book info
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break; // Empty line

                String[] parts = line.split(",");
                books.add(new Book(parts[0], parts[1]));
            }

            // Load borrowed book info
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break; // Empty line

                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1]);
                Student student = new Student(parts[2], parts[3], parts[4]);
                borrowedBooks.add(new BorrowedBook(book, student));
            }

            scanner.close();

            System.out.println("Loaded");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading the state.");
            e.printStackTrace();
        }
    }
}