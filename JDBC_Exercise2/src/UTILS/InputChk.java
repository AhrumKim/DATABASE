package UTILS;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InputChk {

	public static Date dateChk() {
		Date date = null;
		Scanner sc = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("조회를 희망하는 날짜를 입력해주세요 ex) 2022-05-14");
				String str = sc.nextLine();
				SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd"); // 검증할 날짜 포맷 설정
				dateFormatParser.setLenient(false); // false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
				dateFormatParser.parse(str);
				date = Date.valueOf(str);
				break;
			} catch (Exception e) {
				System.out.println("날짜형식에 맞지않습니다.");
			}
		}

		return date;
	}

}
