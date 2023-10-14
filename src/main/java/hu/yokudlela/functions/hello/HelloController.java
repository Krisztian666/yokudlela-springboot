package hu.yokudlela.functions.hello;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@CrossOrigin//(origins = {"http://localhost:8080","http://localhost:4200"})
@Slf4j
public class HelloController {

    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Query data by request param", description = "/getNameFromQuery?fname=..&lname=..")
    @GetMapping(path = "/getNameFromQuery", produces = MediaType.APPLICATION_JSON_VALUE)
    public Name getQuery(
            @Parameter(description = "First Name") @RequestParam("fname") String fName,
            @Parameter(description = "Last Name") @RequestParam("lname") String lName){
            return Name.builder().lname(lName).fname(fName).build();
    }
    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Query data by path variable", description = "/getNameFromQuery/{fname}/{lname}")
    @GetMapping(path = "/getNameFromPath/{fname}/{lname}")
    public Name getPath(
            @Parameter(description = "First Name") @PathVariable("fname") String fName,
            @Parameter(description = "Last Name") @PathVariable("lname") String lName){
        return Name.builder().lname(lName).fname(fName).build();
    }

    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Data entry")
    @PostMapping(path = "/postName")
    public void post(@RequestBody Name pName){
        log.info(pName.getFname().concat(" ").concat(pName.getLname()));
    }

    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Modification of data parts")
    @PatchMapping(path = "/patchName")
    public void patch(@RequestBody Name pName){
        log.info(pName.getFname().concat(" ").concat(pName.getLname()));
    }

    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Modification of complete data")
    @PutMapping(path = "/putName")
    public void put(@RequestBody Name pName){
        log.info(pName.getFname().concat(" ").concat(pName.getLname()));
    }

    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Lekérdezés object alapján")
    @GetMapping(path = "/getObject")
    public Name getObject(Name pName){
        return pName;
    }
    @ApiResponses({
            @ApiResponse(description = "Successful request",responseCode = "200")
    })
    @Operation(summary = "Törlés")
    @DeleteMapping(path = "/deleteObject")
    public void delete(Name pName){
        log.info(pName.getFname().concat(" ").concat(pName.getLname()));
    }
}
