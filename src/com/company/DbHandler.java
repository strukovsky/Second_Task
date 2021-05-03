package com.company;

import com.sun.source.tree.CompoundAssignmentTree;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {
    private static final String CONN = "jdbc:sqlite:/Users/dmitry/IntelliJIDEAProjects/ProfiStaffSecondTask/db.sqlite";

    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new DbHandler();
        }
        return instance;
    }

    private Connection connection;

    private DbHandler() throws SQLException
    {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CONN);

    }

    public void setupTable()
    {
        try(Statement statement = this.connection.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS students(name TEXT NOT NULL, surname TEXT NOT NULL, patronymic TEXT NOT NULL, birthDate TEXT NOT NULL, id INT NOT NULL UNIQUE, groupId TEXT NOT NULL)");
        }
        catch (Exception e )
        {
            System.out.println(e);
        }
    }

    public List<Student> getAllStudents()
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<Student> students = new ArrayList<>();

            ResultSet set = statement.executeQuery("SELECT name, surname, patronymic, birthDate, id, groupId FROM students");
            while(set.next())
            {
                students.add(new Student(
                        set.getString("name"),
                        set.getString("surname"),
                        set.getString("patronymic"),
                        set.getString("birthDate"),
                        set.getInt("id"),
                        set.getString("groupId")
                ));
            }
            return students;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public void addStudent(Student student)
    {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO students(`name`, `surname`, `patronymic`, `birthDate`, `id`, `groupId`) " +
                        "VALUES (?, ?, ?, ?, ?, ?)"
        ))
        {
            statement.setObject(1, student.name);
            statement.setObject(2, student.surname);
            statement.setObject(3, student.patronymic);
            statement.setObject(4, student.birthDate);
            statement.setObject(5, student.id);
            statement.setObject(6, student.groupId);
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public void deleteStudent(int id)
    {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM students WHERE id = ?"
        ))
        {
            statement.setObject(1, id);
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
