class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow copy
    public Person shallowClone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    // Deep copy
    public Person deepClone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.address = new Address(address.city);
        return p;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', city='" + address.city + "'}";
    }
}

public class Main4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("John", new Address("Chennai"));

        Person shallow = p1.shallowClone();
        Person deep = p1.deepClone();

        shallow.address.city = "Delhi";
        System.out.println("After shallow change:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow: " + shallow);

        deep.address.city = "Mumbai";
        System.out.println("\nAfter deep change:");
        System.out.println("Original: " + p1);
        System.out.println("Deep: " + deep);
    }
}
