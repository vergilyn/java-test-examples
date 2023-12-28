package com.vergilyn.examples.mockito;

import com.google.common.collect.Lists;
import com.vergilyn.examples.mockito.argument.ArgumentCaptorTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author vergilyn
 * @since 2023-08-31
 */
public class EnhanceMethodTests {

    /**
     * 目的：捕获 {@link TestClass#method(String, Integer)} 的入参
     *
     * @see ArgumentCaptorTest
     */
    @Test
    void test_byProxy(){
        List<Object[]> captors = Lists.newArrayList();

        TestClass mockObject = Mockito.mock(TestClass.class);
        Mockito.doAnswer(invocation -> {
            Object[] parameters = new Object[2];
            parameters[0] = invocation.getArgument(0, String.class);
            parameters[1] = invocation.getArgument(1, Integer.class);
            captors.add(parameters);

            return null;
        }).when(mockObject).method(any(), any());

        mockObject.method("A", 1);
        mockObject.method("B", 2);
        mockObject.method("C", 3);

        assertThat(captors).hasSize(3)
                .contains(new Object[]{"A", 1}, atIndex(0))
                .contains(new Object[]{"B", 2}, atIndex(1))
                .contains(new Object[]{"C", 3}, atIndex(2));
    }

    @Test
    void test_byArgumentCaptor(){
        ArgumentCaptor<String> strCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);

        TestClass mockObject = Mockito.mock(TestClass.class);

        mockObject.method("A", 1);
        mockObject.method("B", 2);
        mockObject.method("C", 3);

        verify(mockObject, times(3)).method(strCaptor.capture(), intCaptor.capture());

        assertThat(strCaptor.getAllValues()).containsExactly("A", "B", "C");
        assertThat(intCaptor.getAllValues()).containsExactly(1, 2, 3);
    }



    private static class TestClass {

        public void method(String str, Integer index){
            // do something
        }
    }
}
