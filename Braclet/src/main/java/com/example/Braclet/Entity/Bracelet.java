package com.example.Braclet.Entity;

import com.example.Braclet.Entity.Maladie;
import com.example.Braclet.Entity.Vocal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Bracelet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bracelet_id;

    @OneToMany(mappedBy = "bracelet", cascade = CascadeType.ALL)
    private List<Maladie> maladies = new ArrayList<>();

    @OneToMany(mappedBy = "bracelet", cascade = CascadeType.ALL)
    private List<Vocal> records = new ArrayList<>();
    @OneToMany(mappedBy = "bracelet", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
}
