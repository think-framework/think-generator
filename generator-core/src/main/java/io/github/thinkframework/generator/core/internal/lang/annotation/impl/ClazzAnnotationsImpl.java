package io.github.thinkframework.generator.core.internal.lang.annotation.impl;

import io.github.thinkframework.generator.core.internal.lang.annotation.ClazzAnnotation;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 有序集合
 *
 * @author hdhxby
 * @since 2017/5/25.
 */
public class ClazzAnnotationsImpl extends ArrayList<ClazzAnnotation> {

    public ClazzAnnotationsImpl() {
        super();
    }

    @Override
    public String toString() {
        Iterator<ClazzAnnotation> it = iterator();

        StringBuilder sb = new StringBuilder();
        ClazzAnnotation e;
        while (it.hasNext()) {
            e = it.next();
            sb.append(e.toString());
            if (it.hasNext()) {
                sb.append(System.getProperty("line.separator")).append("\t");
            }
        }
        return sb.toString();
    }
}
