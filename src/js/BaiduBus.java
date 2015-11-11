package js;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class BaiduBus {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		// 加载一个文件
		Reader r2 = new InputStreamReader(JavaScriptDemo.class.getResourceAsStream("baidu.js"));
		// 把Reader放入eval中(读者可以去API看一下,重载了很多的eval()方法)
		engine.eval(r2);
		Invocable inv = (Invocable) engine;
		String value = (String) inv.invokeFunction("search", "沈阳", "214");
		System.out.println(value);
	}
}
