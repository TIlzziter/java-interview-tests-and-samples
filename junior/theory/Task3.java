//Какой будет результат выполнения метода main?


class Main {
    public static void main(String[] args) {

        var stringList = new ArrayList<String>();
        stringList.add("one");
        stringList.add("one and a half");
        stringList.add("two");
        stringList.add("two and a half");
        stringList.add("three and a half");

        System.out.println("Before " + stringList);

        for (var next : stringList) {
            if (next.equals("two and a half")) {
                stringList.add("three");
            }
        }
        System.out.println("After " + stringList);
    }
}