package university;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UniversitySystemDemo {
    public static void main(String[] args) {
        System.out.println("=== University Course Registration System Demo ===\n");
        
        // Create Registration System
        RegistrationSystem registrationSystem = new RegistrationSystem();
        
        // Create Courses
        Course cs101 = new Course("CS101", "Introduction to Programming", 3, new String[0]);
        Course cs201 = new Course("CS201", "Data Structures", 3, new String[]{"CS101"});
        Course math101 = new Course("MATH101", "Calculus I", 4, new String[0]);
        Course math201 = new Course("MATH201", "Calculus II", 4, new String[]{"MATH101"});
        
        cs101.setSchedule("MWF 10:00-11:00");
        cs101.setSemester("Fall 2024");
        
        registrationSystem.addCourse(cs101);
        registrationSystem.addCourse(cs201);
        registrationSystem.addCourse(math101);
        registrationSystem.addCourse(math201);
        
        // Create different types of students
        
        // 1. Freshman Student
        FreshmanStudent freshman = new FreshmanStudent("F001", "Alice Johnson", 
            "Computer Science", "Central High School", 3.8);
        freshman.setEmail("alice@university.edu");
        freshman.setPhoneNumber("555-0101");
        
        // 2. Transfer Student with previous courses
        Map<String, String> transferCourses = new HashMap<>();
        transferCourses.put("CS101", "A");
        transferCourses.put("MATH101", "B");
        
        TransferStudent transfer = new TransferStudent("T001", "Bob Smith", 
            "Computer Science", transferCourses, "State Community College", 30);
        transfer.setEmail("bob@university.edu");
        
        // 3. Graduate Student
        Map<String, String> undergradCourses = new HashMap<>();
        undergradCourses.put("CS101", "A");
        undergradCourses.put("CS201", "A");
        undergradCourses.put("MATH101", "B");
        undergradCourses.put("MATH201", "B");
        
        AcademicRecord undergradRecord = new AcademicRecord("G001_UNDERGRAD", 
            "Computer Science", LocalDate.of(2020, 9, 1), undergradCourses, 
            new String[]{"Dean's List", "Summa Cum Laude"});
        
        GraduateStudent graduate = new GraduateStudent("G001", "Carol Davis", 
            "Computer Science", undergradRecord, "Machine Learning Applications", "Dr. Johnson");
        graduate.setEmail("carol@university.edu");
        
        // 4. International Student
        Map<String, String> intlCourses = new HashMap<>();
        intlCourses.put("CS101", "A");
        
        InternationalStudent international = new InternationalStudent("I001", "Hiroshi Tanaka",
            "Computer Science", intlCourses, "F-1", "TOEFL-95", "Japan", 
            LocalDate.of(2025, 12, 31));
        international.setEmail("hiroshi@university.edu");
        
        // Create Professor and Classroom
        Professor professor = new Professor("PROF001", "Computer Science", 
            Arrays.asList("Ph.D. Computer Science", "M.S. Mathematics"));
        professor.setOfficeHours("MW 2-4 PM");
        professor.setCurrentCourses(Arrays.asList("CS101", "CS201"));
        
        Classroom classroom = new Classroom("CS-101", 30, 
            new String[]{"Projector", "Whiteboard", "Computers"});
        classroom.setCurrentSchedule("MWF 10:00-11:00 CS101");
        
        // Demonstrate enrollment
        System.out.println("=== Student Enrollment Demo ===");
        
        // Freshman trying to enroll in CS101 (should succeed)
        boolean result1 = registrationSystem.enrollStudent(freshman, cs101);
        System.out.println("Freshman enrollment in CS101: " + result1);
        
        // Freshman trying to enroll in CS201 (should fail - no prerequisites)
        boolean result2 = registrationSystem.enrollStudent(freshman, cs201);
        System.out.println("Freshman enrollment in CS201: " + result2);
        
        // Transfer student trying to enroll in CS201 (should succeed - has CS101)
        boolean result3 = registrationSystem.enrollStudent(transfer, cs201);
        System.out.println("Transfer student enrollment in CS201: " + result3);
        
        // Transfer student trying to enroll in MATH201 (should succeed - has MATH101)
        boolean result4 = registrationSystem.enrollStudent(transfer, math201);
        System.out.println("Transfer student enrollment in MATH201: " + result4);
        
        // International student enrollment
        boolean result5 = registrationSystem.enrollStudent(international, cs201);
        System.out.println("International student enrollment in CS201: " + result5);
        
        System.out.println("\n=== Student Information Demo ===");
        
        // Display student information
        System.out.println("\nFreshman Student:");
        System.out.println("Contact Info: " + freshman.getContactInfo());
        System.out.println("Academic Standing: " + freshman.getAcademicStanding());
        System.out.println("High School: " + freshman.getHighSchool());
        System.out.println("High School GPA: " + freshman.getHighSchoolGPA());
        
        System.out.println("\nTransfer Student:");
        System.out.println("Contact Info: " + transfer.getContactInfo());
        System.out.println("Academic Standing: " + transfer.getAcademicStanding());
        System.out.println("Previous University: " + transfer.getPreviousUniversity());
        System.out.println("Transfer Credits: " + transfer.getTransferCredits());
        System.out.println("Current GPA: " + transfer.getAcademicRecord().getCumulativeGPA());
        
        System.out.println("\nGraduate Student:");
        System.out.println("Contact Info: " + graduate.getContactInfo());
        System.out.println("Academic Standing: " + graduate.getAcademicStanding());
        System.out.println("Thesis Title: " + graduate.getThesisTitle());
        System.out.println("Advisor: " + graduate.getAdvisor());
        System.out.println("Undergraduate GPA: " + graduate.getUndergraduateRecord().getCumulativeGPA());
        
        System.out.println("\nInternational Student:");
        System.out.println("Contact Info: " + international.getContactInfo());
        System.out.println("Academic Standing: " + international.getAcademicStanding());
        System.out.println("Country of Origin: " + international.getCountryOfOrigin());
        System.out.println("Visa Status: " + international.getVisaStatus());
        System.out.println("Language Proficiency: " + international.getLanguageProficiency());
        System.out.println("Visa Valid: " + international.isVisaValid());
        
        System.out.println("\n=== Course Information Demo ===");
        System.out.println("CS101 Course: " + cs101);
        System.out.println("Prerequisites met by transfer student for CS201: " + 
                          transfer.getAcademicRecord().meetsPrerequisites("CS201"));
        System.out.println("Prerequisites met by freshman for CS201: " + 
                          freshman.getAcademicRecord().meetsPrerequisites("CS201"));
        
        System.out.println("\n=== Faculty and Classroom Demo ===");
        System.out.println("Professor: " + professor);
        System.out.println("Classroom: " + classroom);
        
        System.out.println("\n=== Academic Records Demo ===");
        System.out.println("Transfer Student Academic Record:");
        System.out.println(transfer.getAcademicRecord());
        
        System.out.println("\nGraduate Student Undergraduate Record:");
        System.out.println(graduate.getUndergraduateRecord());
        
        System.out.println("\n=== System Policies Demo ===");
        System.out.println("Maximum Credit Hours: " + RegistrationSystem.MAX_CREDIT_HOURS);
        System.out.println("Minimum GPA for Enrollment: " + RegistrationSystem.MIN_GPA_FOR_ENROLLMENT);
        System.out.println("Enrollment Periods: " + Arrays.toString(RegistrationSystem.ENROLLMENT_PERIODS));
        
        // Demonstrate package-private access (would be used by administration)
        System.out.println("\n=== Administration Access Demo ===");
        System.out.println("Number of enrolled student-course pairs: " + 
                          registrationSystem.getEnrolledStudents().size());
        
        // Generate transcript for transfer student
        System.out.println("\n=== Transcript Generation Demo ===");
        registrationSystem.generateTranscript("T001");
        
        // Demonstrate immutability
        System.out.println("\n=== Immutability Demo ===");
        AcademicRecord record = transfer.getAcademicRecord();
        System.out.println("Original GPA: " + record.getCumulativeGPA());
        
        // Try to modify completed courses (should not affect original)
        Map<String, String> courses = record.getCompletedCourses();
        System.out.println("Completed courses before: " + courses.size());
        try {
            courses.put("NEW_COURSE", "A"); // This should fail due to immutable map
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify completed courses - properly immutable!");
        }
        
        // Try to modify academic honors (should not affect original)
        String[] honors = record.getAcademicHonors();
        System.out.println("Academic honors count: " + honors.length);
        if (honors.length > 0) {
            honors[0] = "MODIFIED"; // This modifies the copy, not the original
        }
        String[] originalHonors = record.getAcademicHonors();
        System.out.println("Original academic honors preserved: " + 
                          (honors != originalHonors)); // Should be true (different arrays)
        
        // Demonstrate JavaBean compliance
        System.out.println("\n=== JavaBean Compliance Demo ===");
        freshman.setCurrentName("Alice M. Johnson");
        freshman.setCurrentAddress("123 University Ave");
        freshman.setEmergencyContact("Mary Johnson - 555-0102");
        
        System.out.println("Updated freshman name: " + freshman.getCurrentName());
        System.out.println("Updated address: " + freshman.getCurrentAddress());
        System.out.println("Emergency contact: " + freshman.getEmergencyContact());
        
        // Update course schedule
        cs101.setSchedule("MWF 11:00-12:00");
        cs101.setSemester("Spring 2025");
        System.out.println("Updated CS101 schedule: " + cs101.getSchedule());
        
        // Update professor information
        professor.setOfficeHours("TTh 1-3 PM");
        System.out.println("Updated professor office hours: " + professor.getOfficeHours());
        
        // Update classroom availability
        classroom.setAvailable(false);
        classroom.setCurrentSchedule("TTh 9:00-10:30 MATH201");
        System.out.println("Classroom now available: " + classroom.isAvailable());
        System.out.println("New classroom schedule: " + classroom.getCurrentSchedule());
        
        System.out.println("\n=== Demo Complete ===");
    }
}