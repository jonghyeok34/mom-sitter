package com.company.app.users.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.company.app.common.codes.UserTypes;
import com.company.app.common.converter.UserTypesListConverter;
import com.company.app.common.model.DateTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "USER", schema = "MOMSITTER")
@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserModel extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long memberNo;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Convert(converter = UserTypesListConverter.class)
    @Column(name = "USER_TYPE", nullable = false, length = 10)
    private List<UserTypes> userType;
    
}
