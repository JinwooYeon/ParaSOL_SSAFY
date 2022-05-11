package com.parasol.core.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private String id;

    @NotBlank
    private String name;
    @Column(unique=true)
    @Pattern(regexp="^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$")
    @ApiModelProperty(name="resident_number", example = "000000-0000000")
    @NotBlank
    @Size(max=14, min=14)
    private String residentNumber;
};
