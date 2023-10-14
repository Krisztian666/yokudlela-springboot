package hu.yokudlela.functions.table;

import hu.yokudlela.functions.table.models.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController("table")
@RequestMapping("/table")
@Validated
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

}
