// Какой будет результат выполнения метода main?

class Main {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("one and a half");
        stringList.add("two");
        stringList.add("two and a half");
        stringList.add("three and a half");

        System.out.println("Before " + stringList);

        Iterator<String> stringIterator = stringList.iterator();
        while (stringIterator.hasNext()) {
            String next = stringIterator.next();
            if (next.equals("two and a half")) {
                stringList.add("three");
            }
        }
        System.out.println("After " + stringList);
    }
}

// Выведется заполненный в начале список, после чего выкинется ошибка (ConcurrentModificationException), потому что
// здесь есть попытка модификации списка, по которому идет итерация (следит за этим сам список, итератор получен из него)
// Чтобы все-таки изменить коллекции, по которым идет итерация, надо использовать потокобезопаные аналоги коллекций
// Для удаления элемента же достаточно использовать метод итератора remove()