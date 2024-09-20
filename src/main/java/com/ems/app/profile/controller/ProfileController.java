package com.ems.app.profile.controller;

import com.ems.app.profile.model.Profile;
import com.ems.app.profile.model.Profile;
import com.ems.app.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public Profile createProfile(@NonNull @Valid @RequestBody Profile profile) {
        return this.profileService.createProfile(profile);
    }

    @GetMapping
    public Page<Profile> getProfiles(Pageable pageable) {
        return this.profileService.getProfiles(pageable);
    }

    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable("id") Long id) {
        return this.profileService.getProfileById(id);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable("id") Long id,
                                 @NonNull @Valid @RequestBody Profile profile) {
        profile.setId(id);
        return this.profileService.updateProfile(id, profile);
    }

    @PutMapping("/upload")
    public Profile uploadProfilePic(@RequestParam("id") Long id,
                                    @RequestParam("file") MultipartFile file) {
        var profile = this.profileService.getProfileById(id);
        return this.profileService.uploadProfilePic(profile, file);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable("id") Long id) {
        this.profileService.deleteProfile(id);
    }

    @DeleteMapping
    public void deleteProfiles() {
        this.profileService.deleteProfiles();
    }

}
