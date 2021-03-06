package org.app.portofolio.webui.hr.master.job.jobcategory.model;

import org.module.hr.model.MstJobCategory;
import org.module.hr.service.MasterJobService;
import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class MstJobCategoryListItemRenderer implements ListitemRenderer<MstJobCategory>{
	
	private MasterJobService masterJobService = (MasterJobService) SpringUtil.getBean("masterJobService");		
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void render(Listitem item, final MstJobCategory mstJobCategory, int index) throws Exception {
		Listcell listcell;
		
		final Button buttonSave = new Button();
    		buttonSave.setImage("/images/icons/btn_save.gif");
		
		final Button buttonEdit = new Button();
			buttonEdit.setImage("/images/icons/btn_edit.gif");
			
		final Button buttonDelete = new Button();
        	buttonDelete.setImage("/images/icons/btn_delete.gif");
        
        final Button buttonCancel = new Button();
        	buttonCancel.setImage("/images/icons/btn_cancel.gif");
        	
        final Label labelName = new Label();			        
        
        final Textbox textboxName = new Textbox();
        	
        listcell = new Listcell();
        	buttonEdit.setParent(listcell); 
        	buttonSave.setParent(listcell); 
        	buttonCancel.setParent(listcell); 
        	buttonDelete.setParent(listcell); 
		listcell.setParent(item);
        
		listcell = new Listcell();
			textboxName.setParent(listcell); 
			labelName.setParent(listcell); 
		listcell.setParent(item);

		if(mstJobCategory.getIdJobCategory() == null){
			buttonEdit.setVisible(false);
			buttonDelete.setVisible(false);
		} else {
        	buttonSave.setVisible(false);
        	buttonCancel.setVisible(false);
        	buttonDelete.setVisible(false);
        	
        	labelName.setValue(mstJobCategory.getJobCategoryName());
        	
        	textboxName.setVisible(false);
		}
		
		buttonSave.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				if(mstJobCategory.getIdJobCategory() == null){
					mstJobCategory.setJobCategoryName(textboxName.getValue());

					masterJobService.save(mstJobCategory);
					
					BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
				}else{
					mstJobCategory.setJobCategoryName(textboxName.getValue());

					masterJobService.update(mstJobCategory);
					
					BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
				}
			}
		});
		
		buttonEdit.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {		
				buttonEdit.setVisible(false);
				buttonSave.setVisible(true);
				buttonDelete.setVisible(true);
				
				textboxName.setVisible(true);
				
				labelName.setVisible(false);
				
				textboxName.setValue(mstJobCategory.getJobCategoryName());
			}					
		});
		
		buttonDelete.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Messagebox.show("Do you really want to remove item?", "Confirm", Messagebox.OK | Messagebox.CANCEL, Messagebox.EXCLAMATION, new EventListener() {
				    public void onEvent(Event event) throws Exception {    	
				 		if (((Integer) event.getData()).intValue() == Messagebox.OK) {

				 			masterJobService.delete(mstJobCategory);
				 			
				 			BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
				 		}else{
				 			return;
				 		}
				 	}
				});
			}
		});
		
		buttonCancel.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				BindUtils.postGlobalCommand(null, null, "refreshAfterSaveOrUpdate", null);
			}
		});
	}
}
