package io.github.thinkframework.generator.provider;

import io.github.thinkframework.generator.context.GeneratorContext;
import io.github.thinkframework.generator.context.GeneratorProperties;
import io.github.thinkframework.generator.lang.impl.ClazzImpl;
import io.github.thinkframework.generator.lang.reflect.impl.ClazzFieldImpl;
import io.github.thinkframework.generator.provider.adapter.TableClassAdapter;
import io.github.thinkframework.generator.provider.adapter.TableClassBuild;
import io.github.thinkframework.generator.sql.TableBuilder;
import io.github.thinkframework.generator.sql.TableFactory;
import io.github.thinkframework.generator.sql.model.Table;
import io.github.thinkframework.generator.util.StringUtils;
import io.github.thinkframework.generator.util.TypesUtils;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 适配器
 * @author lixiaobin
 * @since 2017/3/24
 */
public class TableGeneratorProvider implements GeneratorProvider, Ordered {

    @Override
    public GeneratorProperties build(GeneratorProperties generatorProperties){
        Map result = new HashMap();
        TableFactory tableFactory = new TableFactory(GeneratorContext.get().getBean(GeneratorContext.get().getDastSourceName(), DataSource.class));
        String tableName = generatorProperties.getProperties().get("tableName").toString();
        //设置表的属性
        Table table = new TableBuilder()
                .addTable(tableFactory.getTable(tableName))
                .addColumn(tableFactory.getColumns(tableName))
                .addPrimaryKey(tableFactory.getPrimaryKeys(tableName))
                .addIndexInfo(tableFactory.getIndexInfo(tableName))
//                .addExportedKey(tableFactory.getExportedKeys(tableName))
//                .addImportedKey(tableFactory.getImportedKeys(tableName))
                .build();

        //适配器,同时提供表和类的字段
        TableClassAdapter tableClassAdapter = new TableClassAdapter();
        tableClassAdapter.table(table);
        tableClassAdapter.clazz(new TableClassBuild(generatorProperties).buildClass(table));
        result.put("table",tableClassAdapter);
        result.put("clazz",tableClassAdapter);

        //根据下划线拆分
        String[] prefixs = generatorProperties.getProperty("prefix", "").split(",");
        for (String prefix: prefixs) {
            if(tableName.toUpperCase().startsWith(prefix)){
                tableName = tableName.toUpperCase().replaceFirst(prefix,"");
                break;
            }
        }

        result.put("className",tableClassAdapter.getClazz().getSimpleName());

        //全小写,JavaScript需要
        result.put("className_lower_case",tableName.toLowerCase());
        result.put("className-lower-case",tableName.replaceAll("_","-").toLowerCase());
        //空格拆分的单词,国际化需要
        result.put("className_space", StringUtils.classNameWithSpace(tableName));

        Optional.ofNullable(table.getPrimaryKey())
            .ifPresent(primaryKey -> {//有主键的话
                table.getColumns()
                .stream().filter(column -> primaryKey.getColumnName().equals(column.getColumnName()))
                .findFirst().ifPresent(column -> {
                    result.put("id", new ClazzFieldImpl(StringUtils.fieldName(column.getColumnName()), new ClazzImpl(TypesUtils.dataType(column.getDataType()))));
                });
        });
        generatorProperties.getProperties().putAll(result);
        return generatorProperties;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}