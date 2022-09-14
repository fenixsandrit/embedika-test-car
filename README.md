# embedika-test-car
Тестовое задание в компанию эмбедика(Справочник автомобилей)<br />
Реализован сервис справочник автомобилей с хранением данных в PostgreSQL, дополнительно добавил Dockerfile, docker-compse; Добавил Unit Тесты, и небольшое логирование в консоль.<br />
# API:<br />
вывод списка: GET METHOD (http://localhost:8080/car) <br />
добавления автомобиля: POST METHOD (http://localhost:8080/car) Пример JSON:<br />
{<br />
    "number": "AA999A92",<br />
    "model": "toyouta",<br />
    "color": "purple",<br />
    "year": 2000<br />
}<br />
удаление автомобиля: DELETE METHOD (http://localhost:8080/car/id) <br />
вывод статистики: GET METHOD (http://localhost:8080/car/statistic) <br />

# Запуск приложения:
mvn package<br />
docker-compose up
