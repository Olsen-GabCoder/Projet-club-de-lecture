package tn.esprit.rolleaters.models;

public class Book {
    private String title;
    private String author;
    private int pages;
    private int coverResId;  // Référence à l'image de couverture
    private String pdfPath;  // Chemin du fichier PDF

    public Book(String title, String author, int pages, int coverResId, String pdfPath) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.coverResId = coverResId;
        this.pdfPath = pdfPath;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public int getCoverResId() {
        return coverResId;
    }

    public String getPdfPath() {
        return pdfPath;
    }
}
