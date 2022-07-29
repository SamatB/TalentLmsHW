package com.peaksoft.controller;

import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import com.peaksoft.service.GroupService;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/students";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/addStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student, student.getGroupId());
        return "redirect:/students";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(Model model, @PathVariable("id") long id) {
        model.addAttribute("studentUpdate", studentService.getStudentById(id));
        return "student/updateStudent";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("studentUpdate") Student student,
                         @PathVariable("id") long id) {
        studentService.updateStudent(student, id);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        studentService.deleteStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/groups/{studentId}")
    public String getGroups(@PathVariable("studentId") Long studentId, Model model) {
        model.addAttribute("groups", groupService.getGroupsByStudentId(studentId));
        return "/student/getGroups";
    }
}
