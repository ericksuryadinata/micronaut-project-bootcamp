package praxis.eventreligi.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import praxis.eventreligi.model.Event;

public interface EventRepositoryInf {

    // buat dulu fungsinya yang mau di pakai misal create,read dll
    // yang di create oleh event bentunya string, sedangkan parameternya event yang ada dimodel.
    String create(Event event);

    // read mencari id di didalam database, ketika kita mencari id 1 misal kalau ada berarti
    // mengembalikan event
    Event read(@NotNull int id);

    // ketika ingin mengupdate sebuah event maka yang dikirimkan adalah event yang berbentuk string
    String update(Event event);

    String cancel(@NotNull int id);

    // untuk mendapatkan semua event gunakan getAll, jadi tidak mencari event satu2.
    List<Event> getAll(int page, int limit);
}
