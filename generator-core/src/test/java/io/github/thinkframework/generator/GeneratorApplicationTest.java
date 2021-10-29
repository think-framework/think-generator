package io.github.thinkframework.generator;

import io.github.thinkframework.generator.core.AbstractGenerator;
import io.github.thinkframework.generator.core.chain.ConfigurationResponsibility;
import io.github.thinkframework.generator.core.chain.IDResponsibility;
import io.github.thinkframework.generator.core.configuration.GeneratorConfiguration;
import io.github.thinkframework.generator.core.context.GeneratorContext;
import io.github.thinkframework.generator.core.exception.GeneratorRuntimeException;
import io.github.thinkframework.generator.core.internal.TableFactory;
import io.github.thinkframework.generator.util.FreeMarkerHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.xml.DomUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 容器测试
 */
public class GeneratorApplicationTest {

    Logger logger = LoggerFactory.getLogger(GeneratorApplicationTest.class);

    private GeneratorConfiguration generatorConfiguration;

    @Before
    public void before() {
        generatorConfiguration = new GeneratorConfiguration();
        // packageName = companyName + appName + moduleName
        generatorConfiguration.setFrameName("io.github.thinkframework"); // 框架包
        generatorConfiguration.setCompanyName("companyName"); // 公司名称
        generatorConfiguration.setAppName("appName"); // 应用名称
        generatorConfiguration.setModuleName("moduleName"); // 模块名称
        generatorConfiguration.setAuthorName("authorName"); // 作者名称
        generatorConfiguration.setNamespace("namespace"); // 命名空间
        generatorConfiguration.setTemplate("template"); // 模板目录
        generatorConfiguration.setOutput("output"); // 输出目录

        generatorConfiguration.setPrefixs(List.of("t_","table_")); //表前缀

        generatorConfiguration.setIgnores(List.of("id","created_by","created_date","last_modified_by","last_modified_date","version")); // 忽略字段

        generatorConfiguration.setExtensions(List.of("java","xml")); // 文件后缀

        generatorConfiguration.setConverts(Map.of("java.sql.Types.TIMESTAMP","java.time.Instant",
                "java.sql.Types.BIGINT","java.math.BigInteger")); // 类型映射
    }

    /**
     * 通过spring初始化
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        Generator generator = new AbstractGenerator(generatorConfiguration)
                .responsibilitys(List.of(new ConfigurationResponsibility(),
                        new IDResponsibility()));

        generator.generate(() -> "helloword",
                (context -> {
                    System.out.println(new FreeMarkerHelper().string(((GeneratorContext<?>) context).getProperties(),
                            "package ${companyName}.${appName}.${moduleName}\n" +
                            "$import {frameName_path};\n" +
                                    "/**\n" +
                                    " * @author ${authorName}\n" +
                                    " */\n" +
                                    "public class {\n" +
                                    " private ${id.type} ${id.name};\n" +
                                    "}"));
                }));
    }
}
