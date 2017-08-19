package com.example.ebooks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by IvanaPlamella on 19/08/17.
 */

public class ResponseBook {

    @SerializedName("items")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }
}
