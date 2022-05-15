package com.parasol.main_authentication.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {
    @Id
    @Column(unique = true)
    private String clientId;

    @NotBlank
    @Size(min = 7, max = 15)
    private String ipAddr;

    @NotBlank
    private String companyName;
}
