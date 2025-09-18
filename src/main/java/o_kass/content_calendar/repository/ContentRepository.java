package o_kass.content_calendar.repository;

import o_kass.content_calendar.model.Content;
import o_kass.content_calendar.model.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {

    List<Content> findAllByTitleContains(String keyword);


    @Query(
            """
SELECT * FROM Content WHERE status = :status
"""
    )
    List<Content> listByStatus(@Param("status")Status status);

}
