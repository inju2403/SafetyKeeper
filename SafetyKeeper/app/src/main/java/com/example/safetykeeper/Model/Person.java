package com.example.safetykeeper.Model;

public class Person implements Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    //이름 순으로 친구 목록을 정렬
    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }

}