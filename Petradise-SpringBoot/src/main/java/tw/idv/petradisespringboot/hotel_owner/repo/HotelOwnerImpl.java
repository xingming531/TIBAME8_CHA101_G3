//package tw.idv.petradisespringboot.hotel_owner.repo;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;
//
//public class HotelOwnerImpl implements HotelOwnerDAO_interface {
//	private SessionFactory sessionFactory;
//
//	public HotelOwnerImpl() {
//	    try {
//	        sessionFactory = new Configuration().configure().buildSessionFactory();
//	    } catch (Throwable ex) {
//	        throw new ExceptionInInitializerError(ex);
//	    }
//	}
//	
//	@Override
//	public void insert(HotelOwnerVO hotelOwnerVO) {
//	    Session session = sessionFactory.openSession();
//	    Transaction tx = null;
//	    try {
//	        tx = session.beginTransaction();
//	        session.save(hotelOwnerVO);
//	        tx.commit();
//	    } catch (RuntimeException ex) {
//	        if (tx != null)
//	            tx.rollback();
//	        throw ex;
//	    } finally {
//	        session.close();
//	    }
//	}
//
//	@Override
//	public void update(HotelOwnerVO hotelOwnerVO) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Integer hotelId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public HotelOwnerVO findByPrimaryKey(Integer hotelId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<HotelOwnerVO> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
