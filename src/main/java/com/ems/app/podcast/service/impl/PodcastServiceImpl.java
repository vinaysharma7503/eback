package com.ems.app.podcast.service.impl;

import com.ems.app.podcast.model.Podcast;
import com.ems.app.podcast.repository.PodcastRepository;
import com.ems.app.podcast.service.PodcastService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PodcastServiceImpl implements PodcastService {

    private final PodcastRepository podcastRepository;

    @Override
    public Podcast save(Podcast Podcast) {
        log.debug("Request to save Podcast : {}", Podcast);
        var podcast = new Podcast();
        podcast.setId(Podcast.getId());
        podcast.setTitle(Podcast.getTitle());
        podcast.setVideoLink(Podcast.getVideoLink());
        podcast = podcastRepository.save(podcast);
        Podcast.setId(podcast.getId());
        Podcast.setTitle(podcast.getTitle());
        Podcast.setVideoLink(podcast.getVideoLink());
        return Podcast;
    }

    @Override
    public Podcast update(Podcast Podcast) {
        log.debug("Request to update Podcast : {}", Podcast);
        Podcast podcast = new Podcast();
        podcast.setId(Podcast.getId());
        podcast.setTitle(Podcast.getTitle());
        podcast.setVideoLink(Podcast.getVideoLink());
        podcast = podcastRepository.save(podcast);
        Podcast.setId(podcast.getId());
        Podcast.setTitle(podcast.getTitle());
        Podcast.setVideoLink(podcast.getVideoLink());
        return Podcast;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Podcast> findOne(Long id) {
        log.debug("Request to get Podcast : {}", id);
        var podcast = podcastRepository.findById(id).get();
        var Podcast = new Podcast();
        Podcast.setId(podcast.getId());
        Podcast.setTitle(podcast.getTitle());
        Podcast.setVideoLink(podcast.getVideoLink());
        return Optional.ofNullable(Podcast);
    }

    @Override
    public List<Podcast> findAll() {
        return podcastRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Podcast : {}", id);
        podcastRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        podcastRepository.deleteAll();
    }
}
