/* * *
 * Created on Jun 20, 2005
 * @author netlander@montebanato.ro
 * * */
package eurobiz;
import java.io.File;

public class tele4deps {
  public tele4deps() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
		// getDepNr("010205062015460030.rar");
		tele4deps.Search4Rar();
	}

	public static void Search4Rar() {
          String osName = new String();
          osName = System.getProperty("os.name");
          System.out.println(osName);
          // Linux ?
          String path2dir = new String();
          path2dir = "c:\\Eurobit\\Manager\\" ;
          //path2dir = "m:\\" ;
          System.out.println(path2dir);
          if(osName.matches("Linux") ){
            path2dir = "/opt/Eurobit/Manager/";
          }
          String[] listDir = new File(path2dir).list();
	  String ultimaTele = new String();
          String sResult = new String();
	  int lengthList = listDir.length ;
	  for(int i=0; i<lengthList;i++) {

            if(listDir[i].length()==10){
              sResult = listDir[i].substring(0, 8);
              ultimaTele = getLatestRar(path2dir+listDir[i]);
            }else{
              sResult="send_NULL";
              System.out.println(listDir[i] + "diferit 10 caractere");
            }
            System.out.println(sResult);
            if(osName.matches("Linux") &&  sResult.matches("send_pli")){
              sendTeleMail(path2dir + listDir[i] + '/', ultimaTele);
              System.out.println(path2dir+listDir[i]+'/'+ultimaTele);
            }
              else {
                if (!osName.matches("Linux") && sResult.matches("send_pli")){
                  System.out.println(path2dir+listDir[i]+"\\"+ultimaTele);
                  sendTeleMail(path2dir + listDir[i] + "\\", ultimaTele);
                }
            }
          }
	}

	public static void sendTeleMail(String thePath, String teleFile){
//		String [] depozite = {"NULL", "NULL", "Timisoara","Deva",
//				"Severin","Arad","Bucuresti","Galati","NULL","Pitesti",
//				"Cluj","BaiaM","Oradea","Bistrita","TgMures","Bacau","Brasov",
//				"Constanta","Sibiu","Suceava","Craiova"};
                
      		String [] depozite = {"NULL", "NULL", "NULL","NULL",
				"NULL","NULL","Bucuresti","NULL","NULL","NULL",
				"Cluj","NULL","NULL","NULL","NULL","Bacau","NULL",
				"NULL","NULL","NULL","NULL"};

		int iDepNr = getDepNr(teleFile);
		String sDepMail=depozite[iDepNr];
		if(!sDepMail.equals("NULL")){
                  SendMailUsage myMail = new SendMailUsage();
                  System.out.println("sendTeleMail) " + thePath + teleFile +  " *** dep" + sDepMail);
                  myMail.sendMyMail(thePath + teleFile, sDepMail);
                } else {
                  System.out.println(" N U L L ");
                }
	}

	public static String getLatestRar(String inTheFolder){
		String[] listDir = new File(inTheFolder).list();
                String ultimaTele = new String();
                if (listDir != null && listDir.length >=1){
                  ultimaTele = maxArray(listDir);
                } else {
                     ultimaTele = " NoFilesInFolder ";

                }
                System.out.println("Folder: " + inTheFolder+" ultimaTele: "+ultimaTele);
            return ultimaTele;
	}

	public static void getLatestRar1(){
		String path2dir = "/opt/Eurobit/Manager/send_pli02";
		String[] listDir = new File(path2dir).list();
		String ultimaTele = maxArray(listDir);
		System.out.println(ultimaTele);
	}

	public static int getDepNr(String fileName){
		//String a2 = "010205062015460030.rar";
	    //System.out.println(fileName);
	    String sResult = fileName.substring(2,4);
	    //System.out.println(sResult);
	    Integer iResult = new Integer(sResult);
	    return iResult.intValue();
	}


	public static Integer getTeleNr(String fileName){
	    System.out.println(fileName);
            if(fileName.length()<22){
              System.out.println(fileName + " - telefile sub 22 caractere");
                          return new Integer(-1);
            }
            if(fileName.length()>22){
              System.out.println(fileName + " - telefile peste 22 caractere");
                          return new Integer(-1);
            }
            String sResult = fileName.substring(14,18);
	    System.out.println(sResult);
	    Integer iResult = new Integer(sResult);
	    return iResult;
	}

    // return maximum of all values in the array:
    public static String maxArray(String A[]) {
	int i;
	Integer max;
	max = getTeleNr(A[0]);
	i = 0;
	int imax= 0;
	while (i < A.length) {
	    if (getTeleNr(A[i]).compareTo(max) > 0 ) {
	    	max = getTeleNr(A[i]);
	    	imax=i;
	    }
	    i = i+1;
	}
	return A[imax];
    }

	protected void sendThemAll(){
		SendMailUsage myMail = new SendMailUsage();
		// String path2dir = "c:\Eurobit\Manager\";
		String path2dir = "/opt/Eurobit/Manager/";
		String[] listDir = new File(path2dir).list();
		String[] listFiles;
		int lengthList = listDir.length ;
		for(int i=0; i<lengthList;i++) {
			System.out.println(listDir[i]);
			listFiles = new File(path2dir+listDir[i]).list( );
			int nrFiles = listFiles.length ;
			for(int j=0; j<nrFiles;j++) {
				System.out.println(listFiles[j]);
				myMail.sendMyMail(path2dir + listDir[i]+"/"+listFiles[j], " ");
			}
		}
		System.out.println(" Gata ! ");
	}

  private void jbInit() throws Exception {
  }
}
