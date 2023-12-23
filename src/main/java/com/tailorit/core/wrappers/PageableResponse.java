package com.tailorit.core.wrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Getter
@Setter
public class PageableResponse<T> extends PageImpl<T> {
    private static final long serialVersionUID = -3761192289652004841L;

    private boolean fist;
    private boolean last;
    private int totalPages;
    private int numberOfElements;
    private boolean empty;
    private JsonNode node;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageableResponse(@JsonProperty("content") List<T> content,
                            @JsonProperty("pageable") JsonNode pageable,
                            @JsonProperty("last") boolean last,
                            @JsonProperty("totalPages") int totalPages,
                            @JsonProperty("totalElements") int totalElements,
                            @JsonProperty("size") int size,
                            @JsonProperty("number") int number,
                            @JsonProperty("sort") JsonNode sort,
                            @JsonProperty("first") boolean first,
                            @JsonProperty("numberOfElements") int numberOfElements,
                            @JsonProperty("empty") boolean empty) {
        super(content, PageRequest.of(number, size), totalElements);
        this.fist = first;
        this.last = last;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.empty = empty;
        this.node = sort;
    }
}
