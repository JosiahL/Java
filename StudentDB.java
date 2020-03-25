//Josiah Lovin
//StudentDB.java
import java.io.*;
import java.util.*;
//Class Student represents a student object in a school database.
//Each student has an ID (1-100), a name (30 or less characters), an age, and a major (30 or less characters).
//The students are created in such a way to be stored and manipulated in a Random Access File.
class Student
{
    private static final int NAMEMAX = 30;
    private int id;
    private char[] name = new char[NAMEMAX];
    private int age;
    private char[] major = new char[NAMEMAX];
    //Post: Set ID and age to -1 and set name and major to all blank spaces if no parameters are given
    public Student()
    {
        id = -1;
        for(int j=0; j<NAMEMAX; ++j)
            name[j] = ' ';
        age = -1;
        for(int j=0; j<NAMEMAX; ++j)
            major[j] = ' ';
    }
    //Desc: A Student object to be used by a random access file
    //Pre:  ID between [1, 100]
    //Post: Student object created with given ID, name, age, and major
    public Student(int id, String nam, int age, String maj)
    {
        this.id = id;
        for(int j=0; j<nam.length(); ++j)
        {
            if(j<NAMEMAX)
                name[j] = nam.charAt(j);
            else
                break;
        }
        for(int j=nam.length(); j<NAMEMAX; ++j)
            name[j] = ' ';
        this.age = age;
        for(int j=0; j<maj.length(); ++j)
        {
            if(j<NAMEMAX)
                major[j] = maj.charAt(j);
            else
                break;
        }
        for(int j= maj.length(); j<NAMEMAX; ++j)
            major[j] = ' ';
    }
    //Pre:  ID between [1, 100]
    //Post: this.id set to s
    public void setId(int s)
    {
        id = s;
    }
    //Return: this.id
    public int getID()
    {
        return id;
    }
    //Pre:  Name less than 30 characters
    //Post: this.name set to s
    public void setName(String s)
    {
        for(int j=0; j<s.length(); ++j)
        {
            if(j<NAMEMAX)
                name[j] = s.charAt(j);
            else
                return;
        }
        for(int j=s.length(); j<NAMEMAX; ++j)
            name[j]=' ';
    }
    //Return: this.name
    public String getName()
    {
        String s = new String(name);
        return s.trim();
    }
    //Post: this.age set to a
    public void setAge(int a)
    {
        age = a;
    }
    //Return: this.age
    public int getAge()
    {
        return age;
    }
    //Pre:  Major less than 30 characters
    //Post: this.major set to s
    public void setMajor(String s)
    {
        for(int j=0; j<s.length(); ++j)
        {
            if(j<NAMEMAX)
                major[j] = s.charAt(j);
            else
                return;
        }
        for(int j=s.length(); j<NAMEMAX; ++j)
            major[j]=' ';
    }
    //Return: this.major
    public String getMajor()
    {
        String s = new String(major);
        return s.trim();
    }
    //Post: this.id, this.name, this.age, and this.major written to random access file f
    public void write(RandomAccessFile f) throws IOException
    {
        f.writeInt(id);
        for(int j=0; j<NAMEMAX; ++j)
            f.writeChar(name[j]);
        f.writeInt(age);
        for(int j=0; j<NAMEMAX; ++j)
            f.writeChar(major[j]);
    }
    //Post: this.id, this.name, this.age, and this.major read from random access file f
    public void read(RandomAccessFile f) throws IOException
    {
        id = f.readInt();
        for(int j=0; j<NAMEMAX; ++j)
            name[j] = f.readChar();
        age = f.readInt();
        for(int j=0; j<NAMEMAX; ++j)
            major[j] = f.readChar();
    }
    //Return: The size in bites of each student object
    public static int size()
    {
        return 108;
    }
}
public class StudentDB
{
    private static Scanner keyboard = new Scanner(System.in);
    //Desc:   Maintains a database of Student records. The database is stored in
    //        a random access file Student.data
    //Input:  User enters commands from keyboard to manipulate the database.
    //Output: Database updated as directed by user.
    public static void main(String[] args) throws IOException
    {
        Student blank = new Student();
        RandomAccessFile stuDB = new RandomAccessFile("students.data", "rw");
        //Fill random access file with blank students
        for(int i=0; i<100; i++)
            blank.write(stuDB);
        int choice = 6;
        do
        {
            System.out.println("\n\t1. Add new student record");
            System.out.println("\t2. Remove student record");
            System.out.println("\t3. Update student record");
            System.out.println("\t4. Display student record");
            System.out.println("\t5. Display all student records");
            System.out.println("\t6. Exit");
            System.out.print("\tWhat would you like to do? (1-6): ");
            choice = keyboard.nextInt();
            keyboard.nextLine();
            switch(choice)
            {
                case 1: addStudent(stuDB); break;
                case 2: removeStudent(stuDB); break;
                case 3: updateStudent(stuDB); break;
                case 4: displayStudent(stuDB); break;
                case 5: displayAllStudents(stuDB); break;
                default: break;
            }

        } while(choice!=6);
    }
    //Desc:   Adds a student to the random access file database
    //Input:  User enters id, name, age, and major all on seperate lines
    //Output: Tells the user if the student is added successfully, or if
    //        the student already exists in the database.
    public static void addStudent(RandomAccessFile r) throws IOException
    {
        System.out.println("\nEnter ID, name, age, major all on seperate lines:");
        int id = keyboard.nextInt();
        keyboard.nextLine();
        String name = keyboard.nextLine();
        int age = keyboard.nextInt();
        keyboard.nextLine();
        String major = keyboard.nextLine();
        Student s = new Student(id, name, age, major);
        Student check = new Student();
        r.seek((long)(id-1)*Student.size());
        check.read(r);
        if(check.getID()==id)
            System.out.println("\nStudent with that ID already exists.");
        else
        {
            r.seek((long)(id-1)*Student.size());
            s.write(r);
            System.out.println("\nStudent added.");
        }
    }
    //Desc:   Removes student from database given ID
    //Input:  User enters ID of student they wish to remove
    //Output: Tells the user if the student has been successfully removed,
    //        or if the student does not exist
    public static void removeStudent(RandomAccessFile r) throws IOException
    {
        Student s = new Student();
        Student check = new Student();
        System.out.print("\nEnter ID of student you wish to remove: ");
        int id = keyboard.nextInt();
        r.seek((long)(id-1)*Student.size());
        check.read(r);
        if(check.getID()!=id)
            System.out.println("\nStudent does not exist.");
        else
        {
            r.seek((long)(id-1)*Student.size());
            s.write(r);
            System.out.println("\nStudent removed.");
        }
    }
    //Desc:   Updates a student already in the database
    //Input:  User enters the ID, name, age, and major of the student they wish to update
    //Output: Tells the user if the student has been successfully updated,
    //        or if the student does not yet exist
    public static void updateStudent(RandomAccessFile r) throws IOException
    {
        System.out.println("\nEnter the id, name, age, and major of the student you wish to update:");
        int id = keyboard.nextInt();
        keyboard.nextLine();
        String name = keyboard.nextLine();
        int age = keyboard.nextInt();
        keyboard.nextLine();
        String major = keyboard.nextLine();
        Student s = new Student(id, name, age, major);
        Student check = new Student();
        r.seek((long)(id-1)*Student.size());
        check.read(r);
        if(check.getID()==id)
        {
            r.seek((long)(id-1)*Student.size());
            s.write(r);
            System.out.println("\nStudent updated.");
        }
        else
            System.out.println("\nStudent does not yet exist. Please add student.");
    }
    //Desc:   Displays the ID, name, age, and major of the student given ID
    //Input:  User enters the ID of the student they wish to be displayed
    //Output: The ID, name, age, and major of the student, or a message telling
    //        the user that the student does not exist
    public static void displayStudent(RandomAccessFile r) throws IOException
    {
        System.out.print("\nEnter the ID of the student you wish to be displayed: ");
        int id = keyboard.nextInt();
        r.seek((long)(id-1)*Student.size());
        Student s = new Student();
        s.read(r);
        if(s.getID()==id)
        {
            System.out.println("\nID: " + s.getID());
            System.out.println("Name: " + s.getName());
            System.out.println("Age: " + s.getAge());
            System.out.println("Major: " + s.getMajor());
        }
        else
            System.out.println("\nStudent does not exist.");
    }
    //Desc:   Displays all students in the database
    //Output: Displays the ID, name, age, and major of all students in the database
    public static void displayAllStudents(RandomAccessFile r) throws IOException
    {
        Student s = new Student();
        for(int i=1; i<=100; ++i)
        {
            r.seek((long)(i-1)*Student.size());
            s.read(r);
            if(s.getID()>0 && s.getID()<101)
            {
                System.out.println("\nID: " + s.getID());
                System.out.println("Name: " + s.getName());
                System.out.println("Age: " + s.getAge());
                System.out.println("Major: " + s.getMajor());
            }
        }
    }
}