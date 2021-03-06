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
        DemoClass DemoClass = new DemoClass();
    }
}

// Будет выведено "I am abstract!", а на следующей строке "I am demo!". Нельзя создать экзеипляр абстрактного класса,
// но дочерний класс всегда вызовет сначала конструктор родительского класса вне зависимости от обстоятельста