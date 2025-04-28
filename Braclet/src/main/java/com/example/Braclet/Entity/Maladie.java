package com.example.Braclet.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Maladie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_m;

    private String nom;
    private String description;
    private LocalDate dateDiagnostic;
    private String nomDoc;
    private String numDoc;

    @ManyToOne
    @JoinColumn(name = "bracelet_id")
    private Bracelet bracelet;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyAppUser user;
}
