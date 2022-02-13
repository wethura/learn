//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.attribute.FileAttribute;
//
///**
// * @author wethura
// * @date 9/2/21 6:21 PM
// */
//public class FileSymbolicLink {
//    public static void main(String[] args) throws IOException {
//        Files.createSymbolicLink(new File("/home/sola/Workspaces/Project/java/learn/java/8/target/link").toPath(),
//                Paths.get("./classes"),
//                new FileAttribute() {
//                    @Override
//                    public String name() {
//                        return "--override";
//                    }
//
//                    @Override
//                    public Object value() {
//                        return null;
//                    }
//                });
//    }a
//}
