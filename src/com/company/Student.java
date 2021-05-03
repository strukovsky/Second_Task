package com.company;

public class Student {
    String name;
    String surname;
    String patronymic;
    String birthDate;
    int id;
    String groupId;

    public Student(String _name, String _surname, String _patronymic, String _birthDate, int _id, String _group) {
        name = _name;
        surname = _surname;
        patronymic = _patronymic;
        birthDate = _birthDate;
        id = _id;
        groupId = _group;

    }

    @Override
    public String toString() {
        return
                "name: " + name  +
                        ", surname: " + surname +
                        ", patronymic: " + patronymic  +
                        ", birth date: " + birthDate +
                        ", id: " + id +
                        ", group id: " + groupId;
    }
}

