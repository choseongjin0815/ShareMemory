package com.chobocho.ShareMemory_back_end.util.pagination;


import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PageRequestDTO<E> {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
