# yokudlela-springboot  01

Asztalkezelés és foglalás Rest API lokális H2 adatbázissal, validációval és tisztességes hibakezeléssel

Valósítsuk meg azt az alkalmazást amely lehetőséget biztosít egy étterem számára 
* asztalok felvitelére,
  * Minden asztal rendelkezik egy egyedi névvel, nem vihető fel olyan új asztal aminek neve már létezik az étteremben
* meglévő asztalok törlésére,
  * csak olyan asztal törölhető amelyen nincs és nem is volt foglalás 
* asztalok vendégek számára foglalható és nem foglaható státusz kezelésére
* vendégek számára foglalás megvalósítására
  * egy vendég neve elérhetősége megadásával adott időintervallumra megadott ülőhelyet foglal, a rendszer automatikusan határozza meg hogy ezen ülőhelyeket hány és melyik asztalokkal lehet biztosítani (nem az optimalizáció a cél) 
* Meglévő foglalások törlésére
  * ha nem érvényes foglalást szeretne törölni akkor jelezzük vissza 

## Adatbázis kezelés
* [Spring data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html)
* [Jakarta Persistence](https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html)
* [Példa](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
```
  
## Validáció
* [Jakarta Bean Validation](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html)
* [Spring validation](https://spring.io/guides/gs/validating-form-input/)
* [Példa](https://www.baeldung.com/spring-boot-bean-validation)
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
            <version>3.0.2</version>
        </dependency>
```
## ObjectMapper
* [tool page](https://modelmapper.org/)
```
  <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.1.1</version>
  </dependency>
```
