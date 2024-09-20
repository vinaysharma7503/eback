package com.ems.app.resource.service.impl;

import com.ems.app.common.service.S3Service;
import com.ems.app.resource.model.Resource;
import com.ems.app.resource.repository.ResourceRepository;
import com.ems.app.resource.service.ResourceService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    private final S3Service s3Service;

    @Override
    public Resource saveResource(Resource resource, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String imageUrl = s3Service.uploadFile(file);
            resource.setImageUrl(imageUrl);
        }
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
