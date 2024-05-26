package lms;

public class LMSTester {
    public static void main(String[] args) {
        LMS iliauniLibrary = new LMS();


        Book lor = new Book("Lord of the rings", "Tolkien");
        Book oop = new Book("OOP", "Paata Gogisvhili");
        iliauniLibrary.addBook(lor);
        iliauniLibrary.addBook(oop);

        Student gocha = new Student("Gocha", "Gegeshidze", "dfasdf");
        iliauniLibrary.borrowBook(lor, gocha);

        Student maka = new Student("Maka", "Lobjanidze", "3421325");
        iliauniLibrary.borrowBook(oop, maka);


        iliauniLibrary.saveState("libraryState.txt");
        for (BorrowedBook borrowedBook : iliauniLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName());
        }

        iliauniLibrary.loadState("libraryState.txt");
        iliauniLibrary.returnBook(lor);
        for (BorrowedBook borrowedBook : iliauniLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName());
        }



    }
}
