package com.phillippko.smns.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class City {
    final private String name;

    @Id
    @GeneratedValue
    private UUID id;

}
