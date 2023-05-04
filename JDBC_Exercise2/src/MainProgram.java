import java.util.Scanner;

import DAO.UsersDao;
import DTO.Users;

public class MainProgram {
	
	UsersDao usersDao = new UsersDao();

	public void run() {
		Scanner sc = new Scanner(System.in);

		main: while (true) {
			System.out.println("1. 회원가입 , 2. 로그인 , 3. 프로그램 종료");
			String num = sc.nextLine();

			switch (num) {
				case "1": {
					System.out.println("회원가입 실행");
					System.out.println("아이디 입력 : ");
					String u_id = sc.nextLine();
					System.out.println("암호 입력 : ");
					String u_pwd = sc.nextLine();
					System.out.println("이름 입력 : ");
					String u_name = sc.nextLine();
					System.out.println("몸무게 입력 : ");
					int weigth = Integer.parseInt(sc.nextLine());
					System.out.println("키 입력 : ");
					int height = Integer.parseInt(sc.nextLine());
					System.out.println("성별 입력 남 - m / 여 - f");
					String gender = sc.nextLine();
					Users user = new Users(u_id, u_pwd, u_name, height, height, gender);
					usersDao.createUser(user);
					System.out.println("회원가입 완료");
					
					break;
				}
				case "2": {
					System.out.println("로그인 실행");
					System.out.println("아이디 입력 : ");
					String u_id = sc.nextLine().trim();
					System.out.println("암호 입력 : ");
					String u_pwd = sc.nextLine().trim();
					Users users= usersDao.login(u_id, u_pwd);
					if(users != null) {
						UserProgram userProgram = new UserProgram();
						System.out.println(users.getU_Name() + " 님 환영합니다.");
						userProgram.run(u_id);
						break;
					}else {
						System.out.println("아이디 및 비밀번호를 확인해주세요");
						break;
					}
					
				}
				case "3": {
					System.out.println("프로그램 종료");
					break main;
	
				}
	
				}// switch	
		}// while

	}// run

	
	
}
