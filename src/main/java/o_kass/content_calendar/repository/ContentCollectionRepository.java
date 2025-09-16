package o_kass.content_calendar.repository;


import jakarta.annotation.PostConstruct;
import o_kass.content_calendar.model.Content;
import o_kass.content_calendar.model.Status;
import o_kass.content_calendar.model.Type;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {}

    public List<Content> findAll() {

        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    public void init() {
        Content c = new Content(
                 1,
                "BlogPost 1",
                "Hello this blog is new",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        contentList.add(c);
    }


    public void save(Content c) {
        contentList.removeIf(content -> content.id().equals(c.id()));
        contentList.add(c);
    }

    public void delete(Integer id) {
        contentList.removeIf(content -> content.id().equals(id));
    }
}
