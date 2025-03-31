package com.moduledomain.command.domain.theater;

import lombok.Getter;

@Getter
public class SeatNo implements Comparable<SeatNo> {
    private char row;
    private int number;

    protected SeatNo() {
    }

    public SeatNo(char row, int number) {
        this.row = row;
        this.number = number;
    }

    @Override
    public int compareTo(SeatNo o) {
        if (this.row == o.row) {
            return Integer.compare(this.number, o.number);
        }
        return Character.compare(this.row, o.row);
    }

    public boolean isNextTo(SeatNo other) {
        return this.row == other.row && this.number + 1 == other.number;
    }

}
