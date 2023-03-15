package sjc;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

import static java.util.Objects.requireNonNull;

public class JavaSource extends SimpleJavaFileObject {

    private final String code;

    public JavaSource(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = requireNonNull(code, "code must not be null");
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

}
