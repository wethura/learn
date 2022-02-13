//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//
///**
// * @author wethura
// * @date 2020/11/22 上午4:46
// */
//@Author(name = "wethura", birthday = "9999")
//public class AuthorTester {
//
//    @Author(name = "sola", birthday = "1015")
//    private String json;
//
//    public static void main(String[] args) {
//        resolve(new AuthorTester());
//    }
//
//    private static void resolve(AuthorTester str) {
//        Class<? extends AuthorTester> clazz = str.getClass();
//
//        System.out.println(clazz.isAnnotationPresent(Author.class));
//
//        System.out.println(clazz.isAnnotationPresent(Author.class));
//
//        for (Field field : clazz.getDeclaredFields()) {
//            System.out.println(field.getName());
//            for (Annotation annotation : field.getAnnotations()) {
//                System.out.println(annotation.getClass().getName());
//            }
//
//            for (Annotation annotation : field.getDeclaredAnnotations()) {
//                System.out.println(annotation.getClass().getName());
//            }
//
//        }
//    }
//}
