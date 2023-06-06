        package com.revature.DDWar.dtos.responses;

        import java.util.List;

import com.revature.DDWar.models.*;

import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
public class Monster {
        private String name; 
        private String size;
        private String type;
        private String alignment;
        private List<Ac> armor_class;
        private Integer hit_points;
        private Speed speed;
        private Integer strength;
        private Integer dexterity;
        private Integer constitution;
        private Integer intelligence;
        private Integer wisdom;
        private Integer charisma;
        private List<Proficiency> proficiencies;
        private List<String> damage_vulnerabilities;
        private List<String> damage_resistances;
        private List<String> damage_immunities;
        private List<Proficiency> condition_immunities;
        private Senses senses;
        private String languages;
        private Integer challenge_rating;
        private Integer xp;
        private List<Actions> special_abilities;
        private List<Actions> actions;
        private List<Actions> legendary_actions;
        private String image;
        private String url;    
}