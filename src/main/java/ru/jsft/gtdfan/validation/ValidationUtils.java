package ru.jsft.gtdfan.validation;

import lombok.experimental.UtilityClass;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import ru.jsft.gtdfan.error.IllegalRequestDataException;
import ru.jsft.gtdfan.model.BaseEntity;

import java.util.Optional;

@UtilityClass
public class ValidationUtils {

    public static void checkNew(@NonNull BaseEntity bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id = null)");
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> T checkEntityNotNull(@NonNull Optional<T> obj, long id, Class<T> clazz) {
        if (obj.isEmpty()) {
            throw new IllegalRequestDataException(String.format("%s with id = %d not found", clazz.getSimpleName(), id));
        }
        return obj.get();
    }

    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        // https://stackoverflow.com/a/65442410/548473
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }
}
