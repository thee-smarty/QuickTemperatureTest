package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="UnitData")
	public String [][] UnitData() throws IOException
	{
		String path=".\\testData\\QuickTemperatureData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("UnitData");	
		int totalcols=xlutil.getCellCount("UnitData",1);
				
		String data[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				data[i-1][j]= xlutil.getCellData("UnitData",i, j);  //1,0
			}
		}
	return data; //returning two dimension array		
	}
	
	
	//DataProvider 2
	
	@DataProvider(name="TemperatureData")
	public String [][] TemperatureData() throws IOException
	{
		String path=".\\testData\\QuickTemperatureData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("TemperatureData");	
		int totalcols=xlutil.getCellCount("TemperatureData",1);
				
		String data[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				data[i-1][j]= xlutil.getCellData("TemperatureData",i, j);  //1,0
			}
		}
	return data; //returning two dimension array		
	}
	
	//DataProvider 3 
	
	@DataProvider(name="BackgroundChangeData")
	public String [][] BackgroundChangeData() throws IOException
	{
		String path=".\\testData\\QuickTemperatureData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("BackgroundChangeData");	
		int totalcols=xlutil.getCellCount("BackgroundChangeData",1);
				
		String data[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				data[i-1][j]= xlutil.getCellData("BackgroundChangeData",i, j);  //1,0
			}
		}
	return data; //returning two dimension array		
	}
	
	//DataProvider 4
	
		@DataProvider(name="CityData")
		public String [][] CityData() throws IOException
		{
			String path=".\\testData\\QuickTemperatureData.xlsx";//taking xl file from testData
			
			ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
			
			int totalrows=xlutil.getRowCount("CityData");	
			int totalcols=xlutil.getCellCount("CityData",1);
					
			String data[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
			
			for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
			{		
				for(int j=0;j<totalcols;j++)  //0    i is rows j is col
				{
					data[i-1][j]= xlutil.getCellData("CityData",i, j);  //1,0
				}
			}
		return data; //returning two dimension array		
		}
		
		
}

