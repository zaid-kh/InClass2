package com.example.inclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String DATA = "";

    private EditText edtTitle;
    private EditText edtAuthor;
    private EditText edtPages;
    private Button btnAdd;
    private Button btnSave;
    private List<Book> books = new ArrayList<>();

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpElements();

    }

    private void setUpElements() {
        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtPages = findViewById(R.id.edtPages);
        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
    }

    public void saveClicked(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String bookString = gson.toJson(books);
        editor.putString(DATA, bookString);
        editor.commit();
        Toast.makeText(this, "Saved list to shared preferences\n" + bookString, Toast.LENGTH_SHORT).show();

    }


    public void addClicked(View view) {

        String title = edtTitle.toString();
        String author = edtAuthor.toString();
        int pages = Integer.parseInt(edtPages.toString());
        Book book = new Book(title, author, pages);
        books.add(book);

    }
}