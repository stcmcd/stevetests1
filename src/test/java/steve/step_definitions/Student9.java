package steve.step_definitions;

class Student9{
// will be created when 'new Student9' is called
    int rollno;
    String name;

// will be created when static void change() is called
    static String college = "ITS";

    public static final String steve;

// will be called when before the first line of public static void main is called
    static {
        steve = "mcdonald";
    }

    static void change(){
        college = "BBDIT";
    }

    // Constructor that is run when 'new Student9' is called
    Student9(int r, String n){
        rollno = r;
        name = n;
    }

    void display (){System.out.println(rollno+" "+name+" "+college);}

    public static void main(String args[]){
// calls a static method nothing to do with a class
        Student9.change();

        Student9 s1 = new Student9 (111,"Indian");
        Student9 s2 = new Student9 (222,"American");
        Student9 s3 = new Student9 (333,"China");

        s1.display();
        s2.display();
        s3.display();
    }
}
