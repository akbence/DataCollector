
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import quarks.connectors.mqtt.MqttStreams;
import quarks.console.server.HttpServer;
import quarks.function.Function;
import quarks.function.Supplier;
import quarks.providers.development.DevelopmentProvider;
import quarks.providers.direct.DirectProvider;
import quarks.topology.TStream;
import quarks.topology.Topology;


		
public class Main {

	public static void main(String[] args) throws Exception {
		
		  
		 
		
		  
	
		  	String URL = "tcp://test.mosquitto.org:1883";
	        String clientId = "quarks_client";
	        CPUSupply<Double> sensor = new CPUSupply<Double>();
	        
	        DirectProvider dp = new DirectProvider();
	        //System.out.println(dp.getServices().getService(HttpServer.class).getConsoleUrl());
	        Topology topology = dp.newTopology();
	        MqttStreams mystream = new MqttStreams(topology, URL, clientId);
	        TStream<Double> tempReadings = topology.poll( sensor, 1000, TimeUnit.MILLISECONDS);
	        
	        TStream<String> cpupercent = tempReadings.map(new Function<Double, String>() {
	            
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public String apply(Double arg0) {
	                return arg0.toString();
	                
	            }
	        });
	        
	        mystream.publish(cpupercent, "CPUUsage",0, true);
	        dp.submit(topology);

	
	}
}
