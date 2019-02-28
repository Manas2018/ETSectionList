package com.til.et.testLocal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.til.et.testLocal.model.json.Response;

public class TestJsonJackson {

	public static void main(String[] args) throws IOException {

		// inline can be cached
		String inline = getETSectionList();

		// test inputs
		String l1, l2, l3, l4;
		l1 = "News";
		l2 = "Elections";
		l3 = "Lok Sabha";
		l4 = "India";

		// calling to get corresponding section ids
		String sectionIds = getETSectionIds(inline, l1, l2, l3, l4);
		System.out.println(sectionIds);
		
	}

	public static String getETSectionList() throws MalformedURLException, IOException, ProtocolException {
		String inline = "";
		URL url = new URL("https://economictimes.indiatimes.com/manageads_etnav.cms?feedtype=sjson");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();

		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}
			// System.out.println("\nJSON data in string format");
			// System.out.println(inline);
			sc.close();
		}
		return inline;
	}

	public static String getETSectionIds(String json, String l1, String l2, String l3, String l4) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		String r1, r2, r3, r4;
		r1 = "Not Present";
		r2 = "Not Present";
		r3 = "Not Present";
		r4 = "Not Present";

		try {
			Response value = mapper.readValue(json, Response.class);

			for (int i = 0; i < value.getIleft().getSubsec0().size(); i++) {
				if (value.getIleft().getSubsec0().get(i).getSsname().equalsIgnoreCase(l1)) {
					r1 = value.getIleft().getSubsec0().get(i).getMsid();
					if (l2 == null)
						break;
					for (int j = 0; j < value.getIleft().getSubsec0().get(i).getSubsec1().size(); j++) {
						if (value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getSsname().equalsIgnoreCase(l2)) {
							r2 = value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getMsid();
							if (l3 == null)
								break;
							for (int k = 0; k < value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getSubsec2()
									.size(); k++) {
								if (value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getSubsec2().get(k)
										.getSsname().equalsIgnoreCase(l3)) {
									r3 = value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getSubsec2().get(k)
											.getMsid();
									if (l4 == null)
										break;
									for (int l = 0; l < value.getIleft().getSubsec0().get(i).getSubsec1().get(j)
											.getSubsec2().get(k).getSubsec3().size(); l++) {
										if (value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getSubsec2().get(k)
												.getSubsec3().get(l).getSsname().equalsIgnoreCase(l4)) {
											r4 = value.getIleft().getSubsec0().get(i).getSubsec1().get(j).getSubsec2()
													.get(k).getSubsec3().get(l).getMsid();
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			
			System.out.println(l1 + "," + l2 + "," + l3 + "," + l4);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r1 + "," + r2 + "," + r3 + "," + r4;
	}

}
