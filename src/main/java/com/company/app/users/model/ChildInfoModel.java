package com.company.app.users.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.model.DateTimeEntity;
import com.company.app.users.model.dto.ChildInfoRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 아이 정보
@Entity
@Table(name = "CHILD_INFO", schema = "MOMSITTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ChildInfoModel extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHILD_NO")
    private Long childNo;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private GenderTypes gender;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    @JsonIgnore
    private UserModel parent;

    public ChildInfoModel(ChildInfoRequestDto kidInfo){
        this.gender = GenderTypes.valueOf(kidInfo.getGender());
        this.birthdate = kidInfo.getBirthDate();
    }
}
