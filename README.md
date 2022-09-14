# embedika-test-car
Тестовое задание в компанию эмбедика(Справочник автомобилей)
Реализован сервис справочник автомобилей с хранением данных в PostgreSQL, дополнительно добавил Dockerfile, docker-compse; Добавил Unit Тесты, и небольшое логирование в консоль.
API:
вывод списка: GET METHOD (http://localhost:8080/car)
добавления автомобиля: POST METHOD (http://localhost:8080/car) Пример JSON:
{
    "number": "AA999A92",
    "model": "toyouta",
    "color": "purple",
    "year": 2000
}
удаление автомобиля: DELETE METHOD (http://localhost:8080/car/id)
вывод статистики: GET METHOD (http://localhost:8080/car/statistic)
