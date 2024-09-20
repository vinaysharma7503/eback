package com.ems.app.profile.service.impl;

import com.ems.app.common.service.S3Service;
import com.ems.app.profile.model.Profile;
import com.ems.app.profile.repository.ProfileRepository;
import com.ems.app.profile.service.ProfileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final S3Service s3Service;

    @Override
    public Profile createProfile(@NonNull Profile profile) {
        return this.profileRepository.save(profile);
    }

    @Override
    public Page<Profile> getProfiles(Pageable pageable) {
        return this.profileRepository.findAll(pageable);
    }

    @Override
    public Profile getProfileById(Long id) {
        return this.profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No user found with given id"));
    }

    @Override
    public Profile updateProfile(Long id, @NonNull Profile profile) {
        this.profileRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("No record found with profile id");
        });
        return this.profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long id) {
        this.profileRepository.deleteById(id);
    }

    @Override
    public void deleteProfiles() {
        this.profileRepository.deleteAll();
    }

    @SneakyThrows
    @Override
    public Profile uploadProfilePic(Profile profile, MultipartFile file) {
        var dpUrl = this.s3Service.uploadFile(file);
        profile.setProfilePhotoUrl(dpUrl);
        return profileRepository.save(profile);
    }
}
