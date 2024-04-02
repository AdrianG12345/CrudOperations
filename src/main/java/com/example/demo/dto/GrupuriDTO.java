package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupuriDTO {

    @NotNull
    @JsonProperty("group_id")
    private int group_id;
    @NotNull
    @JsonProperty("groupNr")
    private int groupNr;
}
