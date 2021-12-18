package com.company.app.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.company.app.common.model.DateTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// TODO 시터 정보
@Entity
@Table(name = "SITTER_DETAIL", schema = "MOMSITTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SitterDetailModel extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SITTER_NO")
    private Long sitterNo;

    /**
     * 케어 가능한 최소연령
     */
    @Column(name = "MIN_CARE_AGE")
    private Integer minCareAge;
    /**
     * 케어 가능한 최대 연령
     */
    
    @Column(name = "MAX_CARE_AGE")
    private Integer maxCareAge;

    /**
     * 자기 소개
     */
    @Column(name = "INTRODUCE")
    private String introduce;

}
