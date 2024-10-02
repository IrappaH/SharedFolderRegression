package cocoro.testng.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.xml.XmlClass;


public class MethodOrderer  implements IMethodInterceptor {
	    @SuppressWarnings("rawtypes")
		@Override
	    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
	        Map<Class, List<IMethodInstance>> mapping = new LinkedHashMap<>();
	        List<XmlClass> xmlClasses = context.getCurrentXmlTest().getXmlClasses();
	        for (XmlClass xmlClass : xmlClasses) {
	            Class<?> clazz = xmlClass.getSupportClass();
	            mapping.put(clazz, new ArrayList<IMethodInstance>());
	            for (IMethodInstance method : methods) {
	                Class<?> methodClass = method.getMethod().getTestClass().getRealClass();
	                if (mapping.containsKey(methodClass)) {
	                    List<IMethodInstance> methodInstances = mapping.get(methodClass);
	                    if (!methodInstances.contains(method)) {
	                        methodInstances.add(method);
	                    }
	                }
	                else {
	                	System.out.println("This Method Not available in the class");
	                }
	            }
	        }
	        List<IMethodInstance> returnValue = new ArrayList<>(methods.size());
	        Sorter sorter = new Sorter();
	        for (Map.Entry<Class, List<IMethodInstance>> each : mapping.entrySet()) {
	            List<IMethodInstance> methodInstances = each.getValue();
	            Collections.sort(methodInstances, sorter);
	            returnValue.addAll(methodInstances);
	        }
	        return returnValue;
	    }

	    public static class Sorter implements Comparator<IMethodInstance> {

	        @Override
	        public int compare(IMethodInstance o1, IMethodInstance o2) {
	            PriorityInClass o1Priority = getPriority(o1);
	            PriorityInClass o2Priority = getPriority(o2);
	            int priorityO1 = (o1Priority == null ? getPriorityViaTestAnnotation(o1) : o1Priority.value());
	            int priorityO2 = (o2Priority == null ? getPriorityViaTestAnnotation(o2) : o2Priority.value());
	            return (priorityO1 < priorityO2) ? -1 : ((priorityO1 == priorityO2) ? 0 : 1);
	        }

	        private PriorityInClass getPriority(IMethodInstance instance) {
	            return instance.getMethod().getConstructorOrMethod().getMethod().getAnnotation(PriorityInClass.class);
	        }

	        private int getPriorityViaTestAnnotation(IMethodInstance instance) {
	            return instance.getMethod().getPriority();
	        }
	    }
}
