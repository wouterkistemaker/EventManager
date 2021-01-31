package me.wouterkistemaker.eventmanager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Simple annotation to help indicating
 * which methods are able to invoke
 * on a called {@link Event}
 * <p>
 * This annotation has no other use
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTag {
}
