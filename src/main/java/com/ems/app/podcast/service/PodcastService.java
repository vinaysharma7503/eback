package com.ems.app.podcast.service;

import com.ems.app.podcast.model.Podcast;
import java.util.List;
import java.util.Optional;

public interface PodcastService {

    Podcast save(Podcast podcast);

    /**
     * Updates a podcast.
     *
     * @param Podcast the entity to update.
     * @return the persisted entity.
     */
    Podcast update(Podcast Podcast);


    /**
     * Get the "id" podcast.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Podcast> findOne(Long id);

    /**
     * Get the "all" podcast.
     *
     * @return the entity.
     */
    List<Podcast> findAll();

    /**
     * Delete the "id" podcast.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete the "all" podcast.
     *
     */
    void deleteAll();
}
