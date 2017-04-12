package de.dc.swt.custom.ui.generator;

import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.dc.swt.custom.ui.generator.model.Attribute;
import de.dc.swt.custom.ui.generator.template.BeanTemplate;

public class BeanGenerator extends ApplicationWindow {
	private Text attributeText;
	private Text generatorText;
	private Text classText;

	private ArrayList<Attribute> input = new ArrayList<Attribute>();
	private ListViewer listViewer;
	
	/**
	 * Create the application window.
	 */
	public BeanGenerator() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		Label lblClass = new Label(container, SWT.NONE);
		lblClass.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblClass.setText("Class");
		
		classText = new Text(container, SWT.BORDER);
		classText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		composite.setLayout(new GridLayout(6, false));
		Label lblAttribute = new Label(composite, SWT.NONE);
		lblAttribute.setText("Attribute:");
		attributeText = new Text(composite, SWT.BORDER);
		attributeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		Label lblDatatype = new Label(composite, SWT.NONE);
		lblDatatype.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDatatype.setText("Datatype:");
		Combo datatypeCombo = new Combo(composite, SWT.NONE);
		datatypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		datatypeCombo.setItems(new String[] { "String", "int", "boolean", "double" });
		datatypeCombo.select(0);
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Attribute a = new Attribute();
				a.setName(attributeText.getText());
				a.setDatatype(datatypeCombo.getText());
				input.add(a);
				listViewer.refresh();
			}
		});
		btnAdd.setText("Add");
		
		Button btnGenerate = new Button(composite, SWT.NONE);
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String content = BeanTemplate.gen(classText.getText(), input);
				generatorText.setText(content);
				
			}
		});
		btnGenerate.setText("Generate");
		
		listViewer = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		list.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));
		listViewer.setContentProvider(new ArrayContentProvider());
		generatorText = new Text(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		generatorText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		listViewer.setInput(input);
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			BeanGenerator window = new BeanGenerator();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 479);
	}

}
