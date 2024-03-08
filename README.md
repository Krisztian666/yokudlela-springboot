# 01 - Asztalkezelés és foglalás Rest API (lokális H2 adatbázissal, validációval, hibakezeléssel)

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
[youtube](https://youtu.be/4oMU7iLa-_w) 
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
* [Spring data JPA -doc ](https://docs.spring.io/spring-data/jpa/docs/current/reference/html)
* [Spring data query methods -doc](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)
* [Jakarta Persistence - doc](https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html)
* [Example - baeldung](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)

  
## Validáció
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
* [Jakarta Bean Validation -doc](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html)
* [Jakarta Bean Validation -api](https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary)
* [Spring validation -doc](https://spring.io/guides/gs/validating-form-input/)
* [UseCase - ismételhető annotációk](https://blog.hackajob.com/type-and-repeatable-annotations-explained/)
* [Example - baeldung](https://www.baeldung.com/spring-boot-bean-validation)
* [Example - Tom Hombergs](https://reflectoring.io/bean-validation-with-spring-boot/)
* 

## Adatok másololás objektum lépdányok között
```
  <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.1.1</version>
  </dependency>
```
* [tool page](https://modelmapper.org/)


# Feladat
Valósítsd meg azt a pincér funkcionalitást mely során
* Lehetőség van adott asztalhoz az étel és ital lap alapján tetszőleges mennyiségú meglévő tétel rendelésére 
  * Az elemekhez lehessen megjegyzést tenni pl: sósan, jól átsütve, jéggel, szobahőmérségleten stb kéri a vendég.
  * Egy tételből több is rendelhető
* legyen lehetőség az egyes rendelt elemek státuszának lekérdezésére
* Fizetéskor autómatikusan megtörténik az asztal takarjtása
  * Fizetni lehet több részletben pl valaki a két kólát és a hamburgert fizeti csak
* Legyen lehetőség az összes rendelés áttekintésére
      
