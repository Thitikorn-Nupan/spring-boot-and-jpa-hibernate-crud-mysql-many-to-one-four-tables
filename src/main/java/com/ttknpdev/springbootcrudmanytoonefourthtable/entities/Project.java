package com.ttknpdev.springbootcrudmanytoonefourthtable.entities;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@NoArgsConstructor

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @NotBlank
    private String project_name;
    @NotNull
    private Double project_cost;
    @NotBlank
    @Setter(AccessLevel.PRIVATE)
    private String project_build = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    @NotNull
    private Boolean project_status;
    @ManyToOne(optional = false)
    @JoinColumn(name = "p_id")
    //@JsonIgnore
    private Programmer programmer;
    @ManyToOne(optional = false) // By default, the @ManyToOne association uses FetchType.EAGER for fetch type but it is bad for performance
    @JoinColumn(name = "s_id")  //  @JoinColumn annotation to specify the foreign key column
    //@JsonIgnore  /* @JsonIgnore is used to ignore the logical property used in serialization and deserialization. */
    private Sale sale;
    @ManyToOne(optional = false) // By default, the @ManyToOne association uses FetchType.EAGER for fetch type but it is bad for performance
    @JoinColumn(name = "m_id")  //  @JoinColumn annotation to specify the foreign key column
    //@JsonIgnore  /* @JsonIgnore is used to ignore the logical property used in serialization and deserialization. */
    private Marketing marketing;

    public Project(String project_name, Double project_cost, Boolean project_status) {
        this.project_name = project_name;
        this.project_cost = project_cost;
        this.project_status = project_status;
    }
}
