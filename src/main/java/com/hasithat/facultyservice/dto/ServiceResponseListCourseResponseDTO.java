package com.hasithat.facultyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseListCourseResponseDTO {

    List<CourseResponseDTO> response;
    String httpStatus;
    List<ErrorDTO> error;


}
