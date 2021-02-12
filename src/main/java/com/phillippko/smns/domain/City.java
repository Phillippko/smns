package com.phillippko.smns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor(force = true)
public class City {
    final private String name;
    @Id
    @GeneratedValue
    final private UUID id;

}
