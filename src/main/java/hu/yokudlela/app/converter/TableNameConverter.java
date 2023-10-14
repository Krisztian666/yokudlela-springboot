package hu.yokudlela.app.converter;

import hu.yokudlela.functions.table.models.TableEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class TableNameConverter implements Converter<TableEntity, String> {
    @Override
    public String convert(MappingContext<TableEntity, String> context) {
        if (context.getSource() == null) {
            return "";
        }
        return context.getSource().getName();
    }
}