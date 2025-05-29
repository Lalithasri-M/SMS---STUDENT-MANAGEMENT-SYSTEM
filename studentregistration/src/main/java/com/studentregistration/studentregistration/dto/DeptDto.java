package com.studentregistration.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {

    private Long deptId;
    private String deptCode;
    private String deptName;
    private String hodName;
}
