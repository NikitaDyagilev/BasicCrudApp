package com.crudapp.security.jwt;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class UsernameAndPasswordAuthReq {
    @Getter(onMethod =@__({@JsonProperty(value = "password")}))
    private String password;
    @Getter(onMethod =@__({@JsonProperty(value = "username")}))
    private String username;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsernameAndPasswordAuthReq( @JsonProperty("username") String username,
                                       @JsonProperty("password") String password){
        this.username = username;
        this.password = password;
    }
    public UsernameAndPasswordAuthReq(){
        super();
    }



}
