package com.company.app.users.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.codes.UserTypes;
import com.company.app.common.converter.UserTypesListConverter;
import com.company.app.common.model.DateTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER", schema = "MOMSITTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "GENDER", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderTypes gender;

    @Convert(converter = UserTypesListConverter.class)
    @Column(name = "USER_TYPE", nullable = false, length = 10)
    private List<UserTypes> userType;

    // 돌봄 신청 내용
    @Column(name = "REQUEST_INFO")
    private String requestInfo;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ChildInfoModel> childInfoList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SITTER_NO")
    private SitterDetailModel sitterDetail;

    public void setChildInfoList(List<ChildInfoModel> childList) {
        this.childInfoList = childList;
        if (this.childInfoList != null && this.childInfoList.size() > 0) {
            for (ChildInfoModel c : childInfoList) {
                c.setParent(this);
            }
        }
    }

}
