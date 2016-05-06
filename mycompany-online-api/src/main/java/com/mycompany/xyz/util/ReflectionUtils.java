/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.util;

import com.mycompany.xyz.exception.NotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ReflectionUtils class - Class containing some reflection utilities we can use
 * along the project.
 *
 * @author nagarajut
 */
@Component
public class ReflectionUtils {

    public final String REQUEST_FIELDS_MISMATCH = "api.common.mismatchrequest";

    @Autowired
    NotFoundException notfoundException;

    public ReflectionUtils() {
    }

    /**
     * copies mapping fields from object to too object Iterates over the fields
     * of the src object and set the values into the dest object. Note that the
     * fields in both objects should have the same name and type.
     *
     * @param <T>
     * @param <Y>
     * @param from Object we are copying from
     * @param too Object where we are copying to
     */
    public <T extends Object, Y extends Object> Y transform(T from, Y too) throws NotFoundException {

        Class<? extends Object> fromClass = from.getClass();
        Field[] fromFields = fromClass.getDeclaredFields();

        Class<? extends Object> tooClass = too.getClass();
        Field[] tooFields = tooClass.getDeclaredFields();

        if (fromFields != null && tooFields
                != null) {
            for (Field fromField : fromFields) {
                fromField.setAccessible(true);
                try {
                    // Check if that fields exists in the other class
                    Field tooField = getDeclaredField(tooClass, fromField.getName());
                    tooField.setAccessible(true);
                    if (fromField.getType().equals(tooField.getType())) {
                        tooField.set(too, fromField.get(from));
                    }
                } catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                    notfoundException.setCode(REQUEST_FIELDS_MISMATCH);
                    throw notfoundException;
                }
            }
        }

        return too;
    }

    /**
     * gets fields including inherited from super class
     *
     * @param type
     * @return List<Field>
     */
    private static List<Field> getFieldsInclInherited(Class<?> type) {
        List<Field> result = new ArrayList<>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            for (Field field : i.getDeclaredFields()) {
                if (!field.isSynthetic()) {
                    result.add(field);
                    field.setAccessible(true);
                }
            }
            i = i.getSuperclass();
        }

        return result;
    }

    /**
     * gets declared fields incl inherited
     *
     * @param clazz
     * @param fieldName
     * @return Field
     */
    private static Field getDeclaredField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> tmpClass = clazz;
        do {
            for (Field field : tmpClass.getDeclaredFields()) {
                String candidateName = field.getName();
                if (!candidateName.equals(fieldName)) {
                    continue;
                }
                field.setAccessible(true);
                return field;
            }
            tmpClass = tmpClass.getSuperclass();
        } while (clazz != null);
        throw new NoSuchFieldException();
    }

}
