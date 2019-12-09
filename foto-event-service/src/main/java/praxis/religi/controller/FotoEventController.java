package praxis.religi.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/fotoEvent")
public class FotoEventController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}