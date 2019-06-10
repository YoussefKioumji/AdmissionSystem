import model.entity.Subject;
import model.service.SubjectService;
import org.junit.Test;

import static org.junit.Assert.*;

public class JDBCSubjectFactoryTest {
    @Test
    public void testFindById() {
        SubjectService subjectService = new SubjectService();
        Subject subject = subjectService.findById(2);
        assertNotNull(subject);
        assertEquals(subject.getId(), 2);
        assertEquals(subject.getMaximum(), 100);
    }
}