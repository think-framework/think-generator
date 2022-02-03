package io.github.thinkframework.swing.core.component.frame.about;

import io.github.thinkframework.GeneratorSwingApplication;
import io.github.thinkframework.boot.config.GeneratorContextLoader;
import io.github.thinkframework.swing.core.component.frame.GeneratorAboutFrame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorSwingApplication.class)
@ContextConfiguration(loader = GeneratorContextLoader.class)
public class GeneratorAboutFrameTest {

    @Autowired
    private GeneratorAboutFrame generatorAboutFrame;

    @Test
    public void getModel() {
        Assert.assertFalse(generatorAboutFrame.isVisible());
    }
}
