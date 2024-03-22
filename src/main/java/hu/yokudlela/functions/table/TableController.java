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
@CrossOrigin
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
    @Operation(summary = "Enable Table", description = "Enable table to use")
    public TableEntity enable(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.TRUE);
        return repoTable.save(tbl);
    }



    @Validated(TableAvailableIsTrueGroup.class)
    @PatchMapping("/disable")
    @Operation(summary = "Disable Table", description = "Disable table to use")
    public TableEntity disable(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.FALSE);
        return repoTable.save(tbl);
    }


    @DeleteMapping("")
    @Operation(summary = "Delete Table", description = "Delete table from database")
    public void delete(TableIdRequest pdata){
        this.repoTable.deleteById(pdata.getId());
    }

    @GetMapping("/cannotbeused")
    @Operation(summary = "Get Tables that cannot be used", description = "Get tables that cannot be used")
    public List<TableEntity> getNotUse(){
        return this.repoTable.findByAvailable(false);
    }
    @GetMapping("/usable")
    @Operation(summary = "Get Tables that can be used", description = "Get tables that can be used")
    public List<TableEntity> getUse(){
        return this.repoTable.findByAvailable(true);
    }

}
