package com.study.profile_stack_api.domain.techstack.controller;

import com.study.profile_stack_api.domain.techstack.dto.TechStackRequest;
import com.study.profile_stack_api.domain.techstack.dto.TechStackResponse;
import com.study.profile_stack_api.domain.techstack.service.TechStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles/{profileId}/tech-stacks")
public class TechStackController {
    private final TechStackService techStackService;

    @PostMapping()
    public ResponseEntity<TechStackResponse> addTechStack(@PathVariable Long profileId, @RequestBody TechStackRequest request) {
        TechStackResponse res = techStackService.addTechStack(profileId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechStackResponse> getTechStack(@PathVariable Long profileId, @PathVariable Long id) {
        TechStackResponse res = techStackService.getTechStack(profileId, id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechStackResponse> updateTechStack(
            @PathVariable Long profileId, @PathVariable Long id, @RequestBody TechStackRequest request) {
        TechStackResponse res = techStackService.updateTechStack(profileId, id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTechStack(@PathVariable Long profileId, @PathVariable Long id) {
        techStackService.deleteTechStack(profileId, id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("삭제되었습니다.");
    }


}
