package com.forthset.mission.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissionDto {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @NotNull
    @Size(max = 256)
    private String name;

    @NotNull
    @Size(max = 4096)
    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageUrl;

    // TODO
    // poster user avatar

    // TODO
    // poster rating

    // TODO
    // poster votes amount

    // TODO
    // price

    // TODO
    // isBookmarked

    @Nullable
    private Long duration;
}
