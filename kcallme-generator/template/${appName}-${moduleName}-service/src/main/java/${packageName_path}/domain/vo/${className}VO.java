package ${packageName}.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ${clazz.remarks}
 *
 * @author ${authorName}
 * @version 1.0.0
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Data
public class ${className}VO {
<#list clazz.fields as field>

    ${field.annotations}
    private ${field.type} ${field.name};
</#list>

}
