package com.dbAgnos.idea;

import com.dbAgnos.user.User;
import com.dbAgnos.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeaService {
    private final IdeaRepository repository;
    private final UserRepository userRepository;

    public ProjectIdea createIdea(CreateIdeaRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProjectIdea idea = ProjectIdea.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .tags(request.getTags())
                .user(user)
                .build();

        return repository.save(idea);
    }

    public List<ProjectIdea> getAllIdeas() {
        return repository.findAll();
    }

    public List<ProjectIdea> getMyIdeas(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repository.findByUserId(user.getId());
    }
}
