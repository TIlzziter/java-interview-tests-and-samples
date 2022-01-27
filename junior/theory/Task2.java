//Какой будет результат выполнения метода main?

public abstract class AbstractClass {

    public AbstractClass() {
        System.out.println("I am abstract!");
    }
}

class DemoClass extends AbstractClass {

    public DemoClass() {
        System.out.println("I am demo!");
    }
}

class Main {
    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
    }
}
