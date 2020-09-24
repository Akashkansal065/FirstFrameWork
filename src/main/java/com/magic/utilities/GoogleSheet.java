package com.magic.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheet {


	/** Application name. */
	private static final String APPLICATION_NAME = "Magicbricks";
	
	/** Directory to store user credentials for this application. */
	private static final String CREDENTIALS_FOLDER = "credentials";

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials
	 * at ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If there is no client_secret.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = new FileInputStream(System.getProperty("user.dir")+"\\client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
				.setAccessType("offline")
				.build();
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws IOException {
		NetHttpTransport HTTP_TRANSPORT = null;
		
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		Credential credential = getCredentials(HTTP_TRANSPORT);
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}
	public List<List<Object>> getSpreadSheetRecords(String spreadsheetId, String range) throws IOException {
		Sheets service = getSheetsService();		

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

		List<List<Object>> values = response.getValues();
		if (values != null && values.size() != 0) {
			return values;
		}else {
			System.out.println("No data found.");
			return null;
		}
	}
	public static void main(String args[]) throws IOException
	{
		String spreadsheetId="14-7PNS2RzGrdvvx5VMBvRlZoyX_oHJ_JJtiPidrgbEs";
		String range="'Sprint 20'";
		GoogleSheet sheet = new GoogleSheet();
		List<List<Object>> values=sheet.getSpreadSheetRecords(spreadsheetId, range);
		String arr[][] = new String[values.size()][];
		for(int i=0;i<1;i++)
		{
			arr[i] = new String[3];
			for(int j=0;j<3;j++)
			{
				System.out.print(values.get(i).get(j).toString()+",");
				arr[i][j]= values.get(i).get(j).toString();
			}
			System.out.println();
		}
	}
}