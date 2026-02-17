package com.study.profile_stack_api.domain.techstack.service;

import com.study.profile_stack_api.domain.profile.entity.Profile;
import com.study.profile_stack_api.domain.techstack.dao.TechStackDao;
import com.study.profile_stack_api.domain.techstack.dto.TechStackRequest;
import com.study.profile_stack_api.domain.techstack.dto.TechStackResponse;
import com.study.profile_stack_api.domain.techstack.entity.TechStack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechStackService {
    private final TechStackDao techStackDao;

    public TechStackResponse addTechStack(Long profileId, TechStackRequest techStackRequest) {
        TechStack techStack = TechStack.builder()
                .profileId(profileId)
                .name(techStackRequest.getName())
                .category(techStackRequest.getCategory())
                .proficiency(techStackRequest.getProficiency())
                .yearsOfExp(techStackRequest.getYearsOfExp())
                .build();

        TechStack savedTechStack = techStackDao.saveTechStack(techStack);

        return TechStackResponse.from(savedTechStack);
    }

    public TechStackResponse getTechStack(Long profileId, Long id) {
        TechStack techStack = techStackDao.findById(profileId, id).orElseThrow();

        return TechStackResponse.from(techStack);
    }

    public TechStackResponse updateTechStack(Long profileId, Long id, TechStackRequest techStackRequest) {
        TechStack techStack = techStackDao.findById(profileId, id).orElseThrow();

        techStack.setName(techStackRequest.getName());
        techStack.setCategory(techStackRequest.getCategory());
        techStack.setProficiency(techStackRequest.getProficiency());
        techStack.setYearsOfExp(techStackRequest.getYearsOfExp());

        TechStack updatedTechStack = techStackDao.updateTechStack(profileId, id, techStack);

        return TechStackResponse.from(updatedTechStack);
    }

    public void deleteTechStack(Long profileId, Long id) {
        techStackDao.deleteTechStack(profileId, id);
    }
}
