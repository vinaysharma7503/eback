package com.ems.app.podcast.controller;

import com.ems.app.podcast.model.Podcast;
import com.ems.app.podcast.repository.PodcastRepository;
import com.ems.app.podcast.service.PodcastService;
import jakarta.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/podcast")
@CrossOrigin(origins = "*")
public class PodcastController {

    private final PodcastService podcastService;
    private final PodcastRepository podcastRepository;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Podcast> createPodcast(@NonNull @Valid @RequestBody Podcast podcast) {
        if (podcast.getId() != null) {
            throw new BadRequestException("A new podcast cannot already have an ID");
        }
        return ResponseEntity.ok(podcastService.save(podcast));
    }

    /**
     * {@code PUT  /podcasts/:id} : Updates an existing podcast.
     *
     * @param id the id of the Podcast to save.
     * @param Podcast the Podcast to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Podcast,
     * or with status {@code 400 (Bad Request)} if the Podcast is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Podcast couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Podcast> updatePodcast(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody Podcast Podcast
    ) {
        if (Podcast.getId() == null) {
            throw new BadRequestException("Invalid id idnull");
        }
        if (!Objects.equals(id, Podcast.getId())) {
            throw new BadRequestException("Invalid ID idinvalid");
        }

        if (!podcastRepository.existsById(id)) {
            throw new BadRequestException("Entity not found idnotfound");
        }

        Podcast = podcastService.update(Podcast);
        return ResponseEntity.ok()
                .body(Podcast);
    }


    /**
     * {@code GET  /podcasts/:id} : get the "id" podcast.
     *
     * @param id the id of the Podcast to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Podcast, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Podcast> getPodcast(@PathVariable("id") Long id) {
        Optional<Podcast> Podcast = podcastService.findOne(id);
        return ResponseEntity.ok(Podcast.get());
    }

    @GetMapping
    public ResponseEntity<List<Podcast>> getPodcasts() {
        return ResponseEntity.ok(podcastService.findAll());
    }

    /**
     * {@code DELETE  /podcasts/:id} : delete the "id" podcast.
     *
     * @param id the id of the Podcast to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePodcast(@PathVariable("id") Long id) {
        podcastService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePodcasts() {
        podcastService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
