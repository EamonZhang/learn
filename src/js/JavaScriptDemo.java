package js;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptDemo {

	public static void main(String[] args) throws Exception {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		 //加载一个文件
		 Reader r=new
		 InputStreamReader(JavaScriptDemo.class.getResourceAsStream("my.js"));
		 //把Reader放入eval中(读者可以去API看一下,重载了很多的eval()方法)
		 engine.eval(r);
		 Invocable inv = (Invocable) engine;
		 Double value =
		 (Double)inv.invokeFunction("add",5,110);
		 System.out.println(value);

		Compilable compilable = (Compilable) engine;
		Bindings bindings = engine.createBindings(); // Local级别的Binding
		String script = "function add(op1,op2){return op1+op2} add(a, b)"; //
		// 定义函数并调用
		CompiledScript JSFunction = compilable.compile(script); // 解析编译脚本函数
		bindings.put("a", 7);
		bindings.put("b", 2); 
		// 通过Bindings加入参数
		Object result = JSFunction.eval(bindings);
		System.out.println(result); // 调用缓存着的脚本函数对象，Bindings作为参数容器传入
	}
}
