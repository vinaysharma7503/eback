package com.ems.app.resource.service;

import com.ems.app.resource.model.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ResourceService {

    Resource saveResource(Resource resource, MultipartFile file) throws IOException;

    List<Resource> getAllResources();

    Optional<Resource> getResourceById(Long id);

    void deleteResource(Long id);
}
