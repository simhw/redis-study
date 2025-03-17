package com.moduledomain.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Theater {
    private Long id;
    private String name;
    List<Seat> seats;
}
