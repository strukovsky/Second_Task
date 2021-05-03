package com.company;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbHandler h = null;
        try {
            h = DbHandler.getInstance();
            h.setupTable();
            System.out.println("Welcome to console.");
            Scanner s = new Scanner(System.in);
            while (true) {
                System.out.println("Write command name to execute");
                System.out.println("list -- show all students up to the moment");
                System.out.println("add -- add new student");
                System.out.println("delete -- remove student by id");
                String operation = s.next();
                if(operation.equals("list"))
                {
                    List<Student> list = h.getAllStudents();
                    for(Student student: list)
                    {
                        System.out.println(student);
                    }
                }else
                if(operation.equals("add"))
                {
                    System.out.println("Enter data for student in following format:");
                    System.out.println("name surname patronymic dd.mm.YYYY id groupId");
                    String name = s.next();
                    String surname = s.next();
                    String patronymic = s.next();
                    String birthDate= s.next();
                    int id = s.nextInt();
                    String groupId = s.next();
                    Student student = new Student(name, surname, patronymic, birthDate, id, groupId);
                    h.addStudent(student);

                }
                else if (operation.equals("delete"))
                {
                    System.out.println("Enter unique id of student to delete");
                    int id = s.nextInt();
                    h.deleteStudent(id);
                }
                else
                    System.out.println("Cannot understand the command. Try again");
            }

        } catch (Exception throwables) {
            System.out.println(throwables);
        }


    }
}
