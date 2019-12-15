package praxis.eventreligi.app.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;
import com.google.gson.*;

import java.util.HashMap;
import java.util.List;

import praxis.eventreligi.app.command.LoginCommand;
import praxis.eventreligi.app.repository.AuthRepositoryInf;
import praxis.eventreligi.app.model.User;

@Controller("/auth")
public class AuthController {

    private AuthRepositoryInf authRepositoryInf;// mengambil repository yang interface

    AuthController(AuthRepositoryInf authRepositoryInf) {
        this.authRepositoryInf = authRepositoryInf;
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON) // menyediakan untuk diproses oleh user
    public String index() {
        List<User> user = authRepositoryInf.findAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("status", "sukses");
        data.put("user", user);
        return (new Gson()).toJson(data);
    }

    @Post("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@Body LoginCommand command) {
        // TODO : membuat JWT result untuk api_token, digunakan buat aplikasi android

        HashMap<String, Object> data = new HashMap<>();
        // untuk mencegah error saat runtime, kita gunakan try catch
        try {
            Boolean isExistEmail = authRepositoryInf.checkExistEmail(command.getEmail());
            if (isExistEmail) {//cek apakah email yang dimasukan ada
                User user = authRepositoryInf.login(command.getEmail(), command.getPassword());
                if (user == null) {
                    data.put("status", "gagal");
                    data.put("pesan", "password salah");
                } else {
                    data.put("status", "sukses");
                    data.put("user", user);
                }
            } else {
                data.put("status", "gagal");
                data.put("pesan", "user tidak ada");
            }

            return (new Gson()).toJson(data);
        } catch (Exception e) {
            // error masuk sini
            data.put("status", "gagal");
            data.put("pesan", "terjadi error " + e.getMessage());
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

        /**
         * HASIL COPY PASTE DARI
         * https://guides.micronaut.io/micronaut-oauth2-oidc-google/guide/index.html
         */

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
    public String register(@Body User user) {
        HashMap<String, Object> data = new HashMap<>();
        // cek email, apakah sudah ada di database dengan fungsi isExistEmail
        Boolean isExistEmail = authRepositoryInf.checkExistEmail(user.getEmail());
        if (isExistEmail) {
            data.put("status", "gagal");
            data.put("pesan", "email " + user.getEmail() + " sudah ada");
            return (new Gson()).toJson(data);
        } else {
            try {
                User result = authRepositoryInf.save(user);
                data.put("status", "sukses");
                data.put("new_user", result);
                return (new Gson()).toJson(data);
            } catch (Exception e) {
                data.put("status", "gagal");
                return (new Gson()).toJson(data);
            }
        }
    }

}
