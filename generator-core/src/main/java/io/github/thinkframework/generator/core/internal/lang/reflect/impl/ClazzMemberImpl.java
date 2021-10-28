package io.github.thinkframework.generator.core.internal.lang.reflect.impl;

import io.github.thinkframework.generator.core.internal.lang.reflect.ClazzMember;

/**
 * @author hdhxby
 * @since 2017/3/24
 */
public class ClazzMemberImpl implements ClazzMember {
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
