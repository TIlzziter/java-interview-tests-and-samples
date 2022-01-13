//Что будет выведено в консоль при выполнении метода?
//Какие наблюдения можешь рассказать по этому примеру кода?

summ_nums() {
        int[] nums = {1, 2, 3};
        for ( int x = 0; x < nums.length; x++ ) {
        x += nums[x];
        }
        System.out.println(x);
}

// будет выведена ошибка, так как переменная определена внутри цикла, а обращение к ней идет вне его
// у метода не определен тип возвращаемого значения
// метод назван в нетипичном для джавы снейк-кейсе