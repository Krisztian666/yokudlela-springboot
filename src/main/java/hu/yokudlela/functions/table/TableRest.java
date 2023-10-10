package hu.yokudlela.functions.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("table")
@RequestMapping("/table")
public class TableRest {
    @Autowired
    private TableRepository tableService;
    @PostMapping("/save")
    public void save(@RequestBody Table pdata){
        this.tableService.save(pdata);
    }
}
