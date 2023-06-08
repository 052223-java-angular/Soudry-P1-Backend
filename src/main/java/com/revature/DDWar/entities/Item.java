package com.revature.DDWar.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    private String id;

    // set the column username to username
    @Column(name = "itemname", nullable = false)
    private String itemName;

    @Column(name = "itemdescription", nullable = false)
    private String itemDescription;

    @Column(name = "itemtype", nullable = false)
    private String itemType;

    @Column(name = "itembonus", nullable = false)
    private Integer itemBonus;

   
  
    public Item(String name, String description, String type, Integer bonus) {
        this.id = UUID.randomUUID().toString();
        this.itemName = name;
        this.itemDescription = description;
        this.itemType = type;
        this.itemBonus = bonus;
    }
}
