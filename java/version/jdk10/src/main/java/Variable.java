/**
 * @author wethura
 * @date 2020/11/22 下午9:01
 */
public class Variable {

    static class Student {
        public void live() {
            System.out.println("Life is too hard.");
        }
    }

    public static void main(String[] args) {
        var student = new Student();
        student.live();
    }
}
