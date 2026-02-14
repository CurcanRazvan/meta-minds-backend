package com.petconnect.petplatform.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private int age;
    private String location;
    private boolean adopted;

    @ManyToOne
    @JoinColumn(name = "adopted_by_user_id")
    private User adoptedBy;
}
