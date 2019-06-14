package com.hajres.domain.model;

import com.hajres.validation.OlderThan;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[0-9]{13}", message = "Must be 13 digits long")
    @Column(name = "jmbg")
    private String jmbg;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "is required")
    @OlderThan
    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_company")
    private Company company;

    @Valid
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_address")
    private Address address;

    public Worker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getLocalDateBirthDate() {
        return birthDate.toLocalDate();
    }

    public void setLocalDateBirthDate(LocalDate birthDate) {
        this.birthDate = Date.valueOf(birthDate);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date date) {
        this.birthDate = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(150);
        sb.append("JMBG: ").append(jmbg).append(", ");
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append(", ");
        sb.append("Born on: ").append(birthDate).append(", ");
        sb.append("Address: ").append(address);
        if (company != null && company.getName() != null) {
            sb.append(", ").append("Employed at ").append(company);
        }
        return sb.toString();
    }
}
