package clone;
class  Person1 implements Cloneable{ // 实现Cloneable接口
    int age;
    String name;
    public  Person1(int a, String b){
        age=a;
        name=b;
    }
    public Person1(Person1 p){ // 用一个对象来构造另一个对象
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
public class Body1 {
    public static void main(String[] args){
        Person1 p1=new Person1(10,"wang");
        Person1 p2 = (Person1) p1.clone();
        System.out.println(p1);//输出堆内存的地址
        System.out.println(p2);
        System.out.println(p2.age);
        System.out.println(p1==p2);
    }
 
}

 
