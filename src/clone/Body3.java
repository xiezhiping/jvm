package clone;
class Student1 implements Cloneable{
    String grade;
    public Student1(){
 
    }
    public void  setGrade(String a){
        this.grade=a;
    }
    public Object clone(){
        Student1 stu=null;
        try {
            stu=(Student1)super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stu;
    }
}
class  Person3 implements Cloneable{
    int age;
    String name;
    Student1  grades;
    public void setGrades(Student1 gra){
        this.grades=gra;
    }
    public  Person3(int a, String b,Student1 gra){
        age=a;
        name=b;
        this.grades=gra;
    }
    public Person3(Person3 p){
        this.age=p.age;
        this.name=p.name;
        this.grades=p.grades;
    }
@Override
public Object clone() {
    Person3 per=null;    
    try {
            per=(Person3)super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    per.grades=(Student1)grades.clone();//深拷贝：就是对引用的对象单独拷贝一次哦。
        return  per;
}
 
}
public class Body3 {
    public static void main(String[] args){
        // TODO Auto-generated method stub
        Student1 stu=new Student1();
        stu.setGrade("100");
        Person3 p1=new Person3(10,"wang",stu);
        Person3 p2 = (Person3)  p1.clone();
        System.out.println(p1.grades==p2.grades);
        // 检验子对象的值有没有被修改
        System.out.println("*******检验子对象的值有没有被修改********");
        stu.setGrade("200");
        System.out.println(p2.grades.grade);
        System.out.println(p1.grades.grade);

    }
 
}
