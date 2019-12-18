package praxis.religi.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import praxis.religi.model.Bookmark;

public interface BookmarkRepositoryInf{
   String subscribe(Bookmark bookmark);

   String unsubscribe(Bookmark bookmark);

List<Bookmark> getAll(int page, int limit);
}