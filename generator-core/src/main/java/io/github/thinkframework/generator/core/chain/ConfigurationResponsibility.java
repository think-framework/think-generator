package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.util.BeanUtils;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * 解析配置文件
 * 默认
 *
 * @author hdhxby
 * @since 2017/3/24
 */
public class ConfigurationResponsibility extends AbstractResponsibility implements GeneratorResponsibility {

    @Override
    public GeneratorContext apply(GeneratorContext generatorContext) {
        BeanUtils.describe(generatorContext.getConfiguration()).forEach((key, value) -> {
            //设置GeneratorConfiguration属性
            if(key == null || value == null){
                return; // 忽略空字段
            }

            generatorContext.getProperties().put(key, value);
            if (value instanceof String) {//转换成路径
                generatorContext.getProperties().put(key+"_"+"path", value.toString().replace(".", Matcher.quoteReplacement(File.separator)));
            }
        });
        return generatorContext;
    }

}
