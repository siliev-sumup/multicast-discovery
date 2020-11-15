package com.forthset.search.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Generic params:
 *   - DT data type
 *   - MT meta type
 * */
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dto<DT, MT> {

    @NotNull
    private final DT data;

    @Nullable
    private MT meta;
}
