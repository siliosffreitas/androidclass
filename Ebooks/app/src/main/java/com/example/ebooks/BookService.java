package com.example.ebooks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    String BASE_URL = "https://www.googleapis.com/books/v1/";

    @GET("volumes")
    Call<ResponseBook> searchBooks(@Query("q") String query);
}
