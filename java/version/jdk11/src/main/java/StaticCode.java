import net.bingosoft.ufs.client.preview.UfsPreviewClientImpl;

import java.util.*;

/**
 * @author wethura
 * @date 2021/2/1 下午5:59
 */
public class StaticCode {
    public static void main(String[] args) {
        final List<Author> wethura = new ArrayList<Author>() {
            {
                add(new Author() {
                    {
                        setBirthday(new Date());
                        setName("wethura");
                    }

                    @Override
                    public String toString() {
                        return "classname{" +
                                "name='" + getName() + '\'' +
                                ", birthday=" + getBirthday() +
                                '}';
                    }
                });
            }
        };

        System.out.println(wethura);
    }

    public static class Author {
        private String name;
        private Date birthday;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }
}
