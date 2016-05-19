package se.nackademin.librarytest.model;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private String datePublished;
    private Integer nbrAvailable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public Integer getNbrAvailable() {
        return nbrAvailable;
    }

    public void setNbrAvailable(Integer nbrAvailable) {
        this.nbrAvailable = nbrAvailable;
    }
}
