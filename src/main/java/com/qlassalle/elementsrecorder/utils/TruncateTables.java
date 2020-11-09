package com.qlassalle.elementsrecorder.utils;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@ExtendWith(TruncateTablesExtension.class)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TruncateTables {
}
