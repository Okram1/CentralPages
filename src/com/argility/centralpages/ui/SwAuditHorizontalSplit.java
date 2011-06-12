package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.argility.centralpages.XmlPrettyPrintFormatter;
import com.argility.centralpages.data.SwAudit;
import com.argility.centralpages.ui.form.SwAuditForm;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TextArea;

@SuppressWarnings("serial")
public class SwAuditHorizontalSplit extends HorizontalSplitPanel {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	private SwAuditForm form;
	private TextArea xmlText;
	private SwAudit swAud;
	
	public SwAuditHorizontalSplit(SwAudit swAud) {
		
		if (swAud == null) {
			form = null;
			xmlText = null;
			return;
		}
		
		this.swAud = swAud; 
		
		form = new SwAuditForm(swAud);
		xmlText = getSwitchXmlTextArea();
		
		setSizeFull();
		
		setFirstComponent(form);
		setSecondComponent(xmlText);
		
		setSplitPosition(40);
		
		addStyleName("view");
	}
	
	private TextArea getSwitchXmlTextArea() {
		TextArea ta = new TextArea();
		if (swAud != null && swAud.getAudXml() != null) {
			try {
				ta.setValue(new XmlPrettyPrintFormatter().format(swAud.getAudXml()));
			} catch (Exception e) {
				ta.setValue(swAud.getAudXml());
			}
		} else {
			ta.setValue("Unable to retrieve XML for sw_aud_id " + swAud.getSwAudId());
		}
		
		ta.setSizeFull();
		ta.setReadOnly(true);
		
		return ta;
	}

	public SwAuditForm getForm() {
		return form;
	}

	public void setForm(SwAuditForm form) {
		this.form = form;
	}

	public TextArea getXmlText() {
		return xmlText;
	}

	public void setXmlText(TextArea xmlText) {
		this.xmlText = xmlText;
	}

	public SwAudit getSwAud() {
		return swAud;
	}

	public void setSwAud(SwAudit swAud) {
		this.swAud = swAud;
	}

}
