package hu.yokudlela.functions.hello;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@CrossOrigin//(origins = {"http://localhost:8080","http://localhost:4200"})
public class HelloController {

    @GetMapping(path = "/getNameFromQuery", produces = MediaType.APPLICATION_JSON_VALUE)
    public Name getQuery(
            @Parameter(description = "First Name") @RequestParam("fname") String fName,
            @Parameter(description = "Last Name") @RequestParam("lname") String lName){
            return Name.builder().lname(lName).fname(fName).build();
    }
    @GetMapping(path = "/getNameFromPath")
    public Name getPath(
            @Parameter(description = "First Name") @RequestParam("fname") String fName,
            @Parameter(description = "Last Name") @RequestParam("lname") String lName){
        return Name.builder().lname(lName).fname(fName).build();
    }

}
