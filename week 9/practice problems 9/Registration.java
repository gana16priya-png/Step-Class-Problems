class ContactInfo implements Cloneable {
    String email;
    String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }
}

class Student implements Cloneable {
    String id;
    String name;
    ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Shallow copy
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep copy
    protected Student deepClone() throws CloneNotSupportedException {
        Student copy = (Student) super.clone();
        copy.contact = (ContactInfo) contact.clone();
        return copy;
    }
}

public class Registration {
    public static void main(String[] args) throws CloneNotSupportedException {
        ContactInfo c1 = new ContactInfo("ritika@mail.com", "9876543210");
        Student s1 = new Student("S01", "Ritika", c1);

        Student shallow = (Student) s1.clone();
        Student deep = s1.deepClone();

        // Modify original contact
        s1.contact.email = "changed@mail.com";

        System.out.println("Original: " + s1.contact.email);
        System.out.println("Shallow:  " + shallow.contact.email);
        System.out.println("Deep:     " + deep.contact.email);
    }
}
