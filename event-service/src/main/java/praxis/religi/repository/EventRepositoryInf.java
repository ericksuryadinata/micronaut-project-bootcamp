package praxis.religi.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import praxis.religi.model.Event;

public interface EventRepositoryInf{

    String create(Event event);

    Event read(@NotNull int id);

    String update(Event event);

    String cancel(@NotNull int id);

    List<Event> getAll(int page, int limit);
}