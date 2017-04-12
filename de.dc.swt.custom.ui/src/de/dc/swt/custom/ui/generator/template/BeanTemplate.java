package de.dc.swt.custom.ui.generator.template;

import java.util.List;

import de.dc.swt.custom.ui.generator.model.Attribute;

public class BeanTemplate {
	public static final String NL = "\n\n";
	
	public static String gen(String className, List<Attribute> attributes){
		StringBuilder sb = new StringBuilder();
		sb.append("public class "+className+"{"+NL);
		sb.append("private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);" +NL);
		sb.append("public void addPropertyChangeListener(PropertyChangeListener listener) { changeSupport.addPropertyChangeListener(listener);"+NL);
		sb.append("public void removePropertyChangeListener(PropertyChangeListener listener) { changeSupport.removePropertyChangeListener(listener);"+NL);
		sb.append("protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {changeSupport.firePropertyChange(propertyName, oldValue, newValue);}"+NL);
		
		for (Attribute a : attributes) {
			sb.append("public "+a.getDatatype()+" "+a.getName()+"(){return "+a.getName()+";}"+NL+NL);
			sb.append("public void "+a.getName()+"("+a.getDatatype()+" "+a.getName()+"){this."+a.getName()+"="+a.getName()+";}"+NL+NL);
		}
		
		sb.append("}");
		return sb.toString();
		
	}
}
