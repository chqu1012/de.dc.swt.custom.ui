package de.dc.swt.custom.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LabelComboText extends Composite {
	
	private Text memberText;
	private Combo memberCombo;

	public LabelComboText(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Label label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Member");
		
		Combo combo = new Combo(this, SWT.READ_ONLY);
		combo.setItems(new String[] {"New", "Exists"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo.select(1);
		
		Composite stackedComposite = new Composite(this, SWT.NONE);
		StackLayout stackLayout = new StackLayout();
		stackedComposite.setLayout(stackLayout);
		stackedComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		
		memberText = new Text(stackedComposite, SWT.BORDER);
		memberCombo = new Combo(stackedComposite, SWT.READ_ONLY);

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isNew = combo.getSelectionIndex()==0;
				if (isNew) {
					stackLayout.topControl=memberText;
				}else{
					stackLayout.topControl=memberCombo;
				}
				stackedComposite.layout();
			}
		});
		
		stackLayout.topControl=memberCombo;
	}

}
