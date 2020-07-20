package com.marstalk.strace;

/**
 * 如果是操作对象呢，会调动操作系统函数吗？
 * write(1, "begin to create object: Dog()", 29) = 29
 * stat("/root/strace/strace-object/StraceObject$Dog.class", {st_mode=S_IFREG|0644, st_size=705, ...}) = 0
 * open("/root/strace/strace-object/StraceObject$Dog.class", O_RDONLY) = 5
 * fstat(5, {st_mode=S_IFREG|0644, st_size=705, ...}) = 0
 * stat("/root/strace/strace-object/StraceObject$Dog.class", {st_mode=S_IFREG|0644, st_size=705, ...}) = 0
 * read(5, "\312\376\272\276\0\0\0004\0\"\n\0\5\0\31\t\0\4\0\32\t\0\4\0\33\7\0\35\7\0 \1"..., 705) = 705
 * close(5)                                = 0
 * write(1, "finish new Dog()", 16)        = 16
 * write(1, "end to handle object: Dog()", 27) = 27
 * write(1, "begin to create object Dog(1, 'v"..., 33) = 33
 * write(1, "end to handle object Dog", 24) = 24
 */
public class StraceObject {
    public static void main(String[] args) {
        System.out.print("begin to create object: Dog()");
        Dog dog = new Dog();
        System.out.print("finish new Dog()");
        int i = dog.getAge() + 1;
        dog.setAge(i);
        System.out.print("end to handle object: Dog()");

        System.out.print("begin to create object Dog(1, 'v'");
        Dog d = new Dog(1, "v");
        d.setName("vv");
        System.out.print("end to handle object Dog");
    }
    private static class Dog{
        private int age;
        private String name;

        public Dog() {
        }

        public Dog(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

