package com.hasithat.facultyservice.controller;

import com.hasithat.facultyservice.dto.*;
import com.hasithat.facultyservice.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/faculty-service")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @PostMapping
    public ServiceResponseCourseResponseDTO addNewCourseToDashboard(@RequestBody CourseRequestDTO courseRequestDTO) {
        return facultyService.addNewCourseToDashboard(courseRequestDTO);
    }

    @GetMapping
    public ServiceResponseListCourseResponseDTO getAllCourses(){
        return facultyService.fetchAllCourses();
    }

    @GetMapping("/{id}")
    public ServiceResponseCourseResponseDTO getCourseById(@PathVariable int id){
        return facultyService.fetchCourseById(id);
    }

    @PutMapping("/{id}")
    public ServiceResponseCourseResponseDTO updateCourse(@PathVariable int id, @RequestBody CourseRequestDTO courseRequestDTO){
        facultyService.updateCourse(courseRequestDTO, id);
        return facultyService.fetchCourseById(id);
    }

    @DeleteMapping("/{id}")
    public ServiceResponseCourseResponseDTO deleteCourse(@PathVariable int id){
        ServiceResponseCourseResponseDTO serviceResponseCourseResponseDTOBeforeDelete=facultyService.fetchCourseById(id);
        ServiceResponseCourseResponseDTO serviceResponseCourseResponseDTOAfterDelete=new ServiceResponseCourseResponseDTO();;
        if(serviceResponseCourseResponseDTOBeforeDelete.getHttpStatus().equals("NOT_FOUND")){
            serviceResponseCourseResponseDTOAfterDelete=serviceResponseCourseResponseDTOBeforeDelete;
        }else{
            facultyService.deleteCourse(id);
            serviceResponseCourseResponseDTOAfterDelete=facultyService.fetchCourseById(id);
            if(serviceResponseCourseResponseDTOAfterDelete.getHttpStatus().equals("NOT_FOUND")){
                serviceResponseCourseResponseDTOAfterDelete.setHttpStatus(HttpStatus.OK.toString());
                serviceResponseCourseResponseDTOAfterDelete.setError(null);
            }else{
                serviceResponseCourseResponseDTOAfterDelete.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                ErrorDTO errorDTO= new ErrorDTO();
                errorDTO.setErrorMessage("Object deletion failed");
                List<ErrorDTO> errorDTOList= new ArrayList<ErrorDTO>();
                errorDTOList.add(errorDTO);
                serviceResponseCourseResponseDTOAfterDelete.setError(errorDTOList);
            }
        }
        return serviceResponseCourseResponseDTOAfterDelete;
    }

}
