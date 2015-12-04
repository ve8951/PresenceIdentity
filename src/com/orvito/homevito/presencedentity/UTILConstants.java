package com.orvito.homevito.presencedentity;


public class UTILConstants {

	public static String DATABASE_NAME = "smartphone";

	public static Boolean debugMode=true;
	public final static char DEVTYPE='2';
	public final static String SERVERURL="http://192.168.1.113/xampp/homevito/";
	public final static int RECEIVERPORT=12369;
	public final static char PROTOCOLVERSION='1';




	/* ------------------Message Types------------------------------*/
	public final static char TABREG='1';
	public final static char TABREGACK=(char)'\t';//stands for 9
	public final static char TABSYNC='3';
	public final static char WEATHER=(char)68;//stands for 20
	public final static char WEATHERACK=(char)69;//stands for 21


	/* ------------------TCP Message PacketMaxSize------------------------------*/
	public final static int REGPACKETSIZE=15;


	/* ------------------Preference Keys---------------------------------------------*/
	public final static String SESSIONID="authtoken";
	public final static String FIRSTNAME="firstname";
	public final static String LASTNAME="lastname";
	public final static String DOB="dob";
	public final static String EMAIL="email";
	public final static String PHONENUM="phonenum";
	public final static String ONETIMEKEY="onetimekey";
	public final static String NETWORKNAME="networkname";
	public final static String AUTOSTATE="autostate";


}
