package com.miniusos_app.controller;

import com.miniusos_app.model.Student;
import com.miniusos_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView getStudentForm(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        m.setViewName("student");
        m.addObject("grupy",studentService.getAllGroups());
        String studentID = request.getUserPrincipal().getName();
        Student student = studentService.getStudentByID(Integer.parseInt(studentID));
        m.addObject("wyniki",studentService.getAllStudentsMarks(student));
        return m;
    }

    @RequestMapping(value = "/student/zarejestruj", method = RequestMethod.POST)
    public ModelAndView registreToGroup(HttpServletRequest request,
                                        @RequestParam(value = "chosenGroup")Integer id_grupy){
        String studentID = request.getUserPrincipal().getName();
        Student student = studentService.getStudentByID(Integer.parseInt(studentID));
        studentService.registerStudentToGroup(student,id_grupy);

        return getStudentForm(request);
    }
}
