package com.scaler.blogapp.articles.dtos;

import jakarta.annotation.Nullable;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {
    @Nullable
    private String title;
    @Nullable
    private String body;
    @Nullable
    private String subtitle;
}
