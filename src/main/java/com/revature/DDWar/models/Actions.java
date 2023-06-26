package com.revature.DDWar.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actions {
    private String name;
    private String desc;
    private List<DamageList> damage;
}
