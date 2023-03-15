package sjc;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.Collections;
import java.util.stream.Collectors;

public class SJC {

    private SJC() { }

    private static final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public static Class<?> getClassFromSourceCode(String classname, String code) throws ClassNotFoundException {
        var manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));
        var sourceFiles = Collections.singletonList(new JavaSource(classname, code));

        var diagnostics = new DiagnosticCollector<>();
        var task = compiler.getTask(null, manager, diagnostics, null, null, sourceFiles);

        if (!task.call()) {
            throw new RuntimeException(diagnostics.getDiagnostics().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
        }

        return manager.getClassLoader(null).loadClass(classname);
    }

}
