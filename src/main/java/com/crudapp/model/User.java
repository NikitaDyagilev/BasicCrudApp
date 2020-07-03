package com.crudapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "User")
@TableGenerator(name = "USER")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String bio;


}
