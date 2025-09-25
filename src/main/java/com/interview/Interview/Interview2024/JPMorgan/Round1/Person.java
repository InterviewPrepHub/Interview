package com.interview.Interview.Interview2024.JPMorgan.Round1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Person {

    private final PersonId personId;

    private final String firstName;

    private final String lastName;

    private final int age;

    public Person(PersonId personId, String firstName, String lastName, int age) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "%s %s, age=%s, id=%s".formatted(firstName, lastName, age, personId.getId());
    }
}

class PersonId {

    private final String id;

    public PersonId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PersonId) && (((PersonId) obj).getId() == id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}

/*
 * A simple in-memory database, backed by a HashMap.
 * For the purpose of this question assume:
 *   - we have unlimited memory
 *   - 100s of millions of people in the database
 *   - the process will never crash
 */
class PersonInMemoryRepository {

    private final Map<PersonId, Person> personByIdMap = new HashMap<>();
    private final Map<Integer, List<Person>> personsByAge = new HashMap<>();

    public void savePerson(Person person) {
        personByIdMap.put(person.getPersonId(), person);
        personsByAge.computeIfAbsent(person.getAge(), k -> new ArrayList<>()).add(person);
        personsByAge.computeIfPresent(person.getAge(), (age, personList) -> personList).add(person);
    }

    public Person findById(PersonId id) {
        return personByIdMap.get(id);
    }

    public List<Person> findByAge(int age) {
        return personByIdMap.values().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Person> findByAgeFaster(int age) {
        return personsByAge.getOrDefault(age, new ArrayList<>());
    }

    public static void main(String[] args) {
        PersonInMemoryRepository repo = new PersonInMemoryRepository();
        var person = new Person(new PersonId("12345"), "Joe", "Bloggs", 25);
        System.out.println("person: " + person);
        repo.savePerson(person);
        System.out.println("find by age: " + repo.findByAge(25));
        System.out.println("find by id: " + repo.findById(new PersonId("12345")));
    }
}

/*
1.Explain what map look like here and how does the entries get stored here

HashMap is storing key as PersonId object and value as Person object which has PersonId object as one column
Internally, HashMap maintains an array of buckets to store entries.

Hashmap Array
Index 0 : []
Index 1 : [(12345, Person1)]
Index 2 : []

key's hashcode method is called to determine index of bucket where should be stored

Storage of Entries:

Each bucket in the array holds a linked list of key-value pairs.
If multiple keys hash to the same index, their entries are stored as nodes in this linked list.

Storage of Entries:

Each bucket in the array holds a linked list of key-value pairs.
If multiple keys hash to the same index, their entries are stored as nodes in this linked list.
index = hashcode(id)

if there are two keys which have same result after hashcode function is applied to them then
they are added in the arraylist of that bucket/index

Index 1 : [(12345, Person1), (54321, Person2)]

======================================================================================================
2. What's the relationship between the below two hashcode?

hashCode1 = new PersonId("12345").hashCode();
hashCode2 = new PersonId("12345").hashCode();

Both the hashcode should be identical which is a consequence of the hashcode contract where two objects
are considered equal as per the equals method

======================================================================================================

3. when we ran the code as is we code see the below result
person: Joe Bloggs, age=25, id=12345
find by age: [Joe Bloggs, age=25, id=12345]
find by id: null

here find by id was null so I checked the PersonId class and added the below code for hashcode
    @Override
    public int hashCode() {
        return id.hashCode();
    }

The issue is due to how the hashmap is encountering hashcode and equals method to store and retrieve
objects.
HashMap uses both equals() and hashcode() methods to manage its internal

hashcode - determines the bucket in which the key-value entry is placed
equals - ensures that if two keys are same then their hashcode() values will collide and they are in same bucket

by default hashcode() method always returns unique integer for each object based on its memory address

======================================================================================================

4. Current time complexity of findByAge method
O(n) - going through all entries of map

======================================================================================================
5. How can you implement findByAgeFaster
indexing on Age
time complexity of findByAgeFaster - O(1)

======================================================================================================

 */