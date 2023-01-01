package com.hasithat.facultyservice.service;

import com.hasithat.facultyservice.dto.CourseRequestDTO;
import com.hasithat.facultyservice.dto.CourseResponseDTO;
import com.hasithat.facultyservice.dto.ServiceResponseCourseResponseDTO;
import com.hasithat.facultyservice.dto.ServiceResponseListCourseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacultyService {

    private static final String  BASE_URL="http://localhost:8080/";

    @Autowired
    RestTemplate restTemplate;

    public ServiceResponseCourseResponseDTO addNewCourseToDashboard(CourseRequestDTO courseRequestDTO) {
        try {
            return restTemplate.postForObject(BASE_URL + "course", courseRequestDTO, ServiceResponseCourseResponseDTO.class);
        }catch (Exception ex){
            System.out.println("ex "+ex.getMessage());
        }
        return null;
    }

    public ServiceResponseListCourseResponseDTO  fetchAllCourses(){
        return restTemplate.getForObject(BASE_URL + "course", ServiceResponseListCourseResponseDTO.class);
    }

    public ServiceResponseCourseResponseDTO  fetchCourseById(int courseId){
        return restTemplate.getForObject(BASE_URL + "course/"+courseId, ServiceResponseCourseResponseDTO.class);
    }

    public void updateCourse(CourseRequestDTO courseRequestDTO,int courseId){
        restTemplate.put(BASE_URL+"course/"+courseId, courseRequestDTO);
    }

    public void deleteCourse(int courseId){
        restTemplate.delete(BASE_URL+"course/"+courseId);
    }



}
