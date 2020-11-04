package model.services;

import model.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentServices {

    public List<Department> findAll(){

        List<Department> list = new ArrayList<>();

        list.add(new Department(1,"Books"));
        list.add(new Department(2,"Computer"));
        list.add(new Department(3,"Electronics"));


        return list;
    }
}
