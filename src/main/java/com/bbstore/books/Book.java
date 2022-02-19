package com.bbstore.books;

public class Book {
    private final String isbn;
    private final String name;
    private final String author;
    private final String publisher;
    private final String year;
    private final String price;
    private final String edition;
    private final String quantity;
    private final String language;
    private final String category;
    private final String description;

    public Book(String isbn, String name, String author, String publisher, String year, String price,
                String edition, String quantity, String language, String category, String description) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.edition = edition;
        this.quantity = quantity;
        this.language = language;
        this.category = category;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getYear() {
        return year;
    }

    public String getPrice() {
        return price;
    }

    public String getEdition() {
        return edition;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getLanguage() {
        return language;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
