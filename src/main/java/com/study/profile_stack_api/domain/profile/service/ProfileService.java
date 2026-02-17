package com.study.profile_stack_api.domain.profile.service;

import com.study.profile_stack_api.domain.profile.dao.ProfileDao;
import com.study.profile_stack_api.domain.profile.dto.ProfileRequest;
import com.study.profile_stack_api.domain.profile.dto.ProfileResponse;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import com.study.profile_stack_api.global.common.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileDao profileDao;

    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        Profile profile = Profile.builder()
                .name(profileRequest.getName())
                .email(profileRequest.getEmail())
                .bio(profileRequest.getBio())
                .position(profileRequest.getPosition())
                .careerYears(profileRequest.getCareerYears())
                .githubUrl(profileRequest.getGithubUrl())
                .blogUrl(profileRequest.getBlogUrl())
                .build();

        Profile savedProfile = profileDao.saveProduct(profile);

        return ProfileResponse.from(savedProfile);
    }

    public PageResponse<ProfileResponse> findAllWithPaging(int page, int size) {
        Page<Profile> profilePage = profileDao.findAllWithPaging(page, size);

        List<ProfileResponse> profileResponseList = profilePage.getContent().stream()
                .map(ProfileResponse::from)
                .toList();

        return new PageResponse<>(
                profileResponseList,
                profilePage.getNumber(),
                profilePage.getSize(),
                profilePage.getTotalElements()
        );
    }

    public ProfileResponse getProfile(Long id) {
        Profile profile = profileDao.findById(id);

        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse updateProfile(Long id,ProfileRequest profileRequest) {
        Profile profile = profileDao.findById(id);

        profile.setName(profileRequest.getName());
        profile.setEmail(profileRequest.getEmail());
        profile.setBio(profileRequest.getBio());
        profile.setPosition(profileRequest.getPosition());
        profile.setCareerYears(profileRequest.getCareerYears());
        profile.setGithubUrl(profileRequest.getGithubUrl());
        profile.setBlogUrl(profileRequest.getBlogUrl());

        return ProfileResponse.from(profile);
    }

    public List<ProfileResponse> findByPosition(String position) {
        List<Profile> profiles = profileDao.findByPosition(position);

        return profiles.stream()
                .map(ProfileResponse::from)
                .toList();
    }

    public void deleteProfile(Long id) {
        profileDao.deleteProfile(id);
    }
}
