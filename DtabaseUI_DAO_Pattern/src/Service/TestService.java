package Service;

public class TestService {
	public static void main(String[] args) {
		
		//Test AppService
		AppService srv = new AppService();
		
		srv.getAllPeople().forEach(p->System.out.println(p.getName()));
		System.out.println();
		srv.getAllCategories().forEach(c->System.out.println(c.getId() + "-" + c.getName()));
		
		
		
		
	}

}
