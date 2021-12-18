package com.company.app.users.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.company.app.common.codes.GenderTypes;
import com.company.app.common.configs.validations.EnumTypeValid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateChildInfoRequestDto implements Serializable {
    
    @NotNull(message="childNo를 입력해주세요")
    private Long childNo;

    @NotBlank(message = "gender을 입력해주세요.")
    @EnumTypeValid(target = GenderTypes.class, message = "올바른 gender를 입력해주세요")
    private String gender;

    @NotNull(message="birthDate를 입력해주세요.")
    @JsonFormat(pattern = "yyyyMMdd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
}
