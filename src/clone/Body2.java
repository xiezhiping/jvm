package clone;

class Student{
    String grade;
    public Student(){
    }
    public void  setGrade(String a){
        this.grade=a;
    }
}
class  Person2 implements Cloneable{
    int age;
    String name;
    Student  grades;
    public void setGrades(Student gra){
        this.grades=gra;
    }
    public  Person2(int a, String b,Student gra){
        age=a;
        name=b;
        this.grades=gra;
    }
    public Person2(Person2 p){
        this.age=p.age;
        this.name=p.name;
    }
@Override
public Object clone() {
    Person2 per=null;    
    try {
            per=(Person2)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return  per;
}
}
public class Body2 {
    public static void main(String[] args){
        Student stu=new Student();
        stu.setGrade("100");
        Person2 p1=new Person2(10,"wang",stu);
        Person2 p2 = (Person2)  p1.clone();
        System.out.println(p1.grades==p2.grades);
        // 检验子对象的值有没有被修改
        System.out.println("*******检验子对象的值有没有被修改********");
        stu.setGrade("200");
        System.out.println(p2.grades.grade);
        System.out.println(p1.grades.grade);

    }
}
