import sjc.SJC;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Clazz {

    private static int variable = 12;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var clazz = SJC.getClassFromSourceCode("TestClass", """
                public class TestClass implements ExternalClass {
                                
                    public void doSomething() {
                        try {
                            System.out.println("Hello, world!");
                            var field = Clazz.class.getDeclaredField("variable");
                            field.setAccessible(true);
                            field.set(null, 19);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                                
                }
                """);

        ExternalClass externalClass = (ExternalClass) clazz.getDeclaredConstructor().newInstance();
        externalClass.doSomething();

        System.out.println(variable);
    }

}