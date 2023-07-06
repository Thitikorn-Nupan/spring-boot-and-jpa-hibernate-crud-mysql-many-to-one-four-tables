package com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "header_marketings")
public class Marketing {
    @Id // mark primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mark auto increment
    private Long m_id;
    @NotBlank
    private String fullname;
    @NotNull
    private Double salary;
    @Pattern(regexp = "junior|middle|senior")
    private String level;

    public Marketing(String fullname, Double salary, String level) {
        this.fullname = fullname;
        this.salary = salary;
        this.level = level;
    }
}
