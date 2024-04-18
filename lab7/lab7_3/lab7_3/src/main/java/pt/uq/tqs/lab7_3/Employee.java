package pt.uq.tqs.lab7_3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    private long id;
    private String name;
    private int age;
    private long salary;
    private String jobposition;

    public Employee() {
    }

    public Employee(long id, String name, int age, long salary, String jobposition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.jobposition = jobposition;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return this.salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getJobposition() {
        return this.jobposition;
    }

    public void setJobposition(String jobposition) {
        this.jobposition = jobposition;
    }

    public Employee id(long id) {
        setId(id);
        return this;
    }

    public Employee name(String name) {
        setName(name);
        return this;
    }

    public Employee age(int age) {
        setAge(age);
        return this;
    }

    public Employee salary(long salary) {
        setSalary(salary);
        return this;
    }

    public Employee jobposition(String jobposition) {
        setJobposition(jobposition);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && age == employee.age && salary == employee.salary && Objects.equals(jobposition, employee.jobposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary, jobposition);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            ", salary='" + getSalary() + "'" +
            ", jobposition='" + getJobposition() + "'" +
            "}";
    }

}