package hu.yokudlela.functions.reservation.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import hu.yokudlela.functions.table.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListTableToArrayString extends JsonSerializer<List<Table>> {

    @Override
    public void serialize(List<Table> tmpString,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        List<String> res = new ArrayList<>();
        for (Table t:tmpString) {
           res.add(t.getName());
        }

        jsonGenerator.writeArray(res.toArray(new String[res.size()]),0,tmpString.size());
    }
}