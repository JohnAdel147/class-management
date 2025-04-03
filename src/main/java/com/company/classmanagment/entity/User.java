package com.company.classmanagment.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.HasTimeZone;
import io.jmix.core.annotation.Secret;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.security.authentication.JmixUserDetails;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Entity
@Table(name = "USER_", indexes = {
        @Index(name = "IDX_USER__ON_USERNAME", columnList = "USERNAME", unique = true),
        @Index(name = "IDX_USER__GRADUATION_PROJECT", columnList = "GRADUATION_PROJECT_ID")
})
public class User implements JmixUserDetails, HasTimeZone {

    @Id
    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    private UUID id;

    @Column(name = "NEED_ACTIVATION")
    private Boolean needActivation;
    @Column(name = "ACTIVATION_TOKEN")
    private String activationToken;
    @Column(name = "STUDENT")
    private Boolean student;
    @Column(name = "STUDENT_EXAMS_SCORE")
    private Double studentExamsScore;
    @Column(name = "PASSED_EXAMS")
    private Integer passedExams;
    @Column(name = "SEMESTER")
    private Integer semester;
    @JoinTable(name = "USER_SUBJECTS_LINK",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECTS_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Subject> subjects;
    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "USERNAME", nullable = false)
    protected String username;

    @Secret
    @SystemLevel
    @Column(name = "PASSWORD")
    protected String password;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Email
    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "ACTIVE")
    protected Boolean active = true;

    @Column(name = "TIME_ZONE_ID")
    protected String timeZoneId;


    @JoinTable(name = "EXAM_USER_LINK",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EXAM_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Exam> exams;
    @Column(name = "TOTAL_GRADE_SCORE")
    private Double totalGradeScore;
    @Column(name = "STUDENT_ATTENDANCE_SCORE")
    private Double studentAttendanceScore;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "GRADUATION_PROJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GraduationProject graduationProject;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student")
    private GraduationProjectRequest graduationProjectRequest;
    @Transient
    protected Collection<? extends GrantedAuthority> authorities;

    public GraduationProjectRequest getGraduationProjectRequest() {
        return graduationProjectRequest;
    }

    public void setGraduationProjectRequest(GraduationProjectRequest graduationProjectRequest) {
        this.graduationProjectRequest = graduationProjectRequest;
    }

    public GraduationProject getGraduationProject() {
        return graduationProject;
    }

    public void setGraduationProject(GraduationProject graduationProject) {
        this.graduationProject = graduationProject;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public Boolean getNeedActivation() {
        return needActivation;
    }

    public void setNeedActivation(Boolean needActivation) {
        this.needActivation = needActivation;
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    public Integer getPassedExams() {
        return passedExams;
    }

    public void setPassedExams(Integer passedExams) {
        this.passedExams = passedExams;
    }

    public void setStudentExamsScore(Double studentExamsScore) {
        this.studentExamsScore = studentExamsScore;
    }

    public Double getStudentExamsScore() {
        return studentExamsScore;
    }

    public Double getStudentAttendanceScore() {
        return studentAttendanceScore;
    }

    public void setStudentAttendanceScore(Double studentAttendanceScore) {
        this.studentAttendanceScore = studentAttendanceScore;
    }

    public Double getTotalGradeScore() {
        return totalGradeScore;
    }

    public void setTotalGradeScore(Double totalGradeScore) {
        this.totalGradeScore = totalGradeScore;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void setSemester(Grade semester) {
        this.semester = semester == null ? null : semester.getId();
    }

    public Grade getSemester() {
        return semester == null ? null : Grade.fromId(semester);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : Collections.emptyList();
    }

    @Override
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(active);
    }

    @InstanceName
    @DependsOnProperties({"firstName", "lastName", "username"})
    public String getDisplayName() {
        return String.format("%s %s [%s]", (firstName != null ? firstName : ""),
                (lastName != null ? lastName : ""), username).trim();
    }

    @Override
    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

}