# 02 - Docker, RDBMS, Adatbázis szerkezet és tartalom verziókezelése

Tegyük képessé az alaklamzásunkat
* psql RDBMS-sel való együtt működésre
* az alaklamzásunk autóomatikusan hozza létre vagy igény esetén módosítsa az adatbázis szerkezetét és a működéshez szükséges adatokat.
* csomagoljuk az alkalmazásznkat egy docker konténerbe
* 
## Adatbázis kezelés
```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.49</version>
        </dependency>
        <dependency>
            <groupId>org.liquibase</groupId>
            <artifactId>liquibase-core</artifactId>
            <version>4.2.2</version>
        </dependency>
```
* [Spring data JPA -doc ](https://docs.spring.io/spring-data/jpa/docs/current/reference/html)
* [Jakarta Persistence - doc](https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html)
* [Example - baeldung](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)

# Feladat
Módosítsd az alkalmazásodat a fenti leírás alapján.      
