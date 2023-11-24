package hu.yokudlela.waiter;

import hu.yokudlela.table.clients.invoker.ApiClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import hu.yokudlela.table.clients.api.*;

@Service
@Slf4j
@Getter
public class TableClientService {

    private String path;

    public TableControllerApi api;

    public TableClientService(@Value("${client.table}") String path){
        this.path = path;
        ApiClient c = new ApiClient();
        c.setBasePath(path);
        this.api = new TableControllerApi(c);
        System.out.println("-------------"+path);
    }

}
