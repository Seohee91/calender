import java.io.*;
import java.util.*;

class Person{
   protected String name ;
   protected int birth;
   protected String workplace;
   public double[][] worktime=new double[12][31];
   public double[][] workmoney=new double[12][31];
   
   Scanner input=new Scanner(System.in);
   
   public void mainMenu() {
	   
	   while(true) {
	   System.out.println("\n<<<<<<알바 급여 계산기>>>>>>");
	   System.out.println("등록된 회원이시면 1");
	   System.out.println("등록되지 않으셨으면 2를 입력하세요");
	   int a=input.nextInt();
	   if(a==1) {
		   signed(); break;}
	   else if(a==2) {
		   unsigned(); break;}	    
	   }
  }
   public void signed() {
	   System.out.println("생년월일을 입력하세요 : ");
	   System.out.println("***커서를 끝까지 내려서 입력하세요***");
	   int check=input.nextInt();
	   String fname=String.valueOf(check);
	   try(FileReader fr=new FileReader(fname)){
		   int ch;
		   int i=0;
		   char a=0,b=0,c=0,d=0,e=0,f=0;
		   while((ch=fr.read())!=-1) {
			   if(i==0){
				   System.out.print("생년월일 : ");
				   a=(char)ch;}
			   System.out.print((char)ch);
			   if(i==1)
				   b=(char)ch;
			   else if(i==2)
				   c=(char)ch;
			   else if(i==3)
				   d=(char)ch;
			   else if(i==4)
				   e=(char)ch;
			   else if(i==5) {
				   f=(char)ch;
				   System.out.print("\n이름 : ");}
			   else if(i==8)
				   System.out.println("\n근무지 : ");
			   i++;
		   }
		   String str=String.valueOf(a);
		   str+=String.valueOf(b);
		   str+=String.valueOf(c);
		   str+=String.valueOf(d);
		   str+=String.valueOf(e);
		   str+=String.valueOf(f);
		   birth=Integer.parseInt(str);
	   }catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("입출력 오류!");
		   System.out.println("초기화면부터 다시 시작합니다");
		   mainMenu();
	   }
   }
   public void unsigned() {
	   System.out.println("등록을 시작합니다");
	   System.out.println("***커서를 끝까지 내려서 입력하세요***");
	   System.out.println("생년월일 : ");
	   birth=input.nextInt();
	   String birthString=String.valueOf(birth);
	   System.out.println("이름 : ");
	   name=input.next();
	   System.out.println("근무지 : ");
	   workplace=input.next();
	   
	   String info=birthString + name + workplace;
	   char[] chinfo=info.toCharArray();
	   
	   try(FileWriter out=new FileWriter(birthString)){
		   for(int i=0;i<chinfo.length;i++)
			   out.write(chinfo[i]);
	   }catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("입출력 오류!");
		   System.out.println("초기화면부터 다시 시작합니다");
		   mainMenu();
	   }
	   finally {
		   System.out.println("등록이 완료되었습니다");
		   mainMenu();
	   }
   }

   public String getBirth() {
	   return  String.valueOf(birth) + "Object";
   }
}
class Employee extends Person implements Serializable {
   private int[][] hour=new int[10][2];
   private int[][] minute=new int[10][2];
   private int[] money=new int[10];
   private static int m=0;
   private static int year=0;
   
   public void calmenuback() {
	   System.out.println("=====================");
	      System.out.println("달력으로 돌아가시려면 1번");
	      System.out.println("근투타임/달력선택 메뉴로 돌아가시려먼 2번");
	      System.out.println("종료하시려면 아무키나 누르세요");
	      int c=input.nextInt();
	      if(c==1)
	    	  calMenu();
	      else if(c==2)
	    	  menu1();        
   }
   public void worktimemenu() {
	   System.out.println("====================");
	   System.out.println("1. 근무타입 확인");
	   System.out.println("2. 근무타입 설정");
	   System.out.println("상위 메뉴로 돌아가시려면 아무키나 누르세요");
	   int a=input.nextInt();
	   if(a==1)
		   timecheck();
	   else if(a==2)
		   timeSet();
	   else 
		   menu1();	   
   }
   public void menu1() {
	   System.out.println("선택할 메뉴를 고르세요");
	   System.out.println("1.근무타입");
	   System.out.println("2.달력");
	   System.out.println("종료하려면 아무키나 누르세요");
	   int a=input.nextInt();
	   if(a==1)
		   worktimemenu();
	   else if(a==2) {
		   Calset();
		   calMenu(); }		   
   }
   public void timecheck() {
	   System.out.println("근무타입을 확인합니다.");
	   for (int i=0;i<hour.length;i++) {
		   System.out.println("근무타입"+(i+1));
		      System.out.println("시작 시간 : "+hour[i][0]+"시 "+minute[i][0]+"분");
		      System.out.println("종료 시간 : "+hour[i][1]+"시 "+minute[i][1]+"분");
		      System.out.println("시급 : "+money[i]+"원");
	   }
	   worktimemenu();
   }
   public void timeSet() {
      while(true){
            System.out.println("몇번 째 근무타입을 수정하시겠습니까?(최대 10개)");
            System.out.println("근무타입설정을 그만두시려면 0을 입력하세요");
            int a=input.nextInt();
            if(a==0){
            	System.out.println("근무타입 설정이 완료되었습니다.");
                break;
            }
            else{
            	 System.out.println("근무타입을 설정하세요. (ex. 15시 00분 → 15 00(시간, 분)");
                 System.out.println("근무타입"+(a));
                 System.out.print("시작 시간 : ");
                 hour[a-1][0]=input.nextInt();
                 minute [a-1][0]=input.nextInt();
                 System.out.print("종료 시간 : ");
                 hour[a-1][1]=input.nextInt();
                 minute [a-1][1]=input.nextInt();
                 System.out.print("시급(원) : ");
                 money[a-1]=input.nextInt();
            }
            a=0;
         }
      	worktimemenu();
   }
   public void Calset() {
       System.out.println("확인하려는 연도와 월을 입력하세요.  (ex년도 : 2018,월 : 3)");
      System.out.print("연도 : ");
      year = input.nextInt();
      System.out.print("월: ");
      m = input.nextInt();        
   }
   public void Cal(int a, int b) {
       System.out.println("       [ "+a+"년 "+b+"월"+" ] ");
       System.out.println("=============================");
        System.out.println("    일     월     화     수     목     금     토");

      Calendar cal = Calendar.getInstance();
       cal.set(a, b-1,1);
      int lastOfDate = cal.getActualMaximum(Calendar.DATE);
      int week = cal.get(Calendar.DAY_OF_WEEK);
     for(int i = 1;i < week ; i++)
             System.out.print("    ");
         for(int i =1;i<=lastOfDate;i++){
            
            if (i<10) {
               System.out.printf("  %2d",i) ;
            }
            else if (i>9&& i<21) {
               System.out.printf("  %2d",i) ;
            }
            else if (i>20&& i<28) {
               System.out.printf("  %2d",i) ;
            }
            else if (i>27) {
               System.out.printf("  %2d",i) ;
            }
              //토요일에 날짜를 표시하고 줄바꿈 하는 코드
              if(week%7==0)System.out.println();
              week++;
          }
   }
   public void daySet() {
	  Scanner sc=new Scanner(System.in);
	  int a;
      while(true) {
      System.out.println("등록하려는 근무 타입을 선택하세요");
      System.out.println("근무 날짜 등록을 그만두려면 0을 입력하세요");
      a=input.nextInt();
      if(a==0)
         break;
      System.out.println("선택한 근무타입");
      System.out.println("근무타입"+a);
      System.out.println("시작 시간 : "+hour[a-1][0]+"시 "+minute[a-1][0]+"분");
      System.out.println("종료 시간 : "+hour[a-1][1]+"시 "+minute[a-1][1]+"분");
      System.out.println("시급 : "+money[a-1]+"원");
      System.out.println("근무 타입에 맞는 근무 날짜를 선택하세요(ex) 1, 8, 9, 10): "); 
      String n =sc.nextLine(); 
      String[] ma =n.split(",");
      int[] w=new int[ma.length];
      for(int i=0;i<ma.length;i++) {
         w[i]=Integer.parseInt(ma[i]);}
      for(int i=0;i<w.length;i++) {
         worktime[m][w[i]-1]+=(hour[a-1][1]-hour[a-1][0])+(double)(minute[a-1][1]-minute[a-1][0])/60;
         workmoney[m][w[i]-1]+=money[a-1]*(hour[a-1][1]-hour[a-1][0])+(double)(minute[a-1][1]-minute[a-1][0])/60;
      }
      a=0;
      }
      calmenuback();  
}
   public void moneySet() {
      System.out.println("급여 계산할 날짜를 선택하세요");
      System.out.println("시작 날짜 : ");
      int a=input.nextInt();
      System.out.println("종료 날짜 : ");
      int b=input.nextInt();
      System.out.println("====================");
      double pay=0;
      for(int i=0;i<(b-a)+1;i++)
         pay+=workmoney[m][a+i];
      System.out.println("알바 급여는 총"+pay+"입니다");
      calmenuback();
   }
   public void hoilday() {
      System.out.println("==================");
      System.out.println(m+"월달 동안 총 근로시간 : ");
      double work=0;
      double money=0;
      System.out.println(work);
      System.out.println(money);
      for(int i=0;i<worktime[m].length;i++) {
         work+=worktime[m][i];
         money+=workmoney[m][i];
      }
      System.out.println(work+" 시간 일하였습니다.");
      System.out.println("===================");
      if(work<60){
         System.out.println("주휴수당이 없습니다.");
         System.out.println("일주일 평균근무 시간이 15시간 이상이어야 합니다.");
      }
      else{
         System.out.println("주휴수당은 평균 시급으로 지급됩니다.");
         System.out.println("근로시간 "+work+"시간 / 평균시급 "+money/work+"원");
         System.out.println("일주일 근무에 대한 예상 주휴수당은 "+((double)work/20)*(money/work)+"원 입니다");  
      }
      calmenuback();
}
   public void calMenu() {
	  Cal(year,m);
      System.out.println("\n선택할 메뉴를 고르세요 : ");
      System.out.println("1.근로 날짜 설정  : ");
      System.out.println("2.알바 급여계산 : ");
      System.out.println("3.주휴수당 계산 : ");
      System.out.println("상위 메뉴로 가시려면 아무키나 누르세요 ");
      int a=input.nextInt();
      if(a==1)
         daySet();
      else if(a==2)
         moneySet();
      else if (a==3)
         hoilday();
      else
         menu1();      
   }  
   public void start(Object q) throws IOException {
		while(true) {
		System.out.println("\n=========================");
		System.out.println("선택하세요");
		System.out.println("1. 이전정보 사용하기/처음등록하는분 2번  ㄱㄱ");
		System.out.println("2. 정보 초기화 하기");
		int a=input.nextInt();
		if(a==1) {
			Last(q);
			break;
		}
		else if(a==2)
			New(q);
			break;
		}
	}
	public void Last(Object q) throws IOException {
		Employee first=(Employee)q;
		ObjectInputStream in=null;
		try{
			in=new ObjectInputStream(new FileInputStream(first.getBirth()));
			Employee second=(Employee)in.readObject();
			second.menu1();
			New(second);
		}catch(ClassNotFoundException e) {
		}finally {
			in.close();
		}
	}
	public void New(Object q) throws IOException {
		Employee first=(Employee)q;
		first.menu1();
		ObjectOutputStream out=null;
		try{
			out=new ObjectOutputStream(new FileOutputStream(first.getBirth()));
			out.writeObject(first);
			out.flush();
		}catch(IOException e) {
		}finally {
			if(out!=null) {
				out.close();
			}
		}
	}
}
public class Calender {
   public static void main(String[] args) throws IOException {
	   Employee one=new Employee();
	   Employee check=one;
	   one.mainMenu();
	   one.start(check);
	   
   }
   }