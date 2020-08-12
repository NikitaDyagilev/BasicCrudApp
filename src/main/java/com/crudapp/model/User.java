package com.crudapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

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
    @JsonProperty("username")
    private String username;

    @Column
    @JsonProperty("password")
    private String password;

    @Column
    @JsonProperty("first_name")
    private String first_name;

    @Column
    @JsonProperty("last_name")
    private String last_name;

    @Column
    @JsonProperty("bio")
    private String bio;

}
