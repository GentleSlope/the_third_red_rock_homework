import java.io.*;
import java.util.*;

class Stduent implements Comparable<Stduent> ,Externalizable {
    private String name;
    private long stuNum;
    private long classNum;
    private int age;
    private String sex;
    public  Stduent(){};
    public Stduent(String name, long stuNum, long classNum, int age, String sex) {
        this.name = name;
        this.stuNum = stuNum;
        this.classNum = classNum;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "\t" + "学号：" + this.stuNum + " 班级：" + this.classNum + " 姓名:" + this.name + " 性别：" + this.sex + " 年龄:" + this.age + "\n";
    }

    public int compareTo(Stduent stu) {
        if (this.stuNum > stu.stuNum) {
            return 1;
        } else if (this.stuNum < stu.stuNum) {
            return -1;
        } else {
            return 0;
        }
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException,ClassNotFoundException{
        this.age = in.readInt();
        this.stuNum = in.readLong();
        this.classNum = in.readLong();
        this.sex = (String)in.readObject();
        this.name = (String)in.readObject();
    }
    public void writeExternal(ObjectOutput out) throws IOException{
         out.writeInt(this.age);
         out.writeLong(this.stuNum);
         out.writeLong(this.classNum);
         out.writeObject(this.sex);
         out.writeObject(this.name );
    }

}
class stuClass {
    public static void main(String[] args) {
        List<Stduent>stuList = new ArrayList<Stduent>();
        stuList.add(new Stduent("黄飞鸿",2017212675,8,18,"男"));
        stuList.add(new Stduent("李小龙",2017212671,8,18,"男"));
        stuList.add(new Stduent("爬山虎",2017212672,8,18,"男"));
        stuList.add(new Stduent("九头蛇",2017212673,8,18,"男"));
        stuList.add(new Stduent("一枝花",2017212674,8,18,"女"));
        Collections.sort(stuList);
        System.out.println("普通List输出\n"+stuList);
        Iterator<Stduent> iter = stuList.iterator();
        System.out.println("迭代器遍历");
        while (iter.hasNext()){
            Stduent str = iter.next();
            System.out.println(str);
        }
        System.out.println("foreach遍历");
        for (Stduent sty:stuList
             ) {
            System.out.println(sty);
        }
        Collections.reverse(stuList);
        System.out.println("倒序输出List\n"+stuList);
    }
}
class SerStu {
    public static void main(String[] args) throws Exception {
        Stduent stu[] = {new Stduent("黄飞鸿", 2017212675, 8, 18, "男"),
                new Stduent("李小龙", 2017212671, 8, 18, "男"),
                new Stduent("爬山虎", 2017212672, 8, 18, "男"),
                new Stduent("爬山虎", 2017212672, 8, 18, "男"),
                new Stduent("九头蛇", 2017212673, 8, 18, "男"),
                new Stduent("一枝花", 2017212674, 8, 18, "女")};
        ser(stu);
        Object o[] = dser();
        for (int i = 0; i < o.length; i++) {
            Stduent stu2 = (Stduent) o[i];
            System.out.println(stu2);
        }
    }

    public static void ser(Object obj[]) {
        try {
            File f = new File("D:\\Game\\class\\studentImformations.txt");

        OutputStream out = new FileOutputStream(f);
        ObjectOutputStream oss = new ObjectOutputStream(out);
        oss.writeObject(obj);
        oss.close(); }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Object[] dser() throws Exception {
            File f = new File("D:\\Game\\class\\studentImformations2.txt");
            InputStream input = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(input);
            Object obj[] = (Object[]) ois.readObject();
            ois.close();
            return obj;


    }
}