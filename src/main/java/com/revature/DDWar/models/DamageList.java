package com.revature.DDWar.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DamageList {
    private ProfParser damage_type;
     private String damage_dice;
}
