//Какой будет результат выполнения метода main?

public abstract class AbstractClass {

    public OurAbstractClass() {
        System.out.println("I am abstract!");
    }
}

class DemoClass extends AbstractClass {

    public OurDemoClass() {
        System.out.println("I am demo!");
    }
}

class Main {
    public static void main(String[] args) {
        DemoClass ourDemoClass = new DemoClass();
    }
}