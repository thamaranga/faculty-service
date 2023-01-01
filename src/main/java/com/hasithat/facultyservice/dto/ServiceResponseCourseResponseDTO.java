package com.hasithat.facultyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseCourseResponseDTO {

    CourseResponseDTO response;
    String httpStatus;
    List<ErrorDTO> error;
}
