package com.npeetha.expensetracker.dao;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Service;

import com.npeetha.common.dao.CoreDAO;
import com.npeetha.expensetracker.entity.ExpenseEntity;

/**
 * @author nimilpeethambaran
 *
 */
@Service
public class ExpenseDAO extends CoreDAO implements IExpenseDAO {
	private static Logger log = Logger.getLogger(ExpenseDAO.class.getName());
	
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	private Session getSession(){
//		Session session = sessionFactory.getCurrentSession();
//		if(session == null){
//			session = sessionFactory.openSession();
//		}
//		return session;
//	}
//	@Override
	public String save(ExpenseEntity expense) {
		if(expense.getId() == null || expense.getId().trim().isEmpty())
			expense.setId(UUID.randomUUID().toString());
		Session session = getSession();
//		Serializable save = 
		session.saveOrUpdate(expense);	
		return expense.getId();//.toString();
	}

//	@Override
	
	public List<ExpenseEntity> list() {
		Session session = getSession();
		List<ExpenseEntity> expenseList = session.createQuery("from ExpenseEntity").list();
		session.flush();
		return expenseList;
	}

//	@Override
	public ExpenseEntity get(String id) {
		ExpenseEntity expense = new ExpenseEntity();
		expense.setId(id);
		Session session = getSession();
		return (ExpenseEntity) session.createCriteria(ExpenseEntity.class).add(Example.create(expense)).uniqueResult();
	}

//	@Override
	public String update(ExpenseEntity expense) {
		Session session = getSession();
		session.update(expense);
		session.flush();
		return expense.getId();
	}

//	@Override
	public void delete(String[] ids) {
		
		Session session = getSession();
//		ExpenseEntity expense = get(id);
		Query deleteQuery = session.createQuery("delete from ExpenseEntity where id in (:ids)");
		deleteQuery.setParameterList("ids", ids);
		deleteQuery.executeUpdate();
//		session.delete(expense);
		session.flush();
//		return id;
	}

	
}
