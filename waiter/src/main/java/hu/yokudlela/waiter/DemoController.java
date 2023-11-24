package hu.yokudlela.waiter;

import hu.yokudlela.table.clients.invoker.ApiException;
import hu.yokudlela.table.clients.model.TableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private TableClientService service;

    @GetMapping(path = "post")
    public TableEntity post() throws ApiException {
        return service.api.getById(new Long(1));
    }
}
