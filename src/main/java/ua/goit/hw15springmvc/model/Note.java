package ua.goit.hw15springmvc.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Note {
    private Long id;
    private String title;
    private String content;
}
