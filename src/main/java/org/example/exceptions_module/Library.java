package org.example.exceptions_module;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Library {
    ArrayList<Book> catalog = new ArrayList<>();
    private static Logger logger = Logger.getLogger(Library.class.getName());

    public Library(){
        catalog.add(new Book("Граф Монте Кристо", "Александр Дюма", 5));
        catalog.add(new Book("Слушай песню ветра", "Харуки Мураками", 4));
        catalog.add(new Book("Преступление и наказание", "Федор Достаевский", 8));
    }

    public void addBook(String title, String author, int availableCopies){
        catalog.add(new Book(title, author, availableCopies)); //можно добавить InputMismatchException некорректный ввод пользователя
    }

    public void takeBook(String title) throws NoSuchElementException, NoAvailableCopiesException {
        for(Book book: catalog){
            if (book.getTitle().equals(title)){
                if(book.getAvailableCopies() > 1){
                    book.setAvailableCopies(book.getAvailableCopies()-1);
                    System.out.println("книга успешно выдана");
                }
                else throw new NoAvailableCopiesException("книги с таким названиям закончились");

            }
            else {
                throw new NoSuchElementException("такой книги нет в каталоге");
            }

        }
    }

    public void returnBook(String title){
        for (Book book: catalog){
            if (book.getTitle().equals(title)){
                book.setAvailableCopies(book.getAvailableCopies()+1);
                System.out.println("книга успешно возвращена");
            }
            else logger.log(Level.SEVERE, "такой книги нет в каталоге");
        }
    }

    public void getAllBooks(){
        catalog.forEach(System.out::println);
    }
}
