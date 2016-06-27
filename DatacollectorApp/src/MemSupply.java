import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

import quarks.function.Supplier;


public class MemSupply<Long> implements Supplier<Long>{
	OperatingSystemMXBean operatingSystemMXBean =null;
	
	public MemSupply(){
		// TODO Auto-generated constructor stub
		operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	}
	 
	@Override
	public Long get() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("getFreePhysicalMemorySize") 
		        ) {
		            Long value;
		        try {
		            value = (Long) method.invoke(operatingSystemMXBean);
		            System.out.println(method.getName() + " = " + value);
			        return value;
		        } catch (Exception e) {
		            System.out.println("problem");
		        } // try
		       
		    } // if
		  }
		return null;
	} // for

	

}
