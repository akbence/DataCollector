import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

import quarks.function.Supplier;


public class CPUSupply<Double> implements Supplier<Double>{
	OperatingSystemMXBean operatingSystemMXBean =null;
	
	public CPUSupply() {
		// TODO Auto-generated constructor stub
		operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
	}
	 
	@Override
	public Double get() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("getSystem") 
		        ) {
		            Double value;
		        try {
		            value = (Double) method.invoke(operatingSystemMXBean);
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
