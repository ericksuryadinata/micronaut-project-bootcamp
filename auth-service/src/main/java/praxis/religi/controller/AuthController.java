package praxis.religi.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;
import com.google.gson.*;

import java.util.HashMap;
import java.util.List;

import praxis.religi.command.LoginCommand;
import praxis.religi.repository.AuthRepositoryInf;
import praxis.religi.model.User;

@Controller("/auth")
public class AuthController {

    private AuthRepositoryInf repository;

    AuthController(AuthRepositoryInf r) {
        this.repository = r;
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String index() {
        List<User> user = repository.findAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("status", "sukses");
        data.put("user", user);
        return (new Gson()).toJson(data);
    }

    @Post("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@Body LoginCommand command) {
        // TODO : membuat login yang menggunakan hash password, sekarang masih plain
        // text
        HashMap<String, Object> data = new HashMap<>();

        try { // kalau ada user nya
            User user = repository.login(command.getEmail(), command.getPassword());
            data.put("status", "sukses");
            data.put("user", user);
            return (new Gson()).toJson(data);
        } catch (Exception e) { // ini gak ada usernya
            data.put("status", "sukses");
            data.put("user", "tidak ada user");
            return (new Gson()).toJson(data);
        }
    }

    @Post("/loginByGoogle")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginByGoogle() {
        // Logic
        // pertama kali pencet tombol login by google di android, endpoint ini akan di
        // hit
        // cek dulu apakah ada email yang dikirim oleh android sudah ada di record
        // database
        // kalau ada sukses login
        // kalau tidak ada register / masukkan data tersebut ke database
        HashMap<String, Object> data = new HashMap<>();
        data.put("status", "sukses");
        return (new Gson()).toJson(data);
    }

    @Post("/loginByFacebook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginByFacebook() {

        // hampir sama dengan loginByGoogle

        HashMap<String, Object> data = new HashMap<>();
        data.put("status", "sukses");
        return (new Gson()).toJson(data);
    }

    @Post("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String register() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("status", "sukses");
        return (new Gson()).toJson(data);
    }
}