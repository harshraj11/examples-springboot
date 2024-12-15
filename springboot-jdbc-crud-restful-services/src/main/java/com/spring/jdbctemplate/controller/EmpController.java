package com.spring.jdbctemplate.controller;
import java.util.List;

import com.spring.jdbctemplate.model.Emp;
import com.spring.jdbctemplate.repository.EmpRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp/v1")
public class EmpController {

    @Autowired
    EmpRepoImpl dao;//will inject dao from xml file

    @GetMapping("/getAllEmp")
    public List<Emp> getAllEmp(){
        List<Emp> list=dao.getEmployees();
        return list;
    }

    /* It displays object data into form for the given id.  
     * The @PathVariable puts URL data into variable.*/  
    @RequestMapping(value="/editemp/{id}")  
    public String edit(@PathVariable int id, Model m){  
        Emp emp=dao.getEmpById(id);  
        m.addAttribute("command",emp);
        return "empeditform";  
    }

    /* It updates model object. */  
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public String editsave(@ModelAttribute("emp") Emp emp){  
        dao.update(emp);  
        return "redirect:/viewemp";  
    }  
    /* It deletes record for the given id in URL and redirects to /viewemp */  
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
    public String delete(@PathVariable int id){  
        dao.delete(id);  
        return "redirect:/viewemp";  
    }   
}  