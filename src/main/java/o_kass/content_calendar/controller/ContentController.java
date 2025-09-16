package o_kass.content_calendar.controller;

import jakarta.validation.Valid;
import o_kass.content_calendar.model.Content;
import o_kass.content_calendar.model.Status;
import o_kass.content_calendar.repository.ContentCollectionRepository;
import o_kass.content_calendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentRepository repository; // once created dont need to change it

    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    //get all content in one request
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")//dynamic placeholder syntax in spring instead of just 1,2 as not scalable
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Content found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    // @Valid and @NotBlank work together to ensure content is formatted CORRECTLY
    public void create(@Valid @RequestBody Content c) {
        repository.save(c);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content c,@PathVariable Integer id){
        if(repository.findById(id).isPresent()){
            repository.save(c);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Content found");
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable Integer id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){
        return repository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status){
        return repository.listByStatus(status);
    };
}
