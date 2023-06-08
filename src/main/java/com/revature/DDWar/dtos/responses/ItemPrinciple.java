package com.revature.DDWar.dtos.responses;

import java.util.List;
import com.revature.DDWar.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
// @NoArgsConstructor
@Getter
@Setter
public class ItemPrinciple {
private final List<Item> item;
}