package hu.yokudlela.functions.table;

import hu.yokudlela.functions.table.models.*;
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
    public void save(@RequestBody TableEntity pdata){
        this.repoTable.save(pdata);
    }

    @Validated(TableAvailableIsFalseGroup.class)
    @PatchMapping("/enable")
    public TableEntity enable(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.TRUE);
        return repoTable.save(tbl);
    }



    @Validated(TableAvailableIsTrueGroup.class)
    @PatchMapping("/disable")
    public TableEntity disable(@Valid @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.FALSE);
        return repoTable.save(tbl);
    }


    @DeleteMapping("")
    public void delete(TableIdRequest pdata){
        this.repoTable.deleteById(pdata.getId());
    }

    @GetMapping("/cannotbeused")
    public List<TableEntity> getNotUse(){
        return this.repoTable.findByAvailable(false);
    }
    @GetMapping("/usable")
    public List<TableEntity> getUse(){
        return this.repoTable.findByAvailable(true);
    }

    @GetMapping("/getById/{tid}")
    public TableEntity getById(
            @Parameter(description = "Id of Table") @PathVariable("tid") long pTid,){

        return this.repoTable.findById(pTid);
    }

}
