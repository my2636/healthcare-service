# Сервис медицинских показаний

В классе Main, создаются несколько тестовых данных о пациентах и записываются в файл (репозиторий)

Написать тесты для проверки класса MedicalServiceImpl, сделав заглушку для класса PatientInfoFileRepository, который он использует
 - Проверить вывод сообщения во время проверки давления checkBloodPressure
 - Проверить вывод сообщения во время проверки температуры checkTemperature
 - Проверить, что сообщения не выводятся, когда показатели в норме.

Реализация
Склонируйте удаленный репозиторий сервиса https://github.com/neee/healthcare-service или сделайте его fork (предпочтительно) или скачайте к себе в виде архива;
Подключите к maven-проекту зависимости junit и mockito (их нужно добавить в файл pom.xml);
Создайте класс для тестов в папке src/test/java (можете также создать подпапки в соответствии с package'ом класса, который вы будете тестировать);
Создайте тесты в соответствии с задачей (для сервиса MedicalServiceImpl нужно обязательно создать заглушки (mock) в виде PatientInfoFileRepository и SendAlertService) - сделать минимум 3 unit-теста;
Для заглушки класса SendAlertService нужно проверить вызов метода send (можно воспользоваться ArgumentCaptor и verify из библиотеки mockito)
Отправьте задачу на проверку.