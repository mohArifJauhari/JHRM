package org.module.hr.dao;

import java.util.List;

import org.module.hr.model.MstJobtitle;

public interface MstJobtitleDAO {
	public void save(MstJobtitle mstJobtitle);
    public void update(MstJobtitle mstJobtitle);
    public void saveOrUpdate(MstJobtitle mstJobtitle);
    public void delete(MstJobtitle mstJobtitle);
    public List<MstJobtitle> getAllMstJobtitles();
}
