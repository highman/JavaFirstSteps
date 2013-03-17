package fraction_calculator;


import java.io.*;
import java.util.*;


//------------------- Класс дробей -----------------------------------
 class Fract {
  private int FRACT_INTEGER; // Целое значение дроби
	private int FRACT_NUMERATOR; // Числитель дроби
	private int FRACT_DENOMINATOR; // Знаменатель дроби
	Scanner sc; // Объекс сканер для получения информации с консоли
	
	// Конструтор класс для случая когда параметры не заданы
	public Fract() { 		
		sc = new Scanner(System.in);
	}
	
	//Конструктор класса для случая когда при создании объекта задаются сразу параметры дроби
	public Fract(int fRACT_INTEGER, int fRACT_NUMERATOR, int fRACT_DENOMINATOR) {
		super();
		FRACT_INTEGER = fRACT_INTEGER;
		FRACT_NUMERATOR = fRACT_NUMERATOR;
		FRACT_DENOMINATOR = fRACT_DENOMINATOR;
		sc = new Scanner(System.in);
	}
	
	//--- Метод получения целого значения дроби
	public int getInt() {
		return FRACT_INTEGER;
	}
	
	//--- Метод присвоения целого значения дроби
	public void setINT(int fRACT_INT) {
		FRACT_INTEGER = fRACT_INT;
	}
	
	//--- Метод получения числителя дроби
	public int getNumer() {
		return FRACT_NUMERATOR;
	}
	
	//--- Метод присвоения числителя дроби
	public void setNumer(int fRACT_NUMERATOR) {
		FRACT_NUMERATOR = fRACT_NUMERATOR;
	}
	
	//--- Метод получения знаменателя дроби
	public int getDenom() {
		return FRACT_DENOMINATOR;
	}
	
	//--- Метод присвоения знаменателя дроби
	public void setDenom(int fRACT_DENOMINATOR) {
		FRACT_DENOMINATOR = fRACT_DENOMINATOR;
	}
	
	//--- Метод перевода дроби из неправильной в правильную форму
	public void convWR() {
		FRACT_NUMERATOR += (FRACT_INTEGER*FRACT_DENOMINATOR);
		FRACT_INTEGER = 0;
	}
	
	//--- Метод перевода дроби из правильной в неправильную форму
	public void convRW() {
		FRACT_INTEGER = (int)(FRACT_NUMERATOR / FRACT_DENOMINATOR);
		FRACT_NUMERATOR = (FRACT_NUMERATOR % FRACT_DENOMINATOR);
	}
	
	//--- Метод сокращения дроби. Пока что в разработке
	public void reduction() {
		System.out.println();
	}
	
	//--- Метод отображения дроби
	public void print() {
		if(FRACT_INTEGER != 0) {
			System.out.println("Fraction is equal to: " +FRACT_INTEGER + " " +   
				    			FRACT_NUMERATOR + "/" + FRACT_DENOMINATOR+"\n");
		}
		else {
			System.out.println("Fraction is equal to: " +   
				    FRACT_NUMERATOR + "/" + FRACT_DENOMINATOR+"\n");
		}		
	}
	
	//--- Метод инициализации значений дроби с переводом дроби в правильную форму в случае необходимости
	public void initial(String frName) {						
		int PosSlash, PosSpace;
		String istr;
		System.out.print("Please, enter "+frName +". (For example, 10 1/2) : ");
		// Строка, которая будет считана при вводе c консоли. Также вызывается метод TRIM
		//чтобы убрать ошибочно введенные пробелы и осущестляется проверка не пустая ли это строка		
		do {						
			istr = sc.nextLine().trim();
			if(!istr.isEmpty()) {
				PosSlash = istr.indexOf('/'); // вычисление позиции слеша
				PosSpace = istr.indexOf(' '); // вычисление позиции пробела
				
				FRACT_NUMERATOR = Integer.parseInt(istr.substring(PosSpace+1, PosSlash)); //вычисление числителя дроби
				FRACT_DENOMINATOR = Integer.parseInt(istr.substring(PosSlash+1, istr.length())); // вычисление знаменателя дроби
				
				if(FRACT_DENOMINATOR != 0) 
					break;
				else
					System.out.println("Divide by zero is impossible");
			}			
			System.out.print("Wrong format. Be attentive! Please, enter in correct form: ");			
		}while(true);
		
		if(PosSpace != -1) { //Проверяем была ли введена целая часть дроби
			FRACT_INTEGER = Integer.parseInt(istr.substring(0, PosSpace)); // вычисление целого значения дроби
			convWR(); // перевод дроби из неправильной формы в правильную
		}
		else {
			FRACT_INTEGER = 0; //Если целая часть не введена, присвоить нулевое значение 
		}		
	}
}


 class Calculator {
	private Fract fr1, fr2, frRes;
	
	Calculator() {
		System.out.println("Welcome to Calculator");
		frRes = new Fract();
	}	

	public Fract getFr1() {
		return fr1;
	}
	public void setFr1(Fract fr1) {
		this.fr1 = fr1;
	}
	public Fract getFr2() {
		return fr2;
	}
	public void setFr2(Fract fr2) {
		this.fr2 = fr2;
	}

	void add() {		
		if( fr1.getDenom() != fr2.getDenom()) {
			
			frRes.setDenom(fr1.getDenom() * fr2.getDenom());						
			fr1.setNumer(fr1.getNumer() * fr2.getDenom());						
			fr2.setNumer(fr2.getNumer()*fr1.getDenom());
		}
		else {
			frRes.setDenom(fr1.getDenom());
		}
		
		frRes.setNumer(fr1.getNumer() + fr2.getNumer());
		if(frRes.getNumer() >= frRes.getDenom()) {
			frRes.convRW();
		}
		else {
			frRes.setINT(0);
		}		
		frRes.print();
	}
	
	void subtract() {
		if( fr1.getDenom() != fr2.getDenom()) {
			
			frRes.setDenom(fr1.getDenom() * fr2.getDenom());			
			fr1.setNumer(fr1.getNumer() * fr2.getDenom());			
			fr2.setNumer(fr2.getNumer()*fr1.getDenom());
		}
		else {
			frRes.setDenom(fr1.getDenom());
		}
		
		frRes.setNumer(fr1.getNumer() - fr2.getNumer());
		if(frRes.getNumer() >= frRes.getDenom()) {
			frRes.convRW();
		}
		else {
			frRes.setINT(0);
		}
		frRes.print();
	}
	
	void multiply() {
		frRes.setNumer(fr1.getNumer() * fr2.getNumer());
		frRes.setDenom(fr1.getDenom() * fr2.getDenom());			
		if(frRes.getNumer() >= frRes.getDenom()) {
			frRes.convRW();
		}
		else {
			frRes.setINT(0);
		}		
		frRes.print();
	}
	
	void devide() {
		frRes.setNumer(fr1.getNumer() * fr2.getDenom());
		frRes.setDenom(fr1.getDenom() * fr2.getNumer());			
		if(frRes.getNumer() >= frRes.getDenom()) {
			frRes.convRW();
		}
		else {
			frRes.setINT(0);
		}
		frRes.print();
	}	
}

 class Console {
	static final int CHOICE_ADD = 1;
	static final int CHOICE_SUBTRACT = 2;
	static final int CHOICE_MULTIPLY = 3;
	static final int CHOICE_DEVIDE = 4;
	static final int CHOICE_EXIT = 5;
	private int choice;
	BufferedReader br; 
	
	Calculator cCalc;
	
	public Console() {
		cCalc = new Calculator();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Methods GetChoice
	boolean getChoice() throws IOException {				 
		do {
			System.out.println("Please, choose: \n" +
							   "1-Addition, 2-Subtract, " +
							   "3-Multiplay, 4-Devision, 5-Exit: ");
			String istr = br.readLine();
			try {
				choice = Integer.parseInt(istr);
				if((choice<1 || choice>5) != true) break;  
			}catch(NumberFormatException e) {
				System.out.println("Wrong format!");
			}			
		}while(true);
		
		if(choice == 5) 
			return false;		
		else 
			return true;
	}
	
	void execute() {
			System.out.println("Result: ");
			switch(choice) {		
			case CHOICE_ADD:			
				cCalc.add();				
				break;
			case CHOICE_SUBTRACT:
				cCalc.subtract();
				break;
			case CHOICE_MULTIPLY:
				cCalc.multiply();
				break;
			case CHOICE_DEVIDE:
				cCalc.devide();									
				break;
			}
		}
}


public class FractCalcStarter {
	public static void main(String[] args) throws IOException {
		Fract fract1 = new Fract();
		Fract fract2 = new Fract();
		Console cons = new Console();
		
		while(cons.getChoice()) {
			fract1.initial("first fraction");
			cons.cCalc.setFr1(fract1);
			
			fract2.initial("second fraction");
			cons.cCalc.setFr2(fract2);
			
			cons.execute();	
		}		
		
		

	}

}
