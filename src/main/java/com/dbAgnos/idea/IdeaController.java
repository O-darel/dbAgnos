package com.dbAgnos.idea;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ideas")
@RequiredArgsConstructor
public class IdeaController {

    private final IdeaService service;

    @PostMapping
    public ResponseEntity<ProjectIdea> createIdea(
            @RequestBody CreateIdeaRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(service.createIdea(request, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<ProjectIdea>> getAllIdeas() {
        return ResponseEntity.ok(service.getAllIdeas());
    }

    @GetMapping("/my")
    public ResponseEntity<List<ProjectIdea>> getMyIdeas(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(service.getMyIdeas(userDetails.getUsername()));
    }
}
