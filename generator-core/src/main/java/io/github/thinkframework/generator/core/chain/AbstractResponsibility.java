package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;

/**
 * @author hdhxby
 * @since 2017/5/16.
 */
public abstract class AbstractResponsibility implements GeneratorResponsibility {
    protected GeneratorResponsibility before;

    public GeneratorResponsibility getBefore() {
        return before;
    }

    public void setBefore(GeneratorResponsibility before) {
        this.before = before;
    }
}
