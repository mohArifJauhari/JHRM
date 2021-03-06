package org.module.hr.dao.impl;

import java.util.List;

import org.module.api.common.dao.base.BasisDAO;
import org.module.hr.dao.MstSkillDAO;
import org.module.hr.model.MstSkill;


public class MstSkillDAOImpl extends BasisDAO<MstSkill> implements MstSkillDAO{

	@Override
	public List<MstSkill> getAllMstSkill() {
		List<MstSkill>list = (List<MstSkill>) getHibernateTemplate().find("FROM MstSkill");
		return list;
	}
	
	@Override
	public MstSkill getMstSkillById(Integer id) {
		MstSkill mstSkill = getHibernateTemplate().get(MstSkill.class, id);
		return mstSkill;
	}
}