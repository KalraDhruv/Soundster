import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertWithMessage;

public interface Deque<T> {
    void addFirst(T x);

    void addLast(T x);

    List<T> toList();

    boolean isEmpty();

    int size();

    T removeFirst();

    T removeLast();

    T get(int index);

    T getRecursive(int index);
}