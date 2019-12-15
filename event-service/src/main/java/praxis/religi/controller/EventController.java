package praxis.religi.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.google.gson.Gson;

import io.micronaut.http.MediaType;
import praxis.religi.model.Event;
import praxis.religi.repository.EventRepositoryInf;

@Controller("/event-service")
public class EventController {

    private EventRepositoryInf eventRepositoryInf;

    EventController(EventRepositoryInf eventRepositoryInf) {
        this.eventRepositoryInf = eventRepositoryInf;
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String index(@QueryValue int page, @QueryValue int limit) {
        List<Event> event = eventRepositoryInf.getAll(page, limit);
        HashMap<String, Object> data = new HashMap<>();
        data.put("message", "success");
        data.put("data", event);
        return (new Gson()).toJson(data);
    }

    @Post("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@Body @Valid Event event) {
        String status = eventRepositoryInf.create(event);
        HashMap<String, Object> data = new HashMap<>();
        if (status.equals("sukses")) {
            data.put("message", "success");
        } else {
            data.put("message", "failed");
        }
        return (new Gson()).toJson(data);
    }

    @Put("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@Body @Valid Event event){
        String status = eventRepositoryInf.update(event);
        HashMap<String, Object> data = new HashMap<>();
        if (status.equals("sukses")) {
            data.put("message", "success");
        } else {
            data.put("message", "failed");
        }
        return (new Gson()).toJson(data);
    }

    @Delete("/cancel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String cancel(@PathVariable int id){
        String status = eventRepositoryInf.cancel(id);
        HashMap<String, Object> data = new HashMap<>();
        if (status.equals("sukses")) {
            data.put("message", "success");
        } else {
            data.put("message", "failed");
        }
        return (new Gson()).toJson(data);
    }

    @Get("/read/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathVariable int id){
        Event event = eventRepositoryInf.read(id);
        HashMap<String, Object> data = new HashMap<>();
        data.put("message", "success");
        data.put("data", event);
        return (new Gson()).toJson(data);
    }

}