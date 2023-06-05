package com.revature.DDWar.dtos.responses;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonsterList {
    private int count;
    private List<MonsterResult> results;
}