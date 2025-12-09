package com.dbAgnos.idea;

import lombok.Data;

@Data
public class CreateIdeaRequest {
    private String title;
    private String description;
    private String tags;
}
