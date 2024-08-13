package br.com.gemalto.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import br.com.gemalto.oath.TOTP;

public class Aplicacao {

	public static void main(String[] args) {
	      // Seed for HMAC-SHA1 - 20 bytes
        String seed = "3132333435363738393031323334353637383930";
        // Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "3132333435363738393031323334353637383930" +
        "313233343536373839303132";
        // Seed for HMAC-SHA512 - 64 bytes
        String seed64 = "3132333435363738393031323334353637383930" +
        "3132333435363738393031323334353637383930" +
        "3132333435363738393031323334353637383930" +
        "31323334";
        long T0 = 0;
        long X = 30;
        long testTime[] = {59L, 1111111109L, 1111111111L,
                1234567890L, 2000000000L, 20000000000L};

        String steps = "0";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        long nowTime = new Date().getTime()/1000L;
        System.out.println(nowTime);
        System.out.println(String.format("%1$-11s", nowTime));
        
        try {
            System.out.println(
                    "+---------------+-----------------------+" +
            "------------------+--------+--------+");
            System.out.println(
                    "|  Time(sec)    |   Time (UTC format)   " +
            "| Value of T(Hex)  |  TOTP  | Mode   |");
            System.out.println(
                    "+---------------+-----------------------+" +
            "------------------+--------+--------+");
            
            long T = (nowTime - T0)/X;
            steps = Long.toHexString(T).toUpperCase();
            while (steps.length() < 16) steps = "0" + steps;
            String fmTime = String.format("%1$-11s", nowTime);
            String utcTime = df.format(new Date(nowTime*1000));
            System.out.print("|  " + fmTime + "  |  " + utcTime +
            		"  | " + steps + " |");
            System.out.println(TOTP.generateTOTP(seed, steps, "6", "HmacSHA1") + "|SHA1    |");
            
//            for (int i=0; i<testTime.length; i++) {
//                long T = (testTime[i] - T0)/X;
//                steps = Long.toHexString(T).toUpperCase();
//                while (steps.length() < 16) steps = "0" + steps;
//                String fmtTime = String.format("%1$-11s", testTime[i]);
//                String utcTime = df.format(new Date(testTime[i]*1000));
//                System.out.print("|  " + fmtTime + "  |  " + utcTime +
//                        "  | " + steps + " |");
//                System.out.println(TOTP.generateTOTP(seed, steps, "8",
//                "HmacSHA1") + "| SHA1   |");
//                System.out.print("|  " + fmtTime + "  |  " + utcTime +
//                        "  | " + steps + " |");
//                System.out.println(TOTP.generateTOTP(seed32, steps, "8",
//                "HmacSHA256") + "| SHA256 |");
//                System.out.print("|  " + fmtTime + "  |  " + utcTime +
//                        "  | " + steps + " |");
//                System.out.println(TOTP.generateTOTP(seed64, steps, "8",
//                "HmacSHA512") + "| SHA512 |");
//
//                System.out.println(
//                        "+---------------+-----------------------+" +
//                "------------------+--------+--------+");
//            }
        }catch (final Exception e){
            System.out.println("Error : " + e);
        }

	}

}
