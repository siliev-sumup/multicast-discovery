package com.forthset.search.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class SearchRequest {

    @NotNull
    @Size(max = 1024)
    private String term;
}
