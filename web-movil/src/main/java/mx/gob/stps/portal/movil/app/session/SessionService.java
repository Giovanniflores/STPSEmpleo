package mx.gob.stps.portal.movil.app.session;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.ejb.Local;

import org.joda.time.DateTime;

import mx.gob.stps.portal.movil.app.model.Session;


@Local
public class SessionService {

    Map<Long, Session> sessions = new HashMap<Long, Session>();
    private final AtomicLong counter = new AtomicLong();

    public SessionService() {

    }

    public Long save(Session session) {
        sessions.put(counter.incrementAndGet(), session);
        return counter.get();
    }


    public void update(Session session) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            session.setUpdatedAt(new DateTime());
            sessions.put(session.getId(), session);
        } finally {
            lock.unlock();
        }
    }

    public void delete(Session session) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            sessions.remove(session);
        } finally {
            lock.unlock();
        }
    }

    public Session findById(Long id) {
        for (Map.Entry<Long, Session> e : sessions.entrySet()) {
            Session session = e.getValue();
            if (session.getId().equals(id)) {
                return session;
            }
        }
        return null;
    }

    public Session findBySessionId(String sessionId) {
        return null;
    }

    public Session findByToken(String token) {
        for (Map.Entry<Long, Session> e : sessions.entrySet()) {
            Session session = e.getValue();
            if (session.getToken().equals(token)) {
                return session;
            }
        }
        return null;
    }

    public List<Session> findAll() {
        return null;
    }

    public List<Session> findByCurrentDateGreaterThanUpdatedAt(DateTime currentRequestDate) {
        return null;
    }

    public List<String> findAllUsernameRows() {
        return null;
    }

    public List<Session> findSessionsByUsername(String username) {
        return null;
    }

    public Session findByUsername(String username) throws Exception {
        for (Map.Entry<Long, Session> e : sessions.entrySet()) {
            Session session = e.getValue();
            if (session.getUsername().equals(username)) {
                return session;
            }
        }
        return null;
    }

    public Session findDataByUsername(String username) {
        return null;
    }

    public int countByUsername(String username) throws SQLException {
        return 0;
    }
}

