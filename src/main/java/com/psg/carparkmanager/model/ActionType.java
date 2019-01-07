package com.psg.carparkmanager.model;

import lombok.Getter;

public enum ActionType {

    PARK('p'),
    UNPARK('u'),
    COMPACT('c');

    @Getter
    private Character initial;

    ActionType(Character initial) {

        this.initial = initial;
    }
}
