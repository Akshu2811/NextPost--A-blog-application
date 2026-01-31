package com.scaler.blogapp.articles.dtos;

import jakarta.annotation.Nullable;
import lombok.*;

@Setter(AccessLevel.NONE)
@Data
public class CreateArticleRequest {

    @NonNull
    private String title;
    @NonNull
    private String body;
    @Nullable
    private String subtitle;
}
