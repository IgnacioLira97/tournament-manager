package com.pkmtourney.tournament_manager.model;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate date;
   


    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        UPCOMING,
        ONGOING,
        COMPLETED
    }
}

