package com.ems.app.profile.service;

import com.ems.app.profile.model.Profile;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    Profile createProfile(@NonNull @Valid Profile profile);

    Page<Profile> getProfiles(Pageable pageable);

    Profile getProfileById(Long id);

    Profile updateProfile(Long id, @NonNull @Valid Profile profile);

    void deleteProfile(Long id);

    void deleteProfiles();

    Profile uploadProfilePic(Profile profile, MultipartFile file);
}
