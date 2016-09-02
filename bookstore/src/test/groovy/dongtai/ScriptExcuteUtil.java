package dongtai;

import org.junit.Ignore;
import org.junit.Test;

import javax.script.ScriptEngineManager;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class ScriptExcuteUtil {

    public ScriptExcuteUtil() {

    }
    @Test
    @Ignore
    public void demo() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        try {
            System.out.println("calling groovy from java start");
            engine.put("name", "VerRan");
            engine.eval("println \"${name}\"+\"你好\";name=name+'！'");
            System.out.println(engine.get("name"));
            System.out.println("calling groovy from java end");

            engine.eval("");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}

