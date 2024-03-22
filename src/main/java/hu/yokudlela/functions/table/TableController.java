package hu.yokudlela.functions.table;

import hu.yokudlela.functions.table.models.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("table")
@RequestMapping("/table")
@Validated
@CrossOrigin//(origins = {"http://localhost:8080","http://localhost:4200"})
public class TableController {
    @Autowired
    private TableRepository repoTable;
    @PostMapping("")
    @Operation(summary = "Save Table", description = "Save table to database")
    public void save(@RequestBody TableEntity pdata){
        this.repoTable.save(pdata);
    }

    @Validated(TableAvailableIsFalseGroup.class)
    @PatchMapping("/enable")
    @Operation(summary = "Enable Table by Id", description = "Enable table from database by id")
    public TableEntity enable(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.TRUE);
        return repoTable.save(tbl);
    }

    @Validated(TableAvailableIsTrueGroup.class)
    @PatchMapping("/disable")
    @Operation(summary = "Disable Table by Id", description = "Disable table from database by id")
    public TableEntity disable(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.FALSE);
        return repoTable.save(tbl);
    }

    @GetMapping("/get")
    @Operation(summary = "Get Table by Id", description = "Get table from database by id")
    public TableEntity get(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        return tbl;
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get All Tables", description = "Get all tables from database")
    public List<TableEntity> get(){
        return repoTable.getAllByAvailable(true);
    }

    @DeleteMapping("")
    @Operation(summary = "Delete Table by Id", description = "Delete table from database by id")
    public void delete(TableIdRequest pdata){
        this.repoTable.deleteById(pdata.getId());
    }

}
