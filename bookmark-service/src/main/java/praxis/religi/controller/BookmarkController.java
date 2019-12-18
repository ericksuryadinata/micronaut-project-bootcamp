package praxis.religi.controller;

import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import praxis.religi.repository.BookmarkRepositoryInf;

import java.util.HashMap;

import javax.validation.Valid;

import com.google.gson.Gson;

import antlr.collections.List;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;

@Controller("/bookmark")
public class BookmarkController {

    private BookmarkRepositoryInf bookmarkRepositoryInf;

    BookmarkController(BookmarkController bookmarkController) {
        this.bookmarkRepositoryInf = bookmarkRepositoryInf;
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String index(@QueryValue int page, int limit) {
        List<Bookmark> bookmark = bookmarkRepositoryInf.getAll(page, limit);
        HashMap<String, Object> data = new HashMap<>();
        data.put("status", "success");
        data.put("data", bookmark);
        return (new Gson().toJson(data));
    }

    @Post("/subscribe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String subscribe(){
        
    }
}