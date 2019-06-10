import model.entity.Faculty;
import model.service.FacultyService;
import org.junit.Test;
import static org.junit.Assert.*;

public class JDBCFacultyFactoryTest {
    @Test
    public void testFindById() {
        FacultyService facultyService = new FacultyService();
        Faculty faculty = facultyService.findById(1);
        assertNotNull(faculty);
        assertEquals(faculty.getId(), 1);
    }
}