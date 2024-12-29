package tn.esprit.rolleaters.activities;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.adapters.BookAdapter;
import tn.esprit.rolleaters.models.Book;

public class BibliothequeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private SearchView searchView;
    private TextView totalBooksText;  // Déclaration du TextView pour afficher le total des livres

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibliotheque);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        totalBooksText = findViewById(R.id.totalBooksText);  // Initialisation du TextView

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Charger des livres par défaut
        bookList = new ArrayList<>();
        loadBooks();

        // Adapter pour afficher les livres
        adapter = new BookAdapter(bookList, book -> {
            // Action sur l'élément cliqué, par exemple ouvrir le PDF
            openBookPdf(book);
        });

        recyclerView.setAdapter(adapter);

        // Mettre à jour le nombre total de livres
        updateTotalBooksCount();

        // Configurer le SearchView pour filtrer les livres
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrer les livres en fonction du texte recherché
                filterBooks(newText);
                return false;
            }
        });
    }

    private void loadBooks() {
        // Ajout de quelques livres par défaut
        bookList.add(new Book("L'alchimiste", "Paulo Coelho", 253, R.drawable.ic_book_cover, "/path/to/book1.pdf"));
        bookList.add(new Book("Le Petit Prince", "Antoine de Saint-Exupéry", 96, R.drawable.prince, "/path/to/book2.pdf"));
        bookList.add(new Book("1984", "George Orwell", 328, R.drawable.george, "/path/to/book3.pdf"));
        bookList.add(new Book("Le Seigneur des Anneaux", "J.R.R. Tolkien", 1216, R.drawable.seigneur, "/path/to/book4.pdf"));
        bookList.add(new Book("Harry Potter à l'école des sorciers", "J.K. Rowling", 320, R.drawable.harry_potter, "/path/to/book5.pdf"));
        bookList.add(new Book("La Recherche du Temps Perdu", "Marcel Proust", 4216, R.drawable.proust, "/path/to/book6.pdf"));
        bookList.add(new Book("Les Misérables", "Victor Hugo", 1462, R.drawable.miserable, "/path/to/book7.pdf"));
        bookList.add(new Book("Le Comte de Monte-Cristo", "Alexandre Dumas", 1243, R.drawable.dumas, "/path/to/book8.pdf"));
        bookList.add(new Book("Crime et Châtiment", "Fiodor Dostoïevski", 671, R.drawable.crime, "/path/to/book9.pdf"));
        bookList.add(new Book("L'Étranger", "Albert Camus", 123, R.drawable.etranger, "/path/to/book10.pdf"));
        bookList.add(new Book("Don Quichotte", "Miguel de Cervantes", 1072, R.drawable.don, "/path/to/book11.pdf"));
        bookList.add(new Book("Les Fleurs du Mal", "Charles Baudelaire", 148, R.drawable.mal, "/path/to/book12.pdf"));
        bookList.add(new Book("Moby Dick", "Herman Melville", 635, R.drawable.moby, "/path/to/book13.pdf"));
        bookList.add(new Book("Orgueil et Préjugés", "Jane Austen", 279, R.drawable.orgueil, "/path/to/book14.pdf"));
        bookList.add(new Book("Le Rouge et le Noir", "Stendhal", 576, R.drawable.rouge, "/path/to/book15.pdf"));
        bookList.add(new Book("La Métamorphose", "Franz Kafka", 201, R.drawable.metamorphose, "/path/to/book16.pdf"));

        // Ajoutez d'autres livres si nécessaire
    }

    private void filterBooks(String query) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                filteredBooks.add(book);
            }
        }
        // Mettre à jour l'adaptateur avec la liste filtrée
        adapter = new BookAdapter(filteredBooks, book -> openBookPdf(book));
        recyclerView.setAdapter(adapter);

        // Mettre à jour le nombre total de livres
        updateTotalBooksCount(filteredBooks);
    }

    private void updateTotalBooksCount() {
        // Met à jour le nombre total de livres avec la liste complète
        totalBooksText.setText("Nombre total de livres : " + bookList.size());
    }

    private void updateTotalBooksCount(List<Book> filteredBooks) {
        // Met à jour le nombre total de livres avec la liste filtrée
        totalBooksText.setText("Nombre total de livres : " + filteredBooks.size());
    }

    private void openBookPdf(Book book) {
        // Ouvrir le PDF du livre (cela peut être personnalisé selon votre logique)
        Toast.makeText(this, "Ouvrir le livre: " + book.getTitle(), Toast.LENGTH_SHORT).show();
    }
}

