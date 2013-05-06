package integracion;

import java.util.Iterator;

import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

public class LifecycleFactoryWrapperImpl extends LifecycleFactory{

	private LifecycleFactory other;
	
	public LifecycleFactoryWrapperImpl(LifecycleFactory lifecycleFactory){
		System.out.println("**************LifecycleFactoryWrapperImpl*******************");
		this.other = lifecycleFactory;
	}
	
	@Override
	public void addLifecycle(String arg0, Lifecycle arg1) {
		this.other.addLifecycle(arg0, arg1);
	}

	@Override
	public Lifecycle getLifecycle(String arg0) {
		return new LifecycleWrapper(this.other.getLifecycle(arg0));
	}

	@Override
	public Iterator<String> getLifecycleIds() {		
		return this.other.getLifecycleIds();
	}

}
