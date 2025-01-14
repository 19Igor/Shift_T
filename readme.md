# Как запустить
1. Склонируйте репозиторий на собственную машину.
2. Перейдите в корневую папку проекта.
3. Запустите следующую команду в терминале:
```
java -jar target/util-1.0-SNAPSHOT-jar-with-dependencies.jar -f -p sample- in1.txt in2.txt
```
После выполнения этой команды результат появится в консоли, а файлы создадутся в папке "Results".

# Стек
Java 18

Maven 3.9.6
# Зависимости
[log4j -- 2.20.0](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.20.0)

[lombok -- 1.18.36](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.36)

[junit -- 5.11.4](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine)
