package hu.yokudlela.app.converter;

import hu.yokudlela.functions.table.Table;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class TableNameConverter implements Converter<Table, String> {
    @Override
    public String convert(MappingContext<Table, String> context) {
        if (context.getSource() == null) {
            return "";
        }
        return context.getSource().getName();
    }
};