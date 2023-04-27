import DTO.Emp;

/*
EMP 테이블 ....... 

전체조회 

조건조회 where empno=?

삽입 insert into emp( ....) values(?,?,?,?,?,?,?,?)

삭제 delete from emp where empno=?

수정 update emp set ename=? , job=? , sal=? , hiredate=? where empno=?

Like 검색  >> 이름 검색

​

5개의 함수 생성 처리 (답글로 올려주세요)

​
 */
public class select_emp {
	public static void main(String[] args) {
		
		Emp emp = new Emp();
		emp.setEmpno(0);
		emp.setEname(nulll);
		System.out.println(emp.toString());
		
		

	}

}
